// Ricardo Rigo Antunes RA 1136661
// Pedro Henrique Piovezan RA 1135911
// Henrique Panisson Agostineto RA 1136301
// Matheus Rodrigues Souza RA 1136389

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceVeiculo {
    private List<Veiculo> veiculos = new ArrayList<>();

    public String cadastrarVeiculo(String tipo, String marca, String modelo, int ano, String placa) {

        String tipoErro = validarTipoVeiculo(tipo);
        if (tipoErro != null) return tipoErro;

        if (marca.isBlank()) return "A marca do veículo não pode estar em branco.";
        if (modelo.isBlank()) return "O modelo do veículo não pode estar em branco.";

        String anoErro = validarAno(ano);
        if (anoErro != null) return anoErro;

        if (placa.isBlank()) return "A placa do veículo não pode estar em branco.";
        if (findByPlaca(placa) != null) return "Já existe um veículo cadastrado com essa placa.";

        Veiculo veiculo;
        if ("carro".equals(tipo)) {
            int numeroPortas = inputNumerico("Digite o número de portas do carro: ");
            String portasErro = validarNumeroPortas(numeroPortas);
            if (portasErro != null) return portasErro;
            veiculo = new Carro(marca, modelo, ano, placa, numeroPortas);
        } else {
            boolean partidaEletrica = inputTexto("O veículo tem partida elétrica? (sim/não): ").equalsIgnoreCase("sim");
            veiculo = new Moto(marca, modelo, ano, placa, partidaEletrica);
        }

        save(veiculo);
        return "Veículo cadastrado com sucesso!";
    }

    public String removerVeiculo(String placa) {
        Veiculo veiculo = findByPlaca(placa);
        if (veiculo != null) {
            remove(veiculo);
            return "Veículo removido com sucesso!";
        }
        return "Veículo não encontrado.";
    }

    public String validarTipoVeiculo(String tipo) {
        if (!tipo.equalsIgnoreCase("carro") && !tipo.equalsIgnoreCase("moto")) {
            return "Tipo de veículo inválido.";
        }
        return null;
    }

    public String validarAno(int ano) {
        if (ano < 1886 || ano > 2024) {
            return "Ano inválido! O ano deve ser entre 1886 e 2024.";
        }
        return null;
    }

    public String validarNumeroPortas(int numeroPortas) {
        if (numeroPortas <= 0) {
            return "O número de portas deve ser maior que 0.";
        }
        return null;
    }

    public Veiculo findByPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    public void save(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public void remove(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }

    public List<Veiculo> findAll() {
        return veiculos;
    }

    private int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
        Scanner input = new Scanner(System.in);
        System.out.print(mensagem);
        do {
            String valorStr = input.nextLine();
            try {
                valor = Integer.parseInt(valorStr);
                entradaValida = true;
            } catch (Exception e) {
                System.out.println("ERRO. Valor informado deve ser um número inteiro.");
            }
        } while (!entradaValida);
        return valor;
    }

    private String inputTexto(String mensagem) {
        Scanner input = new Scanner(System.in);
        System.out.print(mensagem);
        return input.nextLine();
    }
}

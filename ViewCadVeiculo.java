// Ricardo Rigo Antunes RA 1136661
// Pedro Henrique Piovezan RA 1135911
// Henrique Panisson Agostineto RA 1136301
// Matheus Rodrigues Souza RA 1136389

import java.util.List;
import java.util.Scanner;

public class ViewCadVeiculo {
    private static ServiceVeiculo service = new ServiceVeiculo();
    static Scanner input = new Scanner(System.in);

    public static void limparTela() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void aguardarEnter() {
        System.out.print("Pressione Enter para continuar...");
        input.nextLine();
    }

    private static int inputNumerico(String mensagem) {
        int valor = 0;
        boolean entradaValida = false;
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

    private static String inputTexto(String mensagem) {
        System.out.print(mensagem);
        return input.nextLine();
    }

    public static void main(String[] args) {
        String menu = """
                SISTEMA DE GERENCIAMENTO DE FROTAS
                Menu de Opções:
                1 - Cadastrar novo Veículo;
                2 - Listar todos Veículos cadastrados;
                3 - Pesquisar Veículo pela placa;
                4 - Remover Veículo;
                0 - Sair;
                Digite a opção desejada:  
                """;
        int opcao;
        do {
            limparTela();
            opcao = inputNumerico(menu);
            switch (opcao) {
                case 0:
                    System.out.println("VOLTE SEMPRE!!!");
                    break;
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    listarVeiculos();
                    break;
                case 3:
                    pesquisarVeiculo();
                    break;
                case 4:
                    removerVeiculo();
                    break;
                default:
                    System.out.println("Opção Inválida!!!");
                    aguardarEnter();
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarVeiculo() {
        try {
            String tipo = inputTexto("Digite o tipo do veículo (carro/moto): ").toLowerCase();

            if (tipo.isBlank()) {
                System.out.println("O tipo do veículo não pode estar em branco.");
                aguardarEnter();
                return;
            }

            String marca = inputTexto("Digite a marca do veículo: ");
            if (marca.isBlank()) {
                System.out.println("A marca do veículo não pode estar em branco.");
                aguardarEnter();
                return;
            }

            String modelo = inputTexto("Digite o modelo do veículo: ");
            if (modelo.isBlank()) {
                System.out.println("O modelo do veículo não pode estar em branco.");
                aguardarEnter();
                return;
            }

            int ano = inputNumerico("Digite o ano do veículo: ");
            if (ano < 1886 || ano > 2024) {
                System.out.println("Ano inválido! O ano deve ser entre 1886 e 2024.");
                aguardarEnter();
                return;
            }

            String placa = inputTexto("Digite a placa do veículo: ");
            if (placa.isBlank()) {
                System.out.println("A placa do veículo não pode estar em branco.");
                aguardarEnter();
                return;
            }

            if (service.findByPlaca(placa) != null) {
                System.out.println("Já existe um veículo cadastrado com essa placa.");
                aguardarEnter();
                return;
            }

            Veiculo veiculo;
            if ("carro".equals(tipo)) {
                int numeroPortas = inputNumerico("Digite o número de portas do carro: ");
                if (numeroPortas <= 0) {
                    System.out.println("O número de portas deve ser maior que 0.");
                    aguardarEnter();
                    return;
                }
                veiculo = new Carro(marca, modelo, ano, placa, numeroPortas);
            } else if ("moto".equals(tipo)) {
                boolean partidaEletrica = inputTexto("O veículo tem partida elétrica? (sim/não): ").equalsIgnoreCase("sim");
                veiculo = new Moto(marca, modelo, ano, placa, partidaEletrica);
            } else {
                System.out.println("Tipo de veículo inválido.");
                aguardarEnter();
                return;
            }

            service.save(veiculo);
            System.out.println("Veículo cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("NÃO FOI POSSÍVEL CADASTRAR O VEÍCULO.");
            System.out.println("Erro: " + e.getMessage());
        }
        aguardarEnter();
    }

    private static void listarVeiculos() {
        limparTela();
        System.out.println("Lista de Veículos Cadastrados:");
        for (Veiculo veiculo : service.findAll()) {
            System.out.println(veiculo);
        }
        aguardarEnter();
    }

    private static void pesquisarVeiculo() {
        try {
            String placa = inputTexto("Digite a placa do veículo: ");
            Veiculo veiculo = service.findByPlaca(placa);
            if (veiculo != null) {
                System.out.println("Veículo encontrado:");
                System.out.println(veiculo);
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("NÃO FOI POSSÍVEL ENCONTRAR O VEÍCULO.");
            System.out.println("Erro: " + e.getMessage());
        }
        aguardarEnter();
    }

    private static void removerVeiculo() {
        try {
            String placa = inputTexto("Digite a placa do veículo que deseja remover: ");
            Veiculo veiculo = service.findByPlaca(placa);
            if (veiculo != null) {
                service.remove(veiculo);
                System.out.println("Veículo removido com sucesso!");
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("NÃO FOI POSSÍVEL REMOVER O VEÍCULO.");
            System.out.println("Erro: " + e.getMessage());
        }
        aguardarEnter();
    }
}

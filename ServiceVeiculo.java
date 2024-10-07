import java.util.ArrayList;
import java.util.List;

public class ServiceVeiculo {
    private List<Veiculo> veiculos = new ArrayList<>();

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
}

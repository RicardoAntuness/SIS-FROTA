import java.util.ArrayList;
import java.util.List;

public class ServiceVeiculo {
    private List<Veiculo> veiculos = new ArrayList<>();

    public void save(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public List<Veiculo> findAll() {
        return veiculos;
    }

    public Veiculo findByPlaca(String placa) {
        return veiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst()
                .orElse(null);
    }

    public void remove(Veiculo veiculo) {
        veiculos.remove(veiculo);
    }
}

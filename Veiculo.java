public abstract class Veiculo {
    private String marca;
    private String modelo;
    private int ano;
    private String placa;

    public Veiculo(String marca, String modelo, int ano, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public String toString() {
        return String.format("Veículo %s %s, Ano: %d, Placa: %s", marca, modelo, ano, placa);
    }
}

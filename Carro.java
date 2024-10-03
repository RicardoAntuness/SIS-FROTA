public class Carro extends Veiculo {
    private int numeroPortas;

    public Carro(String marca, String modelo, int ano, String placa, int numeroPortas) {
        super(marca, modelo, ano, placa);
        this.numeroPortas = numeroPortas;
    }

    @Override
    public String toString() {
        return "Carro [Marca: " + super.toString() + ", Número de Portas: " + numeroPortas + "]";
    }
}

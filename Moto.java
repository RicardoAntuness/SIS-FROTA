public class Moto extends Veiculo {
    private boolean partidaEletrica;

    public Moto(String marca, String modelo, int ano, String placa, boolean partidaEletrica) {
        super(marca, modelo, ano, placa);
        this.partidaEletrica = partidaEletrica;
    }

    @Override
    public String toString() {
        return "Moto [Marca: " + super.toString() + ", Partida Elétrica: " + (partidaEletrica ? "Sim" : "Não") + "]";
    }
}

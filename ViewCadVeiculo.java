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
    
            String tipoErro = service.validarTipoVeiculo(tipo);
            if (tipoErro != null) {
                System.out.println(tipoErro);  
                aguardarEnter();
                return;
            }
    
            String marca = inputTexto("Digite a marca do veículo: ");
            String modelo = inputTexto("Digite o modelo do veículo: ");
            int ano = inputNumerico("Digite o ano do veículo: ");
            String placa = inputTexto("Digite a placa do veículo: ");
    
            String resultado = service.cadastrarVeiculo(tipo, marca, modelo, ano, placa);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar o veículo: " + e.getMessage());
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
            System.out.println("Erro ao pesquisar o veículo: " + e.getMessage());
        }
        aguardarEnter();
    }

    private static void removerVeiculo() {
        try {
            String placa = inputTexto("Digite a placa do veículo que deseja remover: ");
            String resultado = service.removerVeiculo(placa);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro ao remover o veículo: " + e.getMessage());
        }
        aguardarEnter();
    }
}

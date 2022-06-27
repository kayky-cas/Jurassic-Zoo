import java.util.Scanner;

/**
 * @author Kayky Belleboni Casagrande
 * @matricula 22104538-8
 */
public class JurassicZoo {

    private final CadastroDinossauro cadastroDinossauro = new CadastroDinossauro();

    private void inicializar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Quantidade de dinossauros iniciais: ");
        int quantidade = scanner.nextInt();

        if (!cadastroDinossauro.adicionarDinossaurosAleatorios(quantidade)) {
            System.out.println("Não foi possível adicionar todos!");
        }
    }

    private void descricaoTipo() {
        System.out.print("1- Carnívoro\n2- Herbívoro\nTipo (1/2): ");
    }

    private void descricaoCaracteristica() {
        System.out.println("1 até 10kg: Pequeno porte\n10 até 100kg: Médio porte\nMais de 100kg: Grande porte");
    }

    private void adicionarDinossauro() {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Raça: ");
            String nomeRaca = scanner.nextLine();

            descricaoTipo();
            int tipo = scanner.nextInt();

            if (tipo < 1 || tipo > 2) {
                System.out.println("Tipo inválido!");
                continue;
            }

            descricaoCaracteristica();
            System.out.print("Peso (kg): ");
            double peso = scanner.nextDouble();

            if (peso < 1) {
                System.out.println("Peso inválido!");
                continue;
            }

            System.out.print("Velocidade (km/h): ");
            double velocidade = scanner.nextDouble();

            if (velocidade < 1) {
                System.out.println("Velocidade inválida!");
                continue;
            }

            int id = cadastroDinossauro.getIdAtual();

            Dinossauro dinossauro = new Dinossauro(id, nomeRaca, tipo, peso, velocidade);

            if (cadastroDinossauro.adicionarDinossauro(dinossauro)) {
                System.out.println("Dinossauro adicionado!");
            } else {
                System.out.println("Impossível de adicionar mais dinossauros!");
            }
            return;
        }
    }

    private void removerDinossauro() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ID do dinossauro que deseja remover: ");
            int id = scanner.nextInt();

            if (cadastroDinossauro.removerDinossauro(id)) {
                System.out.printf("Dinossauro %04d removido com sucesso!\n", id);
                return;
            }
            System.out.println("ID inválido!");
        }
    }

    private void relatorioQuantidadeDeAnimaisDeCadaTipoCategoria() {
        System.out.println(cadastroDinossauro.relatorioQuantidadeDeAnimaisDeCadaTipoCategoria());
    }

    private void relatorioPesoPesado() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            descricaoTipo();
            int tipo = scanner.nextInt();
            if (tipo < 1 || tipo > 2) {
                System.out.println("Tipo inválido!");
                continue;
            }

            descricaoCaracteristica();
            System.out.print("Categoria (1, 2 ou 3): ");
            int categoria = scanner.nextInt();

            if (categoria < 1 || categoria > 3) {
                System.out.println("Categoria inválida!");
                continue;
            }

            System.out.println();

            Dinossauro dinossauro = cadastroDinossauro.relatorioPesoPesado(tipo, categoria);
            if (dinossauro != null) {
                System.out.printf("Dinossauro mais pesado (%s/%s): \n", dinossauro.getTipoString(),
                        dinossauro.getCategoriaString());
                System.out.println(dinossauro);
            } else {
                System.out.println("Nenhum dinossauro encontrado!\n");
            }
            return;
        }
    }

    private void relatorioQuantidadeDeCarneParaCarnivoros() {
        double quantidade = cadastroDinossauro.relatorioQuantidadeDeCarneParaCarnivoros();
        System.out.printf("\nOs carnívoros necessitam de %.2f kg de carne por mês!\n\n", quantidade);
    }

    private void relatorioDaTempoDeFugir() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("ID do dinossauro: ");
            int id = scanner.nextInt();

            if (cadastroDinossauro.naoExiste(id)) {
                System.out.println("Dinossauro não encontrado!");
                continue;
            }

            System.out.print("Distancia entre o dinossauro e o bunker (km): ");
            double distanciaDinoBunker = scanner.nextDouble();

            if (distanciaDinoBunker < 0) {
                System.out.println("Dinostancia inválida!");
                continue;
            }

            System.out.print("Distancia entre a pessoa e o bunker (km): ");
            double distanciaPessoaBunker = scanner.nextDouble();

            if (distanciaPessoaBunker < 0) {
                System.out.println("Dinostancia inválida!");
                continue;
            }

            System.out.println();

            if (cadastroDinossauro.relatorioDaTempoDeFugir(id, distanciaDinoBunker, distanciaPessoaBunker)) {
                System.out.println("A pessoa irá conseguir fugir!");
            } else {
                System.out.println("O dinossauro irá alcançar a pessoa!");
            }

            System.out.println();

            return;
        }
    }

    private void relatorioTop10MaisVelozes() {
        Dinossauro[] dinossauros = cadastroDinossauro.relatorioTop10MaisVelozes();
        System.out.println();
        for (int i = 0; i < dinossauros.length; i++) {
            System.out.println((i + 1) + "º lugar: ");
            System.out.println(dinossauros[i]);
        }
    }

    private void menu() {
        String menu = "1- Adicionar dinossauro\n" +
                "2- Remover dinossauro\n" +
                "3- Listar dinossauros\n" +
                "4- Relatórios\n" +
                "5- Sair";

        System.out.println(menu);
    }

    private void relatorioMenu() {
        Scanner scanner = new Scanner(System.in);

        String menu = "\n1- Quantidade de animais de cada tipo e categoria\n" +
                "2- Peso pesado\n" +
                "3- Quantidade de carne para carnívoros\n" +
                "4- Dá tempo de fugir?\n" +
                "5- Top 10 mais velozes\n" +
                "6- Sair do menu de relatórios";

        while (true) {
            System.out.println(menu);
            switch (scanner.nextInt()) {
                case 1:
                    relatorioQuantidadeDeAnimaisDeCadaTipoCategoria();
                    break;
                case 2:
                    relatorioPesoPesado();
                    break;
                case 3:
                    relatorioQuantidadeDeCarneParaCarnivoros();
                    break;
                case 4:
                    relatorioDaTempoDeFugir();
                    break;
                case 5:
                    relatorioTop10MaisVelozes();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Valor inválido!");
            }
        }
    }

    public void app() {
        inicializar();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            menu();

            switch (scanner.nextInt()) {
                case 1:
                    adicionarDinossauro();
                    break;
                case 2:
                    removerDinossauro();
                    break;
                case 3:
                    cadastroDinossauro.listarDinossauros();
                    break;
                case 4:
                    relatorioMenu();
                    break;
                case 5:
                    System.out.println("Obrigado por usar o programa!");
                    return;
                default:
                    System.out.println("Valor inválido!");
            }
        }
    }

    public static void main(String[] args) {
        JurassicZoo jurassicZoo = new JurassicZoo();
        jurassicZoo.app();
    }
}
import java.util.Random;

public class CadastroDinossauro {
    private final Dinossauro[] dinossauros = new Dinossauro[100];
    private int posicaoAtual = 0;
    private int idAtual = 0;


    public int getPosicaoAtual() {
        return posicaoAtual;
    }

    public int getIdAtual() {
        return idAtual++;
    }

    public boolean cheio() {
        return posicaoAtual >= dinossauros.length;
    }

    public boolean adicionarDinossauro(Dinossauro dinossauro) {
        if (cheio()) {
            idAtual--;
            return false;
        }

        dinossauros[posicaoAtual++] = dinossauro;
        return true;
    }

    public boolean adicionarDinossaurosAleatorios(int quantidade) {
        Random random = new Random();
        String[] nomes = {"Tiranossauro Rex", "Velociraptor", "Diplodoco", "Tricerátops", "Pterodáctilo", "Estegossauro"};

        for (int i = 0; i < quantidade; i++) {
            int id = getIdAtual();
            String nome = nomes[random.nextInt(nomes.length)];
            int tipo = random.nextInt(2) + 1;

            int categoria = random.nextInt(3) + 1;

            double peso;

            if (categoria == Dinossauro.PEQUENO) {
                peso = (random.nextDouble() * 26) + 1;
            } else if (categoria == Dinossauro.MEDIO) {
                peso = (random.nextDouble() * 74) + 26;
            } else {
                peso = (random.nextDouble() * 899) + 101;
            }

            double velocidade = random.nextDouble() * 59 + 1;

            Dinossauro dinossauro = new Dinossauro(id, nome, tipo, categoria, peso, velocidade);

            if (!adicionarDinossauro(dinossauro)) {
                return false;
            }
        }

        return true;
    }

    public boolean removerDinossauro(int id) {
        if (naoExiste(id)) {
            return false;
        }

        for (int i = 0, j = 0; i < posicaoAtual; i++) {
            Dinossauro dinossauro = dinossauros[i];
            if (dinossauro.getId() == id) {
                continue;
            }
            dinossauros[j++] = dinossauro;
        }

        dinossauros[--posicaoAtual] = null;
        return true;
    }

    public Dinossauro pesquisarDinossauro(int id) {
        return pesquisarDinossauro(0, posicaoAtual, id);
    }

    private Dinossauro pesquisarDinossauro(int inicio, int fim, int id) {
        if (fim < inicio) {
            return null;
        }

        int meio = (inicio + fim) / 2;

        int dinoId = dinossauros[meio].getId();

        if (dinoId == id) {
            return dinossauros[meio];
        } else if (dinoId < id) {
            return pesquisarDinossauro(meio + 1, fim, id);
        }

        return pesquisarDinossauro(inicio, meio - 1, id);
    }

    public boolean naoExiste(int id) {
        return pesquisarDinossauro(id) == null;
    }

    public void listarDinossauros() {
        for (int i = 0; i < posicaoAtual; i++) {
            System.out.println(dinossauros[i]);
        }
    }

    public String relatorioQuantidadeDeAnimaisDeCadaTipoCategoria() {
        int[][] quantidadeTipoCat = {{0, 0, 0}, {0, 0, 0}};

        for (int i = 0; i < posicaoAtual; i++) {
            Dinossauro dinossauro = dinossauros[i];
            quantidadeTipoCat[dinossauro.getTipo() - 1][dinossauro.getCategoria() - 1]++;
        }
        String relatorio = "";

        for (int i = 0; i < 2; i++) {
            relatorio += String.format("%s: ", i == Dinossauro.CARNIVORO ? "Carnívoros" : "Herbívoros");
            relatorio += String.format("PP: %2d, ", quantidadeTipoCat[i][Dinossauro.PEQUENO - 1]);
            relatorio += String.format("MP: %2d, ", quantidadeTipoCat[i][Dinossauro.MEDIO - 1]);
            relatorio += String.format("GP: %2d.\n", quantidadeTipoCat[i][Dinossauro.GRANDE - 1]);

        }

        return relatorio;
    }

    public Dinossauro relatorioPesoPesado(int tipo, int categoria) {
        Dinossauro maiorPeso = null;

        for (int i = 0; i < posicaoAtual; i++) {
            Dinossauro dinossauro = dinossauros[i];
            if (dinossauro.getTipo() == tipo && dinossauro.getCategoria() == categoria) {
                if (maiorPeso == null) {
                    maiorPeso = dinossauro;
                } else if (dinossauro.getPeso() > maiorPeso.getPeso()) {
                    maiorPeso = dinossauro;
                }
            }
        }

        return maiorPeso;
    }

    public double relatorioQuantidadeDeCarneParaCarnivoros() {
        double quantidadeDeCarne = 0;
        int[] porcentagemAlimento = {10, 15, 20};

        for (int i = 0; i < posicaoAtual; i++) {
            Dinossauro dinossauro = dinossauros[i];
            if (dinossauro.ehHerbivoro()) {
                continue;
            }
            double porcentagem = (double) porcentagemAlimento[dinossauro.getCategoria() - 1] / 100;
            quantidadeDeCarne += (dinossauro.getPeso() * porcentagem) * 30;
        }

        return quantidadeDeCarne;
    }

    public boolean relatorioDaTempoDeFugir(int id, double distanciaDinoBunker, double distanciaPessoaBunker) {
        Dinossauro dinossauro = pesquisarDinossauro(id);

        double tempoP = distanciaPessoaBunker / 20;
        double tempoD = distanciaDinoBunker / dinossauro.getVelocidade();

        return tempoP < tempoD;
    }

    //TODO arrumar
    public Dinossauro[] relatorioTop10MaisVelozes() {
        int quantidade = Math.min(posicaoAtual, 10);
        Dinossauro[] maisVelozes = new Dinossauro[quantidade];

        for (int i = 0; i < quantidade; i++) {
            Dinossauro maisVeloz = null;
            for (int j = 0; j < posicaoAtual; j++) {
                Dinossauro dinossauro = dinossauros[j];
                boolean existe = false;

                //TODO: Implementar pesquisa binária por velocidade
                for (int k = 0; k < i; k++) {
                    if (dinossauro.getId() == maisVelozes[k].getId()) {
                        existe = true;
                        break;
                    }
                }

                if (existe) {
                    continue;
                }

                if (maisVeloz == null || dinossauro.getVelocidade() > maisVeloz.getVelocidade()) {
                    maisVeloz = dinossauro;
                }
            }
            maisVelozes[i] = maisVeloz;
        }

        return maisVelozes;
    }
}

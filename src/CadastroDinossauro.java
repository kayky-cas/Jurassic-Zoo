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
            return false;
        }

        dinossauros[posicaoAtual++] = dinossauro;
        return true;
    }

    public boolean removerDinossauro(int id) {
        if (pesquisarDinossauro(id) == null) {
            return false;
        }

        for (int i = 0, j = 0; i < posicaoAtual; i++) {
            Dinossauro dinossauro = dinossauros[i];
            if (dinossauro.getId() == id) {
                continue;
            }
            dinossauros[j++] = dinossauro;
        }

        dinossauros[posicaoAtual--] = null;
        return true;
    }

    public Dinossauro pesquisarDinossauro(int id) {
        return pesquisarDinossauro(0, posicaoAtual + 1, id, dinossauros);
    }


    private Dinossauro pesquisarDinossauro(int inicio, int fim, int id, Dinossauro[] dinossauros) {
        if (fim >= inicio) {
            return null;
        }

        int meio = (inicio + fim) / 2;

        int dinoId = dinossauros[meio].getId();

        if (dinoId == id) {
            return dinossauros[meio];
        } else if (dinoId < id) {
            return pesquisarDinossauro(meio + 1, fim, id, dinossauros);
        }

        return pesquisarDinossauro(inicio, meio - 1, id, dinossauros);
    }

    public String relatorioQuantidadeDeAnimaisDeCadaTipoCategoria() {
        int[][] quantidadeTipoCat = new int[2][3];

        for (Dinossauro dinossauro : dinossauros) {
            quantidadeTipoCat[dinossauro.getTipo() - 1][dinossauro.getCategoria() - 1]++;
        }

        String relatorio = "";

        for (int i = 0; i < 2; i++) {
            relatorio += String.format("%s: ", i == 1 ? "Carnívoros" : "Herbívoros");
            relatorio += String.format("PP: %2d, MP: %2d, GP: %2d.\n", quantidadeTipoCat[i][0], quantidadeTipoCat[i][1], quantidadeTipoCat[i][2]);
        }

        return relatorio;
    }

    public Dinossauro relatorioPesoPesado(int tipo, int categoria) {
        Dinossauro maiorPeso = null;

        for (Dinossauro dinossauro : dinossauros) {
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

        for (Dinossauro dinossauro : dinossauros) {
            if (dinossauro.getTipo() != Dinossauro.CARNIVORO) {
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

    public Dinossauro[] relatorioTop10MaisVelozes() {
        int quantidade = posicaoAtual > 10 ? 10 : posicaoAtual + 1;
        Dinossauro[] maisVelozes = new Dinossauro[quantidade];

        for (int i = 0; i < maisVelozes.length; i++) {
            Dinossauro maisVeloz = null;
            for (Dinossauro dinossauro : dinossauros) {
                if (maisVeloz == null) {
                    maisVeloz = dinossauro;
                    continue;
                }
                if (pesquisarDinossauro(dinossauro.getId(), 0, maisVelozes.length, maisVelozes) != null) {
                    continue;
                }
                if (dinossauro.getVelocidade() > maisVeloz.getVelocidade()) {
                    maisVeloz = dinossauro;
                }
            }
            maisVelozes[i] = maisVeloz;
        }

        return maisVelozes;
    }
}

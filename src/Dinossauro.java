public class Dinossauro {
    private final int id;
    private final String nomeRaca;
    private final int tipo;
    private final int categoria;
    private final double peso;
    private final double velocidade;

    public static final int CARNIVORO = 1;
    public static final int HERBIVORO = 2;

    public static final int PEQUENO = 1;
    public static final int MEDIO = 2;
    public static final int GRANDE = 3;

    public Dinossauro(int id, String nomeRaca, int tipo, double peso, double velocidade) {
        this.id = id;
        this.nomeRaca = nomeRaca;
        this.tipo = tipo;
        this.categoria = calcCategoria(peso);
        this.peso = peso;
        this.velocidade = velocidade;
    }

    public Dinossauro(int id, String nomeRaca, int tipo, int categoria, double peso, double velocidade) {
        this.id = id;
        this.nomeRaca = nomeRaca;
        this.tipo = tipo;
        this.categoria = categoria;
        this.peso = peso;
        this.velocidade = velocidade;
    }

    public int getId() {
        return id;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public int getTipo() {
        return tipo;
    }

    public int getCategoria() {
        return categoria;
    }

    public double getPeso() {
        return peso;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public String getTipoString() {
        return tipo == 1 ? "Carnívoro" : "Herbívoro";
    }

    public String getCategoriaString() {
        return switch (categoria) {
            case 1 -> "Pequeno porte";
            case 2 -> "Médio porte";
            case 3 -> "Grande porte";
            default -> "Categoria inválida!";
        };
    }

    public boolean ehHerbivoro() {
        return tipo == Dinossauro.HERBIVORO;
    }

    private int calcCategoria(double peso) {
        if (peso <= 10) {
            return 1;
        } else if (peso > 10 && peso <= 100) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public String toString() {
        return String.format("[%04d]: \n", id) +
                String.format("\tRaça: %s\n", nomeRaca) +
                String.format("\tTipo: %s\n", getTipoString()) +
                String.format("\tCategoria: %s\n", getCategoriaString()) +
                String.format("\tPeso: %.2f kg\n", peso) +
                String.format("\tVelocidade: %.2f km/h\n", velocidade);
    }
}

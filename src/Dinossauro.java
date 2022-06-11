public class Dinossauro {
    private final int id;
    private final String nomeRaca;
    private final int tipo;
    private final int categoria;
    private final double peso;
    private final double velocidade;

    public Dinossauro(int id, String nomeRaca, int tipo, int categoria, int peso, int velocidade) {
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

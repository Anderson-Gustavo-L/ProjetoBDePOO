package byteassistencia.model;

public class Produto implements ItemCobravel {
    private String descricao;
    private int quantidade;
    private double valorUnitario;

    public Produto() {}

    public Produto(String descricao, int quantidade, double valorUnitario) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    @Override
    public double calcularValor() {
        return quantidade * valorUnitario;
    }

}

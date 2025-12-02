package Modelo;

public class ItemOS {
    private String tipo;        // SERVICO ou PRODUTO
    private String descricao;
    private int quantidade;
    private double valorUnitario;

    public ItemOS() {
    }

    public ItemOS(String tipo, String descricao, Integer quantidade, Double valorUnitario) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

}

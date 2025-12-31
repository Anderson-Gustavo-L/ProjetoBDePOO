package byteassistencia.model;

public class ItemOS {
    private Long idItemOS;
    private Long idOs;
    private String tipo;
    private String descricao;
    private int quantidade;
    private double valorUnitario;

    public ItemOS(Long idItemOS, Long idOs, String tipo, String descricao, int quantidade, double valorUnitario) {
        this.idItemOS = idItemOS;
        this.idOs = idOs;
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public Long getIdItemOS() {
        return idItemOS;
    }

    public void setIdItemOS(Long idItemOS) {
        this.idItemOS = idItemOS;
    }

    public Long getIdOs() {
        return idOs;
    }

    public void setIdOs(Long idOs) {
        this.idOs = idOs;
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double calcularValorTotalItem() {
        return quantidade * valorUnitario;
    }
}

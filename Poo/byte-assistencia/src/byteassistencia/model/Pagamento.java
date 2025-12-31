package byteassistencia.model;

public class Pagamento {
    private Long idPagamento;
    private Long idOs;
    private String forma;
    private Double valor;
    private String data;

    public Pagamento(Long idPagamento, Long idOs, String forma, Double valor, String data) {
        this.idPagamento = idPagamento;
        this.idOs = idOs;
        this.forma = forma;
        this.valor = valor;
        this.data = data;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Long getIdOs() {
        return idOs;
    }

    public void setIdOs(Long idOs) {
        this.idOs = idOs;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

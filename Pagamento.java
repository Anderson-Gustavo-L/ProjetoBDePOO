package Modelo;
import java.time.LocalDate;
public class Pagamento {
    private String forma;          // PIX, DINHEIRO, CARTAO_CREDITO...
    private Double valor;
    private LocalDate dataPagamento;

    public Pagamento() {
    }

    public Pagamento(String forma, Double valor, LocalDate dataPagamento) {
        this.forma = forma;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
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

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}

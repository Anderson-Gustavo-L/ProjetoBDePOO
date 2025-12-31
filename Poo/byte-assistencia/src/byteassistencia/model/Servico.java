package byteassistencia.model;

public class Servico implements ItemCobravel {
    private String descricao;
    private double horas;
    private double valorHora;

    public Servico() {}

    public Servico(String descricao, double horas, double valorHora) {
        this.descricao = descricao;
        this.horas = horas;
        this.valorHora = valorHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getHoras() {
        return horas;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public double calcularValor() {
        return horas * valorHora;
    }

}

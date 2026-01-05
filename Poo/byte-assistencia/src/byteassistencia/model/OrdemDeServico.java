package byteassistencia.model;

import java.util.ArrayList;
import java.util.List;

public class OrdemDeServico {
    private Long idOS;
    private Long idCliente;
    private Long idAparelho;
    private Long idTecnico;
    private java.time.LocalDate dataAbertura;
    private String status;
    private String descricaoProblema;
    private List<ItemCobravel> itens;

    public OrdemDeServico() {
        this.itens = new ArrayList<>();
    }

    public OrdemDeServico(Long idOS, Long idCliente, Long idAparelho, Long idTecnico, java.time.LocalDate dataAbertura, String status, String descricaoProblema) {
        this.idOS = idOS;
        this.idCliente = idCliente;
        this.idAparelho = idAparelho;
        this.idTecnico = idTecnico;
        this.dataAbertura = dataAbertura;
        this.status = status;
        this.descricaoProblema = descricaoProblema;
        this.itens = new ArrayList<>();
    }

    public Long getIdOS() {
        return idOS;
    }

    public void setIdOS(Long idOS) {
        this.idOS = idOS;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdAparelho() {
        return idAparelho;
    }

    public void setIdAparelho(Long idAparelho) {
        this.idAparelho = idAparelho;
    }

    public Long getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Long idTecnico) {
        this.idTecnico = idTecnico;
    }

    public java.time.LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(java.time.LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public void adicionarItem(ItemCobravel item) {
        this.itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0.0;
        for (ItemCobravel item : itens) {
            total += item.calcularValor();
        }
        return total;
    }



}

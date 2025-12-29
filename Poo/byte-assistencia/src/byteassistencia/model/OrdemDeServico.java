package byteassistencia.model;

public class OrdemDeServico {
    private Long idOS;
    private Long idCliente;
    private Long idAparelho;
    private Long idTecnico;
    private String dataAbertura;
    private String status;
    private String descricaoProblema;

    public OrdemDeServico(Long idOS, Long idCliente, Long idAparelho, Long idTecnico, String dataAbertura, String status, String descricaoProblema) {
        this.idOS = idOS;
        this.idCliente = idCliente;
        this.idAparelho = idAparelho;
        this.idTecnico = idTecnico;
        this.dataAbertura = dataAbertura;
        this.status = status;
        this.descricaoProblema = descricaoProblema;
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

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
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
}

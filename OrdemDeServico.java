package Modelo;
import java.time.LocalDate;
import java.util.List;


public class OrdemDeServico {
    private Cliente cliente;              // composição
    private Aparelho aparelho;            // composição
    private Tecnico tecnico;              // composição
    private String descricaoProblema;
    private String status;
    private LocalDate dataAbertura;
    private List<ItemOS> itens;           // composição
    private List<Pagamento> pagamentos;   // composição

    public OrdemDeServico() {
    }

    public OrdemDeServico(Cliente cliente, Aparelho aparelho, Tecnico tecnico,
                          String descricaoProblema, String status, LocalDate dataAbertura,
                          List<ItemOS> itens, List<Pagamento> pagamentos) {
        this.cliente = cliente;
        this.aparelho = aparelho;
        this.tecnico = tecnico;
        this.descricaoProblema = descricaoProblema;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.itens = itens;
        this.pagamentos = pagamentos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Aparelho getAparelho() {
        return aparelho;
    }

    public void setAparelho(Aparelho aparelho) {
        this.aparelho = aparelho;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public List<ItemOS> getItens() {
        return itens;
    }

    public void setItens(List<ItemOS> itens) {
        this.itens = itens;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }


}

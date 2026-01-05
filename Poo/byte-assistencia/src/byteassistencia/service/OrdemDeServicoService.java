package byteassistencia.service;

import byteassistencia.model.ItemCobravel;
import byteassistencia.model.OrdemDeServico;

public class OrdemDeServicoService {


    public OrdemDeServico abrirOrdemDeServico(Long idCliente, Long idAparelho, Long idTecnico, String descricaoProblema) {

        OrdemDeServico os = new OrdemDeServico();
        os.setIdCliente(idCliente);
        os.setIdAparelho(idAparelho);
        os.setIdTecnico(idTecnico);
        os.setDescricaoProblema(descricaoProblema);
        os.setStatus("EM_ABERTO");
        java.time.LocalDate hoje = java.time.LocalDate.now();
        os.setDataAbertura(hoje);


        return os;
    }

    public double calcularValorTotal(OrdemDeServico ordemDeServico) {
        return ordemDeServico.calcularValorTotal();
    }

    public void adicionarItemOrdem(OrdemDeServico ordemDeServico, ItemCobravel item) {
        ordemDeServico.adicionarItem(item);
    }

    public void fecharOrdemDeServico(OrdemDeServico ordemDeServico) {
        ordemDeServico.setStatus("FECHADA");
    }



}

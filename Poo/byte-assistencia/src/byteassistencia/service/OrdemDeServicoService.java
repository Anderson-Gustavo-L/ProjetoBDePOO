package byteassistencia.service;

import byteassistencia.model.*;
import byteassistencia.exception.DadosInvalidosException;
import byteassistencia.exception.ClienteNaoEncontradoException;

import byteassistencia.model.Cliente;
import byteassistencia.model.Tecnico;
import byteassistencia.model.Aparelho;
import byteassistencia.model.OrdemDeServico;

import byteassistencia.repository.ClienteRepository;
import byteassistencia.repository.TecnicoRepository;
import byteassistencia.repository.AparelhoRepository;
import byteassistencia.repository.OrdemDeServicoRepository;





public class OrdemDeServicoService {

    private ClienteRepository clienteRepository;
    private TecnicoRepository tecnicoRepository;
    private AparelhoRepository aparelhoRepository;
    private OrdemDeServicoRepository ordemDeServicoRepository;

    public OrdemDeServicoService(ClienteRepository clienteRepository,
                                 TecnicoRepository tecnicoRepository,
                                 AparelhoRepository aparelhoRepository,
                                 OrdemDeServicoRepository ordemDeServicoRepository) {
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.aparelhoRepository = aparelhoRepository;
        this.ordemDeServicoRepository = ordemDeServicoRepository;
    }




    public OrdemDeServico abrirOrdemDeServico(Long idCliente, Long idAparelho, Long idTecnico, String descricaoProblema) {

        Cliente cliente = clienteRepository.buscarPorId(idCliente);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado: " + idCliente);
        }

        if (descricaoProblema == null || descricaoProblema.isBlank()) {
            throw new DadosInvalidosException("Descrição do problema não pode ser vazia.");
        }

        Tecnico tecnico = tecnicoRepository.buscarPorId(idTecnico);
        if (tecnico == null) {
            throw new DadosInvalidosException("Técnico não encontrado: " + idTecnico);
        }

        Aparelho aparelho = aparelhoRepository.buscarPorId(idAparelho);
        if (aparelho == null) {
            throw new DadosInvalidosException("Aparelho não encontrado: " + idAparelho);
        }

        OrdemDeServico os = new OrdemDeServico();
        os.setIdCliente(idCliente);
        os.setIdAparelho(idAparelho);
        os.setIdTecnico(idTecnico);
        os.setDescricaoProblema(descricaoProblema);
        os.setStatus("EM_ABERTO");
        java.time.LocalDate hoje = java.time.LocalDate.now();
        os.setDataAbertura(hoje);
        ordemDeServicoRepository.salvar(os);

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

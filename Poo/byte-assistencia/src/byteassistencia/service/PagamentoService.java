package byteassistencia.service;

import byteassistencia.model.OrdemDeServico;
import byteassistencia.model.Pagamento;
import byteassistencia.model.Cliente;
import byteassistencia.repository.OrdemDeServicoRepository;
import byteassistencia.repository.PagamentoRepository;
import byteassistencia.repository.ClienteRepository;
import byteassistencia.exception.DadosInvalidosException;
import byteassistencia.exception.PagamentoInsuficienteException;
import byteassistencia.exception.ClienteNaoEncontradoException;

public class PagamentoService {
    private OrdemDeServicoRepository osRepo;
    private PagamentoRepository pagRepo;
    private ClienteRepository clienteRepo;

    public PagamentoService(OrdemDeServicoRepository osRepo,
                            PagamentoRepository pagRepo,
                            ClienteRepository clienteRepo) {
        this.osRepo = osRepo;
        this.pagRepo = pagRepo;
        this.clienteRepo = clienteRepo;
    }

    public void registrarPagamento(Long idOs, String forma, double valor) {
        OrdemDeServico os = osRepo.buscarPorId(idOs);
        if (os == null) {
            throw new DadosInvalidosException("OS não encontrada: " + idOs);
        }

        Cliente cliente = clienteRepo.buscarPorId(os.getIdCliente());
        if (cliente == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado: " + os.getIdCliente());
        }

        if ("FECHADA".equals(os.getStatus())) {
            throw new DadosInvalidosException("OS já está fechada.");
        }

        if (os.getItens() == null || os.getItens().isEmpty()) {
            throw new DadosInvalidosException("Não é possível pagar uma OS sem itens.");
        }

        if (forma == null || forma.isBlank()) {
            throw new DadosInvalidosException("Forma de pagamento inválida.");
        }

        if (valor <= 0) {
            throw new DadosInvalidosException("Valor do pagamento deve ser positivo.");
        }

        double total = os.calcularValorTotal();
        if (valor < total) {
            throw new PagamentoInsuficienteException("Pagamento menor que o valor da OS.");
        }

        Pagamento pag = new Pagamento();
        pag.setIdOs(idOs);
        pag.setForma(forma);
        pag.setValor(valor);
        java.time.LocalDate hoje = java.time.LocalDate.now();
        String dataStr = hoje.toString();
        pag.setData(dataStr);
        pagRepo.salvar(pag);
    }
}

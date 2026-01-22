package byteassistencia.service;

import byteassistencia.model.OrdemDeServico;
import byteassistencia.model.Pagamento;
import byteassistencia.repository.OrdemDeServicoRepository;
import byteassistencia.repository.PagamentoRepository;
import byteassistencia.exception.DadosInvalidosException;
import byteassistencia.exception.PagamentoInsuficienteException;

public class PagamentoService {

    private OrdemDeServicoRepository osRepo;
    private PagamentoRepository pagRepo;

    public PagamentoService(OrdemDeServicoRepository osRepo, PagamentoRepository pagRepo) {
        this.osRepo = osRepo;
        this.pagRepo = pagRepo;
    }

    public void registrarPagamento(Long idOs, String forma, double valor) {
        OrdemDeServico os = osRepo.buscarPorId(idOs);
        if (os == null) {
            throw new DadosInvalidosException("OS não encontrada: " + idOs);
        }
        if (forma == null || forma.isBlank()) {
            throw new DadosInvalidosException("Forma de pagamento inválida.");
        }
        if (valor <= 0) {
            throw new DadosInvalidosException("Valor do pagamento deve ser positivo.");
        }

        double total = os.calcularValorTotal();
        // aqui você pode somar pagamentos anteriores usando pagRepo.listarTodos() filtrando por id_os, se quiser
        if (valor < total) {
            throw new PagamentoInsuficienteException("Pagamento menor que o valor da OS.");
        }

        Pagamento pag = new Pagamento();
        pag.setIdOs(idOs);
        pag.setForma(forma);
        pag.setValor(valor);

        java.time.LocalDate hoje = java.time.LocalDate.now();
        String dataStr = hoje.toString(); // "2026-01-21" por exemplo
        pag.setData(dataStr);

        pagRepo.salvar(pag);

    }
}

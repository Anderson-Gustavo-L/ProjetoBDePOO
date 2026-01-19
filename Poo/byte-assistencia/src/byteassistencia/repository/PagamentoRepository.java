package byteassistencia.repository;


import byteassistencia.model.Pagamento;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepository {
    private List<Pagamento> pagamentos = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Pagamento pagamento) {
        pagamento.setIdPagamento(proximoId++);
        pagamentos.add(pagamento);
    }

    public Pagamento buscarPorId(Long id) {
        for (Pagamento p : pagamentos) {
            if (p.getIdPagamento().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Pagamento> listarTodos() {
        return pagamentos;
    }

    public void deletar(Long id) {
        pagamentos.removeIf(p -> p.getIdPagamento().equals(id));
    }
}
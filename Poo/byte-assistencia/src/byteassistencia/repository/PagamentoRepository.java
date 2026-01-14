package comByteRepository;

import comByteModel.Pagamento;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepository {
    private List<Pagamento> pagamentos = new ArrayList<>();
    private int proximoId = 1;

    public void salvar(Pagamento pagamento) {
        pagamento.setIdPagamento(proximoId++);
        pagamentos.add(pagamento);
    }

    public Pagamento buscarPorId(int id) {
        for (Pagamento p : pagamentos) {
            if (p.getIdPagamento() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Pagamento> listarTodos() {
        return pagamentos;
    }

    public void deletar(int id) {
        pagamentos.removeIf(p -> p.getIdPagamento() == id);
    }
}
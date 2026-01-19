package byteassistencia.repository;

import byteassistencia.model.Aparelho;
import java.util.ArrayList;
import java.util.List;

public class AparelhoRepository {
    private List<Aparelho> aparelhos = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Aparelho aparelho) {
        aparelho.setIdAparelho(proximoId++);
        aparelhos.add(aparelho);
    }

    public Aparelho buscarPorId(Long id) {
        for (Aparelho a : aparelhos) {
            if (a.getIdAparelho().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public List<Aparelho> listarTodos() {
        return aparelhos;
    }

    public void atualizar(Aparelho aparelhoAtualizado) {
        for (int i = 0; i < aparelhos.size(); i++) {
            if (aparelhos.get(i).getIdAparelho().equals(aparelhoAtualizado.getIdAparelho())) {

                aparelhos.set(i, aparelhoAtualizado);
                return;
            }
        }
    }

    public void deletar(Long id) {
        aparelhos.removeIf(a -> a.getIdAparelho().equals(id));

    }
}
package comByteRepository;

import comByteModel.Aparelho;
import java.util.ArrayList;
import java.util.List;

public class AparelhoRepository {
    private List<Aparelho> aparelhos = new ArrayList<>();
    private int proximoId = 1;

    public void salvar(Aparelho aparelho) {
        aparelho.setIdAparelho(proximoId++);
        aparelhos.add(aparelho);
    }

    public Aparelho buscarPorId(int id) {
        for (Aparelho a : aparelhos) {
            if (a.getIdAparelho() == id) {
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
            if (aparelhos.get(i).getIdAparelho() == aparelhoAtualizado.getIdAparelho()) {
                aparelhos.set(i, aparelhoAtualizado);
                return;
            }
        }
    }

    public void deletar(int id) {
        aparelhos.removeIf(a -> a.getIdAparelho() == id);
    }
}
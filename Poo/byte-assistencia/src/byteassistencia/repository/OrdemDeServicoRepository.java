package comByteRepository;

import comByteModel.OrdemDeServico;
import java.util.ArrayList;
import java.util.List;

public class OrdemDeServicoRepository {
    private List<OrdemDeServico> ordens = new ArrayList<>();
    private int proximoId = 1;

    public void salvar(OrdemDeServico os) {
        os.setIdOS(proximoId++);
        ordens.add(os);
    }

    public OrdemDeServico buscarPorId(int id) {
        for (OrdemDeServico os : ordens) {
            if (os.getIdOS() == id) {
                return os;
            }
        }
        return null;
    }

    public List<OrdemDeServico> listarTodos() {
        return ordens;
    }

    public void atualizar(OrdemDeServico osAtualizada) {
        for (int i = 0; i < ordens.size(); i++) {
            if (ordens.get(i).getIdOS() == osAtualizada.getIdOS()) {
                ordens.set(i, osAtualizada);
                return;
            }
        }
    }

    public void deletar(int id) {
        ordens.removeIf(os -> os.getIdOS() == id);
    }
}
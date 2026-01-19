package byteassistencia.repository;

import byteassistencia.model.OrdemDeServico;
import java.util.ArrayList;
import java.util.List;

public class OrdemDeServicoRepository {
    private List<OrdemDeServico> ordens = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(OrdemDeServico os) {
        os.setIdOS(proximoId++);
        ordens.add(os);
    }

    public OrdemDeServico buscarPorId(Long id) {
        for (OrdemDeServico os : ordens) {
            if (os.getIdOS().equals(id)) {
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
            if (ordens.get(i).getIdOS().equals(osAtualizada.getIdOS())) {
                ordens.set(i, osAtualizada);
                return;
            }
        }
    }

    public void deletar(Long id) {
        ordens.removeIf(os -> os.getIdOS().equals(id));
    }
}
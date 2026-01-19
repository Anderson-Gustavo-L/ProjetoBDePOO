package byteassistencia.repository;

import byteassistencia.model.Tecnico;
import java.util.ArrayList;
import java.util.List;

public class TecnicoRepository {
    private List<Tecnico> tecnicos = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Tecnico tecnico) {
        tecnico.setId(proximoId++);
        tecnicos.add(tecnico);
    }

    public Tecnico buscarPorId(Long id) {
        for (Tecnico t : tecnicos) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public List<Tecnico> listarTodos() {
        return tecnicos;
    }

    public void atualizar(Tecnico tecnicoAtualizado) {
        for (int i = 0; i < tecnicos.size(); i++) {
            if (tecnicos.get(i).getId().equals(tecnicoAtualizado.getId())) {
                tecnicos.set(i, tecnicoAtualizado);
                return;
            }
        }
    }

    public void deletar(Long id) {
        tecnicos.removeIf(t -> t.getId().equals(id));
    }
}
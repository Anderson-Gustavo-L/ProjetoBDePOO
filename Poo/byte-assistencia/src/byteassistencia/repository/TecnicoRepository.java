package comByteRepository;

import comByteModel.Tecnico;
import java.util.ArrayList;
import java.util.List;

public class TecnicoRepository {
    private List<Tecnico> tecnicos = new ArrayList<>();
    private int proximoId = 1;

    public void salvar(Tecnico tecnico) {
        tecnico.setId(proximoId++);
        tecnicos.add(tecnico);
    }

    public Tecnico buscarPorId(int id) {
        for (Tecnico t : tecnicos) {
            if (t.getId() == id) {
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
            if (tecnicos.get(i).getId() == tecnicoAtualizado.getId()) {
                tecnicos.set(i, tecnicoAtualizado);
                return;
            }
        }
    }

    public void deletar(int id) {
        tecnicos.removeIf(t -> t.getId() == id);
    }
}
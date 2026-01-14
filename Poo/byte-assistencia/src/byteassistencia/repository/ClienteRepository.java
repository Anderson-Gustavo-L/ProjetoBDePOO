package comByteRepository;

import comByteModel.Cliente; // Importando o Cliente que você criou antes
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    public void salvar(Cliente cliente) {
        cliente.setId(proximoId++); // Gera o ID e aumenta o contador
        clientes.add(cliente);
    }

    public Cliente buscarPorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // Retorna null se não achar ninguém
    }

    public List<Cliente> listarTodos() {
        return clientes;
    }

    public void atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == clienteAtualizado.getId()) {
                clientes.set(i, clienteAtualizado); // Troca o velho pelo novo
                return;
            }
        }
    }

    public void deletar(int id) {
        // Remove da lista quem tiver esse ID
        clientes.removeIf(c -> c.getId() == id);
    }
}
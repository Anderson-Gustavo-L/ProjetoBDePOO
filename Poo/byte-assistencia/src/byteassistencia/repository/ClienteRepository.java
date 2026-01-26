package byteassistencia.repository;

import byteassistencia.model.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private List<Cliente> clientes = new ArrayList<>();
    private Long proximoId = 1L;

    public void salvar(Cliente cliente) {
        cliente.setId(proximoId++);
        clientes.add(cliente);
    }

    public Cliente buscarPorId(Long id) {
        for (Cliente c : clientes) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public Cliente buscarPorCPF(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }


    public List<Cliente> listarTodos() {
        return clientes;
    }

    public void atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(clienteAtualizado.getId())) {
                clientes.set(i, clienteAtualizado);
                return;
            }
        }
    }

    public void deletar(Long id) {
        clientes.removeIf(c -> c.getId().equals(id));
    }
}
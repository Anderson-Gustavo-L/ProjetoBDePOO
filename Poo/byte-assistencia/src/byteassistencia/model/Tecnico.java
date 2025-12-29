package byteassistencia.model;

public class Tecnico extends Pessoa {
    private String especialidade;

    public Tecnico(Long id, String nome, String cpf, String especialidade) {
        super(id, nome, cpf);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}

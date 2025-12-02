package Modelo;

public class Tecnico extends Pessoa {
    private String especialidade;

    public Tecnico() {
    }

    public Tecnico(String nome, String cpf, String telefone, String email,
                   String especialidade) {
        super(nome, cpf, telefone, email);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

}

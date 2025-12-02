package Modelo;

public class Cliente extends Pessoa {
    private String endereco;
    private String bairro;
    private String cidade;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String telefone, String email,
                   String endereco, String bairro, String cidade) {
        super(nome, cpf, telefone, email);
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
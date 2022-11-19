package BankProject.Contador.Model.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    private String endereco;
    private String cpf;
    private String celular;
    private String cep;
    private String email_de_recuperacao;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEmail_de_recuperacao() {
        return email_de_recuperacao;
    }

    public void setEmail_de_recuperacao(String email_de_recuperacao) {
        this.email_de_recuperacao = email_de_recuperacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

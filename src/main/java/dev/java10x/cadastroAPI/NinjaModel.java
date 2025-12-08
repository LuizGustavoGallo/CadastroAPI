package dev.java10x.cadastroAPI;

import jakarta.persistence.*;

// Transforma a classe em uma entidade do BD
@Entity
@Table(name = "tb_cadastro")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private int idade;

    public NinjaModel() {
    }

    public NinjaModel(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public NinjaModel setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public NinjaModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getIdade() {
        return idade;
    }

    public NinjaModel setIdade(int idade) {
        this.idade = idade;
        return this;
    }
}

package dev.java10x.CadastroApi.Ninja;

import dev.java10x.CadastroApi.Missaoes.MissoesModel;
import jakarta.persistence.*;

import java.util.List;

//Transformando a classe em uma entidade do Banco de Dados
@Entity
@Table(name = "tb_cadastro")
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private int idade;

    //@ManyToOne - um ninja tem uma unica missao
    @ManyToOne
    private MissoesModel missoes;


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

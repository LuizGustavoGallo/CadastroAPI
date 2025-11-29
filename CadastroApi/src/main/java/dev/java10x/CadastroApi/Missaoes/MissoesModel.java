package dev.java10x.CadastroApi.Missaoes;
import dev.java10x.CadastroApi.Ninja.NinjaModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "tb_missoes")
public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDaMissao;

    private String dificuldade;

    // OneToMany - uma Missao pode ter varios ninjas
    @OneToMany(mappedBy = "missoes")
    @JoinColumn(name = "missoes_id") // Foreing key ou chave estrangeira
    private List<NinjaModel> ninjas;
}

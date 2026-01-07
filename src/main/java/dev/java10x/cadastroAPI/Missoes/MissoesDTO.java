package dev.java10x.cadastroAPI.Missoes;

import dev.java10x.cadastroAPI.Ninja.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissoesDTO {

    private Long id;
    private String nomeMissao;
    private String dificuldade;
    private List<NinjaModel> ninjas;
}

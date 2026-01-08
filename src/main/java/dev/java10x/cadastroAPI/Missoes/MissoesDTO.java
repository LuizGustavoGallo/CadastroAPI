package dev.java10x.cadastroAPI.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private List<NinjaModel> ninjas;
}

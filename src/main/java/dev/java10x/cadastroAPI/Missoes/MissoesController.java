package dev.java10x.cadastroAPI.Missoes;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET -- Mandar uma requisisao para mostrar as missoes
    @GetMapping("/listarMissoes")
    public List<MissoesModel> listarMissoes() {
        return missoesService.listarMissoes();
    }

    // GET -- Mostrar missao por id
    @GetMapping("/listarMissoes/{id}")
    public MissoesModel listarMissoesPorId(@PathVariable Long id){
        return missoesService.listarMissoesPorId(id);
    }

    // POST -- Mandar uma requisisao para criar uma missao
    @PostMapping("/criarMissoes")
    public MissoesModel criarMissoes(@RequestBody MissoesModel missoes){
        return missoesService.criarMissoes(missoes);
    }


    // PUT -- Mandar uma requisisao para alterar uma missao
    @PutMapping("/alterar")
    public String alterarMissao() {
        return "missao alterada comn sucesso";
    }

    // DELETE -- Mandar uma requisisao para deletar uma missao
    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "missao deletada com sucesso";
    }
}

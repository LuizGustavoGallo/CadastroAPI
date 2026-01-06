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
    @PutMapping("/alterar/{id}")
    public MissoesModel alterarMissaoPorId(@PathVariable Long id, @RequestBody MissoesModel missaoAtualizada){
        return missoesService.atualizarMissoes(id, missaoAtualizada);
    }

    // DELETE -- Mandar uma requisisao para deletar uma missao
    @DeleteMapping("/deletar/{id}")
    public void deletarMissaoPorId(@PathVariable Long id) {
        missoesService.deletarMissaoPorId(id);
    }
}

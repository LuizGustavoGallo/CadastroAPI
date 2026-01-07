package dev.java10x.cadastroAPI.Missoes;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // GET -- Mostrar missao por id
    @GetMapping("/listarMissoes/{id}")
    public ResponseEntity<?> listarMissoesPorId(@PathVariable Long id){
        MissoesDTO missoesPorId = missoesService.listarMissoesPorId(id);
        if (missoesPorId != null){
            return ResponseEntity.ok("Missão encontrada: " + missoesPorId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID: " + id + " não foi encontrada.");
        }
    }

    // POST -- Mandar uma requisisao para criar uma missao
    @PostMapping("/criarMissoes")
    public ResponseEntity<String> criarMissoes(@RequestBody MissoesDTO missoes){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão: " + novaMissao.getNomeMissao() + ". ID: " + novaMissao.getId() + " criada com sucesso.");
    }


    // PUT -- Mandar uma requisisao para alterar uma missao
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissaoPorId(@PathVariable Long id, @RequestBody MissoesDTO missaoatualizada){
        MissoesDTO missao = missoesService.atualizarMissoes(id, missaoatualizada);
        if (missao != null){
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão não encontrada.");
        }
    }

    // DELETE -- Mandar uma requisisao para deletar uma missao
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissaoPorId(@PathVariable Long id){
        if (missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missão de ID: " + id + " foi deletada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão de ID: " + id + " não foi encontrada.");
        }
    }
}

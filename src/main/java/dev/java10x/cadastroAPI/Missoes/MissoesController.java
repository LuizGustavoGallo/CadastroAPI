package dev.java10x.cadastroAPI.Missoes;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // GET -- Mandar uma requisisao para mostrar as missoes
    @GetMapping("/listarMissoes")
    @Operation(summary = "Lista todas as missões", description = "Lista as missões presentes no BD.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missões mostradas com sucesso."),
            @ApiResponse(responseCode = "400", description = "Não existe missões para serem mostradas no BD.")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes(){
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // GET -- Mostrar missao por id
    @GetMapping("/listarMissoes/{id}")
    @Operation(summary = "Listar Missões por ID", description = "Lista a missão requisitada pelo ID inserido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão encontrada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Missão não foi encontrada.")
    })
    public ResponseEntity<?> listarMissoesPorId(
            @Parameter(description = "Usuario insere o ID na caminho da requisição.")
            @PathVariable Long id){
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
    @Operation(summary = "Cria uma nova missão", description = "Criação de uma nova missão que será inserida no BD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criação da Missão")
    })
    public ResponseEntity<String> criarMissoes(
            @Parameter(description = "Usuario informará os dados da missão a ser criada no campo da requisição")
            @RequestBody MissoesDTO missoes){
        MissoesDTO novaMissao = missoesService.criarMissoes(missoes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão: " + novaMissao.getNomeMissao() + ". ID: " + novaMissao.getId() + " criada com sucesso.");
    }


    // PUT -- Mandar uma requisisao para alterar uma missao
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera a missão por ID", description = "Rota altera as informações da missão presente no determinado ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão alterada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Missão requisitada não foi encontrada.")
    })
    public ResponseEntity<?> alterarMissaoPorId(
            @Parameter(description = "Usuario informará o ID no caminho da requisição.")
            @PathVariable Long id,
            @Parameter(description = "Usuario informará os dados da missão a ser atualizada no corpo da requisição")
            @RequestBody MissoesDTO missaoatualizada){
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
    @Operation(summary = "Deleta a missão por ID", description = "Rota deleta o ninja presente no ID requisitado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão deletada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Missão não foi encontrada.")
    })
    public ResponseEntity<String> deletarMissaoPorId(
            @Parameter(description = "Usuario insere o ID no caminho da requisição.")
            @PathVariable Long id){
        if (missoesService.listarMissoesPorId(id) != null){
            missoesService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missão de ID: " + id + " foi deletada com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("A missão de ID: " + id + " não foi encontrada.");
        }
    }
}

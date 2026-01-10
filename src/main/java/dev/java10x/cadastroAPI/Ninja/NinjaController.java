package dev.java10x.cadastroAPI.Ninja;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem a acessar.")
    public String boasVindas() {
        return "Seja bem vindo!";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criarNinja")
    @Operation(summary = "Cria um novo ninja", description = "Rota cria um novo ninja e insere no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja.")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja foi criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listarNinjas")
    @Operation(summary = "Lista todos os ninjas", description = "Lista todos os ninjas presentes no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninjas mostrados com sucesso."),
            @ApiResponse(responseCode = "400", description = "O banco de dados não possui ninjas registrados.")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar por ID (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por ID", description = "Lista todos os ninjas do banco de dados por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não foi encontrado.")
    })
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "Usuario insere o ID no caminho da requisição.")
            @PathVariable Long id) {
        NinjaDTO ninjasPorId = ninjaService.listarNinjasPorId(id);
        if (ninjasPorId != null) {
            return ResponseEntity.ok("Ninja encontrado: " + ninjasPorId);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com o ID: " + id + " não existe.");
        }
    }

    // Alterar dados dos ninjas (UPDATEE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o Ninja por ID", description = "Rota altera as informações do ninja presente no determinado ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Ninja não foi encontrado.")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "Usuario manda o ID no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "Usuario manda os dados do ninja a ser atualizado no corpo da requisão.")
            @RequestBody NinjaDTO ninjaAtualizado) {


        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAtualizado);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não foi encontrado.");
        }
    }

    // Deleter Ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta o ninja por ID", description = "Rota altera o ninja presente no ID requisitado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja deletado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Ninja requisitado pelo ID não foi encontrado.")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "Usuario insere o ID no caminho da requisição.")
            @PathVariable Long id) {

        if (ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja de ID: " + id + " foi deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja de ID: " + id + " não foi encontrado.");
        }
    }
}
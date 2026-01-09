package dev.java10x.cadastroAPI.Ninja;

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
    public String boasVindas() {
        return "Seja bem vindo!";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criarNinja")
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja foi criado com sucesso: " + novoNinja.getNome() + " (ID): " + novoNinja.getId());
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listarNinjas")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // Mostrar por ID (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id) {
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
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizado) {
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
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {

        if (ninjaService.listarNinjasPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja de ID: " + id + " foi deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja de ID: " + id + " não foi encontrado.");
        }
    }
}
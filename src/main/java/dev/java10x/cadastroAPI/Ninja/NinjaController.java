package dev.java10x.cadastroAPI.Ninja;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Seja bem vindo!";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/todos")
    public String mostrarTodosOsNinjas() {
        return "Exibindo todos os ninjas";
    }

    // Mostrar por ID (READ)
    @GetMapping("/todosID")
    public String mostrarNinjasPorIds() {
        return "Mostrar ninja por ID";
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinjaPorId() {
        return "Alterar ninja por ID";
    }

    // Deleter Ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarId() {
        return "Ninja deletado por ID";
    }
}

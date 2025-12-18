package dev.java10x.cadastroAPI.Ninja;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ninjas")
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
    @GetMapping("/listar")
    public String mostrarTodosOsNinjas() {
        return "Exibindo todos os ninjas";
    }

    // Mostrar por ID (READ)
    @GetMapping("/listarID")
    public String mostrarNinjasPorIds() {
        return "Mostrar ninja por ID";
    }

    // Alterar dados dos ninjas (UPDATEE)
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

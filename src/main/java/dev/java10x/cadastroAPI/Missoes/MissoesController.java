package dev.java10x.cadastroAPI.Missoes;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    // GET -- Mandar uma requisisao para mostrar as missoes
    @GetMapping("/listar")
    public String listarMissao() {
        return "Missoes listadas com sucesso";
    }

    // POST -- Mandar uma requisisao para criar uma missao
    @PostMapping("/criar")
    public String criarMissao() {
        return "missao criada com sucesso";
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

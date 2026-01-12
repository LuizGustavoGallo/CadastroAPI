package dev.java10x.cadastroAPI.Missoes;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listarMissoes")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    @GetMapping("/deletarMissoes/{id}")
    public String deletarMissoesPorId(@PathVariable Long id){
        missoesService.deletarMissaoPorId(id);
        return "redirect:/missoes/ui/listarMissoes";
    }

    @GetMapping("/listarMissoes/{id}")
    public String listarMissoesPorId(@PathVariable Long id, Model model){
        MissoesDTO missoes = missoesService.listarMissoesPorId(id);
        if (missoes != null){
            model.addAttribute("missoes", missoes);
            return "detalheMissoes";
        } else{
            model.addAttribute("mensagem", "Missão não encontrada.");
            return "listarMissoes";
        }
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarMissao(Model model){
        model.addAttribute("missoes", new MissoesDTO());
        return "adicionarMissao";
    }

    @GetMapping("/salvarMissao")
    public String salvarMissao(@ModelAttribute MissoesDTO missoes, RedirectAttributes redirectAttributes){
        missoesService.criarMissoes(missoes);
        redirectAttributes.addFlashAttribute("mensagem", "Missão cadastrada com sucesso!");
        return "redirect:/missoes/ui/listarMissoes";
    }
}

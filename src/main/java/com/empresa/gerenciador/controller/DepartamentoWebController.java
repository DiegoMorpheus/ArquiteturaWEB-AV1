package com.empresa.gerenciador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.gerenciador.model.Departamento;
import com.empresa.gerenciador.service.DepartamentoService;

@Controller
@RequestMapping("/web/departamentos")
public class DepartamentoWebController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("departamentos", departamentoService.listarTodos());
        return "departamentos/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("departamento", new Departamento());
        return "departamentos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Departamento departamento) {
        departamentoService.salvar(departamento);
        return "redirect:/web/departamentos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Departamento departamento = departamentoService.buscarPorId(id);
        model.addAttribute("departamento", departamento);
        return "departamentos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Departamento departamento = departamentoService.buscarPorId(id);

        if (!departamentoService.podeExcluir(departamento)) {
            redirectAttributes.addFlashAttribute("erro", "Não é possível excluir: o departamento possui funcionários vinculados.");
            return "redirect:/web/departamentos";
        }

        departamentoService.excluir(id);
        redirectAttributes.addFlashAttribute("sucesso", "Departamento excluído com sucesso.");
        return "redirect:/web/departamentos";
    }
}

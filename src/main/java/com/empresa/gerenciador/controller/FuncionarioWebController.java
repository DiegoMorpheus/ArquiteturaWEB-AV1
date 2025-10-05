package com.empresa.gerenciador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.gerenciador.model.Funcionario;
import com.empresa.gerenciador.repository.DepartamentoRepository;
import com.empresa.gerenciador.repository.FuncionarioRepository;

@Controller
@RequestMapping("/web/funcionarios")
public class FuncionarioWebController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public String listar(Model model) {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        model.addAttribute("funcionarios", funcionarios);
        return "funcionarios/list";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "funcionarios/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return "redirect:/web/funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow();
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("departamentos", departamentoRepository.findAll());
        return "funcionarios/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);
        return "redirect:/web/funcionarios";
    }
}

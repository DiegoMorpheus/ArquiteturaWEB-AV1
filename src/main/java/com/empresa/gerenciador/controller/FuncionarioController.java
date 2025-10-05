package com.empresa.gerenciador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.empresa.gerenciador.model.Departamento;
import com.empresa.gerenciador.model.Funcionario;
import com.empresa.gerenciador.repository.DepartamentoRepository;
import com.empresa.gerenciador.repository.FuncionarioRepository;

import jakarta.persistence.ManyToOne;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @ManyToOne
private Departamento departamento;


    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @PostMapping
    public ResponseEntity<Funcionario> criar(@RequestBody Funcionario funcionario) {
        if (funcionario.getDepartamento() != null &&
            departamentoRepository.existsById(funcionario.getDepartamento().getId())) {
            return ResponseEntity.ok(funcionarioRepository.save(funcionario));
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return funcionarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable Long id, @RequestBody Funcionario novo) {
        return funcionarioRepository.findById(id)
                .map(func -> {
                    func.setNome(novo.getNome());
                    func.setEmail(novo.getEmail());
                    func.setDataAdmissao(novo.getDataAdmissao());
                    func.setDepartamento(novo.getDepartamento());
                    return ResponseEntity.ok(funcionarioRepository.save(func));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

@GetMapping("/web")
public String listar(Model model) {
    model.addAttribute("funcionarios", funcionarioRepository.findAll());
    return "funcionarios";
}


@GetMapping("/novo")
public String novo(Model model) {
    model.addAttribute("funcionario", new Funcionario());
    model.addAttribute("departamentos", departamentoRepository.findAll());
    return "form-funcionario";
}

@PostMapping("/salvar")
public String salvar(@ModelAttribute Funcionario funcionario) {
    funcionarioRepository.save(funcionario);
    return "redirect:/funcionarios/web";
}

@GetMapping("/editar/{id}")
public String editar(@PathVariable Long id, Model model) {
    Funcionario funcionario = funcionarioRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
    model.addAttribute("funcionario", funcionario);
    model.addAttribute("departamentos", departamentoRepository.findAll());
    return "form-funcionario";
}

@GetMapping("/excluir/{id}")
public String excluirWeb(@PathVariable Long id) {
    if (funcionarioRepository.existsById(id)) {
        funcionarioRepository.deleteById(id);
    }
    return "redirect:/funcionarios/web";
}



}

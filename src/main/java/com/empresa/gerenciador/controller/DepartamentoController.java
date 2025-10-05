package com.empresa.gerenciador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empresa.gerenciador.model.Departamento;
import com.empresa.gerenciador.repository.DepartamentoRepository;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Override
    public String toString() {
    return String.valueOf(id); // ou qualquer valor único
    }


    @Autowired
    private DepartamentoRepository departamentoRepository;

    @PostMapping
    public Departamento criar(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @GetMapping
    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        return departamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> atualizar(@PathVariable Long id, @RequestBody Departamento novo) {
        return departamentoRepository.findById(id)
                .map(dep -> {
                    dep.setNome(novo.getNome());
                    dep.setLocalizacao(novo.getLocalizacao());
                    return ResponseEntity.ok(departamentoRepository.save(dep));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (departamentoRepository.existsById(id)) {
            departamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/excluir/{id}")
public String excluir(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    Departamento departamento = departamentoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Departamento inválido: " + id));

    if (!departamento.getFuncionarios().isEmpty()) {
        redirectAttributes.addFlashAttribute("erro", "Não é possível excluir: o departamento possui funcionários vinculados.");
        return "redirect:/web/departamentos";
    }

    departamentoRepository.deleteById(id);
    redirectAttributes.addFlashAttribute("sucesso", "Departamento excluído com sucesso.");
    return "redirect:/web/departamentos";
}



}

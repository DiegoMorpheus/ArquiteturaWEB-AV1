package com.empresa.gerenciador.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.gerenciador.model.Departamento;
import com.empresa.gerenciador.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }

    public Departamento buscarPorId(Long id) {
        return departamentoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Departamento inválido: " + id));
    }

    public void salvar(Departamento departamento) {
        departamentoRepository.save(departamento);
    }

    public boolean podeExcluir(Departamento departamento) {
        return departamento.getFuncionarios() == null || departamento.getFuncionarios().isEmpty();
    }

    public void excluir(Long id) {
        departamentoRepository.deleteById(id);
    }
}

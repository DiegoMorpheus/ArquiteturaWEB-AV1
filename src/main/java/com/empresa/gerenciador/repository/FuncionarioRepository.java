package com.empresa.gerenciador.repository;

import com.empresa.gerenciador.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {}

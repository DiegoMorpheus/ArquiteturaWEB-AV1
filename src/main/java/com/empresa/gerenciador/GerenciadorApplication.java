package com.empresa.gerenciador;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.empresa.gerenciador.model.Departamento;
import com.empresa.gerenciador.model.Funcionario;
import com.empresa.gerenciador.repository.DepartamentoRepository;
import com.empresa.gerenciador.repository.FuncionarioRepository;

@SpringBootApplication
public class GerenciadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorApplication.class, args);
	}

	@Bean
CommandLineRunner initData(DepartamentoRepository departamentoRepo, FuncionarioRepository funcionarioRepo) {
    return args -> {
        Departamento ti = new Departamento();
        ti.setNome("Tecnologia");
        departamentoRepo.save(ti);

        Departamento rh = new Departamento();
        rh.setNome("Recursos Humanos");
        departamentoRepo.save(rh);

        Funcionario f1 = new Funcionario();
        f1.setNome("Ana");
        f1.setCargo("Desenvolvedora");
        f1.setSalario(8500.0);
        f1.setDepartamento(ti);
        funcionarioRepo.save(f1);

        Funcionario f2 = new Funcionario();
        f2.setNome("Carlos");
        f2.setCargo("Analista de RH");
        f2.setSalario(6200.0);
        f2.setDepartamento(rh);
        funcionarioRepo.save(f2);

		Funcionario f3 = new Funcionario();
        f3.setNome("Diego Jardim de Oliveira");
        f3.setCargo("Analista de Suporte e Estudante de ADS");
        f3.setSalario(2815.0);
        f3.setDepartamento(ti);
        funcionarioRepo.save(f3);
    };
}


}

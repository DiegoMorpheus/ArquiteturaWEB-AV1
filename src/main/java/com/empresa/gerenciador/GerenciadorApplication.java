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
    public CommandLineRunner initData(DepartamentoRepository departamentoRepo, FuncionarioRepository funcionarioRepo) {
        return args -> {
            // Evita duplicação de dados ao reiniciar
            if (departamentoRepo.count() > 0 || funcionarioRepo.count() > 0) {
                return;
            }

            Departamento ti = criarDepartamento("Tecnologia", departamentoRepo);
            Departamento rh = criarDepartamento("Recursos Humanos", departamentoRepo);

            criarFuncionario("Ana", "Desenvolvedora", 8500.0, ti, funcionarioRepo);
            criarFuncionario("Carlos", "Analista de RH", 6200.0, rh, funcionarioRepo);
            criarFuncionario("Diego Jardim de Oliveira", "Analista de Suporte e Estudante de ADS", 2815.0, ti, funcionarioRepo);
        };
    }

    private Departamento criarDepartamento(String nome, DepartamentoRepository repo) {
        Departamento departamento = new Departamento();
        departamento.setNome(nome);
        return repo.save(departamento);
    }

    private Funcionario criarFuncionario(String nome, String cargo, double salario, Departamento departamento, FuncionarioRepository repo) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setSalario(salario);
        funcionario.setDepartamento(departamento);
        return repo.save(funcionario);
    }
}

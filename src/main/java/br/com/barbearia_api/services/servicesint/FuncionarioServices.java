package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.model.entity.Funcionario;

import java.util.List;

public interface FuncionarioServices {

    Funcionario criarFuncionario(Funcionario funcionario);

    Funcionario funcionarioPorId(Long funcionarioId);

    List<Funcionario> todosFuncionarios();

    Funcionario deletarFuncionarioId(Long funcionarioId);

    Funcionario atualizarPorId(Long funcionarioId, Funcionario funcionarioAtt);
}

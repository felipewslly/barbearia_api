package br.com.barbearia_api.services;

import br.com.barbearia_api.ApiException;
import br.com.barbearia_api.model.entity.Funcionario;
import br.com.barbearia_api.repository.FuncionarioRepo;
import br.com.barbearia_api.services.servicesint.FuncionarioServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioServ implements FuncionarioServices {

    @Autowired
    private FuncionarioRepo funcionarioRepo;
    @Override
    public Funcionario criarFuncionario(Funcionario funcionario) {
        try {
            // Validação dos campos do funcionário
            if (funcionario == null) {
                throw new IllegalArgumentException("Nenhum dos campos pode estar vazio.");
            }
            Funcionario func = new Funcionario();
            func.setNome(funcionario.getNome());
            func.setEndereco(funcionario.getEndereco());
            func.setTelefone(funcionario.getTelefone());
            func.setCargo(funcionario.getCargo());
            func.setEmail(funcionario.getEmail());
            func.setDataDeContratacao(funcionario.getDataDeContratacao());

            // Salvando o funcionário
            return funcionarioRepo.save(func);

        } catch (DataIntegrityViolationException e) {
            // Exceção específica para erros de integridade no banco de dados
            throw new IllegalArgumentException("Funcionário já existe: " + e.getMessage());
        } catch (Exception e) {
            // Captura de outras exceções genéricas
            throw new RuntimeException("Erro ao criar funcionário: " + e.getMessage(), e);
        }
    }

    @Override
    public Funcionario funcionarioPorId(Long funcionarioId) {
        try{
            Funcionario funcionarioPorId = funcionarioRepo.findById(funcionarioId).orElseThrow(
                    () -> new IllegalArgumentException("Funcionário não encontrado"));
            return funcionarioPorId;

        }catch (ApiException e){
            throw new ApiException("Funcionário não encontrado" + e.getMessage());
        }
    }

    @Override
    public List<Funcionario> todosFuncionarios() {
         List<Funcionario> allFuncionarios = funcionarioRepo.findAll();
         return allFuncionarios;
    }

    @Override
    public Funcionario deletarFuncionarioId(Long funcionarioId) {
        try{
            Funcionario deleteFuncionarioById = funcionarioRepo.findById(funcionarioId).orElseThrow(
                    () -> new IllegalArgumentException("Funcionário não encontrado"));
            funcionarioRepo.deleteById(funcionarioId);
            return deleteFuncionarioById;

        }catch (ApiException e){
            throw new ApiException("Funcionário não encontrado" + e.getMessage());
        }
    }

    @Override
    public Funcionario atualizarPorId(Long funcionarioId, Funcionario funcionarioAtt) {
        try{
            Funcionario funcionario = funcionarioRepo.findById(funcionarioId).orElseThrow(
                    () -> new IllegalArgumentException("Funcionário não encontrado"));

            funcionario.setNome(funcionarioAtt.getNome());
            funcionario.setCargo(funcionarioAtt.getCargo());
            funcionario.setDataDeContratacao(funcionarioAtt.getDataDeContratacao());
            funcionario.setEmail(funcionarioAtt.getEmail());
            funcionario.setEndereco(funcionarioAtt.getEndereco());
            funcionario.setTelefone(funcionarioAtt.getTelefone());
            return funcionarioRepo.save(funcionario);
        }catch (ApiException e){
            throw new ApiException("Funcionário não encontrado" + e.getMessage());
        }
    }
}

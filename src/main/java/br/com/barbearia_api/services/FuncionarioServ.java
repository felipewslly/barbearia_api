package br.com.barbearia_api.services;

import br.com.barbearia_api.converter.FuncionarioConverter;
import br.com.barbearia_api.dto.FuncionarioDTO;
import br.com.barbearia_api.exception.ApiException;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.repository.FuncionarioRepo;
import br.com.barbearia_api.services.servicesint.FuncionarioServices;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioServ implements FuncionarioServices {

    @Autowired
    private FuncionarioRepo funcionarioRepo;

    @Autowired
    private FuncionarioConverter funcionarioConverter;


    @Override
    @Transactional
    public Funcionario criarFuncionario(Funcionario funcionario) {
        try {

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

            return funcionarioRepo.save(func);

        } catch (DataIntegrityViolationException e) {

            throw new IllegalArgumentException("Funcionário já existe: " + e.getMessage());
        } catch (Exception e) {

            throw new RuntimeException("Erro ao criar funcionário: " + e.getMessage(), e);
        }
    }

    @Override
    public FuncionarioDTO funcionarioPorId(Long funcionarioId) {
        try{
            Funcionario funcionarioPorId = funcionarioRepo.findById(funcionarioId).orElseThrow(
                    () -> new IllegalArgumentException("Funcionário não encontrado"));
            return funcionarioConverter.employeeToDTO(funcionarioPorId);


        }catch (ApiException e){
            throw new ApiException("Funcionário não encontrado" + e.getMessage());
        }
    }

    @Override
    public List<Funcionario> todosFuncionarios() {
         return funcionarioRepo.findAll();
    }

    @Override
    @Transactional
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
    @Transactional
    public Funcionario atualizarPorId(Long funcionarioId, Funcionario funcionarioAtt) {

            Funcionario funcionario = funcionarioRepo.findById(funcionarioId).orElseThrow(
                    () -> new IllegalArgumentException("Funcionário não encontrado"));

            funcionario.setNome(funcionarioAtt.getNome());
            funcionario.setCargo(funcionarioAtt.getCargo());
            funcionario.setDataDeContratacao(funcionarioAtt.getDataDeContratacao());
            funcionario.setEmail(funcionarioAtt.getEmail());
            funcionario.setEndereco(funcionarioAtt.getEndereco());
            funcionario.setTelefone(funcionarioAtt.getTelefone());
            return funcionarioRepo.save(funcionario);

    }
}

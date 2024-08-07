package br.com.barbearia_api.services;
import br.com.barbearia_api.exception.ApiException;
import br.com.barbearia_api.model.Servico;
import br.com.barbearia_api.repository.ServicoRepo;
import br.com.barbearia_api.services.servicesint.ServicoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ServiceServ implements ServicoService {

    @Autowired
    private ServicoRepo servicoRepo;

    @Override
    public Servico registrarServico(Servico servico) {
        Servico servico1 = new Servico();
        servico1.setPreco(servico.getPreco());
        servico1.setCorte(servico.getCorte());
        servicoRepo.save(servico1);
        return servico1;
    }
    @Override
    public Servico atualizarServicoPorId(Long servicoId, Servico servicoAtt){
        Servico servicoById = servicoRepo.findById(servicoId).orElseThrow(
                () -> new ApiException("ID não encontrado"));
        servicoById.setCorte(servicoAtt.getCorte());
        servicoById.setPreco(servicoAtt.getPreco());
            return servicoRepo.save(servicoById);

    }
    @Override
    public List<Servico> todosServicos() {
        List<Servico> servico = servicoRepo.findAll();
            if (servico.isEmpty()){
                throw new RuntimeException("SERVICOS VAZIOS");
            }
            return servico;
    }

    @Override
    public void removerServicoPorId(Long id) {
        Optional<Servico> servico = servicoRepo.findById(id);
            if (servico.isPresent()){
                servicoRepo.deleteById(id);
            }else {
                throw new RuntimeException("ID NÃO ENCONTRADO");
            }
    }
    @Override
    public Optional<Servico> servicoPorId(Long id) {
        Optional<Servico> servicoById = servicoRepo.findById(id);
            if (servicoById.isPresent()){
                return servicoById;
            }else {
                throw new RuntimeException("ID NÃO EXISTENTE");
            }
    }
}


package br.com.barbearia_api.services;
import br.com.barbearia_api.model.entity.Servico;
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
    public Optional<Servico> atualizarServicoPorId(Long servicoId, Servico servicoAtt){
        Optional<Servico> newService = servicoRepo.findById(servicoId);
            if (newService.isPresent()){
                Servico servico = newService.get();
                servico.setPreco(servico.getPreco());
                servico.setCorte(servico.getCorte());
                servicoRepo.save(servico);
                return Optional.of(servico);
            }else {
                return Optional.empty();
            }
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


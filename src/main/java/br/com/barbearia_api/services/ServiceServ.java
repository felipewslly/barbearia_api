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
    public Servico serviceRegister(Servico servico) {
        Servico newService = new Servico();
        newService.setPreco(servico.getPreco());
        newService.setCorte(servico.getCorte());
        newService.setDescricao(servico.getDescricao());
        servicoRepo.save(newService);
        return newService;
    }
    @Override
    public Servico updateServiceById(Long servicoId, Servico newService){
        Servico updateService = servicoRepo.findById(servicoId).orElseThrow(
                () -> new ApiException("ID não encontrado"));
        updateService.setCorte(newService.getCorte());
        updateService.setPreco(newService.getPreco());
        updateService.setDescricao(newService.getDescricao());
            return servicoRepo.save(updateService);

    }
    @Override
    public List<Servico> allServices() {
        List<Servico> allService = servicoRepo.findAll();
            if (allService.isEmpty()){
                throw new ApiException("nenhum serviço registrado");
            }
            return allService;
    }

    @Override
    public void deleteServiceById(Long id) {
        Optional<Servico> servico = servicoRepo.findById(id);
            if (servico.isPresent()){
                servicoRepo.deleteById(id);
            }else {
                throw new RuntimeException("ID NÃO ENCONTRADO/EXISTENTE");
            }
    }
    @Override
    public Optional<Servico> serviceById(Long id) {
        Optional<Servico> servicoById = servicoRepo.findById(id);
            if (servicoById.isPresent()){
                return servicoById;
            }else {
                throw new RuntimeException("ID NÃO ENCONTRADO/EXISTENTE");
            }
    }
}


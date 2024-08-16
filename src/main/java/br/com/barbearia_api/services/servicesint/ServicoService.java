package br.com.barbearia_api.services.servicesint;
import br.com.barbearia_api.model.Servico;
import java.util.List;
import java.util.Optional;

public interface ServicoService {

    Servico serviceRegister(Servico servico);

    Servico updateServiceById(Long id, Servico servicoAtt);

    List<Servico> allServices();

    void deleteServiceById(Long servicoId);

    Optional<Servico> serviceById(Long id);





}

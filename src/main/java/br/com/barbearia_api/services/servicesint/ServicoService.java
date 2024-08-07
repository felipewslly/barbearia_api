package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.model.Servico;

import java.util.List;
import java.util.Optional;

public interface ServicoService {

    Servico registrarServico(Servico servico);

    Servico atualizarServicoPorId(Long id, Servico servicoAtt);

    List<Servico> todosServicos();

    void removerServicoPorId(Long servicoId);

    Optional<Servico> servicoPorId(Long id);





}

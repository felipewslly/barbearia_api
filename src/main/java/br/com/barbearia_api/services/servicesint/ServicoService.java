package br.com.barbearia_api.services.servicesint;

import br.com.barbearia_api.model.entity.Servico;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ServicoService {

    Servico registrarServico(String corte, String preco);

    Servico atualizarServicoPorId(Long servicoId, LocalDate novaData, LocalTime novaHora, String novoCorte, String novoPreco);

    List<Servico> todosServicos();

    List<Servico> removerServicoPorId(Long servicoId);






}

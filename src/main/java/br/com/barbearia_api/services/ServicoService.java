package br.com.barbearia_api.services;

import br.com.barbearia_api.model.entity.Cliente;
import br.com.barbearia_api.model.entity.Servico;
import com.fasterxml.jackson.annotation.OptBoolean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ServicoService {

    Servico registrarServico(LocalDate data, LocalTime hora, String corte, String preco);

    Servico atualizarServicoPorId(Long servicoId, LocalDate novaData, LocalTime novaHora, String novoCorte, String novoPreco);

    List<Servico> todosServicos();

    Optional<Servico> removerServicoPorId(Long servicoId);

    List<Servico> servicoPorData(LocalDate data);

    List<Servico> servicoPorHora(LocalTime hora);





}

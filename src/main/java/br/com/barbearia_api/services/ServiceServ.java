package br.com.barbearia_api.services;

import br.com.barbearia_api.model.entity.Servico;

import br.com.barbearia_api.repository.ServicoRepo;
import br.com.barbearia_api.services.servicesint.ServicoService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class ServiceServ implements ServicoService {


    private ServicoRepo servicoRepo;

    @Override
    public Servico registrarServico(String corte, String preco) {
        Servico servico = new Servico();
        servico.setPreco(servico.getPreco());
        servico.setCorte(servico.getCorte());

        servicoRepo.save(servico);
        return servico;

    }

    @Override
    public Servico atualizarServicoPorId(Long servicoId, LocalDate novaData, LocalTime novaHora, String novoCorte, String novoPreco) {
        Servico servico = servicoRepo.findById(servicoId).orElseThrow(
                () -> new RuntimeException("ID DO SERVIÇO NAO EXISTENTE"));

        servico.setCorte(servico.getCorte());
        servico.setPreco(servico.getPreco());

            return servico;

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
    public List<Servico> removerServicoPorId(Long servicoId) {
        servicoRepo.deleteById(servicoId);

        return servicoRepo.findAll();


    }
}


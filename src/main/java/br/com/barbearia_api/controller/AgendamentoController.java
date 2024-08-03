package br.com.barbearia_api.controller;

import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.model.entity.Agendamento;
import br.com.barbearia_api.services.AgendamentoServ;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/barbearia/agendamentos")
public class AgendamentoController{

    @Autowired
    private AgendamentoServ agendamentoServ;

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<AgendamentoDTO> createSchedule(@RequestBody AgendamentoDTO agendamentoDTO){
        AgendamentoDTO createSchedule = agendamentoServ.criarAgendamento(agendamentoDTO);
        return ResponseEntity.ok(createSchedule);
    }

    @GetMapping()
    ResponseEntity<List<AgendamentoDTO>> allSchedule(){
        List<AgendamentoDTO> allSchedule = agendamentoServ.todosAgendamentos();

        return ResponseEntity.ok(allSchedule);
    }

    @PutMapping("/{id}")
    ResponseEntity<Agendamento> updateScheduleById(@PathVariable Long id, @RequestBody Agendamento agendamentoAtt){
        Agendamento updateScheduleById = agendamentoServ.atualizarAgendamento(id, agendamentoAtt);

        return ResponseEntity.ok(updateScheduleById);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<List<Agendamento>> deleteScheduleById(@PathVariable Long id){
        List<Agendamento> deleteScheduleById = agendamentoServ.removerAgendamentoPorId(id);
        if (deleteScheduleById == null || deleteScheduleById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deleteScheduleById);
        }
        return ResponseEntity.ok(deleteScheduleById);
    }
    @GetMapping("/data")
    ResponseEntity<List<Agendamento>> findScheduleByDate(LocalDate data){
        List<Agendamento> findScheduleByDate = agendamentoServ.agendamentoPorData(data);
        if (findScheduleByDate == null || findScheduleByDate.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findScheduleByDate);
        }
        return ResponseEntity.ok(findScheduleByDate);
    }

    @GetMapping("/clientes/{id}")
    ResponseEntity<List<Agendamento>> findByClientId(@PathVariable Long id){
        List<Agendamento> findByClientId = agendamentoServ.agendamentoPorCliente(id);
        if (findByClientId == null || findByClientId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findByClientId);
        }
        return ResponseEntity.ok(findByClientId);
    }

    @GetMapping("/servicos/{id}")
    ResponseEntity<List<Agendamento>> findByServiceId(@PathVariable Long id){
        List<Agendamento> findByServiceId = agendamentoServ.agendamentoPorServicos(id);

        if (findByServiceId == null || findByServiceId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findByServiceId);
        }
        return ResponseEntity.ok(findByServiceId);


    }


}
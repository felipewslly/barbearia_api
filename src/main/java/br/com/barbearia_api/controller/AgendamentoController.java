package br.com.barbearia_api.controller;

import br.com.barbearia_api.converter.AgendamentoConverter;
import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.services.AgendamentoServ;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/barbearia/agendamentos")
public class AgendamentoController{

    @Autowired
    private AgendamentoServ agendamentoServ;

    @Autowired
    private AgendamentoConverter agendamentoConverter;

    @PostMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<AgendamentoDTO> createSchedule(@RequestBody AgendamentoDTO agendamentoDTO){
        AgendamentoDTO createSchedule = agendamentoServ.criarAgendamento(agendamentoDTO);
        return ResponseEntity.ok(createSchedule);
    }
    @GetMapping("{id}")
    ResponseEntity<AgendamentoDTO> scheduleById(@PathVariable Long id){
        AgendamentoDTO scheduleById = agendamentoServ.agendamentoPorId(id);
            return ResponseEntity.ok(scheduleById);

    }

    @GetMapping()
    ResponseEntity<List<AgendamentoDTO>> allSchedule(){
        List<AgendamentoDTO> allSchedule = agendamentoServ.todosAgendamentos();

        return ResponseEntity.ok(allSchedule);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    ResponseEntity<Agendamento> updateScheduleById(@PathVariable Long id, @RequestBody Agendamento agendamentoAtt){
        Agendamento updateScheduleById = agendamentoServ.atualizarAgendamento(id, agendamentoAtt);

        return ResponseEntity.ok(updateScheduleById);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScheduleById(@PathVariable Long id){
        try{
            agendamentoServ.removerAgendamentoPorId(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/data")
    ResponseEntity<List<AgendamentoDTO>> findScheduleByDate(LocalDate data){
        List<AgendamentoDTO> findScheduleByDate = agendamentoServ.agendamentoPorData(data);
        if (findScheduleByDate == null || findScheduleByDate.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findScheduleByDate);
        }
        return ResponseEntity.ok(findScheduleByDate);
    }

    @GetMapping("/clientes/{id}")
    ResponseEntity<List<AgendamentoDTO>> findByClientId(@PathVariable Long id){
        List<AgendamentoDTO> findByClientId = agendamentoServ.agendamentoPorCliente(id);
        if (findByClientId == null || findByClientId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findByClientId);
        }
        return ResponseEntity.ok(findByClientId);
    }

    @GetMapping("/servicos/{id}")
    ResponseEntity<List<AgendamentoDTO>> findByServiceId(@PathVariable Long id){
        List<AgendamentoDTO> findByServiceId = agendamentoServ.agendamentoPorServicos(id);

        if (findByServiceId == null || findByServiceId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(findByServiceId);
        }
        return ResponseEntity.ok(findByServiceId);


    }


}
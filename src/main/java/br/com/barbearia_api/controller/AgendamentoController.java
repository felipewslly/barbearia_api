package br.com.barbearia_api.controller;

import br.com.barbearia_api.converter.AgendamentoConverter;
import br.com.barbearia_api.dto.AgendamentoDTO;
import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.model.Clientes;
import br.com.barbearia_api.model.Funcionario;
import br.com.barbearia_api.model.Servico;
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

    @PostMapping()
    ResponseEntity<Agendamento> createSchedule(@RequestBody Agendamento agendamento){
        Agendamento createSchedule = agendamentoServ.criarAgendamento(agendamento);
        return ResponseEntity.ok(createSchedule);
    }
    @GetMapping("/{id}")
    ResponseEntity<Agendamento> scheduleById(@PathVariable Long id){
        Agendamento scheduleById = agendamentoServ.agendamentoPorId(id);
            return ResponseEntity.ok(scheduleById);

    }

    @GetMapping()
    ResponseEntity<List<Agendamento>> allSchedule(){
        List<Agendamento> allSchedule = agendamentoServ.todosAgendamentos();

        return ResponseEntity.ok(allSchedule);
    }

    @PutMapping("/{id}")
    ResponseEntity<Agendamento> updateScheduleById(@PathVariable("id") Long id, @RequestBody Agendamento agendamentoAtualizado){
        Agendamento agendamento  = agendamentoServ.atualizarAgendamento(id, agendamentoAtualizado);
            return ResponseEntity.ok(agendamento);
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

}
package br.com.barbearia_api.controller;
import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.services.AgendamentoServ;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/barbearia/agendamentos")
public class AgendamentoController{

    @Autowired
    private AgendamentoServ agendamentoServ;


    @PostMapping()
    ResponseEntity<Agendamento> createSchedule(@RequestBody Agendamento agendamento){
        Agendamento createSchedule = agendamentoServ.createSchedule(agendamento);
        return ResponseEntity.ok(createSchedule);
    }
    @GetMapping("/{id}")
    ResponseEntity<Agendamento> scheduleById(@PathVariable Long id){
        Agendamento scheduleById = agendamentoServ.scheduleById(id);
            return ResponseEntity.ok(scheduleById);

    }

    @GetMapping()
    ResponseEntity<List<Agendamento>> allSchedule(){
        List<Agendamento> allSchedule = agendamentoServ.allSchedule();

        return ResponseEntity.ok(allSchedule);
    }

    @PutMapping("/{id}")
    ResponseEntity<Agendamento> updateScheduleById(@PathVariable("id") Long id, @RequestBody Agendamento agendamentoAtualizado){
        Agendamento agendamento  = agendamentoServ.updateSchedule(id, agendamentoAtualizado);
            return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScheduleById(@PathVariable Long id){
        try{
            agendamentoServ.deleteScheduleById(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
package br.com.barbearia_api.controller;

import br.com.barbearia_api.model.entity.Servico;
import br.com.barbearia_api.services.ServiceServ;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("barbearia/servicos")
public class ServiceController extends ServiceServ {

    @Autowired
    private ServiceServ serviceServ;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Servico> createService(@RequestBody Servico servico){
        Servico servico1 = serviceServ.registrarServico(servico);
        return ResponseEntity.ok(servico1);
    }
    @GetMapping()
    public ResponseEntity<List<Servico>>allServices(){
        List<Servico> servicoList = serviceServ.todosServicos();
        return ResponseEntity.ok(servicoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Servico>> getServicoById(@PathVariable Long id){
        Optional<Servico> servicoById = serviceServ.servicoPorId(id);
        return ResponseEntity.ok(servicoById);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceById(@PathVariable Long id){
        try{
            serviceServ.removerServicoPorId(id);
            return ResponseEntity.ok("Serviço removido com sucesso");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PutMapping("{id}")
    public ResponseEntity<Servico> updateServiceById(@PathVariable Long id, @RequestBody Servico newServico){
        Optional<Servico> servicoAtt = serviceServ.atualizarServicoPorId(id, newServico);
            if (servicoAtt.isPresent()){
                return ResponseEntity.ok(servicoAtt.get());
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }
}

package br.com.barbearia_api.repository;


import br.com.barbearia_api.model.Agendamento;
import br.com.barbearia_api.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepo extends JpaRepository<Clientes, Long> {

    @Query("SELECT c FROM Clientes c WHERE c.cpf = :cpf")
    Clientes findClientByCpf(@Param("cpf") String cpf);

    @Query("SELECT c FROM Clientes c WHERE c.cpf = :cpf")
    Clientes findByCpf(String cpf);
}

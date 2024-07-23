package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente, Long> {


}

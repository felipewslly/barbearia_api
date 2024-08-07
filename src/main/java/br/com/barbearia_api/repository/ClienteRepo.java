package br.com.barbearia_api.repository;


import br.com.barbearia_api.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Clientes, Long> {


}

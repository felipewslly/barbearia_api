package br.com.barbearia_api.repository;

import br.com.barbearia_api.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
}

package io.github.pedrohenrif.msclientes.infra.repository;

import java.util.Optional;
import io.github.pedrohenrif.msclientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);

}

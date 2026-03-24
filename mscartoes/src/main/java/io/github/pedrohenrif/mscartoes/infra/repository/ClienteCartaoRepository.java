package io.github.pedrohenrif.mscartoes.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.pedrohenrif.mscartoes.domain.ClienteCartao;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long>{
    List<ClienteCartao> findByCpf(String cpf);
}

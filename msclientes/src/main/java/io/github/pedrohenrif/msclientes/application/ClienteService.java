package io.github.pedrohenrif.msclientes.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.pedrohenrif.msclientes.domain.Cliente;
import io.github.pedrohenrif.msclientes.infra.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}
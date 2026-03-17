package io.github.pedrohenrif.msclientes.application;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.pedrohenrif.msclientes.application.representation.ClienteSaveRequest;
import io.github.pedrohenrif.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService service;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toModel();
        service.save(cliente);
        
        URI headerLocation = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .query("cpf={cpf}")
            .buildAndExpand(cliente.getCpf())
            .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity <Cliente> dadosCliente(@RequestParam String cpf) {
        var cliente = service.getByCPF(cpf);

        if(cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente.get());
    }
}

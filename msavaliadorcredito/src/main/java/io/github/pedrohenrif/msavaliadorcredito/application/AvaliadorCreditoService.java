package io.github.pedrohenrif.msavaliadorcredito.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.Feign;
import feign.FeignException;
import feign.Response;
import io.github.pedrohenrif.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.pedrohenrif.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import io.github.pedrohenrif.msavaliadorcredito.domain.model.CartaoCliente;
import io.github.pedrohenrif.msavaliadorcredito.domain.model.DadosCliente;
import io.github.pedrohenrif.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.pedrohenrif.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.pedrohenrif.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clientesClient;
    private final CartoesResourceClient cartoesClient;
    
    public SituacaoCliente obterSituacaoCliente(String cpf) 
        throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        
        try{
            ResponseEntity<DadosCliente> dadosClienteResponse = clientesClient.dadosCliente(cpf); 
            ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

            return SituacaoCliente.builder()
                .cliente(dadosClienteResponse.getBody())
                .cartoes(cartoesResponse.getBody())
                .build();

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DadosClienteNotFoundException();
            }

            throw new  ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}   

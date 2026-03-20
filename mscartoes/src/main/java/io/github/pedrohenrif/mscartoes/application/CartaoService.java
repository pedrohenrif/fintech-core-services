package io.github.pedrohenrif.mscartoes.application;

import io.github.pedrohenrif.mscartoes.domain.Cartao;
import io.github.pedrohenrif.mscartoes.infra.repository.CartaoRepository;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository repository;

    @Transactional
    public Cartao save(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
        var rendaBigDecimal = BigDecimal.valueOf(renda);

        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }
}

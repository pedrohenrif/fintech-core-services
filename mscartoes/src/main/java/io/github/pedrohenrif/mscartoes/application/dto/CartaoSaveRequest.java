package io.github.pedrohenrif.mscartoes.application.dto;

import java.math.BigDecimal;

import io.github.pedrohenrif.mscartoes.domain.BandeiraCartao;
import io.github.pedrohenrif.mscartoes.domain.Cartao;
import lombok.Data;


@Data
public class CartaoSaveRequest {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel() {
        return new Cartao(nome, bandeira, renda, limite);
    }
}

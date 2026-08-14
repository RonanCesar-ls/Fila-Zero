package Ronan.filazero.dto;

import Ronan.filazero.domain.Pedido;
import Ronan.filazero.domain.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponseDTO(
        Long id,
        String nomeCliente,
        StatusPedido status,
        BigDecimal valorTotal,
        LocalDateTime dataCriacao
){
    //Construtor pra converter a entidade pedido para DTO de maneira mais simples
    public PedidoResponseDTO(Pedido pedido){
        this(
                pedido.getId(),
                pedido.getNomeCliente(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getDataCriacao()
        );
    }
}

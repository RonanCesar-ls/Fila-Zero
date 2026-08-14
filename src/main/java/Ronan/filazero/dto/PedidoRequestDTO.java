package Ronan.filazero.dto;

import java.math.BigDecimal;

public record PedidoRequestDTO(
        String nomeCliente,
        BigDecimal valorTotal
){}

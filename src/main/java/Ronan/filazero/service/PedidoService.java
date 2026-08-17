package Ronan.filazero.service;


import Ronan.filazero.domain.Pedido;
import Ronan.filazero.domain.StatusPedido;
import Ronan.filazero.dto.PedidoRequestDTO;
import Ronan.filazero.dto.PedidoResponseDTO;
import Ronan.filazero.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Pedido pedido =  new Pedido();
        pedido.setNomeCliente(dto.nomeCliente());
        pedido.setValorTotal(dto.valorTotal());

        Pedido salvo = repository.save(pedido);
        return new PedidoResponseDTO(salvo);
    }

    public List<PedidoResponseDTO> listarTodos(){
        return repository.findAll().stream().map(PedidoResponseDTO::new).toList();
    }

    public PedidoResponseDTO atualizarStatus(Long id, StatusPedido novoStatus){
        Pedido pedido = repository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatus(novoStatus);
        Pedido atualizado = repository.save(pedido);

        return new PedidoResponseDTO(atualizado);
    }
}

package Ronan.filazero.controller;


import Ronan.filazero.domain.StatusPedido;
import Ronan.filazero.dto.PedidoRequestDTO;
import Ronan.filazero.dto.PedidoResponseDTO;
import Ronan.filazero.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criar(@RequestBody PedidoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPedido(dto));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido status) {
        return ResponseEntity.ok(service.atualizarStatus(id, status));
    }
}

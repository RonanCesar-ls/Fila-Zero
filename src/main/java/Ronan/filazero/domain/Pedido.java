package Ronan.filazero.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor // E um lombok que cria construtoror vazio, exigência do JPA
@AllArgsConstructor // E um lombok que cria construtoror com todos os atributos
public class Pedido {

    @Id // diz ao JPA que o atributo id é a chave primária da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // aqui diz ao JPA que o valor do atributo id será gerado automaticamente pelo banco de dados
    private Long id; // atributo que representa o ID da identidade

    @Column(nullable = false)
    private String nomeCliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void onCreate(){
        this.dataCriacao = LocalDateTime.now();
        if(this.status == null) {
            this.status =  StatusPedido.AGUARDANDO_PREPARO;
        }
    }
}

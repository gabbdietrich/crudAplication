package com.example.crudAplication.domain.product;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@AllArgsConstructor //cria os contrutores
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name; //não esquece, aqui é os nomes da colunas

    private Integer prince_in_cents;

    private Boolean active;

    public Product(RequestProduct requestProduct) {
        this.name = requestProduct.name();
        this.prince_in_cents = requestProduct.prince_in_cents();
        this.active = true;
    }


}

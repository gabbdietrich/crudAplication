package com.example.crudAplication.controllers;

import com.example.crudAplication.domain.product.Product;
import com.example.crudAplication.domain.product.ProductRepository;
import com.example.crudAplication.domain.product.RequestProduct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        var allProducts = repository.findAllByActiveTrue(); // retornando tudo que tem dentro da tabela
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data) {
        Product newProduct = new Product(data);
        repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @PutMapping //atualiza um dado
    @Transactional
    //  é utilizado para indicar que é uma transação no banco de dados, tem q ser iniciado e finalizado em conjunto!
    public ResponseEntity updateProduct(@RequestBody @Valid RequestProduct data) {
        Optional<Product> optionalProduct = repository.findById(data.id()); //a classe optinoal é pra não ficar null.
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(data.name());
            product.setPrince_in_cents(data.prince_in_cents());
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletProduct(@PathVariable String id) {
        Optional<Product> optionalProduct = repository.findById(id); //a classe optinoal é pra não ficar null.
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setActive(false);
            return ResponseEntity.noContent().build(); // linha 50 até 56 é apenas pra desativar o produto visualmente, ele vai continuar meu db
        } else {
            throw new EntityNotFoundException();
        }
    }

}

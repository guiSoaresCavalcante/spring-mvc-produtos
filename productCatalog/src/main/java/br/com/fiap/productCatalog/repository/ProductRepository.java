package br.com.fiap.productCatalog.repository;

import br.com.fiap.productCatalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package io.changsoft.fleet_management_mis.repos;

import io.changsoft.fleet_management_mis.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}

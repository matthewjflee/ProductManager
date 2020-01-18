package ca.fatt.productmanager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //Don't need to write any code as it uses all the methods from JpaRepository
}

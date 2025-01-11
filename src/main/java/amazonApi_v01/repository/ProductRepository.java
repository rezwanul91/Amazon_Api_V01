package amazonApi_v01.repository;

import amazonApi_v01.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);
   List<Product> findByCategoryId(Integer categoryId);
   Boolean existsByName(String name);
}

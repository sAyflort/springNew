package ru.shikhov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.shikhov.model.Product;

import java.util.List;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query(value = """
            select * from products p
            where ((:minPriceFilter is null or p.price >= :minPriceFilter) and (:maxPriceFilter is null or p.price <= :maxPriceFilter))
            """, nativeQuery = true)
    List<Product> productByFilter(Double minPriceFilter, Double maxPriceFilter);
}

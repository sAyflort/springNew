package ru.shikhov.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.shikhov.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product> {

    @Query(value = """
            select * from products p
            where ((:minPriceFilter is null or p.price >= :minPriceFilter) and (:maxPriceFilter is null or p.price <= :maxPriceFilter))
            """,
            countQuery =
            """
            select count(*) from products p
            where ((:minPriceFilter is null or p.price >= :minPriceFilter) and (:maxPriceFilter is null or p.price <= :maxPriceFilter))
            """,
            nativeQuery = true)
    Page<Product> productByFilter(Double minPriceFilter, Double maxPriceFilter, Pageable pageable);
}

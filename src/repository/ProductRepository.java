package repository;

import domain.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
    void addProduct(Product product);
    Product getProductById(int id);
}

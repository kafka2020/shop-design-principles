package repository;

import domain.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public Product getProductById(int id) {
        if (id < 1 || id > products.size()) {
            return null;
        }
        return products.get(id - 1);
    }
}

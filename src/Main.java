import repository.InMemoryProductRepository;
import repository.ProductRepository;
import ui.Shop;

public class Main {
    public static void main(String[] args) {
        ProductRepository repository = new InMemoryProductRepository();
        Shop shop = new Shop(repository);
        shop.run();
    }
}

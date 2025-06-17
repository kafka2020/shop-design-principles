package ui;

import domain.Product;
import java.util.List;

public class ProductPrinter {
    public static void print(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Товары отсутствуют");
            return;
        }

        System.out.println("\n   ДОСТУПНЫЕ ТОВАРЫ");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
    }
}

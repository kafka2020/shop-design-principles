package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();
    private static int nextOrderId = 1;

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public Order checkout() {
        Order order = new Order(nextOrderId++, new ArrayList<>(items));
        items.clear();
        return order;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void print() {
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
            return;
        }

        System.out.println("\n   ВАША КОРЗИНА");
        for (int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
            System.out.printf("%d. %s x%d = %.2f руб.\n",
                    i + 1,
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getTotalPrice());
        }
        System.out.printf("ИТОГО: %.2f руб.\n", calculateTotal());
    }
}
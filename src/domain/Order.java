package domain;

import java.util.List;

public class Order {
    private final int id;
    private final List<CartItem> items;

    public Order(int id, List<CartItem> items) {
        this.id = id;
        this.items = items;
    }

    public int getId() {
        return id;
    }
    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    @Override
    public String toString() {
        return "Заказ #" + id + " (на сумму " + getTotalPrice() + " руб.)";
    }
}

package ui;

import constants.ProductPrices;
import domain.*;
import enums.MainMenuAction;
import repository.ProductRepository;
import services.delivery.DeliveryService;
import services.delivery.ExpressDeliveryService;
import services.delivery.StandardDeliveryService;

import java.util.Scanner;

public class Shop {
    private final ProductRepository repository;
    private final ShoppingCart cart;
    private final Scanner scanner;

    public Shop(ProductRepository repository) {
        this.repository = repository;
        this.cart = new ShoppingCart();
        this.scanner = new Scanner(System.in);
        initializeProducts();
    }

    private void initializeProducts() {
        repository.addProduct(new Product("Молоко", ProductPrices.MILK));
        repository.addProduct(new Product("Хлеб", ProductPrices.BREAD));
        repository.addProduct(new Product("Яйца", ProductPrices.EGGS));
        repository.addProduct(new Product("Масло", ProductPrices.BUTTER));
        repository.addProduct(new Product("Кофе", ProductPrices.COFFEE));
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число!");
            }
        }
    }

    public void run() {
        System.out.println("Добро пожаловать в магазин!");

        while (true) {
            MenuPrinter.printMainMenu();

            try {
                int choice = readInt("Выберите действие: ");
                MainMenuAction action = MainMenuAction.fromCode(choice);

                switch (action) {
                    case SHOW_PRODUCTS -> showProducts();
                    case ADD_TO_CART -> addToCart();
                    case REMOVE_FROM_CART -> removeFromCart();
                    case VIEW_CART -> viewCart();
                    case CHECKOUT -> checkout();
                    case EXIT -> {
                        System.out.println("До свидания!");
                        return;
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private void showProducts() {
        ProductPrinter.print(repository.getAllProducts());
    }

    private void addToCart() {
        showProducts();
        if (repository.getAllProducts().isEmpty()) return;

        int productId = readInt("Выберите товар (номер): ");
        int quantity = readInt("Количество: ");

        Product product = repository.getProductById(productId);
        if (product == null) {
            System.out.println("Неверный номер товара!");
            return;
        }

        cart.addItem(product, quantity);
        System.out.printf("Добавлено: %s x%d\n", product.getName(), quantity);
    }

    private void removeFromCart() {
        if (cart.isEmpty()) {
            System.out.println("Корзина пуста!");
            return;
        }

        cart.print();
        int itemNumber = readInt("Выберите номер товара для удаления: ") - 1;

        try {
            Product product = cart.getItems().get(itemNumber).getProduct();
            cart.removeItem(product);
            System.out.println("Товар удален из корзины");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Неверный номер товара!");
        }
    }

    private void viewCart() {
        cart.print();
    }

    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Корзина пуста!");
            return;
        }

        cart.print();
        System.out.print("\nПодтвердить оформление заказа? (да/нет): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("да")) {
            System.out.println("Оформление отменено");
            return;
        }

        MenuPrinter.printDeliveryMenu();
        int deliveryChoice = readInt("Выберите тип доставки: ");

        DeliveryService deliveryService = switch (deliveryChoice) {
            case 1 -> new StandardDeliveryService();
            case 2 -> new ExpressDeliveryService();
            default -> {
                System.out.println("Неверный выбор, используется стандартная доставка");
                yield new StandardDeliveryService();
            }
        };

        Order order = cart.checkout();
        System.out.println("Оформлен заказ #" + order.getId());
        deliveryService.deliver(order);
    }
}

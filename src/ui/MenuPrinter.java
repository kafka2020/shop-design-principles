package ui;

public class MenuPrinter {
    public static void printMainMenu() {
        System.out.println("""
            \n   ГЛАВНОЕ МЕНЮ
            1. Показать все товары
            2. Добавить товар в корзину
            3. Удалить товар из корзины
            4. Просмотреть корзину
            5. Оформить заказ
            6. Выйти""");
    }

    public static void printDeliveryMenu() {
        System.out.println("\n   ВЫБОР ДОСТАВКИ");
        System.out.println("1. Стандартная доставка (3-5 дней)");
        System.out.println("2. Экспресс-доставка (24 часа)");
        System.out.print("Выберите тип доставки: ");
    }
}

package enums;

public enum MainMenuAction {
    SHOW_PRODUCTS(1),
    ADD_TO_CART(2),
    REMOVE_FROM_CART(3),  // Новый пункт меню
    VIEW_CART(4),
    CHECKOUT(5),
    EXIT(6);

    private final int code;

    MainMenuAction(int code) {
        this.code = code;
    }

    public static MainMenuAction fromCode(int code) {
        for (MainMenuAction action : values()) {
            if (action.code == code) {
                return action;
            }
        }
        throw new IllegalArgumentException("Неизвестный код действия: " + code);
    }
}

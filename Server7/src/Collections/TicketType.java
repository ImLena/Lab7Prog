package Collections;

import java.io.Serializable;

/**
 * Класс для храниения типов билетов
 */

public enum TicketType implements Serializable {
    VIP,
    USUAL,
    BUDGETARY,
    CHEAP;
}

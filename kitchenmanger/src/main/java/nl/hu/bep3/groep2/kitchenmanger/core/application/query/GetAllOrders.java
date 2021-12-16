package nl.hu.bep3.groep2.kitchenmanger.core.application.query;

import java.util.UUID;

public class GetAllOrders {
    private final String orderBy;
    private final String direction;

    public GetAllOrders(String orderBy, String direction) {
        if (orderBy == null) {
            orderBy = "id";
        }

        if (direction == null) {
            direction = "asc";
        }

        this.orderBy = orderBy;
        this.direction = direction;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }
}

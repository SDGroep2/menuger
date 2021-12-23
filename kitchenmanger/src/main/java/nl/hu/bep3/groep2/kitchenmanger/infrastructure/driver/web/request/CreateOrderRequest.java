package nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web.request;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.Status;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

public class CreateOrderRequest {
    @NotBlank
    public int table;
    @NotBlank
    public HashMap<String, Integer> meals;
    @NotBlank
    public Status status;
}

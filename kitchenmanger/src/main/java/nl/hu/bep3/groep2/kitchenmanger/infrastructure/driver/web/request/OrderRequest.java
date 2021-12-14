package nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;

public class OrderRequest {
    @NotBlank
    public String order;
}

package nl.hu.bep3.groep2.kitchenmanger.infrastructure.driver.web.request;

import nl.hu.bep3.groep2.kitchenmanger.core.domain.Status;

import javax.validation.constraints.NotBlank;

public class ChangeOrderStatusRequest {
    @NotBlank
    public Status status;
}

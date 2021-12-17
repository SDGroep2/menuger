package nl.hu.bep3.groep2.menumanger.core.port.storage;

import nl.hu.bep3.groep2.menumanger.core.application.command.CreateOrder;

public interface KitchenRepository {
	CreateOrder save(CreateOrder createOrder);
}

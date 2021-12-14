package nl.hu.bep3.groep2.kitchenmanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.KitchenEvent;
import nl.hu.bep3.groep2.kitchenmanger.core.domain.event.KitchenFinishedOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document
@Getter
@Setter
public class Kitchen {
	@Id
	private UUID id;
	private String name;

	@Indexed
	private Set<String> orders;

	@Transient
	private List<KitchenEvent> events = new ArrayList<>();

	public Kitchen(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}

	public void FinishOrder(String order) {
		this.orders.remove(order);
		this.events.add(new KitchenFinishedOrder(id, order));
	}

	public List<KitchenEvent> listEvents() {
		return events;
	}

	public void clearEvents() {
		this.events.clear();
	}
}

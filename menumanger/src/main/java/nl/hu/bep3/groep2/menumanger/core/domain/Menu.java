package nl.hu.bep3.groep2.menumanger.core.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@Setter
public class Menu {
	@Id
	private UUID id;
	private String name;

	public Menu(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}
}

package nl.hu.bep3.groep2.kitchenmanger.core.application.query;

public class GetKitchenByName {
	private final String name;

	public GetKitchenByName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

package nl.hu.bep3.groep2.inventorymanger.core.application.query;

public class GetIngredientsByName {

    private final String name;

    public GetIngredientsByName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

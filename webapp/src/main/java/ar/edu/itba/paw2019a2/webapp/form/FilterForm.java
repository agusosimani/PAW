package ar.edu.itba.paw2019a2.webapp.form;

import ar.edu.itba.paw2019a2.model.Enum.Order;

import java.util.List;

public class FilterForm {

    private Order order = Order.New;

    private List<String> tags;

    private boolean withMyIngredients = true;

    public boolean getWithMyIngredients() {
        return withMyIngredients;
    }

    public void setWithMyIngredients(boolean withMyIngredients) {
        this.withMyIngredients = withMyIngredients;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

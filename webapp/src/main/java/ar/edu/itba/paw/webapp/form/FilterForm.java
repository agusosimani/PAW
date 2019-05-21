package ar.edu.itba.paw.webapp.form;

import ar.edu.itba.paw.model.Enum.Order;

import java.util.List;

public class FilterForm {

    private Order order;

    private List<String> tags;

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

package org.glydar.api.model.entity.component;

public class NameComponent implements Component {

    private String name;

    public NameComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

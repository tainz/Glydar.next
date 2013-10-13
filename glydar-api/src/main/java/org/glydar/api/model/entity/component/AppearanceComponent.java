package org.glydar.api.model.entity.component;

import org.glydar.api.model.entity.Appearance;

public class AppearanceComponent implements Component {

    private Appearance appearance;

    public AppearanceComponent(Appearance appearance) {
        this.appearance = appearance;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }
}

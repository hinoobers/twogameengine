package org.hinoob.tge;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    private final List<GameObject> renderers = new ArrayList<>();

    public void addRenderer(GameObject renderer) {
        renderer.environment = this;
        renderers.add(renderer);
    }

    public List<GameObject> getRenderers() {
        return renderers;
    }

    public List<GameObject> getRenderers(Class<?> type) {
        return renderers.stream().filter(type::isInstance).toList();
    }
}

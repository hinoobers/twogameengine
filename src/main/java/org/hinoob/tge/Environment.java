package org.hinoob.tge;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    private final List<Renderer> renderers = new ArrayList<>();

    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
    }

    public List<Renderer> getRenderers() {
        return renderers;
    }
}

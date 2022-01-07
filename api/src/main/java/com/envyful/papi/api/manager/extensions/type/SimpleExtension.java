package com.envyful.papi.api.manager.extensions.type;

import com.envyful.papi.api.manager.extensions.AbstractExtension;

import java.util.List;

public abstract class SimpleExtension<T> extends AbstractExtension<T> {

    public SimpleExtension(String name, int priority, List<String> description, List<String> examples) {
        super(name, priority, description, examples);
    }

    @Override
    public boolean matches(T player, String placeholder) {
        return placeholder.equalsIgnoreCase(this.getName());
    }
}

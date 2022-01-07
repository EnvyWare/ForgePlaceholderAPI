package com.envyful.papi.api.manager.extensions;

import java.util.List;

public abstract class AbstractExtension<T> implements PlaceholderExtension<T> {

    private final String name;
    private final int priority;
    private final List<String> description;
    private final List<String> examples;

    public AbstractExtension(String name, int priority, List<String> description, List<String> examples) {
        this.name = name;
        this.priority = priority;
        this.description = description;
        this.examples = examples;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public List<String> getDescription() {
        return this.description;
    }

    @Override
    public List<String> getExamples() {
        return this.examples;
    }
}

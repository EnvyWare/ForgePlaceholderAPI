package com.envyful.papi.api.manager;

import com.envyful.papi.api.PlaceholderManager;
import com.envyful.papi.api.manager.extensions.PlaceholderExtension;
import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Basic, platform abstract implementation of a placeholder manager using {@link PlaceholderExtension}s for each
 * registered placeholder
 *
 * @param <T> The player class
 */
public class AbstractPlaceholderManager<T> implements PlaceholderManager<T> {

    private static final Pattern PATTERN = Pattern.compile("%([a-zA-Z0-9]+)_([a-zA-Z0-9-_]+)%");

    private final String identifier;
    private final String[] authors;
    private final String version;
    private final String name;
    private final String surroundingChar;
    private final Pattern pattern;
    private final Class<T> clazz;
    private final List<PlaceholderExtension<T>> extensions = Lists.newArrayList();

    public AbstractPlaceholderManager(String identifier, String[] authors, String version, String name,
                                      String surroundingChar, Class<T> clazz) {
        this.identifier = identifier;
        this.authors = authors;
        this.version = version;
        this.name = name;
        this.surroundingChar = surroundingChar;
        this.clazz = clazz;
        this.pattern =
                Pattern.compile(this.surroundingChar + "(" + this.identifier + "_([a-zA-Z0-9_.&|+\\-#]+))" + this.surroundingChar);
    }

    public AbstractPlaceholderManager(String identifier, String[] authors, String version, String name, Class<T> clazz) {
        this(identifier, authors, version, name, "%", clazz);
    }

    @Override
    public List<String> getAdminDescription() {
        List<String> info = Lists.newArrayList();

        info.add(" ");
        info.add(this.name);
        info.add(" ");

        for (PlaceholderExtension<T> extension : this.extensions) {
            info.add(" * " + String.join(", ", extension.getExamples()) + " - " + String.join(", ", extension.getDescription()));
        }

        return info;
    }

    @Override
    public List<String> getDescription() {
        List<String> info = Lists.newArrayList(
                "§e§l" + this.name + " Placeholder Extension",
                " ",
                "§eAuthors: §b" + String.join("§e,§b", this.authors),
                "§eVersion: §b" + this.version,
                "§ePlaceholders: ",
                " "
        );

        for (PlaceholderExtension<T> extension : this.extensions) {
            info.add("§b§l" + extension.getName());
            info.add("  §eDescription:");

            for (String s : extension.getDescription()) {
                info.add("  §b" + s);
            }

            info.add("  §eExamples:");

            for (String s : extension.getExamples()) {
                info.add("  §b" + s);
            }
        }

        return info;
    }

    @Override
    public String onPlaceholderRequest(Object o, String placeholder) {
        if (this.clazz.isAssignableFrom(o.getClass())) {
            return this.onPlaceholderRequested((T) o, placeholder);
        }

        boolean modified = false;
        Matcher globalMatcher = PATTERN.matcher(placeholder);

        while (globalMatcher.find()) {
            String replaced = globalMatcher.group();
            Matcher internal = PATTERN.matcher(replaced);

            if (!internal.find()) {
                continue;
            }

            Matcher matcher = this.pattern.matcher(replaced);

            if (!matcher.matches()) {
                continue;
            }

            String data = matcher.group(2);
            String fullPlaceholder = matcher.group();

            for (PlaceholderExtension<T> extension : this.extensions) {
                if (extension.matchesObject(o, data)) {
                    String newData = extension.parseObject(o, data);

                    if (newData != null) {
                        modified = true;
                        placeholder = placeholder.replace(fullPlaceholder, newData);
                    }
                }
            }
        }

        if (modified) {
            return placeholder;
        }

        return null;
    }

    private String onPlaceholderRequested(T player, String placeholder) {
        boolean modified = false;
        Matcher globalMatcher = PATTERN.matcher(placeholder);

        while (globalMatcher.find()) {
            String replaced = globalMatcher.group();
            Matcher internal = PATTERN.matcher(replaced);

            if (!internal.find()) {
                continue;
            }

            Matcher matcher = this.pattern.matcher(replaced);

            if (!matcher.matches()) {
                continue;
            }

            String data = matcher.group(2);
            String fullPlaceholder = matcher.group();

            for (PlaceholderExtension<T> extension : this.extensions) {
                if (extension.matches(player, data)) {
                    String newData = extension.parse(player, data);

                    if (newData != null) {
                        modified = true;
                        placeholder = placeholder.replace(fullPlaceholder, newData);
                    }
                }
            }
        }

        if (modified) {
            return placeholder;
        }

        return null;
    }

    @Override
    public <A extends PlaceholderExtension<T>> void registerPlaceholder(A extension) {
        this.extensions.add(extension);
        this.extensions.sort(Comparator.comparing(o -> ((PlaceholderExtension<?>) o).getPriority()).reversed());
    }
}

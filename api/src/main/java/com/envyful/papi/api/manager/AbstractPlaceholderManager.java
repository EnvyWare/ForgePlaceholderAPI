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

    private final String identifier;
    private final String[] authors;
    private final String version;
    private final String name;
    private final String surroundingChar;
    private final Pattern pattern;
    private final List<PlaceholderExtension<T>> extensions = Lists.newArrayList();

    public AbstractPlaceholderManager(String identifier, String[] authors, String version, String name,
                                      String surroundingChar) {
        this.identifier = identifier;
        this.authors = authors;
        this.version = version;
        this.name = name;
        this.surroundingChar = surroundingChar;
        this.pattern =
                Pattern.compile(this.surroundingChar + "(" + this.identifier + "_([a-zA-Z0-9_.&|+\\-#]+))" + this.surroundingChar);
    }

    public AbstractPlaceholderManager(String identifier, String[] authors, String version, String name) {
        this(identifier, authors, version, name, "%");
    }

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
    public String onPlaceholderRequest(T player, String placeholder) {
        String[] args = placeholder.split(" ");
        boolean modified = false;

        for (String arg : args) {
            Matcher matcher = this.pattern.matcher(arg);

            if (!matcher.matches()) {
                continue;
            }

            String data = matcher.group(2);
            String fullPlaceholder = matcher.group(0);

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
    public void registerPlaceholder(PlaceholderExtension<T> extension) {
        this.extensions.add(extension);
        this.extensions.sort(Comparator.comparing(o -> ((PlaceholderExtension<?>) o).getPriority()).reversed());
    }
}

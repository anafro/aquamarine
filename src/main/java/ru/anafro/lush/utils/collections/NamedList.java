package ru.anafro.lush.utils.collections;

import ru.anafro.lush.utils.collections.exceptions.ElementAlreadyExistsException;
import ru.anafro.lush.utils.collections.exceptions.ElementNotFoundException;
import ru.anafro.lush.utils.strings.StringSimilarity;

import java.util.*;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NamedList<E extends Nameable> implements Iterable<E> {
    private final List<E> elements;

    @SafeVarargs
    public NamedList(E... elements) {
        this.elements = new ArrayList<>(Arrays.asList(elements));
    }

    public NamedList(List<E> elements) {
        this.elements = elements;
    }

    public NamedList() {
        this.elements = new ArrayList<>();
    }

    public void add(E element) {
        if(containsAnything()) {
            ensureMissing(element.getName());
        }

        elements.add(element);
    }

    public void add(E element, String ifElementWithSuchNameExists) {
        if(containsAnything()) {
            ensureMissing(element.getName(), ifElementWithSuchNameExists);
        }

        elements.add(element);
    }

    public E get(String name) {
        return elements
                .stream()
                .filter(element -> name.equals(element.getName()))
                .findFirst()
                .get();
    }

    public E named(String name) {
        return get(name);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean containsAnything() {
        return !isEmpty();
    }

    public boolean has(String name) {
        return containsAnything() && get(name) != null;
    }

    public boolean missing(String name) {
        return !has(name);
    }

    public void ensureMissing(String name) {
        ensureMissing(name, "An object with name %s already exists in the list.".formatted(name));
    }

    public void ensureMissing(String name, String exceptionMessageIfExists) {
        if(has(name)) {
            throw new ElementAlreadyExistsException(exceptionMessageIfExists);
        }
    }

    public void ensureHas(String name, String exceptionMessageIfMissing) {
        if(missing(name)) {
            throw new ElementNotFoundException(exceptionMessageIfMissing);
        }
    }

    public void ensureHas(String name, Function<E, String> exceptionMessageProducerIfMissing) {
        ensureHas(name, exceptionMessageProducerIfMissing.apply(guess(name)));
    }
    
    public E guess(String notExistingName) {
        return get(Collections.max(elements.stream().map(Nameable::getName).toList(), StringSimilarity.StringComparator.of(notExistingName)));
    }

    public void ensureHas(String name) {
        ensureHas(name, "NamedList<>: An object with name %s is not found.");
    } // TODO

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }


    @Override
    public String toString() {
        return '[' +
                elements.stream().map(Nameable::getName).collect(Collectors.joining(", ")) +
                ']';
    }

    public List<E> asCommonList() {
        return elements;
    }
}

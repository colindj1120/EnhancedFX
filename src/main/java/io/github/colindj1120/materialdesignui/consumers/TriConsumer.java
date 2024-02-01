package io.github.colindj1120.materialdesignui.consumers;

@FunctionalInterface
public interface TriConsumer<T, U, V> {
    void accept(T t, U u, V v);
}

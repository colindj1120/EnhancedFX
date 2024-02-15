package io.github.colindj1120.materialdesignui.utils;

import java.util.AbstractMap;
import java.util.Map;

public class MapUtils {
    public static <K,V> Map.Entry<K,V> simpleEntry(K key, V value){
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    public static <K,V> Map.Entry<K,V> simpleImmutableEntry(K key, V value){
        return new AbstractMap.SimpleImmutableEntry<K,V>(key, value);
    }
}

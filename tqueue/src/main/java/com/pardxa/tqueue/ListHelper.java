package com.pardxa.tqueue;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public class ListHelper {
    private ListHelper() {
    }

    public static int findNextItem(Set<Integer> keySet, int key) {
        Optional<Integer> nextItem = new ArrayList<>(keySet).stream().sorted().filter(item -> (item > key))
                .min((a, b) -> a.compareTo(b));
        return (nextItem.isPresent()) ? nextItem.get() : key;
    }

    public static int findPrevItem(Set<Integer> keySet, int key) {
        Optional<Integer> prevItem = new ArrayList<>(keySet).stream().sorted().filter(item -> (item < key))
                .max((a, b) -> a.compareTo(b));
        return (prevItem.isPresent()) ? prevItem.get() : key;
    }
}

package com.pardxa.tqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Throttle {
    private int throttleRate;
    private Map<Integer, Integer> buckets;

    public Throttle(int throttleRate) {
        this.throttleRate = throttleRate;
        buckets = new HashMap<>();
    }

    public int getOutputPriority(Set<Integer> keySet) {
        List<Integer> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        Iterator<Integer> itr = keyList.iterator();
        int outputPriority = -1;
        while (itr.hasNext()) {
            outputPriority = itr.next();
            if (!isFullBucket(outputPriority)) {
                outputPriority = findFullBucket(outputPriority, keySet);
                addToBucket(outputPriority);
                clearPrevBucket(outputPriority);
                return outputPriority;
            }
        }
        return outputPriority;
    }

    private boolean isFullBucket(int priority) {
        Integer count = buckets.get(priority);
        if (count == null)
            return false;
        return (count + 1 > throttleRate);
    }

    private void addToBucket(int priority) {
        Set<Integer> keySet = buckets.keySet();
        int count = 0;
        if (keySet.contains(priority)) {
            count = buckets.get(priority);
        }
        buckets.put(priority, (count % throttleRate) + 1);
    }

    private void clearPrevBucket(int priority) {
        Set<Integer> keySet = buckets.keySet();
        List<Integer> list = new ArrayList<>(keySet);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            if (priority == list.get(i) && i > 0) {
                buckets.put(list.get(i - 1), 0);
            }
        }
    }

    private int findFullBucket(int priority, Set<Integer> originalKeySet) {
        int outputPriority = priority;
        if (buckets.size() > 0) {
            LinkedList<Integer> bucketList = new LinkedList<>(buckets.keySet());
            Collections.sort(bucketList);
            if (bucketList.peekLast() != priority && buckets.keySet().contains(priority)) {
                for (int idx = bucketList.indexOf(priority) + 1; idx < bucketList.size(); idx++) {
                    int fullBucketkey = bucketList.get(idx);
                    if (isFullBucket(fullBucketkey)) {
                        outputPriority = findNext(originalKeySet, fullBucketkey);
                        if (outputPriority < 0) {
                            outputPriority = fullBucketkey;
                        }
                    }
                }
            }
        }
        return outputPriority;
    }

    private int findNext(Set<Integer> originalKeySet, int fullBucketkey) {
        List<Integer> originalList = new ArrayList<>(originalKeySet);
        Collections.sort(originalList);
        if (!originalKeySet.contains(fullBucketkey)) {
            for (int originalKey : originalList) {
                if (originalKey > fullBucketkey) {
                    return originalKey;
                }
            }
        }
        int index = originalList.indexOf(fullBucketkey);
        if (index + 1 == originalList.size()) {
            return originalList.get(index);
        }
        return originalList.get(index + 1);
    }
}

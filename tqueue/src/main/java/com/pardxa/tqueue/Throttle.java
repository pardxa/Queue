package com.pardxa.tqueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
            if (!hasFullBucket(outputPriority)) {
                outputPriority = checkItemNextToFullBucket(outputPriority, keySet);
                break;
            }
        }
        incrementToBucket(outputPriority);
        clearPrevBucket(outputPriority,keySet);
        return outputPriority;
    }

    /**
     * check if there is a full bucket and return next item behind full bucket or
     * return origin item if there has no full bucket
     * 
     * @param priority
     * @param originalKeySet
     * @return
     */
    private int checkItemNextToFullBucket(int priority, Set<Integer> originalKeySet) {
        if (buckets.isEmpty()) {
            return priority;
        }
        LinkedList<Integer> bucketList = new LinkedList<>(buckets.keySet());
        Collections.sort(bucketList);
        if (bucketList.peekLast() == priority || !buckets.keySet().contains(priority)) {
            return priority;
        }
        Optional<Integer> opFirstFullBucket = bucketList.stream()
                .filter(item -> (item > priority && hasFullBucket(item))).findFirst();
        if (!opFirstFullBucket.isPresent()) {
            return priority;
        }
        int firstFullBucket = opFirstFullBucket.get();
        return ListHelper.findNextItem(originalKeySet, firstFullBucket);
    }

    private boolean hasFullBucket(int priority) {
        Integer count = buckets.get(priority);
        return (count != null) ? (count + 1 > throttleRate) : false;
    }

    private void incrementToBucket(int priority) {
        Set<Integer> keySet = buckets.keySet();
        int count = 0;
        if (keySet.contains(priority)) {
            count = buckets.get(priority);
        }
        buckets.put(priority, (count % throttleRate) + 1);
    }

    private void clearPrevBucket(int priority,Set<Integer> originalKeySet) {
        Integer prevPriority = ListHelper.findPrevItem(buckets.keySet(), priority);
        if (hasFullBucket(prevPriority) && prevPriority != priority) {
            if(originalKeySet.contains(priority)){
                buckets.put(prevPriority, 0);
            }else{
                buckets.remove(prevPriority); 
            }
        }
    }

}

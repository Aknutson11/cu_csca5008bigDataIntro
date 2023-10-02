package io.collective;

import java.time.Clock;

public class SimpleAgedCache {

    public class ExpirableEntry {
        public ExpirableEntry() {
            //need to define variables
        }
    }

    public SimpleAgedCache(Clock clock) {
        //look up constrcutor chaining
    }

    public SimpleAgedCache() {
        //need to initiate an array
    }

    public void put(Object key, Object value, int retentionInMillis) {
        //create ExpirableEntry and add to array
    }

    public boolean isEmpty() {
        // check ExpirableEntry to see if anything needs to be expiered, then return True if count > 0
        return false;
    }

    public int size() {
        // check ExpirableEntry to see if anything needs to be expired, then return length of array 
        return 0;
    }

    public Object get(Object key) {
        return null;
    }
}
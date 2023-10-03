package io.collective;

import java.time.Clock;
public class SimpleAgedCache {
    private Clock clock;
    private ExpirableEntry[] items;

    public class ExpirableEntry {
        private Object key;
        private Object value;
        private int retentionInMillis;

        public ExpirableEntry(Object key, Object value, int retentionInMillis) {
            this.key = key;
            this.value = value;
            this.retentionInMillis = retentionInMillis;
        }
    }

    public SimpleAgedCache(Clock clock) {
        //look up constrcutor chaining
        this();
        this.clock = clock;
        // this.items = new ExpirableEntry[0];
        
    }

    public SimpleAgedCache() {
        //need to initiate an array
        this.items = new ExpirableEntry[0];
        // this(clock);
    }

    public void put(Object key, Object value, int retentionInMillis) {
        //create ExpirableEntry and add to array
        //https://www.geeksforgeeks.org/how-to-add-an-element-to-an-array-in-java/
        //increment array by 1
        //create a new temp array 
        this.clean();
        int size = this.items.length;
        ExpirableEntry[] tempArray = new ExpirableEntry[size + 1];
        for (int i = 0; i < size; i++)
            tempArray[i] = this.items[i];
        //add new value to the end of an array
        tempArray[size] = new ExpirableEntry(key,value,retentionInMillis);

        this.items = tempArray;
    }

    public boolean isEmpty() {
        // check ExpirableEntry to see if anything needs to be expiered, then return True if count > 0
        this.clean();
        System.out.println(this.items.length);
        if (this.items.length > 0 ){
            return false;
        }
        return true;

    }

    public int size() {
        // check ExpirableEntry to see if anything needs to be expired, then return length of array 
        this.clean();
        return this.items.length;
    }

    public Object get(Object key) {
        for(int i = 0; i < this.items.length; i++)
            if (this.items[i].key == key)
                return this.items[i].value;
        return null;
    }

    private void clean() {
        if (this.clock != null) {
            int newSize = 0;
            for(int i = 0; i < this.items.length; i++) {
                if (this.items[i].retentionInMillis < this.clock.millis()){
                    newSize++;
                }
            }
            ExpirableEntry[] newArray = new ExpirableEntry[newSize];
            int temp = 0;
            for(int i = 0; i < this.items.length; i++) {
                if (this.items[i].retentionInMillis < this.clock.millis()){
                    newArray[temp] = this.items[i];
                    temp++;
                    }
                }
            this.items = newArray;
            }
        }
    //testing
    // public static void main(String args[]) {
    //     SimpleAgedCache empty = new SimpleAgedCache();
    //     SimpleAgedCache nonempty = new SimpleAgedCache();
    //     nonempty.put("aKey", "aValue", 2000);
    //     nonempty.put("anotherKey", "anotherValue", 4000);
    //     System.out.println("Assert True");
    //     System.out.println(empty.isEmpty());
    //     System.out.println("Assert False");
    //     System.out.println(nonempty.isEmpty());
        // assertTrue(empty.isEmpty());
        // assertFalse(nonempty.isEmpty());
    // }
    
}
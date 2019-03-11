import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    // average number of entries per bucket before we grow the map
    private static final double ALPHA = 1.0;
    // average number of entries per bucket before we shrink the map
    private static final double BETA = .25;

    // resizing factor: (new size) = (old size) * (resize factor)
    private static final double SHRINK_FACTOR = 0.5, GROWTH_FACTOR = 2.0;

    private static final int MIN_BUCKETS = 16;

    // array of buckets
    protected LinkedList<Entry>[] buckets;
    private int size = 0;

    public MyHashMap() {
        initBuckets(MIN_BUCKETS);
    }

    public class Entry implements Map.Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            value = newValue;
            return value;
        }
    }

    /**
     * given a key, return the bucket where the `K, V` pair would be stored if it were in the map.
     */
    private LinkedList<Entry> chooseBucket(Object key) {

        int bucket_id = key.hashCode() % buckets.length;
        return buckets[bucket_id];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * return true if key is in map
     */
    @Override
    public boolean containsKey(Object key) {
        // Iterate through a chosen bucket looking for our Entry with matching key
        LinkedList bucket = chooseBucket(key);
        for(int i = 0; i < bucket.size(); i++){
            if(chooseBucket(key).get(i).key.equals(key)){
                return true;
            }
        }

        return false;
    }

    /**
     * return true if value is in map
     * Is an O(n) operation
     */
    @Override
    public boolean containsValue(Object value) {
        // Iterate through each bucket and then each value in the bucket looking for an Entry with the matching value
        LinkedList bucket;
        for(int i = 0; i<buckets.length; i++){
            bucket = chooseBucket(i);
            Iterator iterator = bucket.iterator();
            while (iterator.hasNext()) {
                if( ((Entry) iterator.next()).getValue() == value ){
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public V get(Object key) {
        // Find the Entry with matching key and return its value.
        LinkedList bucket = chooseBucket(key);
        for(int i = 0; i < bucket.size(); i++){
            if(chooseBucket(key).get(i).key.equals(key)){
                return chooseBucket(key).get(i).value;
            }
        }

        return null;
    }

    /**
     * add a new key-value pair to the map. Grow if needed, according to `ALPHA`.
     * @return the value associated with the key if it was previously in the map, otherwise null.
     */
    @Override
    public V put(K key, V value) {

        // If we'll be adding a value to the map
        if(!containsKey(key)){
            chooseBucket(key).add(new Entry(key, value));
            size += 1;

            // Grow the array
            if(size > buckets.length*ALPHA){
                // Resize larger
                // Copy the contents of the existing array into a new array with a properly scaled memalloc.
                buckets = java.util.Arrays.copyOf(buckets, buckets.length*(int)GROWTH_FACTOR);

                // Fill the new entries with buckets to protect future iterators.
                for(int i = 0; i<buckets.length; i++){
                    if(buckets[i] == null){
                        buckets[i] = new LinkedList<>();
                    }
                }
                // Reshuffle the entries
                rehash(buckets.length);
            }
            return null;
        } else{
            // We found an existing value and are just going to update its value.
            try {
                return get(key);
            } finally{
                for(int i = 0; i < chooseBucket(key).size(); i++){
                    if(chooseBucket(key).get(i).key == key){
                        chooseBucket(key).get(i).setValue(value);
                    }
                }

            }
        }


    }

    /**
     * Remove the key-value pair associated with the given key. Shrink if needed, according to `BETA`.
     * Make sure the number of buckets doesn't go below `MIN_BUCKETS`. Do nothing if the key is not in the map.
     * @return the value associated with the key if it was in the map, otherwise null.
     */
    @Override
    public V remove(Object key) {

        for(int i = 0; i < chooseBucket(key).size(); i++){
            if(chooseBucket(key).get(i).key.equals(key)){
                try {
                    return chooseBucket(key).get(i).value;
                } finally{
                    chooseBucket(key).remove(i);
                    size -= 1;
                    if(size < buckets.length/4 && buckets.length > MIN_BUCKETS) {
                        // Resize smaller
                        int new_length = buckets.length/2 >= MIN_BUCKETS ? buckets.length/2 : MIN_BUCKETS;
                        rehash(new_length);
                        buckets = java.util.Arrays.copyOf(buckets, new_length);
                    }
                }

            }
        }
        // If the value is not in the map
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Changes the number of buckets and rehashes the existing entries.
     * If growthFactor is 2, the number of buckets doubles. If it is 0.25,
     * the number of buckets is divided by 4.
     */

    private void rehash(int toBuckets){ //double growthFactor) {
        // Only iterator-safe solution is to dump contents of map into temp array and then clear it.

        Entry oldentry;
        Entry newentry;
        LinkedList bucket;
        LinkedList old_entries = new LinkedList<>();

        // Dump contents
        for(int i = 0; i<buckets.length; i++){
            bucket = buckets[i];
            Iterator<Entry> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                oldentry = iterator.next();
                old_entries.add(oldentry);
            }
        }
        // Overwrite with new scale.
        initBuckets(toBuckets);

        // Re-put everything back.
        Iterator<Entry> iterator = old_entries.iterator();
        while (iterator.hasNext()) {
            newentry = iterator.next();
            put(newentry.getKey(), newentry.getValue());
            size --;
        }
    }

    private void initBuckets(int size) {
        buckets = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void clear() {
        initBuckets(buckets.length);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> e : entrySet()) {
            keys.add(e.getKey());
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        for (Map.Entry<K, V> e : entrySet()) {
            values.add(e.getValue());
        }
        return values;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<Entry> map : buckets) {
            set.addAll(map);
        }
        return set;
    }
}

package jon;

public class MyArrayList {
    private Cow[] elems;
    private int size; // essentially .length of the useful array

    // TODO: Runtime: O(?)
    public MyArrayList() {
        // Is called when ArrayList is declared
        // Initializes an arary with 10 elements
        elems = new Cow[10];
        size = 0;
        
    }

    // TODO: Runtime: O(?)
    public MyArrayList(int capacity) {
        // TODO
        elems = new Cow[capacity];
        size = 0;
        
    }

    // TODO: Runtime: O(?)
    public void add(Cow c) {
        // TODO
        if (elems.length > size){
            // Add cow to array
            elems[size] = c;
            size += 1;
        } else {
            // Resize the elems array and try again
            elems = java.util.Arrays.copyOf(elems, elems.length * 2);
            add(c);
        }
    }

    // TODO: Runtime: O(?)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(?)
    public Cow get(int index) {
        // Check if index is out of bounds
        if (index > size || index < 0){
            return null;
        }
        
        return elems[index];
    }

    // TODO: Runtime: O(?)
    public Cow remove(int index) {
        // Check if index is out of bounds
        if (index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        Cow var = elems[index];
        return var;
        
        // I like my Go P.P
        try{
            return elems[index];
        }
        finally{
            /// a, b, c, d, e, f, g, _, _
            // length = 9, size = 7
            // remove c
            // start at d and move each element over until size-1 is reached
            
            
            for (int i = index+1; i< size; i++){
                elems[i-1] = elems[i];
            }
            size -= 1;

            if (size <= elems.length/4 ) {
                //ternary checks if newly resized array will be less 10 and defaults to 10
                elems = java.util.Arrays.copyOf(elems, elems.length/4 >= 10 ? elems.length/4 : 10);
            }
        }
    }
    

    // TODO: Runtime: O(?)
    public void add(int index, Cow c) {
        // Check if index is out of bounds
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        
        
        // Resize array if needed
        if (size == elems.length)
            elems = java.util.Arrays.copyOf(elems, elems.length * 2);
        
        /// a, b, c, d, e, f, g, _, _
        // length = 9, size = 7
        // insert X into index 2 (c)
        // start at g and move each element over until index 2 is reached
        // overwrite element 2 with new element
        
        // O(n/2) = O(n). Worst case O(n)
        for (int i = size; i>=index; i-- ){
            elems[i+1] = elems[i];
        }
        elems[index] = c;
        size += 1;
        
        return;
    }
}
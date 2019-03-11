package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> me;


    public MyStack() {
        ll = new LinkedList<>();
        me = new LinkedList<>();

    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);

        if (me.size() == 0){
            me.addFirst(e);
        } else if(me.getFirst() < e){
            me.addFirst(e);
        }
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();

        if (me.getFirst() == pop){
            me.removeFirst();
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        // TODO
        return me.getFirst();
    }
}

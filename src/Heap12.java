/**
 * NAME: Drew Mills
 * ID: A13102641
 * LOGIN: cs12sci
 */
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Heap12 class that implements an unbounded array-backed heap structure and is
 * an extension of the Java Collections AbstractQueue class
 * <p>
 * The elements of the heap are ordered according to their natural
 * ordering,  Heap12 does not permit null elements.
 * The top of this heap is the minimal or maximal element (called min/max)
 * with respect to the specified natural ordering.
 * If multiple elements are tied for min/max value, the top is one of
 * those elements -- ties are broken arbitrarily.
 * The queue retrieval operations poll, remove, peek, and element
 * access the element at the top of the heap.
 * <p>
 * A Heap12 is unbounded, but has an internal capacity governing the size of
 * an array used to store the elements on the queue. It is always at least as
 * large as the queue size. As elements are added to a Heap12, its capacity
 * grows automatically. The details of the growth policy are not specified.
 * <p>
 * This class and its iterator implements the optional methods of the
 * Iterator interface (including remove()). The Iterator provided in method
 * iterator() is not guaranteed to traverse the elements of the Heap12 in
 * any particular order.
 * <p>
 * Note that this implementation is not synchronized. Multiple threads
 * should not access a Heap12 instance concurrently if any of the
 * threads modifies the Heap12.
 */
public class Heap12<E extends Comparable<? super E>> extends AbstractQueue<E> {
    //backing store array
    private ArrayList<E> backStore;
    //keeps track how many elements are in the arraylist
    private int capacity;
    // boolean that decides if the heap is a min or a max heap * true is max
    private boolean maxorMin;
    /**
     * 0-argument constructor. Creates an empty Heap12 with capacity of 5
     * elements, and is a min-heap
     */
    public Heap12() {
        backStore = new ArrayList(5);
        capacity = backStore.size();
        maxorMin = false;
    }

    /**
     * Constructor to build a min or max heap
     *
     * @param isMaxHeap if true, this is a max-heap, else a min-heap. Initial
     *                  capacity of the heap should be 5.
     */
    public Heap12(boolean isMaxHeap) {
        backStore = new ArrayList(5);
        capacity = backStore.size();
        maxorMin = isMaxHeap;
    }

    /**
     * Constructor to build a with specified initial capacity
     * min or max heap
     *
     * @param capacity  initial capacity of the heap.
     * @param isMaxHeap if true, this is a max-heap, else a min-heap
     */
    public Heap12(int capacity, boolean isMaxHeap) {
        backStore = new ArrayList(capacity);
        this.capacity = backStore.size();
        maxorMin = isMaxHeap;
    }

    /**
     * Copy constructor. Creates Heap12 with a deep copy of the argument
     *
     * @param toCopy the heap that should be copied
     */
    public Heap12(Heap12<E> toCopy) {
        //makes a new heap with size of the heap it is going to copy
        backStore = new ArrayList<E>(toCopy.backStore.size());
        //TODO keep track of capacity with instance var
        capacity = toCopy.capacity;
        //TODO keep track of max with getMax method
        //copy elements from tocopy into the heap arraylist
        for( int i = 0; i < toCopy.backStore.size();i++) {
            backStore.add(i,toCopy.backStore.get(i));
        }
    }

    /* The following are defined "stub" methods that provide degenerate
     * implementations of methods defined as abstract in parent classes.
     * These need to be coded properly for Heap12 to function correctly
     */

    /**
     * Size of the heap
     *
     * @return the number of elements stored in the heap
     */
    public int size() {
        return this.backStore.size();
    }

    /**
     * @return an Iterator for the heap
     */
    public Iterator<E> iterator() {
        return new Heap12Iterator();
    }

    /**
     * @return Element at top of heap. Do not remove
     */
    public E peek() {
        //if array is empty
        if(backStore.size() <= 0) {
            throw new NullPointerException("heap is empty");
        }
        //return first element
        return backStore.get(0);
    }

    /**
     * @return Element at top of heap. And remove it from the heap.
     * return <tt>null</tt> if the heap is empty
     * take last elem, put it at the top then trickle down
     *
     */
    public E poll() {
        if (backStore.size() == 0){
            return null;
        }
        //
        E temp = backStore.get(0);
        backStore.set(0,backStore.get(backStore.size()-1));
        backStore.remove(backStore.get(backStore.size()-1));

        //reestablish heap order,by tricking down
        trickleDown(0);

        //return that original
        return temp;
    }

    /**
     * insert an element in the heap
     *
     * @return true
     * @throws ClassCastException       if the class of the element prevents it from being added
     * @throws NullPointerException     if the specified element is null
     * @throws IllegalArgumentException if some property of the element keeps it from being added.
     */
    public boolean offer(E e) {
        if (e == null){
            throw new NullPointerException("Element is null");
        }
        if (e instanceof Heap12){
            throw new ClassCastException(e + " is not of type Heap12");
        }

        if (capacity == backStore.size()){
            // make new array with double the capacity
           ArrayList tempBackStore = new ArrayList<E>(backStore.size()*2);
           //copy
            for (int i = 0; i < backStore.size(); i++) {
                tempBackStore.set(i,backStore.get(i));
            }
            //assign the temp array back into the original back store
            backStore = tempBackStore;
        }
        // add new element
        backStore.add(e);
        // reestablish heap order
        bubbleUp(backStore.size()-1);

        return true;
    }

    /* ------ Private Helper Methods ----*/
        private void trickleDown(int indx) {
            int childIndex = 2 * indx + 1;
            E value = backStore.get(indx);

            while (childIndex < backStore.size()) {
                // Find the max among the node and all the node's children
                E maxValue = value;
                int maxIndex = -1;
                for (int i = 0; i < 2 && i + childIndex < backStore.size(); i++) {
                    if (backStore.get(i + childIndex).compareTo(maxValue) < 0) {
                        maxValue = backStore.get(i + childIndex);
                        maxIndex = i + childIndex;
                    }
                    else if (backStore.get(i + childIndex).compareTo(maxValue) > 0) {
                        maxValue = backStore.get(i + childIndex);
                        maxIndex = i + childIndex;
                    }
                }

                if (maxValue == value) {
                    return;
                }
                else {
                    E temp = backStore.get(indx);
                    backStore.set(indx, backStore.get(maxIndex));
                    backStore.set(maxIndex,temp);
                    indx = maxIndex;
                    childIndex = 2 * indx + 1;
                }
            }
        }
        private void bubbleUp(int indx) {
            while (indx > 0) {
                int parentIndex = (indx - 1) / 2;
                if (0 >= (backStore.get(indx).compareTo(backStore.get(parentIndex))))
                    return ;
                else if (0 <= (backStore.get(indx).compareTo(backStore.get(parentIndex))))
                    return ;
                else {
                    //swap
                    E temp = backStore.get(indx);
                    backStore.set(indx, backStore.get(parentIndex));
                    backStore.set(parentIndex,temp);

                    indx = parentIndex;
                }
            }
        }

     // IDK WAT DO?!?
     private void parent(int indx) {

     }


    /** Inner Class for an Iterator 
	This is a recommended class name. You may change it**/
    private class Heap12Iterator implements Iterator<E> {
        // create a new Heap12
        private Heap12<E> copy;

        private boolean canRemove;
        /* there are several ways to iterate through a heap,
         * the simplest is breadth-first, which is just through
         * the indices
         */

        private Heap12Iterator() {
            heap.iterator();

        }

        /**
         * Returns true if the iteration has more elements. (In other words,
         * returns true if next() would return an element rather than throwing
         * an exception.)
         *
         * @return true if the iteration has more elements
         */
        public boolean hasNext() {
            if (!hasNext()) throw new NoSuchElementException("No next Elem");
            return !copy.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException();

            return null; //TODO
        }

        /**
         * Removes from the underlying collection the last element returned by
         * this iterator (optional operation). This method can be called only
         * once per call to next(). The behavior of an iterator is unspecified
         * if the underlying collection is modified while the iteration is in
         * progress in any way other than by calling this method.
         *
         * @throws IllegalStateException if the next method has not yet been
         * called, or the remove method has already been called after the
         * last call to the next method
         */
        public void remove() throws IllegalStateException {
            // removes the element returned by next
        }
    }
}
// vim:ts=4:sw=4:tw=78:et

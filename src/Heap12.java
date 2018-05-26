/**
 * NAME: Drew Mills
 * ID: A13102641
 * LOGIN: cs12sci
 */
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
    private ArrayList<E> heap;
    /**
     * 0-argument constructor. Creates an empty Heap12 with capacity of 5
     * elements, and is a min-heap
     */
    public Heap12() {
        heap = new ArrayList(5);

    }

    /**
     * Constructor to build a min or max heap
     *
     * @param isMaxHeap if true, this is a max-heap, else a min-heap. Initial
     *                  capacity of the heap should be 5.
     */
    public Heap12(boolean isMaxHeap) {

    }

    /**
     * Constructor to build a with specified initial capacity
     * min or max heap
     *
     * @param capacity  initial capacity of the heap.
     * @param isMaxHeap if true, this is a max-heap, else a min-heap
     */
    public Heap12(int capacity, boolean isMaxHeap) {

    }

    /**
     * Copy constructor. Creates Heap12 with a deep copy of the argument
     *
     * @param toCopy the heap that should be copied
     */
    public Heap12(Heap12<E> toCopy) {
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
        return this.heap.size();
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
        return (E) null;  // TODO: return the correct top of heap
    }

    /**
     * @return Element at top of heap. And remove it from the heap.
     * return <tt>null</tt> if the heap is empty
     */
    public E poll() {
        return (E) null;  // TODO: return the correct top of heap
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
        return true;
    }

    /* ------ Private Helper Methods ----*/
     private void trickleDownMax(int indx) {
         int childIndex = 2 * indx + 1;
         E value = heap.get(indx);

         while (childIndex < heap.size()) {
             // Find the max among the node and all the node's children
             E maxValue = value;
             int maxIndex = -1;
             for (int i = 0; i < 2 && i + childIndex < heap.size(); i++) {
                 if (heap.get(i + childIndex).compareTo(maxValue) > 0) {
                     maxValue = heap.get(i + childIndex);
                     maxIndex = i + childIndex;
                 }
             }

             if (maxValue == value) {
                 return;
             }
             else {
                 E temp = heap.get(indx);
                 heap.set(indx, heap.get(maxIndex));
                 heap.set(maxIndex,temp);
                 indx = maxIndex;
                 childIndex = 2 * indx + 1;
             }
         }
     }
     private void bubbleUpMax(int indx) {
             while (indx > 0) {
                 int parentIndex = (indx - 1) / 2;
                 if (0 <= (heap.get(indx).compareTo(heap.get(parentIndex))))
                     return;
                 else {
                     //swap
                     E temp = heap.get(indx);
                     heap.set(indx, heap.get(parentIndex));
                     heap.set(parentIndex,temp);

                     indx = parentIndex;
                 }
             }
     }
        private void trickleDownMin(int indx) {
            int childIndex = 2 * indx + 1;
            E value = heap.get(indx);

            while (childIndex < heap.size()) {
                // Find the max among the node and all the node's children
                E maxValue = value;
                int maxIndex = -1;
                for (int i = 0; i < 2 && i + childIndex < heap.size(); i++) {
                    if (heap.get(i + childIndex).compareTo(maxValue) < 0) {
                        maxValue = heap.get(i + childIndex);
                        maxIndex = i + childIndex;
                    }
                }

                if (maxValue == value) {
                    return;
                }
                else {
                    E temp = heap.get(indx);
                    heap.set(indx, heap.get(maxIndex));
                    heap.set(maxIndex,temp);
                    indx = maxIndex;
                    childIndex = 2 * indx + 1;
                }
            }
        }
        private void bubbleUpMin(int indx) {
            while (indx > 0) {
                int parentIndex = (indx - 1) / 2;
                if (0 >= (heap.get(indx).compareTo(heap.get(parentIndex))))
                    return;
                else {
                    //swap
                    E temp = heap.get(indx);
                    heap.set(indx, heap.get(parentIndex));
                    heap.set(parentIndex,temp);

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
        private boolean canRemove;
        /* there are several ways to iterate through a heap,
         * the simplest is breadth-first, which is just through
         * the indices
         */

        private Heap12Iterator() {
        }

        public boolean hasNext() {
            return true; // TODO: change this when code is implmented
        }

        public E next() throws NoSuchElementException {
            return (E) null;  // TODO: change this when code is implemented
        }

        public void remove() throws IllegalStateException {
        }
    }
}
// vim:ts=4:sw=4:tw=78:et

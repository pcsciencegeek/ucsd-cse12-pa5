import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/** This is a Heap Sort only 20 lines of code
 *  @author Philip Papadopoulos 
 *  @date 28 April 2014
 */
public class HeapSort12 implements Sort12
{
	public  <T extends Comparable<? super T>> void sort(List<T> list)
	{
	    Heap12<T> heap = new Heap12<>(list.size(), false);

        for (int i = 0; i < list.size()-1; i++) {
            heap.offer(list.get(i));
        }

        for (int i = 0; i < list.size()-1; i++) {
            list.set(i,heap.poll());
        }
	}
}
// vim:ts=4:sw=4:sw=78

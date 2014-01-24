/**
 * Quicksort3Way.java
 */

package uk.co.bluettduncanj.serial;


/**
 * <p>A sorting class based on a 3-way partition implementation of the quicksort algorithm.</p>
 * 
 * <p>For most if not all data sets, the average-case efficiency of '3-way partition' quicksort over the traditional
 * '2-way partition' variant is (Big Oh notation) O(Nlog<sub>2</sub>N), and for data sets with few unique keys the sort
 * speeds up signifcantly towards an efficiency of O(N). Therefore Quicksort3Way.java should be preferred over
 * Quicksort.java.</p>
 * 
 * @author Jonathan
 */
public class Quicksort3Way {
  
  /**
   * <p>An experimentally-produced value that determines the minimum size of a sub-array before 
   * insertion sort is performed on it instead of quicksort.</p>
   * 
   * <p>Insertion sort is performed on those sub-arrays with sizes less than INSERTION_SORT_THRESHOLD,
   * because insertion sort is practically faster than quicksort on 'sufficiently small' inputs.</p>
   */
  private static int INSERTION_SORT_THRESHOLD = 10;
  
  /**
   * Private constructor. Prevents instantiation of Quicksort class.
   */
  private Quicksort3Way() {}
  
  /**
   * A convenience method for quicksort(int[] array, int start, int end) that sorts an entire array of primitive integers.
   * 
   * @param array An int-type array to sort.
   */
  public static void quicksort(int[] array) {
    quicksort(array, 0, array.length-1);
  }
  
  /**
   * Sorts a range of values between two inclusive indexes (start and end) within an array of primitive integers.
   * 
   * @param array An int-type array to act upon.
   * @param start The beginning index of the range of values to sort.
   * @param end The finishing index of the range of values to sort.
   */
  public static void quicksort(int[] array, int start, int end) {
    // Use Insertion Sort if sub-array is 'sufficiently small'
    int range = end - start;
    if (range < INSERTION_SORT_THRESHOLD) {
      InsertionSort.insertionSort(array, start, end);
    }
    else {
      // Quicksort (3-way partition method)
      int lt = start, gt = end;
      int pivot = array[start];
      int i = start;
      while (i <= gt) {
        if (array[i] < pivot)       swap(array, lt++, i++);
        else if (pivot < array[i])  swap(array, i, gt--);
        else                        i++;
      }
      
      // Quicksort the partitions recursively, whilst meeting invariant
      // array[start..lt-1] < pivot = array[lt..gt] < array[gt+1..end].
      quicksort(array, start, lt - 1);
      quicksort(array, gt + 1, end);
    } 
  }
  
  /**
   * Swap elements at two indexes in a given int-type array.
   * 
   * @param array The int-type array to act upon.
   * @param i The index of the first element to swap.
   * @param j The index of the second element to swap.
   */
  private static void swap(int[] array, int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
  
}

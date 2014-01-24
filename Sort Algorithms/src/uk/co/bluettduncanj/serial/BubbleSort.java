/**
 * BubbleSort.java
 */

package uk.co.bluettduncanj.serial;


/**
 * <p>A sorting class based on the bubble sort algorithm.</p>
 * 
 * <p>For most data sets, bubble sort has (Big Oh notation) O(N<sup>2</sup>) efficiency. However, for nearly-sorted and already-sorted
 * data sets, it speeds up (adapts) to O(N) efficiency. It is also a sufficient algorithm for sufficiently small data sets, 
 * since for such data sizes it works faster, and it is easier to program, than popular O(NlogN)) efficiency algorithms 
 * e.g. quicksort, heapsort and mergesort.</p>
 * 
 * <p>It should be noted that practical implementations of bubble sort are generally significantly slower than practical
 * implementations of insertion sort. Therefore InsertionSort.java should be preferred over BubbleSort.java.</p>
 * 
 * @author Jonathan
 */
public class BubbleSort {
  
  /**
   * Private constructor. Prevents instantiation of BubbleSort class.
   */
  private BubbleSort() {}
  
  /**
   * A convenience method for bubbleSort(int[] array, int start, int end) that sorts an entire array of primitive integers.
   * 
   * @param array: An int-type array to sort.
   */
  public static void bubbleSort(int[] array) {
    bubbleSort(array, 0, array.length-1);
  }

  /**
   * Sorts a range of values between two inclusive indexes (start and end) within an array of primitive integers.
   * 
   * @param array An int-type array to act upon.
   * @param start The beginning index of the range of values to sort.
   * @param end The finishing index of the range of values to sort.
   */
  public static void bubbleSort(int[] array, int start, int end)  {
    boolean sorted = false;
    for (int i = start; i < end && !sorted; i++) {
      sorted = true;
      // Ripple
      for (int j = end; j > i; j--) {
        if (array[j-1] > array[j]) {
          sorted = false;
          // Swap
          int tmp = array[j];
          array[j] = array[j-1];
          array[j-1] = tmp;
        }
      }
    }
  }
  
}

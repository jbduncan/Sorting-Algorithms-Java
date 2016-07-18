/**
 * InsertionSort.java
 */

package uk.co.bluettduncanj.serial;


/**
 * <p>A sorting class based on the insertion sort algorithm.</p>
 * 
 * <p>For most data sets, insertion sort has (Big Oh notation) O(N<sup>2</sup>) efficiency. However, for nearly-sorted data sets and
 * already-sorted data sets, it speeds up (adapts) to O(N) efficiency. It is also a good algorithm for sufficiently small 
 * datasets, since it works faster on datasets of a small size (and it also happens to be easier to program than popular O(N*logN) 
 * algorithms like quicksort, heapsort and mergesort).</p>
 * 
 * @author Jonathan Bluett-Duncan
 */
public final class InsertionSort {
  
  /**
   * Private constructor. Prevents instantiation of InsertionSort class.
   */
  private InsertionSort() {}

  /**
   * A convenience method for insertionSort(int[] array, int start, int end) that sorts an entire array of primitive integers.
   * 
   * @param array An int-type array to sort.
   */
  public static void insertionSort(int[] array) {
    insertionSort(array, 0, array.length - 1);
  }
  
  /**
   * Sorts a range of values between two inclusive indexes (start and end) within an array of primitive integers.
   * 
   * @param array An int-type array to act upon.
   * @param start The beginning index of the range of values to sort.
   * @param end The finishing index of the range of values to sort.
   */
  public static void insertionSort(int[] array, int start, int end) {
    // Single-array implementation
    for (int i = start + 1; i <= end; i++) {
      for (int j = i; j > start && array[j] < array[j-1]; j--) {
        // Swap
        int temp = array[j];
        array[j] = array[j - 1];
        array[j - 1] = temp;
      }
    } 
  }
  
}

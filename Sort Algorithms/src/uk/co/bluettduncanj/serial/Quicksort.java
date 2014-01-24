/**
 * Quicksort.java
 */

package uk.co.bluettduncanj.serial;


/**
 * <p>A sorting class based on the quicksort algorithm.</p>
 * 
 * <p>For a majority of data set types, this implementation of quicksort has (Big Oh notation) O(Nlog<sub>2</sub>N) average-case 
 * efficiency. This is not the case for data sets with few unique items/elements/keys, where the average-case effiency slows down 
 * to O(N<sup>2</sup>). Quicksort3Way.java recitfies this issue using a '3 way partition' method over traditional quicksort 
 * '2 way' partitioning, and should therefore be preferred over Quicksort.java.</p>
 * 
 * @author Jonathan
 */
public class Quicksort {
  
  /**
   * <p>An experimentally-produced value that determines the minimum size of a sub-array before 
   * insertion sort is performed on it instead of quicksort.</p>
   * 
   * <p>Insertion sort is performed on those sub-arrays with sizes less than INSERTION_SORT_THRESHOLD,
   * because insertion sort is practically faster than quicksort on 'sufficiently small' inputs.</p>
   */
  private static int INSERTION_SORT_THRESHOLD = 45;
  
  /**
   * Private constructor. Prevents instantiation of Quicksort class.
   */
  private Quicksort() {}
  
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
      // Quicksort
      int middleIndex = start + (range / 2);
      int pivotIndex = median3(array, start, middleIndex, end);
      // Temporarily swap the pivot into start index
      swap(array, pivotIndex, start);
      
      // Partition (i.e. meet invariant array[start .. j-1] <= a[j] <= array[j+1 .. end])
      int i = start;
      int j = end + 1;
      int v = array[start]; // Reference to the pivot
      while (true) {
        
        // Find item on 'low partition' to swap
        while (array[++i] < v) {
          if (i == end) {
            break;
          }
        }
        
        // Find item on 'high partition' to swap
        while (v < array[--j]) {
          if (j == start) {
            break;
          }
        }
        
        // Check if pointers cross
        if (i >= j) {
          break;
        }
        
        swap(array, i , j);
      }
      
      // Put v = array[j] into position between 'low' and 'high' partitions
      swap(array, start, j);
      
      // End partitioning
      
      // Quicksort the partitions recursively
      quicksort(array, start, j - 1);
      quicksort(array, j + 1, end);
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

  /**
   * Finds the median of values at three given indexes in an array, and returns the index of the median.
   * 
   * @param array The int-type array to act upon.
   * @param a The first index.
   * @param b The second index.
   * @param c The third index.
   * 
   * @return the index of the median.
   */
  private static int median3(int[] x, int a, int b, int c) {
    if (x[a] > x[b]) {
      if (x[b] > x[c]) {
        return b;
      }
      if (x[a] > x[c]) {
        return c;
      }
      return a;
    }
    else {
      if (x[a] > x[c]) {
        return a;
      }
      if (x[b] > x[c]) {
        return c;
      }
      return b;
    }
  }
  
}

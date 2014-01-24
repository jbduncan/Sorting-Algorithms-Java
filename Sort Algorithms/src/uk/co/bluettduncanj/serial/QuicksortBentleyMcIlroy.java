/**
 * QuicksortBentleyMcIlroy.java
 */

package uk.co.bluettduncanj.serial;


/**
 * <p>
 * A sorting class based on the Bentley-McIlroy 3-way partitioning quicksort method used in <i>Algorithms, 4th Edition</i> 
 * (http://algs4.cs.princeton.edu/23quicksort/QuickX.java.html), in turn based on research outlined in Jon L. Bentley and 
 * M. Douglas McIlroy's journal article <i>Engineering a sort function</i>.
 * </p>
 * 
 * <p>
 * <b>References:</b> 
 * 
 * <p>J. L. Bentley and M. D. McIlroy, "Engineering a sort function", 
 * <i>Software: Practice and Experience</i>, vol 23, no 11, pp. 1249-1265, Nov. 1993. </p>
 * 
 * <p>R. Sedgewick and K. Wayne, <i>Algorithms</i>, 4th ed., London: Addison-Wesley, 2011.</p>
 * 
 * <p>http://algs4.cs.princeton.edu/23quicksort/QuickX.java.html. Accessed: 01/07/2013.</p>
 * </p>
 * 
 * @author Jonathan Bluett-Duncan
 */
public class QuicksortBentleyMcIlroy {
  
  /**
   * <p>An experimentally-produced value that determines the minimum size of a sub-array before 
   * insertion sort is performed on it instead of quicksort.</p>
   * 
   * <p>Insertion sort is performed on those sub-arrays with sizes less than INSERTION_SORT_THRESHOLD,
   * because insertion sort is practically faster than quicksort on 'sufficiently small' inputs.</p>
   */
  private static int INSERTION_SORT_THRESHOLD = 10;
  
  private static int MEDIAN3_THRESHOLD = 41;
  
  /**
   * Private constructor. Prevents instantiation.
   */
  private QuicksortBentleyMcIlroy() {}
  
  /**
   * Insertion sort - Used on 'sufficiently small' arrays.
   * 
   * Sorts a range of values between two inclusive indexes (start and end) within an array of primitive integers.
   * 
   * @param array 
   *          The array to sort.
   * @param start
   *          First index of range of values to sort.
   * @param end
   *          Last index of range of values to sort.
   */
  private static void insertionSort(int[] array, int start, int end) {
    // Simple single-array implementation
    for (int i = start + 1; i <= end; i++) {
      for (int j = i; j > start && array[j] < array[j-1]; j--) {
        // Swap
        int temp = array[j];
        array[j] = array[j-1];
        array[j-1] = temp;
      }
    } 
  }
  
  /**
   * A convenience method for quicksort(int[] array, int start, int end) that sorts an entire array of primitive integers.
   * 
   * @param array 
   *          The array to sort.
   */
  public static void quicksort(int[] array) {
    quicksort(array, 0, array.length-1);
  }
  
  /**
   * Sorts a range of values between two inclusive indexes (start and end) within an array of primitive integers.
   *
   * @param array
   *          The array to sort.
   * @param start
   *          First index of range of values to sort.
   * @param end
   *          Last index of range of values to sort.
   */
  public static void quicksort(int[] array, int start, int end) {
    int length = end - start + 1;
    
    // Use insertion sort if sub-array is small
    if (length < INSERTION_SORT_THRESHOLD) {
      insertionSort(array, start, end);
      return;
    }
    
    // Use the median of the start, middle and end elements as the pivot
    else if (length < MEDIAN3_THRESHOLD) {
      int pivotIndex = median3(array, start, start + (length / 2), end);
      // Swap pivot to start of sub-array
      swap(array, start, pivotIndex);
    }
    
    // Use 'Tukey's ninther' as the pivot.
    else {
      int eps = length / 8;
      int middleIndex = start + (length / 2);
      int medianIndex1 = median3(array, start, start + eps, start + eps + eps);
      int medianIndex2 = median3(array, middleIndex - 1, middleIndex, middleIndex + 1);
      int medianIndex3 = median3(array, end - eps - eps, end - eps, end);
      int ninther = median3(array, medianIndex1, medianIndex2, medianIndex3);
      swap(array, start, ninther);
    }
    
    // 3-way partition using the Bentley-McIlroy method
    int i = start, j = end + 1, p = start, q = j;
    while (true) {
      int pivot = array[start];
      while (array[++i] < pivot) {
        if (i == end) {
          break;
        }
      }
      while (pivot < array[--j]) {
        if (j == start) {
          break;
        }
      }
      if (i >= j) {
        break;
      }
      swap(array, i, j);
      if (array[i] == pivot) {
        swap(array, ++p, i);
      }
      if (array[j] == pivot) {
        swap(array, --q, j);
      }
    }
    swap(array, start, j);
    
    i = j + 1;
    j--;
    for (int k = start + 1; k <= p; k++) {
      swap(array, k, j--);
    }
    for (int k = end; k >= q; k--) {
      swap(array, k, i++);
    }
    
    // Recursively quicksort the two partitions not equal to the pivot
    if (j - start < end - i) {
      quicksort(array, start, j);
      quicksort(array, i, end);
    }
    else {
      quicksort(array, i, end);
      quicksort(array, start, j);
    }
  }
  
  /**
   * Swap elements at two indexes in a given array.
   * 
   * @param array 
   *          The array to act upon.
   * @param i 
   *          The index of the first element to swap.
   * @param j 
   *          The index of the second element to swap.
   */
  private static void swap(int[] array, int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  /**
   * Finds the median of values at three given indexes in an array, and returns the index of the median.
   * 
   * @param array 
   *          The array to act upon.
   * @param a 
   *          The first index.
   * @param b 
   *          The second index.
   * @param c 
   *          The third index.
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

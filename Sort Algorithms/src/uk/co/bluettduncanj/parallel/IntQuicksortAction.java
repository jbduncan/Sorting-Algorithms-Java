/**
 * IntQuicksortAction.java
 */

package uk.co.bluettduncanj.parallel;

import java.util.concurrent.RecursiveAction;


/**
 * A <tt>RecursiveAction</tt> implementation for quicksorting <tt>int</tt> arrays in <tt>ParallelQuicksort</tt>.
 * 
 * @since 1.7
 * 
 * @author Jonathan Bluett-Duncan
 */
public class IntQuicksortAction extends RecursiveAction {

  /** serialVersionUID */
  private static final long serialVersionUID = -8148918232918180414L;
  
  /** The array of integers to sort */
  private int[] array;
  
  private int start;
  private int end;
  
  /**
   * Public <tt>IntQuicksortAction</tt> constructor.
   * 
   * @param array
   *          The array of integers to sort.
   * @param start
   *          The index in <tt>array</tt> to sort from.
   * @param end
   *          The index in <tt>array</tt> to sort to.
   */
  public IntQuicksortAction(int array[], int start, int end) {
    this.start = start;
    this.end = end;
    this.array = array;
  }
  
  /**
   * <p>Runs a Bentley-McIlroy 3-way partitioning Quicksort.</p>
   * 
   * <p>Sorts values according to the parameters passed to the <tt>IntQuicksortAction</tt> constructor.</p>
   *
   * @see java.util.concurrent.RecursiveAction#compute()
   */
  @Override
  protected void compute() {
    int length = end - start + 1;
    boolean sorted = (length <= 1);
    
    if (sorted)
      return;
    
    // Use insertion sort if array is very small
    if (length <= 7) {
      insertionSort(start, end);
      return;
    }
    
    // Use median of start, mid and end elements as pivot for small-ish arrays
    else if (length <= 40) {
      int mid = start + (length / 2);
      int pivot = median3(start, mid, end);
      
      // Swap pivot to start of sub-array
      swap(start, pivot);
    }
    
    // Use "Tukey's ninther" as pivot for large arrays
    else {
      int eps = length / 8;
      int mid = start + (length / 2);
      int med1 = median3(start, start + eps, start + eps + eps);
      int med2 = median3(mid - 1, mid, mid + 1);
      int med3 = median3(end - eps - eps, end - eps, end);
      int pivotIndex = median3(med1, med2, med3); // Tukey's ninther
      swap(start, pivotIndex);
    }
    
    // 3-way partition using the Bentley-McIlroy method
    int i = start, j = end + 1, p = start, q = j;
    while (true) {
      int pivot = array[start];
      while (array[++i] < pivot) {
        if (i == end) 
          break;
      }
      while (pivot < array[--j]) {
        if (j == start) 
          break;
      }
      if (i >= j) 
        break;
      swap(i, j);
      if (array[i] == pivot)
        swap(++p, i);
      if (array[j] == pivot)
        swap(--q, j);
    }
    swap(start, j);
    
    i = j + 1;
    j--;
    for (int k = start + 1; k <= p; k++)
      swap(k, j--);
    for (int k = end; k >= q; k--)
      swap(k, i++);
    
    // Recursively quicksort in parallel the two partitions not equal to the pivot
    IntQuicksortAction left = new IntQuicksortAction(array, start, j);
    IntQuicksortAction right = new IntQuicksortAction(array, i, end);
    left.fork();
    right.compute();
    left.join();
  }
  
  /**
   * Insertion sort - Used on 'sufficiently small' (sub-)arrays.
   * 
   * Sorts a range of values between two inclusive indexes (start and end) within the array of integers.
   * 
   * This implementation uses the half-exchanges and sentinel approach for optimised sorting.
   * 
   * @param start
   *          First index of range of values to sort.
   * @param end
   *          Last index of range of values to sort.
   */
  private void insertionSort(int start, int end) {
    
    int len = end - start + 1;

    // Put smallest element in position to serve as sentinel
    for (int i = end; i > start; i--)
      if (array[i] < array[i-1]) 
        swap(i, i - 1);

    // Insertion sort with half-exchanges
    for (int i = start + 2; i < start + len; i++) {
      int value = array[i];
      int j = i;
      while (value < array[j-1]) {
        array[j] = array[j-1];
        j--;
      }
      array[j] = value;
    }
  }
  
  /**
   * Swap elements at two indexes in the array.
   * 
   * @param i 
   *          The index of the first element to swap.
   * @param j 
   *          The index of the second element to swap.
   */
  private void swap(int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  /**
   * Finds the median of values at three given indexes in the array, and returns the index of the median.
   * 
   * @param a 
   *          The first index.
   * @param b 
   *          The second index.
   * @param c 
   *          The third index.
   * 
   * @return the index of the median.
   */
  private int median3(int a, int b, int c) {
    if (array[a] > array[b]) {
      if (array[b] > array[c])
        return b;
      if (array[a] > array[c])
        return c;
      return a;
    }
    else {
      if (array[a] > array[c])
        return a;
      if (array[b] > array[c])
        return c;
      return b;
    }
  }
  
}

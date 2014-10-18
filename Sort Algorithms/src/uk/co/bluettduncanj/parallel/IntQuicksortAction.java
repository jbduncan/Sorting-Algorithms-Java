/**
 * IntQuicksortAction.java
 */

package uk.co.bluettduncanj.parallel;

import java.util.concurrent.RecursiveAction;


/**
 * An implementation of <tt>RecursiveAction</tt> for quicksorting <tt>int</tt> arrays, used by <tt>ParallelQuicksort</tt>.
 * 
 * @since 1.7
 * 
 * @author Jonathan Bluett-Duncan
 */
@Deprecated
public class IntQuicksortAction extends RecursiveAction {

  /** serialVersionUID */
  private static final long serialVersionUID = -8148918232918180414L;
  
  private int[] array;
  private int lo;
  private int hi;
  
  /**
   * Public <tt>IntQuicksortAction</tt> constructor.
   * 
   * @param <tt>array</tt>
   *          The array of integers to sort.
   * @param <tt>lo</tt>
   *          The index in <tt>array</tt> to sort from.
   * @param <tt>hi</tt>
   *          The index in <tt>array</tt> to sort to.
   */
  public IntQuicksortAction(int array[], int lo, int hi) {
    this.lo = lo;
    this.hi = hi;
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
    int length = hi - lo + 1;
    
    // Use insertion sort if array is very small
    if (length <= ParallelQuicksort.INSERTION_SORT_CUTOFF) {
      insertionSort(lo, hi);
      return;
    }
    
    // Use median of lo, mid and hi elements as pivot for small-ish arrays
    else if (length <= ParallelQuicksort.SIMPLE_MEDIAN3_CUTOFF) {
      int mid = lo + (length / 2);
      int pivot = median3(lo, mid, hi);
      swap(lo, pivot);
    }
    
    // Use "Tukey's ninther" as pivot for large arrays
    else {
      int eps = length / 8;
      int mid = lo + (length / 2);
      int med1 = median3(lo, lo + eps, lo + eps + eps);
      int med2 = median3(mid - 1, mid, mid + 1);
      int med3 = median3(hi - eps - eps, hi - eps, hi);
      int pivotIndex = median3(med1, med2, med3); // Tukey's ninther
      swap(lo, pivotIndex);
    }
    
    // 3-way partition using the Bentley-McIlroy method
    int i = lo, j = hi + 1, p = lo, q = j;
    while (true) {
      int pivot = array[lo];
      while (array[++i] < pivot) {
        if (i == hi) 
          break;
      }
      while (pivot < array[--j]) {
        if (j == lo) 
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
    swap(lo, j);
    
    i = j + 1;
    j--;
    for (int k = lo + 1; k <= p; k++)
      swap(k, j--);
    for (int k = hi; k >= q; k--)
      swap(k, i++);
    
    // Recursively quicksort the two partitions not equal to the pivot...
    if (length <= ParallelQuicksort.SEQUENTIAL_CUTOFF) {                  // ...sequentially if array is small
      sortSequentially(array, lo, j);
      sortSequentially(array, i, hi);
    } else {                                                              // ...in parallel if array is large
      IntQuicksortAction left  = new IntQuicksortAction(array, lo, j);
      IntQuicksortAction right = new IntQuicksortAction(array, i, hi);
      left.fork(); 
      right.compute();
      left.join();
    }
  }
  
  private void sortSequentially(int[] array, int lo, int hi) {
    int length = hi - lo + 1;
    
    // Use insertion sort if array is very small
    if (length <= ParallelQuicksort.INSERTION_SORT_CUTOFF) {
      insertionSort(lo, hi);
      return;
    }
    
    // Use median of lo, mid and hi elements as pivot for small-ish arrays
    else if (length <= ParallelQuicksort.SIMPLE_MEDIAN3_CUTOFF) {
      int mid = lo + (length / 2);
      int pivot = median3(lo, mid, hi);
      swap(lo, pivot);
    }
    
    // Use "Tukey's ninther" as pivot for large arrays
    else {
      int eps = length / 8;
      int mid = lo + (length / 2);
      int med1 = median3(lo, lo + eps, lo + eps + eps);
      int med2 = median3(mid - 1, mid, mid + 1);
      int med3 = median3(hi - eps - eps, hi - eps, hi);
      int pivotIndex = median3(med1, med2, med3); // Tukey's ninther
      swap(lo, pivotIndex);
    }
    
    // 3-way partition using the Bentley-McIlroy method
    int i = lo, j = hi + 1, p = lo, q = j;
    while (true) {
      int pivot = array[lo];
      while (array[++i] < pivot) {
        if (i == hi) 
          break;
      }
      while (pivot < array[--j]) {
        if (j == lo) 
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
    swap(lo, j);
    
    i = j + 1;
    j--;
    for (int k = lo + 1; k <= p; k++)
      swap(k, j--);
    for (int k = hi; k >= q; k--)
      swap(k, i++);
    
    // Recursively sort in a sequential manner
    sortSequentially(array, lo, j);
    sortSequentially(array, i, hi);
  }
  
  /**
   * Insertion sort - Used on 'sufficiently small' (sub-)arrays.
   * 
   * Sorts a range of values between two inclusive indexes (<tt>lo</tt> and <tt>hi</tt>) within the array of integers.
   * 
   * This implementation uses the half-exchanges and sentinel approach for optimised sorting.
   * 
   * @param lo
   *          The index <tt>array</tt> to sort from.
   * @param hi
   *          The index <tt>array</tt> to sort to.
   */
  private void insertionSort(int lo, int hi) {
    
    // Put smallest element in position to serve as sentinel
    for (int i = hi; i > lo; i--)
      if (array[i] < array[i-1]) 
        swap(i, i - 1);

    // Insertion sort with half-exchanges
    for (int i = lo + 2; i <= hi; i++) {
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
   * Swap elements at two indices in <tt>array</tt>.
   * 
   * @param i 
   *          The index of the first element.
   * @param j 
   *          The index of the second element.
   */
  private void swap(int i, int j) {
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  /**
   * Finds the median of values at three given indices in <tt>array</tt>, and returns the index of the median.
   * 
   * @param a 
   *          The first index.
   * @param b 
   *          The second index.
   * @param c 
   *          The third index.
   * 
   * @return the position of the median of elements in <tt>array</tt> at positions <tt>a</tt>, <tt>b</tt> and <tt>c</tt>.
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

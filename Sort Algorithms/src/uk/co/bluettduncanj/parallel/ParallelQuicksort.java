/**
 * ParallelQuicksorter.java
 */

package uk.co.bluettduncanj.parallel;

import java.util.concurrent.ForkJoinPool;


/**
 * <p>The <tt>ParallelQuicksort</tt> class is a parallelised sorting class that uses the Java 1.7 <i>Fork/Join</i> framework.</p>
 *  
 * <p>
 * It is based on the sequential Bentley-McIlroy 3-way partitioning quicksort implementation found online
 * on the <i>'Algorithms, 4th Edition'</i> <a href="http://algs4.cs.princeton.edu/23quicksort/QuickX.java.html">website</a>, 
 * which is in turn based on research by Jon L. Bentley and M. Douglas McIlroy, 
 * as outlined in their journal article <i>Engineering a sort function</i>.
 * </p>
 * 
 * @since 1.7
 * 
 * @author Jonathan Bluett-Duncan
 */
public class ParallelQuicksort {
  
  // TODO: Improve general QuicksortAction implementations, e.g. IntQuicksortAction, by following good practices under the online
  // lectures for 'Sophomoric Parallelism and Concurrency' @ http://homes.cs.washington.edu/~djg/teachingMaterials/spac/,
  // particularly lectures on 'Introduction to Multithreading and Fork-Join Parallelism' and 'Parallel Prefix, Pack and Sorting'.
  //
  // Be especially on the look out for information on sequential cutoffs and any potentially useful info to do with
  // parallel prefix and sorting.
  
  // TODO: Look for a parallelism library for Java, and experiment with creating an implementation using that.
  
  // TODO: Experiment by creating a new implementation that does in parallel a sequential quicksort on each of
  // min(array size OR sequential cutoff, ForkJoinPool parallelism size OR Java 8 Arrays.parallelSort granularity size) 
  // parts of the array, followed by doing a single merge step (as in a merge sort) for all sorted sub-arrays,
  // using "10. Faster (Unstable) Merge" at http://algs4.cs.princeton.edu/22mergesort/, or a merge that only uses
  // a half-size array (if possible without much effort), or both.
  
  private static final ForkJoinPool sortPool    = new ForkJoinPool();
  
  public static final int INSERTION_SORT_CUTOFF =    7;
  public static final int SIMPLE_MEDIAN3_CUTOFF =   40;
  public static final int SEQUENTIAL_CUTOFF     = 2048;
  
  /*
   * Private constructor - prevent instantiation.
   */
  private ParallelQuicksort() {};
  
  public static void sort(int[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(int[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new IntQuicksortAction(array, lo, hi));
  }
  
  /*
  public static void sort(long[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(long[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new LongQuicksortAction(array, lo, hi));
  }
  
  public static void sort(byte[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(byte[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new ByteQuicksortAction(array, lo, hi));
  }
  
  public static void sort(short[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(short[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new ShortQuicksortAction(array, lo, hi));
  }
  
  public static void sort(char[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(char[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new CharQuicksortAction(array, lo, hi));
  }
  
  public static void sort(double[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(double[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new DoubleQuicksortAction(array, lo, hi));
  }
  
  public static void sort(float[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(float[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new FloatQuicksortAction(array, lo, hi));
  }
  
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static <T extends Comparable<? super T>> void sort(T[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new ComparableQuicksortAction(array, lo, hi));
  }
  
  public static <T> void sort(T[] array, Comparator<T> c) {
    sort(array, 0, array.length-1, c);
  }
  
  public static <T> void sort(T[] array, int lo, int hi, Comparator<T> c) {
    checkRange(lo, hi, array.length);
    sortPool.invoke(new ComparatorQuicksortAction(array, lo, hi, c));
  }
  */
  
  /**
   * Checks that <tt>lo</tt> and <tt>hi</tt> are valid indices for a particular collection with 0-based indexing.
   * 
   * @param lo
   *          Index to sort from.
   * @param hi
   *          Index to sort to.
   * @param length
   *          Size of collection to be sorted, which is assumed to have 0-based indexing.
   * @throws IllegalArgumentException if lo > hi.
   * @throws ArrayIndexOutOfBoundsException if lo < 0 or hi >= length.
   */
  private static void checkRange(int lo, int hi, int length) 
      throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
    
    if (lo > hi) {
      throw new IllegalArgumentException("lo(" + lo + ") > hi(" + hi + ")");
    }
    if (lo < 0) {
      throw new ArrayIndexOutOfBoundsException(lo);
    }
    if (hi >= length) {
      throw new ArrayIndexOutOfBoundsException(hi);
    }
  }
  
}

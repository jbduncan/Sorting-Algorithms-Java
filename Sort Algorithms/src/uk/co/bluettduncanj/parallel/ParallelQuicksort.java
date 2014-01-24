/**
 * ParallelQuicksorter.java
 */

package uk.co.bluettduncanj.parallel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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
  
  // TODO: Experiment by creating a new implementation that does in parallel a sequential quicksort on each of
  // min(array size, ForkJoinPool parallelism size) parts of the array,
  // followed by doing a merge step (as in a merge sort) per pair of sorted sub-arrays,
  // using "10. Faster (Unstable) Merge" at http://algs4.cs.princeton.edu/22mergesort/.
  
  // TODO: Put Sort Algorithms into a Git repo and push to BitBucket.
  
  private static final ForkJoinPool mainPool = new ForkJoinPool();
  
  // Prevents instantiation
  private ParallelQuicksort() {};
  
  public static void sort(int[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(int[] array, int lo, int hi) {
    checkRange(lo, hi, array.length);
    mainPool.invoke(new IntQuicksortAction(array, lo, hi));
  }
  
  public static void sort(long[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(long[] array, int lo, int hi) {
    checkRange(lo, hi, hi-lo+1);
    mainPool.invoke(new LongQuicksortAction(array, lo, hi));
  }
  
  public static void sort(byte[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(byte[] array, int lo, int hi) {
    checkRange(lo, hi, hi-lo+1);
    mainPool.invoke(new ByteQuicksortAction(array, lo, hi));
  }
  
  public static void sort(short[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(short[] array, int lo, int hi) {
    checkRange(lo, hi, hi-lo+1);
    mainPool.invoke(new ShortQuicksortAction(array, lo, hi));
  }
  
  public static void sort(char[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(char[] array, int lo, int hi) {
    checkRange(lo, hi, hi-lo+1);
    mainPool.invoke(new CharQuicksortAction(array, lo, hi));
  }
  
  public static void sort(double[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(double[] array, int lo, int hi) {
    checkRange(lo, hi, hi-lo+1);
    mainPool.invoke(new DoubleQuicksortAction(array, lo, hi));
  }
  
  public static void sort(float[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static void sort(float[] array, int lo, int hi) {
    checkRange(lo, hi, hi-lo+1);
    mainPool.invoke(new FloatQuicksortAction(array, lo, hi));
  }
  
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    sort(array, 0, array.length-1);
  }
  
  public static <T extends Comparable<? super T>> void sort(T[] array, int lo, int hi) {
    checkRange(lo, hi, hi-lo+1);
    mainPool.invoke(new ComparableQuicksortAction(array, lo, hi));
  }
  
  public static <T> void sort(T[] array, Comparator<T> c) {
    sort(array, 0, array.length-1, c);
  }
  
  public static <T> void sort(T[] array, int lo, int hi, Comparator<T> c) {
    checkRange(lo, hi, hi-lo+1);
    mainPool.invoke(new ComparatorQuicksortAction(array, lo, hi, c));
  }
  
  private static void checkRange(int lo, int hi, int length) {
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

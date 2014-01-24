/**
 * HeapSort2.java
 */

package uk.co.bluettduncanj.serial;

import java.util.List;


/**
 * Based on implementation at 
 * <a href="http://algs4.cs.princeton.edu/24pq/Heap.java.html">http://algs4.cs.princeton.edu/24pq/Heap.java.html</a>.
 * 
 * @author Jonathan
 */
public class HeapSort2 {

  private HeapSort2() {}
  
  public static <T extends Comparable<T>> void sort(List<T> list) {
    int n = list.size();
    for (int i = n/2; i >= 1; i--) {
      sink(list, i, n);
    }
    while (n > 1) {
      swap(list, 1, n--);
      sink(list, 1, n);
    }
  }
  
  private static <T extends Comparable<T>> void sink(List<T> list, int i, int n) {
    while (2*i <= n) {
      int j = 2*i;
      if (j < n && less(list, j, j+1))
        j++;
      if (!less(list, i, j))
        break;
      swap(list, i, j);
      i = j;
    }
  }
  
  /**
   * A comparison function that checks whether list.get(i-1) is less than list.get(j-1).
   * 
   * Indices are "off-by-one" to support 1-based indexing.
   * 
   * @param list
   * @param i
   * @param j
   * @return
   */
  private static <T extends Comparable<T>> boolean less(List<T> list, int i, int j) {
    return list.get(i-1).compareTo(list.get(j-1)) < 0;
  }
  
  /**
   * A swap function. Indices are "off-by-one" to support 1-based indexing.
   * 
   * @param list
   * @param i
   * @param j
   */
  private static <T extends Comparable<T>> void swap(List<T> list, int i, int j) {
    T tmp = list.get(i-1);
    list.set(i-1, list.get(j-1));
    list.set(j-1, tmp);
  }
  
}

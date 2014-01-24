/**
 * Heapsort.java
 */

package uk.co.bluettduncanj.serial;

import java.util.PriorityQueue;


/**
 * A naive Java Heapsort implementation.
 * 
 * @author Jonathan
 * @param <T>
 */
public class NaiveHeapSort<T extends Comparable<T>> {
  
  private PriorityQueue<T> queue;
  
  public NaiveHeapSort() {
    super();
    this.queue = new PriorityQueue<T>();
  }
  
  public void add(T element) {
    this.queue.add(element);
  }
  
  public T remove() {
    return this.queue.poll();
  }
  
}

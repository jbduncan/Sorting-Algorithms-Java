/**
 * Heapsort.java
 */

package uk.co.bluettduncanj.serial;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jonathan
 */
public class HeapSort<T extends Comparable<T>> {
  
  private HeapSort() {}

  private class Heap<Item extends Comparable<Item>> {
    ArrayList<Item> heap = new ArrayList<Item>();

    public void add(Item item) {
      int index = heap.size();
      heap.add(item);
      while (index > 0 && item.compareTo(heap.get((index - 1)/2)) < 0){
        heap.set(index, heap.get((index-1)/2));
        index = (index-1) / 2;
      }
      heap.set(index, item);
    }

    public Item remove() {
      Item ans = heap.get(0);
      Item sift = heap.remove(heap.size()-1);
      if (heap.isEmpty()){
        return ans;
      }
      else {
        int index = 0;
        while ((2*index+2) < heap.size() && 
                (sift.compareTo(heap.get(2*index + 1)) > 0 
                    || sift.compareTo(heap.get(2*index + 2)) > 0)) {
          if (heap.get(2*index + 1).compareTo(heap.get(2*index+2)) < 0) {
            heap.set(index, heap.get(2*index+1));
            index = 2*index + 1;
          }
          else {
            heap.set(index, heap.get(2*index+2));
            index = 2*index+2;        
          }
        }
        if (2*index+2 == heap.size()){
          if (sift.compareTo(heap.get(2*index+1)) > 0) {
            heap.set(index, heap.get(2*index+1));
            index = 2*index+1;
          }
        }
        heap.set(index, sift);
        return ans;
      } 
    }

    public boolean isEmpty() {
      return (heap.size() == 0);
    }
  }
  
  /**
   * HeapSort algorithm - sorts a list of Comparables in ascending order 
   * (from smallest to largest according to their compareTo() method).
   * 
   * This algorithm has a guaranteed Big Oh order notation efficiency of O(NlogN) in all cases.
   * 
   * However, it lacks two useful properties found in other slower sort algorithms e.g. BubbleSort, InsertionSort:
   *  - Unstable: Items with the same value in the list of Comparables may be reordered.
   *  - Not really adaptive: Whereas this algorithm gets ever so slightly faster if the list of Comparables is 
   *                         in reverse order, it does not make a meaningful impact to the algorithm's speed.
   *                  
   * This method does not return a separate copy of the list. Therefore, if you need separate copies, e.g.
   * one ordered and one unordered, do that before calling this method on one of the copies.
   * 
   * @param list: The Java list of Comparables to sort. 
   */
  public void sortAsc(List<T> list) {
    HeapSort<T>.Heap<T> heap = new HeapSort<T>().new Heap<T>();
    while (!list.isEmpty()) {
      heap.add(list.remove(0));
    }
    while (!heap.isEmpty()) {
      list.add(heap.remove());
    }
  }
  
}

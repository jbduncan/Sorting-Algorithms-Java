/**
 * Mergesort.java
 */

package uk.co.bluettduncanj.serial;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * @author Jonathan
 */
public class SimpleMergeSort {
  
  /**
   * A very simple implementation of mergesort designed for learning purposes.
   * 
   * If the size of the passed-in ArrayList is 1 or 0, the original ArrayList is returned.
   * Otherwise, an ArrayList containing the elements in ascending-sorted order is returned.
   * 
   * @param list The ArrayList to sort.
   * @return a copy of the list as a sorted ArrayList.
   */
  public static <T extends Comparable<T>> ArrayList<T> simpleSort(ArrayList<T> list) {
    // If the list size is 1 or 0 (empty), it is sorted
    if (list.size() < 2) {
      return list;
    }
    // Else list size is > 1, so split it into two sublists
    
    // 1) DIVIDE
    ArrayList<T> left = new ArrayList<T>();
    ArrayList<T> right = new ArrayList<T>();
    int middleIndex = list.size() / 2;
    for (int i = 0; i < middleIndex; i++) {
      left.add(list.get(i));
    }
    for (int i = middleIndex; i < list.size(); i++) {
      right.add(list.get(i));
    }
    // Call simpleSort() recursively to further split each sublist until sublist size is 1
    left = simpleSort(left);
    right = simpleSort(right);
    
    // 2) CONQUER:
    // Merge returned sublists from prior calls, and return the result merged sublist
    ArrayList<T> result = simpleMerge(left, right);
    return result;
  }
  
  private static <T extends Comparable<T>> ArrayList<T> simpleMerge(ArrayList<T> left, ArrayList<T> right) {
    // Receive the left and right sublists as arguments.
    // 'result' used to store merged result of two sublists.
    ArrayList<T> result = new ArrayList<T>(left.size() + right.size());
    // Assign the elements in the sublists to 'result' until there are no more elements to merge.
    while (!left.isEmpty() || !right.isEmpty()) {
      if (!left.isEmpty() && !right.isEmpty()) {
        // Compare the first elements of each sublist.
        // Remember: Due to the recursive sorting of left and right in simpleSort(), they are already in ascending
        // sorted order, meaning their first (head) elements are the smallest ones.
        if (left.get(0).compareTo(right.get(0)) <= 0) {
          // If the left sublist has the smallest first element, or its first element is equal to the 
          // first element of the right sublist, remove that element and append it to 'result'.
          result.add(left.remove(0));
        }
        else {
          // Otherwise remove the first element in right and append it to 'result', since in this case
          // right has the smallest first element.
          result.add(right.remove(0));
        }
      }
      // Else if just left has any remaining elements...
      else if (!left.isEmpty()) {
        // Append remaining elements in left to 'result', since there are no more elements in right to compare with
        result.add(left.remove(0));
      }
      // Else if just right has any remaining elements...
      else if (!right.isEmpty()) {
        // Append remaining elements in right to 'result'...
        result.add(right.remove(0));
      }
    }
    // Finally return the result
    return result;
  }
  
  /**
   * A very simple implementation of mergesort designed for learning purposes.
   * 
   * If the size of the passed-in LinkedList is 1 or 0, the original LinkedList is returned.
   * Otherwise, a LinkedList containing the elements in ascending-sorted order is returned.
   * 
   * @param list The LinkedList to sort.
   * @return a copy of the list as a sorted LinkedList.
   */
  public static <T extends Comparable<T>> LinkedList<T> simpleSort(LinkedList<T> list) {
    // If the list size is 1 or 0 (empty), it is sorted
    if (list.size() < 2) {
      return list;
    }
    // Else list size is > 1, so split it into two sublists
    
    // 1) DIVIDE
    LinkedList<T> left = new LinkedList<T>();
    LinkedList<T> right = new LinkedList<T>();
    int middleIndex = list.size() / 2;
    for (int i = 0; i < middleIndex; i++) {
      left.add(list.removeFirst());
    }
    for (int i = middleIndex; i < list.size(); i++) {
      right.add(list.removeFirst());
    }
    // Call simpleSort() recursively to further split each sublist until sublist size is 1
    left = simpleSort(left);
    right = simpleSort(right);
    
    // 2) CONQUER:
    // Merge returned sublists from prior calls, and return the result merged sublist
    return simpleMerge(left, right);
  }
  
  private static <T extends Comparable<T>> LinkedList<T> simpleMerge(LinkedList<T> left, LinkedList<T> right) {
    // Receive the left and right sublists as arguments.
    // 'result' used to store merged result of two sublists.
    LinkedList<T> result = new LinkedList<T>();
    // Assign the elements in the sublists to 'result' until there are no more elements to merge.
    while (!left.isEmpty() || !right.isEmpty()) {
      if (!left.isEmpty() && !right.isEmpty()) {
        // Compare the first elements of each sublist.
        // Remember: Due to the recursive sorting of left and right in simpleSort(), they are already in ascending
        // sorted order, meaning their first (head) elements are the smallest ones.
        if (left.getFirst().compareTo(right.getFirst()) <= 0) {
          // If the left sublist has the smallest first element, or its first element is equal to the 
          // first element of the right sublist, remove that element and append it to 'result'.
          result.add(left.removeFirst());
        }
        else {
          // Otherwise remove the first element in right and append it to 'result', since in this case
          // right has the smallest first element.
          result.add(right.removeFirst());
        }
      }
      // Else if just left has any remaining elements...
      else if (!left.isEmpty()) {
        // Append remaining elements in left to 'result', since there are no more elements in right to compare with
        result.add(left.removeFirst());
      }
      // Else if just right has any remaining elements...
      else if (!right.isEmpty()) {
        // Append remaining elements in right to 'result'...
        result.add(right.removeFirst());
      }
    }
    // Finally return the result
    return result;
  }
  
}

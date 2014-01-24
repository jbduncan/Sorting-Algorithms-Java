/**
 * MergesortTest.java
 */

package uk.co.bluettduncanj;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.co.bluettduncanj.serial.SimpleMergeSort;


/**
 * @author Jonathan
 */
public class SimpleMergesortTest {
  
  private static Random r;
  private static Integer[] array;
  private ArrayList<Integer> arrayList;
  private LinkedList<Integer> linkedList;
  public static int arrSize = 30000 /* 5 */;
  public static int range  = 1000 /* 10 */;
  private long start, end;
  
  /**
   * @throws java.lang.Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    r = new Random();
    array = new Integer[arrSize];
    for (int i = 0; i < arrSize; i++) {
      array[i] = r.nextInt(range+1);
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    array = null;
  }

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    start = 0; end = 0;
    arrayList = new ArrayList<Integer>(arrSize);
    linkedList = new LinkedList<Integer>();
    for (int i = 0; i < arrSize; i++) {
      arrayList.add(array[i]);
      linkedList.add(array[i]);
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    start = 0; end = 0;
    arrayList = null;
    linkedList = null;
  }

  /**
   * Test method for {@link uk.co.bluettduncanj.serial.SimpleMergeSort#simpleSort(java.util.ArrayList)}.
   */
  @Test
  public void testSimpleSortArrayListOfT() {
    System.out.println("Mergesort [ArrayList] (SimpleMergesort.java)");
    //System.out.println("Before sorting: " + print(arrayList));
    start = System.currentTimeMillis();
    arrayList = SimpleMergeSort.simpleSort(arrayList);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + print(arrayList));
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    System.out.println("Sorted: " + isSorted(arrayList) + ".");
    
    System.out.println();
  }

  /**
   * Test method for {@link uk.co.bluettduncanj.serial.SimpleMergeSort#simpleSort(java.util.LinkedList)}.
   */
  @Test
  public void testSimpleSortLinkedListOfT() {
    System.out.println("Mergesort [LinkedList] (SimpleMergesort.java)");
    //System.out.println("Before sorting: " + print(linkedList));
    start = System.currentTimeMillis();
    linkedList = SimpleMergeSort.simpleSort(linkedList);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + print(linkedList));
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    System.out.println("Sorted: " + isSorted(linkedList) + ".");
    
    System.out.println();
  }
  
  @Test
  public void testArraysSortLinkedListOfT() {
    System.out.println("Arrays.sort() [LinkedList] (Arrays.java)");
    //System.out.println("Before sorting: " + print(linkedList));
    start = System.currentTimeMillis();
    Collections.sort(linkedList);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + print(linkedList));
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    //System.out.println("Sorted: " + isSorted(linkedList) + ".");
    
    System.out.println();
  }
  
  private static <T> String print(List<T> list) {
    String print = "";
    for (T element : list) {
      print = print + element.toString() + " ";
    }
    return print;
  }

  private static <T extends Comparable<T>> boolean isSorted(List<T> list) {
    for (int i = 1; i < list.size(); i++) {
      if (list.get(i).compareTo(list.get(i-1)) < 0) {
        return false;
      }
    }
    return true;
  }

}

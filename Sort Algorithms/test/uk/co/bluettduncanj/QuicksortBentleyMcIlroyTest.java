/**
 * QuicksortBentleyMcIlroyTest.java
 */

package uk.co.bluettduncanj;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.bluettduncanj.serial.QuicksortBentleyMcIlroy;


/**
 * @author Jonathan
 */
public class QuicksortBentleyMcIlroyTest {
  
  private Random r;
  private int[] array;
  public static int arrSize = 10000000 /* 30 */;
  public static int range  =  10000    /* 10 */;
  private long start, end;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    r = new Random();
    start = 0; end = 0;
    array = new int[arrSize];
    for (int i = 0; i < arrSize; i++) {
      array[i] = r.nextInt(range+1);
    }
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    start = 0; end = 0;
    array = null;
    r = null;
  }

  /**
   * Test method for {@link uk.co.bluettduncanj.serial.QuicksortBentleyMcIlroy#quicksort(int[], int, int)}.
   */
  @Test
  public void testQuicksortIntArrayIntInt() {
    
    // 'Warm up' ...
    QuicksortBentleyMcIlroy.quicksort(new int[] {4, 5, 2, 1, 9, 3});
    
    System.out.println("Quicksort (QuicksortBentleyMcIlroy.java)");
    //System.out.println("Before sorting: " + print(array));
    start = System.currentTimeMillis();
    QuicksortBentleyMcIlroy.quicksort(array);
    end = System.currentTimeMillis();
    //System.out.println("After sorting:  " + print(array));
    System.out.println("Run time: " + (end - start) + " milliseconds.");
    System.out.println("Sorted: " + isSorted(array) + ".");
    
    System.out.println();
  }
  
  private static String print(int[] array) {
    String print = "";
    for (int element : array) {
      print = print + element + " ";
    }
    return print;
  }

  private static boolean isSorted(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i] < (array[i-1])) {
        return false;
      }
    }
    return true;
  }
}

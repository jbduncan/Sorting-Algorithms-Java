/**
 * Quicksort3WayTest.java
 */

package uk.co.bluettduncanj;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.co.bluettduncanj.serial.Quicksort3Way;


/**
 * @author Jonathan
 */
public class Quicksort3WayTest {
  
  private Random r;
  private int[] array;
  public static int arrSize = 3000000;
  public static int range  = 10000;
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
   * Test method for {@link uk.co.bluettduncanj.serial.Quicksort3Way#quicksort(int[], int, int)}.
   */
  @Test
  public void testQuicksortIntArrayIntInt() {
    System.out.println("Quicksort (Quicksort3Way.java)");
    //System.out.println("Before sorting: " + print(array));
    start = System.currentTimeMillis();
    Quicksort3Way.quicksort(array);
    end = System.currentTimeMillis();
    long time = end - start;
    //System.out.println("After sorting:  " + print(array));
    System.out.println("Run time: " + time + " milliseconds.");
    
    System.out.println();
  }
  
  private static String print(int[] array) {
    String print = "";
    for (int element : array) {
      print = print + element + " ";
    }
    return print;
  }

}

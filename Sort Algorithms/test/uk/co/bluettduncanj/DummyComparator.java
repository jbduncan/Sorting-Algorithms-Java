/**
 * DummyComparator.java
 */

package uk.co.bluettduncanj;

import java.util.Comparator;


/**
 * @author Jonathan
 * @param <T>
 */
public class DummyComparator implements Comparator<DummyObject> {

  /**
   * @param o1
   * @param o2
   * @return
   *
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  @Override
  public int compare(DummyObject o1, DummyObject o2) {
    if (o1.dummyCalc() < o2.dummyCalc()) return -1;
    if (o1.dummyCalc() > o2.dummyCalc()) return 1;
    return 0;
  }

}

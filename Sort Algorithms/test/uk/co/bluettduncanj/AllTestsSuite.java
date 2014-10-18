package uk.co.bluettduncanj;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({InsertionSort2Test.class, Mergesort1Test.class, Mergesort2Test.class,
ParallelQuicksortTest.class, Quicksort3WayTest.class, QuicksortBentleyMcIlroyTest.class,
QuicksortTest.class, SimpleMergesortTest.class})
public final class AllTestsSuite {}

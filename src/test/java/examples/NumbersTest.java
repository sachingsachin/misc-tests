package examples;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class NumbersTest {

    @Test
    public void testNumberClasses() throws Exception {
        Object obj=10;
        System.out.println(obj instanceof Number); // true
        obj=10.53;
        System.out.println(obj instanceof Number); // true
    }

    @Test
    public void testLongRange() throws Exception {
        long s = Long.MIN_VALUE;
        long e = Long.MAX_VALUE;
        System.out.println((double)e-(double)s);
        System.out.println(e-s);
        System.out.println(Double.SIZE);
        System.out.println(Long.SIZE);
    }

    /**
     * Sample output:
     *
     * 18014398509564934 - 18014398509481984 = 82950
     * 1.8014398509564936E16 - 1.8014398509481984E16 = 82952.0
     *
     *
     * 18014398509564935 - 18014398509481984 = 82951
     * 1.8014398509564936E16 - 1.8014398509481984E16 = 82952.0
     *
     *
     * 18014398509564937 - 18014398509481984 = 82953
     * 1.8014398509564936E16 - 1.8014398509481984E16 = 82952.0
     *
     * The difference is sometimes more, sometimes less.
     * Total 750,000 inconsistencies in a sample of 1000,000
     */
    @Test
    public void testDoublePrecision() throws Exception {
        long s = 2L<<53;
        System.out.println(s);
        long count=0;
        for (long i=0; i<1000*1000; i++) {
            long e = s + i;
            double sd = (double)s;
            double ed = (double)e;
            if ((long)(ed-sd) != (e-s)) {
                System.out.println(e + " - " + s + " = " + (e-s));
                System.out.println(ed + " - " + sd + " = " + (ed-sd) + "\n\n");
                count++;
            }
        }
        System.out.println(count + " inconsistencies");
    }
}

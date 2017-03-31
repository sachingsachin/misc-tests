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
}

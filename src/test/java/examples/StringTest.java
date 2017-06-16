package examples;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Test;

public class StringTest {

    /**
     * Output:
     * 1)
     * 2) abc
     * 3) 
     * 4) 
     * 5) 
     * 6) 
     * 7) def
     *
     * abc def
     * 1) abc
     * 2) def
     */
    @Test
    public void testSplit() throws Exception {
        String str = " abc     def";
        String parts[] = str.split(" ");
        int count=0;
        for (String part: parts) {
            count++;
            System.out.println(count + ") " + part);
        }

        String afterReplace = str.replaceAll(" +", " ").replaceAll("^ ", "");
        System.out.println("\n" + afterReplace);
        parts = afterReplace.split(" ");
        count=0;
        for (String part: parts) {
            count++;
            System.out.println(count + ") " + part);
        }
    }

    /**
     * Output:
     * %abc ---> abc
     * 10.10:20.20 ---> 10.1020.20
     */
    @Test
    public void testReplaceNonAscii() throws Exception {
        System.out.println("\n");
        String[] arr = new String[] {
               "%abc",
               "10.10:20.20"
        };
        for (String str: arr) {
            String rep = str.replaceAll("[^A-Za-z0-9-\\.]", "");
            System.out.println(str + " ---> " + rep);
        }
        System.out.println("\n");
    }

    @Test
    public void testUUID() throws Exception {
        System.out.println("\n" + UUID.randomUUID().toString() + "\n");
    }
}

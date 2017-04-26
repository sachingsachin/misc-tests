package examples;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

public class DataTypesTest {

    @Test
    public void testNumberClasses() throws Exception {
        System.out.println(LocalDateTime.now().toLocalDate());
    }
}

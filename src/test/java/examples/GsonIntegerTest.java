package examples;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * Output of this test is:
 * <pre>
 * {a=1.0, b=2.0}
 * Class of 1.0 is class java.lang.Double
 * Class of 2.0 is class java.lang.Double
 * {a=1, b=2}
 * Class of 1 is class java.lang.Integer
 * Class of 2 is class java.lang.Integer
 * </pre>
 */
public class GsonIntegerTest {

    @Test
    public void testIntegerHandlingInGson() throws Exception {
        Gson gson = new Gson();
        String json = "{\"a\":1, \"b\":2}";
        Map map = gson.fromJson(json, Map.class);
        assertNotNull (map);
        System.out.println(map);
        for (Object val: map.values()) {
            System.out.println("Class of " + val + " is " + val.getClass());
        }

        ObjectMapper mapper = new ObjectMapper();
        map = mapper.readValue(json, Map.class);
        System.out.println(map);
        for (Object val: map.values()) {
            System.out.println("Class of " + val + " is " + val.getClass());
        }
    }
}

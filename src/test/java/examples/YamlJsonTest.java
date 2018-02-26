package examples;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlJsonTest {

    ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
    ObjectMapper jsonMapper = new ObjectMapper();

    String yamlStr = "" +
    "platforms:\n" +
    "  zookeeper:\n"+
    "    pack: main/zookeeper\n" +
    "    pack_version: '1'\n" +
    "    variables:\n" +
    "      version: '3.4.6'";

    @Test
    public void testYamlToJson() throws Exception {
        JsonNode json = yamlMapper.readTree(yamlStr);
        System.out.println(json);
        json = yamlMapper.readTree(json.toString());
        System.out.println(yamlMapper.writeValueAsString(json));
    }
}

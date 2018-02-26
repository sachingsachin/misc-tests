package examples;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient.Builder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {

    String ZK = "YOUR-ZK-HERE";
    CloudSolrClient solr;

    public SolrTest() {
        Builder solrBuilder = new Builder();
        solrBuilder.withZkHost(ZK);
        solr = solrBuilder.build();
        solr.setDefaultCollection("tmp-collection");
    }

    @Test
    public void testGetById() throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "foo");
        doc.addField("tmp_i", 32);
        doc.addField("dbLockVersion_l", System.currentTimeMillis());
        UpdateResponse uResponse = solr.add(doc);
        System.out.println("Update Response: " + uResponse);
        SolrDocument docRead = solr.getById("foo");
        System.out.println("Doc Read: " + docRead);
        SolrQuery query = new SolrQuery();
        query.setQuery("id:foo");
        QueryResponse qResponse = solr.query(query);
        System.out.println("Query By Id Response: " + qResponse.getResults());
        query = new SolrQuery();
        query.setQuery("tmp_i:32");
        qResponse = solr.query(query);
        System.out.println("Query By field Response: " + qResponse.getResults());
    }
}

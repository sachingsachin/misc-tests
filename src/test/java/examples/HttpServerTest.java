package examples;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.net.httpserver.HttpServer;

/**
 * Creates an HTTP server and tests its URLs by hitting them.
 * Test output:
 * <pre>
 * Default timeout: 0
 * code = 200, result = Hello there                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             , error =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
 * Default timeout: 0
 * code = 200, result = Hi there                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                , error =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
 * Default timeout: 0
 * </pre>
 */
public class HttpServerTest {

    int port = 12345;
    private HttpServer httpServer;

    /**
     * Create an HttpServer listening on the above port
     * The server also responds only to the urls /hello and /hi
     * Any other URL requested on this server is met with a FileNotFoundException
     */
    @Before
    public void before() throws Exception {

        InetSocketAddress addr = new InetSocketAddress(port);
        // backlog is the maximum number of queued incoming connections to allow on the listening socket.
        // Queued TCP connections exceeding this limit may be rejected by the TCP implementation
        int backlog = 0;

        httpServer = HttpServer.create(addr, backlog);
        httpServer.createContext("/hello", exchange -> {
            exchange.sendResponseHeaders(200, 0);
            final OutputStream outputStream = exchange.getResponseBody();
            outputStream.write("Hello there".getBytes());
            outputStream.close();
        });
        httpServer.createContext("/hi", exchange -> {
            exchange.sendResponseHeaders(200, 0);
            final OutputStream outputStream = exchange.getResponseBody();
            outputStream.write("Hi there".getBytes());
            outputStream.close();
        });
        httpServer.start();
    }

    @After
    public void after() {
        int delay = 0; // the maximum time in seconds to wait until exchanges have finished.
        httpServer.stop(delay);
    }

    @Test
    public void testHello() throws Exception {
        checkUrl("http://127.0.0.1:" + port + "/hello");
    }

    @Test
    public void testHi() throws Exception {
        checkUrl("http://127.0.0.1:" + port + "/hi");
    }

    @Test (expected = FileNotFoundException.class)
    public void testRoot() throws Exception {
        checkUrl("http://127.0.0.1:" + port);
    }

    @Test (expected = MalformedURLException.class)
    public void testNoProtocol() throws Exception {
        checkUrl("127.0.0.1:" + port + "/hello");
    }

    private void checkUrl(String url) throws Exception {
        final HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        System.out.println("Default timeout: " + connection.getConnectTimeout());
        connection.setConnectTimeout(1000);
        connection.connect();
        final int code = connection.getResponseCode();
        byte[] resultBuf = new byte[1000];
        byte[] errorBuf = new byte[1000];
        readStream(connection.getInputStream(), resultBuf);
        readStream(connection.getErrorStream(), errorBuf);
        connection.disconnect();
        System.out.println("code = " + code + ", result = " + new String(resultBuf) + ", error = " + new String(errorBuf));
    }

    private void readStream(InputStream stream, byte[] buf) throws Exception {
        if (stream != null)
            stream.read(buf);
    }
}

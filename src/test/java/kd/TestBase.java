package kd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

/**
 * Enable simulation of user input via command line interface and record output
 */
public class TestBase {

    // Read command line interface output
    protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    protected IO io;

    // Simulate command line interface user input (scanner)
    InputStream stdin;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        stdin = System.in;
        io = new IO();
    }

    @After
    public void tearDown() {
        System.setIn(stdin);
    }

    String[] getOutputLines() {
        return outputStream.toString().split("\\r?\\n");
    }

    void setInput(String str) {
        System.setIn(new ByteArrayInputStream((str + "\r\n").getBytes()));
    }

}

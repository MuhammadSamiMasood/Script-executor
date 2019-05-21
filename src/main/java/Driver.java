import java.net.MalformedURLException;

public class Driver {

    public static void main(String args[]) throws MalformedURLException {
        SimpleAppWalkTest simpleAppWalkTest = new SimpleAppWalkTest();
        simpleAppWalkTest.setup();
        simpleAppWalkTest.test();
        simpleAppWalkTest.stop();

    }
}

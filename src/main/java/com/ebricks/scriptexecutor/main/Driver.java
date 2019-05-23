package com.ebricks.scriptexecutor.main;

import com.ebricks.scriptexecutor.Processor.ScriptProcessor;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Driver {

    public static void main(String args[]) throws MalformedURLException {

        ScriptProcessor scriptProcessor = new ScriptProcessor();

        try {
            scriptProcessor.init();
            scriptProcessor.process();
            scriptProcessor.end();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }
}

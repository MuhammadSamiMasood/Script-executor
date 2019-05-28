package com.ebricks.scriptexecutor.main;

import com.ebricks.scriptexecutor.processor.ScriptProcessor;
import com.ebricks.scriptexecutor.resource.MobileDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;

public class Driver {

    public static Logger logger = LogManager.getLogger(Driver.class);

    public static void main(String args[]) throws MalformedURLException {

        ScriptProcessor scriptProcessor = new ScriptProcessor();

        try {
            scriptProcessor.init();
            scriptProcessor.process();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            logger.error(e);
        } catch (ParserConfigurationException e) {
            logger.error(e);
        } catch (InterruptedException e) {
            logger.error(e);
        }
        finally {
            try {
                scriptProcessor.end();
            } catch (IOException e) {
                logger.error(e);
            }
        }

    }
}

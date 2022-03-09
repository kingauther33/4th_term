package com.company;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxParserDemo {
    public static void main(String[] args) {
        try {
            File file = new File("input.xml");
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            ParseHandler handler = new ParseHandler();
            saxParser.parse(file, handler);
            System.out.println(handler.getList());

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

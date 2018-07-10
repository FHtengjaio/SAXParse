package com.imooc.sax;

import com.imooc.handler.ImoocHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SAXParse {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //创建SAX解析器工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //创建SAX解析器
        SAXParser parser = factory.newSAXParser();
        //
        parser.parse("F:\\IdeaProjects\\SAXParse\\xmls\\firstxmlhomework.xml", new ImoocHandler());
    }
}

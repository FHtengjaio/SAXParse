package com.imooc.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ImoocHandler extends DefaultHandler {

    //定义一个变量用来保存当前正在处理的tag
    private String currentTag;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("解析文档开始");
    }

    //解析文档结束时触发该方法
    @Override
    public void endDocument() throws SAXException {
        System.out.println("解析文档结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("开始处理元素:<"+qName+">");
        currentTag = qName;
        if(attributes.getLength()>0){
            System.out.println("<"+currentTag+">的属性内容如下:");
            for(int i=0; i<attributes.getLength(); i++){
                System.out.println(attributes.getQName(i)+":::"+attributes.getValue(i));
            }
        }
    }

    //解析元素结束时触发该方法
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("解析<"+qName+">元素结束");
    }


    //每当处理文本数据时将触发该方法
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch,start,length);
        if(content.trim().length()>0){
            System.out.println("<"+currentTag+">元素的内容是:"+content.trim());
        }
    }
}

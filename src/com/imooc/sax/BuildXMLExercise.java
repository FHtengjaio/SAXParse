package com.imooc.sax;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuildXMLExercise {
    public static void main(String[] args) throws TransformerConfigurationException, SAXException {

        Map<String, ArrayList<Course>> courseMap = new HashMap<>();
        ArrayList<Course> basicsSet = new ArrayList<>();
        ArrayList<Course> majorSet = new ArrayList<>();

        basicsSet.add(new Course("大学英语","36","考试"));
        basicsSet.add(new Course("高等数学","70","考试"));
        basicsSet.add(new Course("计算机应用基础","108","上机操作"));
        basicsSet.add(new Course("工程制图","60","考查"));

        majorSet.add(new Course("Java基础入门","72","考试"));
        majorSet.add(new Course("MySQL数据库入门","108","考试"));
        majorSet.add(new Course("网页设计与制作","74","考查"));

        courseMap.put("基础课程", basicsSet);
        courseMap.put("专业课", majorSet);

        //指定XML文档存放的对象和位置
        Result resultXml = new StreamResult(new File("E:\\Self Study\\exerciseXML.xml"));

        //获取sax生产工厂对象
        SAXTransformerFactory saxTransformerFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();

        //获取sax生成处理者对象实例
        TransformerHandler transformerHandler = saxTransformerFactory.newTransformerHandler();
        transformerHandler.setResult(resultXml);

        //获取sax生成器
        Transformer transformer = transformerHandler.getTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");

        AttributesImpl attrs = new AttributesImpl();

        transformerHandler.startDocument();
        transformerHandler.startElement("","","教学计划", attrs);

        for(String key: courseMap.keySet()){ //key表示课程分类
            for(int i=0; i<courseMap.get(key).size(); i++){ //循环获取课程分类下的所有课程
                Course course = courseMap.get(key).get(i);
                transformerHandler.startElement("","",key,attrs);

                //基础课程第一本教材
                transformerHandler.startElement("","","课程名",attrs);
                transformerHandler.characters(course.getName().toCharArray(),0,course.getName().length());
                transformerHandler.endElement("","","课程名");

                transformerHandler.startElement("","","课时",attrs);
                transformerHandler.characters(course.getPrice().toCharArray(),0,course.getPrice().length());
                transformerHandler.endElement("","","课时");

                transformerHandler.startElement("","","考核方式",attrs);
                transformerHandler.characters(course.getMethod().toCharArray(),0,course.getMethod().length());
                transformerHandler.endElement("","","考核方式");

                transformerHandler.endElement("","",key);
            }
        }
        transformerHandler.endElement("","","教学计划");
        transformerHandler.endDocument();
    }

    private static class Course{
        private String name;
        private String price;
        private String method;

        public Course(String name, String price, String method){
            this.name = name;
            this.price = price;
            this.method = method;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }
}

package com.company;

import com.company.entity.Student;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class ParseHandler extends DefaultHandler {

    private final ArrayList<Student> list;
    private Student currentStudent;
    private String currentTag = "";

    public ParseHandler() {
        list = new ArrayList<>();
    }

    public ArrayList<Student> getList() {
        return list;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Thẻ mở<" + qName + ">");
        switch (qName) {
            case "student":
                currentStudent = new Student();
                break;
            case "firstName":
                currentTag = "firstName";
                break;
            case "lastName":
                currentTag = "lastName";
                break;
            case "nickname":
                currentTag = "nickname";
                break;
            case "marks":
                currentTag = "marks";
                break;
        }
        if (attributes.getLength() > 0) {
            String rollNo = attributes.getValue("rollno");
            currentStudent.setRollNo(rollNo);
        }
        super.endElement(uri, localName, qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Thẻ đóng<" + qName + ">");
        if (qName.equals("student")) {
            list.add(currentStudent);
        } else {
            currentTag = "";
        }
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length);
        System.out.println("Nội dung '" + new String(ch, start, length) + "'");
        switch (currentTag) {
            case "firstName":
                currentStudent.setFirstName(content);
                break;
            case "lastName":
                currentStudent.setLastName(content);
                break;
            case "nickname":
                currentStudent.setNickname(content);
                break;
            case "marks":
                currentStudent.setMarks(Integer.parseInt(content));
                break;
        }
        super.characters(ch, start, length);
    }
}

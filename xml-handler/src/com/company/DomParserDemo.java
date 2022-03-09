package com.company;

import com.company.entity.Post;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DomParserDemo {

    public static void main(String[] args) {
        try {
            DocumentBuilder documentBuilder = null;
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("https://vnexpress.net/rss/suc-khoe.rss");
            NodeList listElement = document.getElementsByTagName("item");
            ArrayList<Post> posts = new ArrayList<>();
            for (int i = 0; i < listElement.getLength(); i++) {
                Post post = new Post();
                Node node = listElement.item(i);
                for (int j = 0; j < node.getChildNodes().getLength(); j++) {
                    Node childNode = node.getChildNodes().item(j);
                    if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                        switch (childNode.getNodeName()) {
                            case "title":
                                post.setTitle(childNode.getTextContent());
                                break;
                            case "description":
                                post.setDescription(childNode.getTextContent());
                                break;
                            case "pubDate":
                                post.setPubDate(childNode.getTextContent());
                                break;
                            case "link":
                                post.setLink(childNode.getTextContent());
                                break;
                            case "guid":
                                post.setGuid(childNode.getTextContent());
                                break;
                        }
                    }
                }
                posts.add(post);
            }

            for (Post post :
                    posts) {
                System.out.println(post);
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}

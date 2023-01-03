package com.automate.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestUtils {

  public static void deleteFolder(File file) {
    File[] files = file.listFiles();
    if (Objects.nonNull(files))
      Arrays.asList(files).forEach(content -> file.delete());
  }

  public static HashMap<String, String> parseStringXML(InputStream in) throws IOException, SAXException, ParserConfigurationException {
    HashMap<String, String> map = new HashMap<>();

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = docFactory.newDocumentBuilder();

    Document document = builder.parse(in);
    document.getDocumentElement().normalize();

    Element root = document.getDocumentElement();
    NodeList nList = document.getElementsByTagName("string");

    for (int temp = 0; temp < nList.getLength(); temp++) {
      Node node = nList.item(temp);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
        map.put(element.getAttribute("name"), element.getTextContent());
      }
    }
    return map;
  }

  public static Logger log() {
    return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
  }
}

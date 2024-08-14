package com.vaadinUI.UI.UIService;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.springframework.core.io.ClassPathResource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static List<RowData> parseXml() throws Exception {
        List<RowData> rows = new ArrayList<>();

        // Load XML file from resources
        ClassPathResource resource = new ClassPathResource("transform.xml");
        InputStream xmlInputStream = resource.getInputStream();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlInputStream);

        String id = doc.getElementsByTagName("id").item(0).getTextContent();
        String batch = doc.getElementsByTagName("batch").item(0).getTextContent();

        NodeList nodeList = doc.getElementsByTagName("row");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element rowElement = (Element) nodeList.item(i);
            String description = rowElement.getElementsByTagName("description").item(0).getTextContent();
            rows.add(new RowData(id, batch, description));
        }

        return rows;
    }

    public static class RowData {
        private String id;
        private String batch;
        private String description;

        public RowData(String id, String batch, String description) {
            this.id = id;
            this.batch = batch;
            this.description = description;
        }

        public String getId() {
            return id;
        }

        public String getBatch() {
            return batch;
        }

        public String getDescription() {
            return description;
        }
    }
}

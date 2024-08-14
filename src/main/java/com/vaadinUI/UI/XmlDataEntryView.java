package com.vaadinUI.UI;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.w3c.dom.Document;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;

@Route("xml-dataEntry")
public class XmlDataEntryView extends VerticalLayout {
    @Value("${app.base.url}")
    private String baseUrl;
    private final RestTemplate restTemplate;

    @Autowired

    public XmlDataEntryView(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        TextArea xmlTextArea = new TextArea("XML Data");
        xmlTextArea.setWidthFull();
        xmlTextArea.setHeight("300px");
        String result = "";
        Button submitButton = new Button("Convert", event -> {
            String xmlData = xmlTextArea.getValue();
            String response = restTemplate.postForObject(baseUrl + "/api/process-xml", xmlData, String.class);
            var formatedResponse = formatXml(response);
            xmlTextArea.setValue(formatedResponse);
            xmlTextArea.setRequired(true);
            // result = response;
            if (response != null) {
                String wrappedResponse = "<div>" + response + "</div>"; // Wrap in a single container element
                Html html = new Html(wrappedResponse);
                add(html);
            } else {
                // Handle null response case
                add(new Html("<p>No response received.</p>"));
            }

        });
        add(xmlTextArea, submitButton);
    }

    private String formatXml(String xml) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new java.io.ByteArrayInputStream(xml.getBytes()));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return xml; // Return unformatted XML if an error occurs
        }
    }
}

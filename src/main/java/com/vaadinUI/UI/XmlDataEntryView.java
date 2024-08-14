package com.vaadinUI.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;

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
            xmlTextArea.setValue(response);
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
}

package com.vaadinUI.UI;

import org.springframework.web.bind.annotation.RestController;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class APIController {
    @PostMapping("/api/process-xml")
    public String postMethodName(@RequestBody String xmlData) {
        // TODO: process POST request
        var result = transform(xmlData);
        return result;
    }

    public String transform(String message) {

        String result = "";

        try {
            StringReader reader = new StringReader(message);
            StringWriter writer = new StringWriter();
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(
                    new javax.xml.transform.stream.StreamSource("src/main/resources/transform.xsl"));

            transformer.transform(
                    new javax.xml.transform.stream.StreamSource(reader),
                    new javax.xml.transform.stream.StreamResult(writer));

            result = writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

}

Task:
 * Build REST API that consumes XML (src/main/resources/data.xml) 
    * Modify DemoController class by adding HTTP interface:
         * POST: /transform
         * Content-Type: application/xml
 * Modifies received XML message according to format below.
 * You will need Java and Maven to build and run the project.

This is what the service should respond (no comments needed):
     
     <order>
         <orderId>123_7</orderId> <!-- id + batch -->
         <documentDateTime>2021-05-31T11:59:15.932Z</documentDateTime> <!-- current datetime -->
         <orderRows>
            <orderRow>
                <rowNumber>1</rowNumber>
                <description>text1</description>
            </orderRow>
            <orderRow>
                <rowNumber>2</rowNumber>
                <description>text2</description>
            </orderRow>
            <orderRow>ion>text3</description>
            </orderRow>
                <rowNumber>3</rowNumber>
                <descript
         </orderRows>
     </order>


* TIPS:
  * /src/main/resources/transform.xsl: blank for creating XSLT-transformer
  * DemoTransformer class can be used for transforming.
  * https://spring.io/guides/tutorials/rest/

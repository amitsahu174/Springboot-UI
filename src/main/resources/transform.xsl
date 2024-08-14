<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:date="http://exslt.org/dates-and-times" extension-element-prefixes="date">

    <!-- Template to match the root element -->
    <xsl:template match="/root">
        <order>
            <!-- Combine id and batch to create orderId -->
            <orderId>
                <xsl:value-of select="concat(id, '_', batch)"/>
            </orderId>

            <!-- Generate the current date and time -->
            <documentDateTime>
                <xsl:value-of select="date:date-time()"/>
            </documentDateTime>

            <orderRows>
                <!-- Loop through each row element -->
                <xsl:for-each select="rows/row">
                    <orderRow>
                        <!-- Generate the row number based on position -->
                        <rowNumber>
                            <xsl:value-of select="position()"/>
                        </rowNumber>
                        <!-- Extract the description -->
                        <description>
                            <xsl:value-of select="description"/>
                        </description>
                    </orderRow>
                </xsl:for-each>
            </orderRows>
        </order>
    </xsl:template>

</xsl:stylesheet>

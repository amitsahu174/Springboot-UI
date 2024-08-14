package com.vaadinUI.UI.UI;

import java.util.List;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadinUI.UI.UIService.XmlParser;

@Route("list-view")
@PageTitle("List View")
public class ListView extends VerticalLayout {
    public ListView() {
        // Create a Grid component
        Grid<XmlParser.RowData> grid = new Grid<>(XmlParser.RowData.class);
        grid.removeAllColumns();
        // Set up the grid columns
        grid.addColumn(XmlParser.RowData::getId).setHeader("ID");
        grid.addColumn(XmlParser.RowData::getBatch).setHeader("Batch");
        grid.addColumn(XmlParser.RowData::getDescription).setHeader("Description");

        // Load and display the data
        try {
            List<XmlParser.RowData> rowDataList = XmlParser.parseXml();
            grid.setItems(rowDataList);
        } catch (Exception e) {
            // Handle parsing exception
            e.printStackTrace();
        }

        // Add the grid to the layout
        add(grid);
    }
}

package com.krzysztof.kuznia.backend.ui;

import com.krzysztof.kuznia.backend.entity.Tool;
import com.krzysztof.kuznia.backend.form.ToolForm;
import com.krzysztof.kuznia.backend.service.ToolService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
@CssImport("./styles/tool-styles.css")
public class ToolView extends VerticalLayout {
    private ToolService toolService;
    private Grid<Tool> grid = new Grid<>(Tool.class);
    private TextField filterText = new TextField();

    private ToolForm toolForm;

    public ToolView (ToolService toolService){
        this.toolService = toolService;
        addClassName("list-tool_view");
        setSizeFull();

        configureGrid();

        toolForm = new ToolForm(toolService);
        toolForm.addListener(ToolForm.SaveEvent.class, this::saveTool);
        toolForm.addListener(ToolForm.DeleteEvent.class, this::deleteTool);
        toolForm.addListener(ToolForm.CloseEvent.class, e->closeEditor());

        Div content = new Div(grid, toolForm);

        content.addClassName("content-tool_view");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();

        closeEditor();
    }

    private void saveTool(ToolForm.SaveEvent event) {
        toolService.save(event.getTool());
        updateList();
        closeEditor();
    }

    private void deleteTool(ToolForm.DeleteEvent event) {
        toolService.delete(event.getTool());
        updateList();
        closeEditor();
    }

    public void editTool(Tool tool){
        if (tool == null){
            closeEditor();
        } else {
            toolForm.setTool(tool);
            toolForm.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        toolForm.setTool(null);
        toolForm.setVisible(false);
        removeClassName("editing");
    }

    private void configureGrid() {
        grid.addClassName("grid-tool_view");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editTool(event.getValue()));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addToolButton = new Button ("Add tool");
        addToolButton.addClickListener(click -> addTool());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addToolButton);
        toolbar.addClassName("toolbar-tool_view");
        return toolbar;
    }

    private void addTool() {
        grid.asSingleSelect().clear();
        editTool(new Tool());
    }


    private void updateList() {
        grid.setItems(toolService.findAll(filterText.getValue()));
    }


}

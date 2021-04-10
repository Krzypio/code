package com.krzysztof.kuznia.backend.form;

import com.krzysztof.kuznia.backend.entity.Tool;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class ToolForm extends FormLayout {
    private Tool tool;

    TextField name = new TextField("Tool name");

    Button saveButton = new Button("Save");
    Button deleteButton = new Button("Delete");
    Button cancelButton = new Button("Cancel");

    Binder<Tool> binder = new BeanValidationBinder<>(Tool.class);

    public ToolForm() {
        addClassName("tool_form");
        binder.bindInstanceFields(this);
        add(name, createButtonsLayout());
    }


    private Component createButtonsLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        saveButton.addClickListener(event -> validateAndSave());
        deleteButton.addClickListener(event -> fireEvent(new DeleteEvent(this, tool)));
        cancelButton.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> saveButton.setEnabled(binder.isValid()));

        return new HorizontalLayout(saveButton, deleteButton, cancelButton);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(tool);
            fireEvent(new SaveEvent(this, tool));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setTool(Tool tool) {
        this.tool = tool;
        binder.readBean(tool);
    }

    // Events
    public static abstract class ToolFormEvent extends ComponentEvent<ToolForm> {
        private Tool item;

        protected ToolFormEvent(ToolForm source, Tool item) {
            super(source, false);
            this.item = item;
        }

        public Tool getTool() {
            return item;
        }
    }

    public static class SaveEvent extends ToolFormEvent {
        SaveEvent(ToolForm source, Tool item) {
            super(source, item);
        }
    }

    public static class DeleteEvent extends ToolFormEvent {
        DeleteEvent(ToolForm source, Tool item) {
            super(source, item);
        }

    }

    public static class CloseEvent extends ToolFormEvent {
        CloseEvent(ToolForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}

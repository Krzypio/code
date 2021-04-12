package com.krzysztof.kuznia.backend.form;

import com.krzysztof.kuznia.backend.entity.Tool;
import com.krzysztof.kuznia.backend.service.ToolService;
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
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.shared.Registration;

import java.util.Date;

public class ToolForm extends FormLayout {
    ToolService toolService;
    private Tool tool;

    TextField name = new TextField("Tool name");

    Button saveButton = new Button("Save");
    Button deleteButton = new Button("Delete");
    Button cancelButton = new Button("Cancel");
    Button withdrawButton = new Button("Withdraw");
    Button restoreButton = new Button("Restore");


    Binder<Tool> binder = new BeanValidationBinder<>(Tool.class);

    public ToolForm(ToolService toolService) {
        this.toolService = toolService;
        addClassName("tool_form");

        binder.bindInstanceFields(this);

        binder.forField(name)
                .withValidator(candidateName -> toolService.findAll().stream().noneMatch(p -> p.getName().equals(candidateName) && p.getId() != tool.getId()), "Name must be unique")
                .withValidator(new StringLengthValidator("Name length must be between 3 and 50", 3, 50))
                .bind(Tool::getName, Tool::setName);

        add(name, createButtonsLayout());
    }

    void setWithdrawButton(){
        boolean isExist = toolService.findAll().contains(tool);
        boolean isWithdrawn = false;
        if (tool != null)
            isWithdrawn = tool.getWithdrawDate().getTime() != Long.MIN_VALUE;
        boolean isEnable = isExist && !isWithdrawn;
        withdrawButton.setVisible(isEnable); //before was isExist, but we dont want to see this when we have restore button
        withdrawButton.setEnabled(isEnable);
    }

    void setRestoreButton(){
        boolean isWithdrawed = false;
        if (tool != null)
            isWithdrawed = tool.getWithdrawDate().getTime() != Long.MIN_VALUE;
        restoreButton.setVisible(isWithdrawed);
        restoreButton.setEnabled(isWithdrawed);
    }


    private Component createButtonsLayout() {
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        cancelButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        withdrawButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        restoreButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        saveButton.addClickListener(event -> validateAndSave());
        deleteButton.addClickListener(event -> fireEvent(new DeleteEvent(this, tool)));
        cancelButton.addClickListener(event -> fireEvent(new CloseEvent(this)));
        withdrawButton.addClickListener(event -> setWithdrawDateAndSave(new Date(System.currentTimeMillis())));
        restoreButton.addClickListener(event -> setWithdrawDateAndSave(new Date(Long.MIN_VALUE)));

        binder.addStatusChangeListener(e -> saveButton.setEnabled(binder.isValid()));

        return new HorizontalLayout(saveButton, deleteButton, cancelButton, withdrawButton, restoreButton);
    }

    private void setWithdrawDateAndSave(Date date) {
        tool.setWithdrawDate(date);
        validateAndSave();
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
        boolean toolExist = toolService.findAll().contains(tool);
        binder.readBean(tool);

        setWithdrawButton();
        setRestoreButton();
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

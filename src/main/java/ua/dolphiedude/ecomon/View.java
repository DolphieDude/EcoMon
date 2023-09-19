package ua.dolphiedude.ecomon;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends VerticalLayout {
    private FacilityRepository repository;
    private TextField name = new TextField("Name");
    private TextField activity = new TextField("Activity");
    private TextField ownership = new TextField("Ownership");
    private TextField ecologicalDescription = new TextField("Ecological Description");


    public View(FacilityRepository repository) {
        this.repository = repository;
        add(getFacilityForm());
    }

    private Component getFacilityForm() {
        var layout = new HorizontalLayout();
        layout.setAlignItems(Alignment.BASELINE);
        Button addButton = new Button("Add");
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        layout.add(name, activity, ownership, ecologicalDescription, addButton);
        return layout;
    }
}

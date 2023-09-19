package ua.dolphiedude.ecomon;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends VerticalLayout {
    private FacilityRepository repository;
    private TextField name = new TextField("Name");
    private TextField activity = new TextField("Activity");
    private TextField ownership = new TextField("Ownership");
    private TextField ecologicalDescription = new TextField("Ecological Description");
    private Binder binder = new Binder<>(Facility.class);


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
        binder.bindInstanceFields(this);

        addButton.addClickListener(add -> {
            try {
                Facility facility = new Facility();
                binder.writeBean(facility);
                repository.save(facility);
                binder.readBean(new Facility());
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        });
        return layout;
    }
}

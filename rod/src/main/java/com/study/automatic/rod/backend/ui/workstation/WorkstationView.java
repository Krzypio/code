package com.study.automatic.rod.backend.ui.workstation;

import com.study.automatic.rod.backend.entity.Material;
import com.study.automatic.rod.backend.service.MaterialService;
import com.study.automatic.rod.backend.ui.MainLayout;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.*;
import java.util.Comparator;
import java.util.List;

@Route(value = "workstation", layout = MainLayout.class)
//@CssImport("./styles/workstation-styles.css")
@PageTitle("Workstation | Automatic")
public class WorkstationView extends VerticalLayout {
    private MaterialService materialService;
    private ChoosenMaterialForm firstMaterial;
    //private ComboBox<Material> firstMaterialBox = new ComboBox<>("First Material");
    //private ComboBox<Material> secondMaterialBox = new ComboBox<>("Second Material");

    /*public WorkstationView() {
        addClassName("workstation-view");
        setSizeFull();
        //Image image = new Image("./images/rys1.png", "rys");

        add(image);
    }*/

    public WorkstationView(MaterialService materialService) {
        this.materialService = materialService;
        addClassName("workstation-view");
        setSizeFull();
        File file = new File("src\\main\\webapp\\images\\rys1.png");    //windows
        //File file = new File("src/main/webapp/images/rys1.png");    //linux
        System.out.println("Expecting to find file from " + file.getAbsolutePath());

        Image image = new Image(new StreamResource("rys1.png", () -> {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                // file not found
                e.printStackTrace();
            }
            return null;
        }), "alt text");
        add(image);
        add(createComboBoxLayout());
    }

    private HorizontalLayout createComboBoxLayout() {
        firstMaterial = new ChoosenMaterialForm(materialService);
        /*List<Material> materials = materialService.findAll();
        materials.sort(Comparator.comparing(Material::getName, String::compareToIgnoreCase));
        firstMaterialBox.setItems(materials);
        firstMaterialBox.setItemLabelGenerator(Material::getName);

        secondMaterialBox.setItems(materials);
        secondMaterialBox.setItemLabelGenerator(Material::getName);*/

        return new HorizontalLayout(firstMaterial);
    }

}

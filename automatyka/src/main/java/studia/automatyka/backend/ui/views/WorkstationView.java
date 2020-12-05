package studia.automatyka.backend.ui.views;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "workstation", layout = MainLayout.class)
//@CssImport("./styles/workstation-styles.css")
@PageTitle("Workstation | Automatic")
public class WorkstationView extends VerticalLayout {

    public WorkstationView() {
        addClassName("workstation-view");
        setSizeFull();
        Image image = new Image("./images/rys1.png", "rys");
        add(image);
    }
}

package studia.automatyka.backend.ui.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import studia.automatyka.backend.ui.views.material.MaterialView;

import java.util.Arrays;
import java.util.List;

@CssImport("./styles/main_layout-styles.css")
public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Authomatic");
        logo.addClassName("logo");

        Anchor logout = new Anchor("logout", "Log out");
        String username = "...";

        VaadinSession session = VaadinSession.getCurrent();
        try {
            username = session.getAttribute("username").toString();
        } catch(Exception e) {
            // no username is session
            Notification.show("Cant get username");
        }
        Label userLabel = new Label(username);

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo/*, userLabel, logout*/);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink materialLink = new RouterLink("Materials", MaterialView.class);
        RouterLink workstationLink = new RouterLink("Workstation", WorkstationView.class);
        RouterLink how_to_calculate_alphaLink = new RouterLink("How to calculate \u03b1", HowToCalculateAlphaView.class);

        List<RouterLink> links = Arrays.asList(materialLink, workstationLink, how_to_calculate_alphaLink);
        for (RouterLink link: links){
            link.setHighlightCondition(HighlightConditions.sameLocation());
            addToDrawer(new VerticalLayout(link));
        }//for

    }
}

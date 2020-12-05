package studia.automatyka.backend.ui.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import studia.automatyka.backend.pdf.EmbeddedPdfDocument;

@Route(value = "how_to_calculate_alpha", layout = MainLayout.class)
//@CssImport("./styles/workstation-styles.css")
@PageTitle("How to calculate \u03b1 | Automatic")
public class HowToCalculateAlphaView extends Div {

    public HowToCalculateAlphaView() {
        add(new EmbeddedPdfDocument("pdf/zad1.pdf"));
        setHeight("100%");
    }//HowToCalculateAlphaView()
}
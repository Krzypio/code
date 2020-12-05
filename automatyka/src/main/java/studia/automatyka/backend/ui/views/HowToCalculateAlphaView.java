package studia.automatyka.backend.ui.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import studia.automatyka.backend.pdf.EmbeddedPdfDocument;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Route(value = "how_to_calculate_alpha", layout = MainLayout.class)
//@CssImport("./styles/workstation-styles.css")
@PageTitle("How to calculate \u03b1 | Automatic")
public class HowToCalculateAlphaView extends Div {

    public HowToCalculateAlphaView(){
    add(new EmbeddedPdfDocument(new StreamResource("zad1.pdf", () -> {
        try {
            return getPdfInputStream();
        } catch (FileNotFoundException e) {
            return new ByteArrayInputStream(new byte[]{});
        }
    })));
    setHeight("100%");
}

    private InputStream getPdfInputStream() throws FileNotFoundException {
        //return new FileInputStream("E:\\0Krzys\\projects\\automatyka\\automatyka\\zad1.pdf");
        return new FileInputStream("/usr/home/krzypio/repo/zad1.pdf");
    }
}
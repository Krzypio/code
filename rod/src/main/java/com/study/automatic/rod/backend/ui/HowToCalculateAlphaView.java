package com.study.automatic.rod.backend.ui;

import com.study.automatic.rod.backend.pdf.EmbeddedPdfDocument;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "how_to_calculate_alpha", layout = MainLayout.class)
//@CssImport("./styles/workstation-styles.css")
@PageTitle("How to calculate \u03b1 | Automatic")
public class HowToCalculateAlphaView extends Div {

    public HowToCalculateAlphaView() {
        add(new EmbeddedPdfDocument("pdf/zad1.pdf"));
        setHeight("100%");

        runJavaScript();
    }//HowToCalculateAlphaView()

    public static void runJavaScript() {
        Page page = UI.getCurrent().getPage();
        page.executeJs("alert(\"Choose wisely your prey\");");
    }
}
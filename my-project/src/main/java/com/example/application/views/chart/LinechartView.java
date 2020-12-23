package com.example.application.views.chart;

import com.study.automatic.rod.backend.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;

@Route(value = "chart", layout = MainLayout.class)
@PageTitle("Chart")
public class LinechartView extends VerticalLayout {
    public LinechartView() {
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        add(getOverallStats(), getChart());
    }

    private Component getOverallStats(){
        Span stats = new Span(10 + "taki Span");
        stats.addClassName("stats");
        return stats;
    }

    private Map<String, Integer> getStats(){
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("Stefan", 12);
        stats.put("Edward", 30);
        stats.put("Ania", 4);
        return stats;
    }

    private Chart getChart(){
        Chart chart = new Chart(ChartType.PIE);
        DataSeries dataSeries = new DataSeries();
        Map<String, Integer> stats = getStats();
        stats.forEach((imie, ilosc)->
                dataSeries.add(new DataSeriesItem(imie, ilosc))
        );
        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}

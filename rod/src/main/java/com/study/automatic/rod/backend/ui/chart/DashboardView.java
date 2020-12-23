package com.study.automatic.rod.backend.ui.chart;

import com.study.automatic.rod.backend.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.Map;

@PageTitle("Dashboard | Vaadin CRM")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    public DashboardView() {

        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);

        add(
                getCompaniesChart()
        );
    }

    private Component getCompaniesChart() {
        Chart chart = new Chart(ChartType.SPLINE);

        DataSeries dataSeries = new DataSeries();
        Map<Integer, Integer> stats = new HashMap<>();
        stats.put(1, 10);
        stats.put(2, 20);
        stats.put(3, 40);
        stats.put(4, 10);
        stats.put(5, 20);
        stats.put(6, 40);
        stats.put(7, 10);
        stats.put(8, 20);
        stats.put(9, 40);
        stats.put(10, 10);
        stats.put(11, 20);
        stats.put(12, 40);
        stats.forEach((name, number) ->
                dataSeries.add(new DataSeriesItem(name, number)));

        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}

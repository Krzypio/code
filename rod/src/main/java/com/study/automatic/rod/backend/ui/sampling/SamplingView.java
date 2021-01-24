package com.study.automatic.rod.backend.ui.sampling;


import com.study.automatic.rod.backend.entity.Record;
import com.study.automatic.rod.backend.entity.Sample;
import com.study.automatic.rod.backend.service.RecordService;
import com.study.automatic.rod.backend.service.SampleService;
import com.study.automatic.rod.backend.ui.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Route(value = "test", layout = MainLayout.class)
//@CssImport("./styles/workstation-styles.css")
@PageTitle("Test | Automatic")
public class SamplingView extends VerticalLayout {
    private UI ui;
    SampleService sampleService;
    RecordService recordService;

    private Sample sample;
    private StreamingDataExampleView chart;

    private PaperSlider paperSlider;
    private Label sliderValueLabel;

    private Button startButton;
    private Button stopButton;

    private boolean simulationRun = false;
    //thread
    Runnable sampling;
    ScheduledExecutorService executor;

    public SamplingView(SampleService sampleService, RecordService recordService){
        this.chart = new StreamingDataExampleView();
        /*addAttachListener(event -> {
            this.ui = event.getUI();
        });*/
        ui = UI.getCurrent();

        sliderValueLabel = new Label("elo");
        this.sampleService = sampleService;
        this.recordService = recordService;
        addClassName("test-view");
        setSizeFull();
        establishSampling();
        add(createSimulationButtonLayout(), createSlider(), sliderValueLabel, chart);

    }

    private void establishSampling() {
        sampling = new Runnable(){
            public void run() {
                addNewRecord();
            }//run()
        };//Runnable()
    }

    private void addNewRecord(){
        if(sample != null && simulationRun){
            Record newRecord = new Record (sample, paperSlider.getValue());
            recordService.save(newRecord);
            System.out.println("Sample " + sample.getId() + " record " + newRecord.getId() + " value " + paperSlider.getValue());

            //notification
            this.ui.access(() -> {
                Notification.show("Sample " + sample.getId() + " record " + newRecord.getId() + " value " + paperSlider.getValue());
            });
            //refreshPlot();
        }//if
    }

    private HorizontalLayout createSimulationButtonLayout() {
        HorizontalLayout hl = new HorizontalLayout();

        startButton = new Button("Start simulation");
        stopButton = new Button("Stop simulation");

        startButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        stopButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        hl.add(startButton, stopButton);

        setStartEnable();

        startButton.addClickListener(event -> startSampling());
        stopButton.addClickListener(event -> stopSampling());

        return hl;
    }

    private PaperSlider createSlider(){
        this.paperSlider = new PaperSlider();
        paperSlider.setMax(100);
        paperSlider.setPin(true);
        //listener
        paperSlider.addValueChangeListener(e -> {
            sliderValueLabel.setText(e.getValue().toString());
            chart.setSliderValueDouble(e.getValue());
        });//e
        return paperSlider;
    }

    private void setStartEnable(){
        startButton.setVisible(true);
        startButton.setEnabled(true);
        stopButton.setVisible(false);
        stopButton.setEnabled(false);
    }
    private void setStopEnable(){
        startButton.setVisible(false);
        startButton.setEnabled(false);
        stopButton.setVisible(true);
        stopButton.setEnabled(true);
    }

    private void startSampling() {
        sample = new Sample();
        simulationRun = true;
        sampleService.save(sample);
        setStopEnable();

        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(sampling, 0, 1, TimeUnit.SECONDS);
    }

    private void stopSampling() {
        simulationRun = false;
        setStartEnable();

        executor.shutdown();
    }
}

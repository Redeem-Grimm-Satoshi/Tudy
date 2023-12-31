package com.example.application.views.tudy;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;

import java.awt.*;
import java.time.LocalTime;

@PageTitle("Tudy")
@Route(value = "tudy", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TudyView extends VerticalLayout {

    //TextField and Add button declaration
    private TextField list;
    private Button add,clear;

    private CheckboxGroup<String> taskList;

    private HorizontalLayout expireLayout;

    public TudyView() {
        setSpacing(false);

        //Call displayTudy method
        displayTudy();
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    public void displayTudy(){
        VerticalLayout tudyListView=new VerticalLayout();
        HorizontalLayout horizontalLayout=new HorizontalLayout();
        list=new TextField("Task");
        list.setPlaceholder("Enter Task");
        list.setRequired(true);
        list.setTooltipText("You can input your task here");
        add=new Button("Add");
        clear=new Button("Clear");

        //button variants
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add.addClickShortcut(Key.ENTER);

        //title
        H1 title=new H1("Your Tudy List");

        //vert
        tudyListView.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        tudyListView.setMargin(true);
        tudyListView.setWidth("auto");

        //This happens when add button get clicked
        add.addClickListener(e->{

            //initialize new checkbox, get value of textfield and add to it
           // Checkbox task=new Checkbox(" " + list.getValue());
            taskList=new CheckboxGroup<>();
            taskList.setItems(list.getValue());
            taskList.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
            expireLayout=new HorizontalLayout();

            //display time
            TimePicker time=new TimePicker();
            time.setLabel("Expires at");
            time.setValue(LocalTime.now());

            //date picker
            DatePicker start=new DatePicker("Start");
            DatePicker end=new DatePicker("End");

            //remove button
            Button remove=new Button("X");
            remove.addClickShortcut(Key.DELETE);
            remove.addThemeVariants(ButtonVariant.LUMO_ERROR);

            expireLayout.add(taskList,time,start,end,remove);
            expireLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

            //make sure task is not empty
            if(list.isEmpty()){Notification.show("Task Can't Be Empty!");
            }else{
                list.clear();
                //this issue needs to be fixed
                //remove button action
                remove.addClickListener(x->{
                    expireLayout.removeAll();
                });
                tudyListView.add(expireLayout);
            }
        });

        //when clear button click, this happens
        clear.addClickListener(e->{

             tudyListView.removeAll();
             Notification.show("List Cleared!");

        });

        //Make textfield and Button to be horizontally aligned
        horizontalLayout.add(list,add,clear);
        horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        tudyListView.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        //add to scroller
        Scroller scroller=new Scroller(new Div(tudyListView));
        scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
        scroller.getStyle()
                .set("border-bottom", "1px solid var(--lumo-contrast-20pct)")
                .set("padding", "var(--lumo-space-m)");
        scroller.setWidthFull();

        //Now add all components to main vertical layout
        add(title,scroller,horizontalLayout);

    }
}

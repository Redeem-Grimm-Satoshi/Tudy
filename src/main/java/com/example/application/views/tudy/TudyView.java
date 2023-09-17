package com.example.application.views.tudy;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

import java.awt.*;

@PageTitle("Tudy")
@Route(value = "tudy", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TudyView extends VerticalLayout {

    //TextField and Add button declaration
    private TextField list;
    private Button add,clear;

    private CheckboxGroup<String> taskList;

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
        add=new Button("Add");
        clear=new Button("Clear");

        //button variants
        add.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

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

            //make sure task is not empty
            if(list.isEmpty()){Notification.show("Task Can't Be Empty!");
            }else{tudyListView.add(taskList);}
        });

        //when clear button click, this happens
        clear.addClickListener(e->{

        });

        //Make textfield and Button to be horizontally aligned
        horizontalLayout.add(list,add,clear);
        horizontalLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        tudyListView.setDefaultHorizontalComponentAlignment(Alignment.CENTER);


        //Now add all components to main vertical layout
        add(title,tudyListView,horizontalLayout);




    }

}

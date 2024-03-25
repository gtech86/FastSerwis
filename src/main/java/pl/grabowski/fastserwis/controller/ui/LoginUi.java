package pl.grabowski.fastserwis.controller.ui;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class LoginUi extends VerticalLayout{
   TextField field = new TextField("Login");
   HorizontalLayout actions = new HorizontalLayout(field);

}

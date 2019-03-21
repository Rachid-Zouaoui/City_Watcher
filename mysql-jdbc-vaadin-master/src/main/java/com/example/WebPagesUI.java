package com.example;




import com.vaadin.data.Binder;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.Alignment;
//import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.Page.UriFragmentChangedEvent;
import com.vaadin.server.Page.UriFragmentChangedListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
 
@SpringUI
public class WebPagesUI extends UI {
	
	//private VerticalLayout root;
	public static Authentication AUTH;
	@Override
	protected void init(VaadinRequest request) {
		AUTH = new Authentication();
		
		new Navigator(this, this);
        
        getNavigator().addView(LoginPage.NAME, LoginPage.class);
        getNavigator().setErrorView(LoginPage.class);
         
       Page.getCurrent().addUriFragmentChangedListener(new UriFragmentChangedListener() {
             
           @Override
            public void uriFragmentChanged(UriFragmentChangedEvent event) {
                router(event.getUriFragment());
            }
        });
         
         
        router("");

		
		//setupLayout();
		//addPanel();	*/
		
	}
	
	private void router(String route){
        Notification.show(route);
        if(getSession().getAttribute("user") != null){
            getNavigator().addView(SecurePage.NAME, SecurePage.class);
       
            if(route.equals("!Secure")){
                getNavigator().navigateTo(SecurePage.NAME);
            }
        }else{
        		getNavigator().navigateTo(LoginPage.NAME);
        }
    }
	
	/*private void setupLayout() {
		
		root = new VerticalLayout();
		setContent(root);
		
	}
	//setupLayoutHome
	//setupLayoutOpzioni
	/*private void addPanel() {
		
	       
	       //Login Form
	        FormLayout content = new FormLayout();
	        TextField username = new TextField("Username");
	        content.addComponent(username);
	        PasswordField password = new PasswordField("Password");
	        content.addComponent(password);
	       //Button Send
	        Button send = new Button("Enter");
	        send.addClickListener(new ClickListener() {
	            private static final long serialVersionUID = 1L;
	 
	            @Override
	            public void buttonClick(ClickEvent event) {
	                if(AUTH.authenticate(username.getValue(), password.getValue())){
	                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
	                    //crea pagina
	                    Notification.show("Right credentials", Notification.Type.ERROR_MESSAGE);
	                }else{
	                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
	                }
	            }
	             
	        });
	        content.addComponent(send);
	        
	      //Login Panel
	    	Panel panel = new Panel("Login");
	        panel.setSizeUndefined();
	        root.addComponent(panel);
	        content.setMargin(true);
	        panel.setContent(content);
	        //setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	}*/
	
}
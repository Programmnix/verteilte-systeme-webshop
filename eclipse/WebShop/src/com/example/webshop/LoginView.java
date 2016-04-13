package com.example.webshop;

import javax.persistence.EntityManager;

import org.openqa.selenium.remote.server.log.LoggingManager;

import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


public class LoginView extends CssLayout implements View{

	
	private PasswordField password;
	private Button login;
	private Button forgotPassword;
	private TextField username;
	//private transient EntityManager entityManager;

	public LoginView() {
		
        addStyleName("login-screen");

        
        //entityManager = JPAContainerFactory.createEntityManagerForPersistenceUnit(WebshopUI.PERSISTENCE_UNIT);
        // login form, centered in the available part of the screen
        Component loginForm = buildLoginForm();

        // layout to center login form when there is sufficient screen space
        // - see the theme for how this is made responsive for various screen
        // sizes
        VerticalLayout centeringLayout = new VerticalLayout();
        centeringLayout.setStyleName("centering-layout");
        centeringLayout.addComponent(loginForm);
        centeringLayout.setComponentAlignment(loginForm,
                Alignment.MIDDLE_CENTER);

        // information text about logging in
        CssLayout loginInformation = buildLoginInformation();

        addComponent(loginInformation);
        addComponent(centeringLayout);
	}
	
	
	private Component buildLoginForm() {
        FormLayout loginForm = new FormLayout();

        loginForm.addStyleName("login-form");
        loginForm.setSizeUndefined();
        loginForm.setMargin(false);

		loginForm.addComponent(username = new TextField("Username", "max"));
        username.setWidth(15, Unit.EM);
        loginForm.addComponent(password = new PasswordField("Password"));
        password.setWidth(15, Unit.EM);
        password.setDescription("Your secrete password");
        CssLayout buttons = new CssLayout();
        buttons.setStyleName("buttons");
        loginForm.addComponent(buttons);

        buttons.addComponent(login = new Button("Login"));
        login.setDisableOnClick(true);
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    login();
                } finally {
                    login.setEnabled(true);
                }
            }


        });
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        buttons.addComponent(forgotPassword = new Button("Forgot password?"));
        forgotPassword.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //showNotification(new Notification("Hint: Try anything"));
            }
        });
        forgotPassword.addStyleName(ValoTheme.BUTTON_LINK);
        return loginForm;
    }

    protected void login() {
    	String usernamestr = username.getValue();
    	String passwordstr = password.getValue();

    	if(LoginManager.login(usernamestr, passwordstr)){
    		LoginManager.afterForwardToLogin(getUI());;
    	}
    	else{
    		password.clear();
    		Notification.show("False password", "Please try again", Type.ERROR_MESSAGE);
    	}
		
	}


	private CssLayout buildLoginInformation() {
        CssLayout loginInformation = new CssLayout();
        loginInformation.setStyleName("login-information");
        Label loginInfoText = new Label(
                "<h1>Login Information</h1>"
                        + "Please login",
                ContentMode.HTML);
        loginInformation.addComponent(loginInfoText);
        return loginInformation;
    }
	
	@Override
	public void enter(ViewChangeEvent event) {
		if(LoginManager.isLogedIn()){
			Notification.show("You are already logged in");
			LoginManager.forwardToLogin(getUI());
		}	
	}

}

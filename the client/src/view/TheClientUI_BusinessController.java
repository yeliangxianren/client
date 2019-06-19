package view;

import java.net.URL;
import java.util.ResourceBundle;


import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



public class TheClientUI_BusinessController implements Initializable {
	private Main application;
	public void setApp(Main app) {
        this.application = app;
    }
	
	
	@FXML
	public void Back_Client(ActionEvent event)
	{
		application.gotoclient();
	}

	 @Override
	    public void initialize(URL url, ResourceBundle rb){}
}

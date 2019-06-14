package ClientModle;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ClientUIController implements Initializable {
	private Main application;
	public void setApp(Main app) {
        this.application = app;
    }
	
	@FXML 
	public void OUT(ActionEvent event)
	{
		application.gotologin();
	}
	@Override
	 public void initialize(URL url, ResourceBundle rb){
	    }
}

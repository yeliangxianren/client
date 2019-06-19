package view;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TheClientUIController implements Initializable {
	private Main application;
	public void setApp(Main app) {
        this.application = app;
    }
	
	@FXML 
	public void Information_View(ActionEvent event)
	{
		application.gotoclient_user();
	}
	@FXML 
	public void Stock_search(ActionEvent event)
	{
		application.gotoStock_search();

	}
	public void Stock_Business(ActionEvent event)
	{
		application.gotoBusiness();
	}
	public void Back_Client(ActionEvent event)
	{
		application.gotoclient();
	}

	@Override
	 public void initialize(URL url, ResourceBundle rb){
	    }
}
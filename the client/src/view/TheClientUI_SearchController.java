package view;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;



public class TheClientUI_SearchController implements Initializable {
	@FXML JFXTextField Search_id;
	private Main application;
	public void setApp(Main app) {
        this.application = app;
    }
	
	
	@FXML
	public void Is_Search(ActionEvent event)
	{
		application.changepassword();
	}
	public void Back_Client(ActionEvent event)
	{
		application.gotoclient();
	}

	 @Override
	    public void initialize(URL url, ResourceBundle rb){}
}

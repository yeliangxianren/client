package view;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import ClientModle.Client2Controller;
import ClientModle.CustomResp;
import ClientModle.FundAccount;
import ClientModle.HttpCommon;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ClientSearchController  implements Initializable {
	@FXML 
	private TextField Buy_Num;
	
	@FXML
	private Label fundIdLabel;
	@FXML
	private Label securitiesIdLabel;
	@FXML
	private Label balanceLabel;
	@FXML
	private Label interestLabel;

	
	
	private Main application;
	
	public void setApp(Main app) {
        this.application = app;
    }
	
	@FXML 
	public void Back(ActionEvent event)
	{
	application.gotoclient();
	}
	public void Password_Change(ActionEvent event)
	{
		application.gotochangepassword();
	}
	
	public void Buy(ActionEvent event)
	{
	Alert error = new Alert(Alert.AlertType.ERROR,"����:����ʧ��"); 
	error.showAndWait();
	application.gotoresualt();
	}
	 @Override
	    public void initialize(URL url, ResourceBundle rb){}
}

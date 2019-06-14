package ClientModle;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.lang.Object;

public class ClientSearchController  implements Initializable {
	@FXML private TextField Buy_Num;
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
	Alert error = new Alert(Alert.AlertType.ERROR,"´íÎó:¹ºÂòÊ§°Ü"); 
	error.showAndWait();
	application.gotoresualt();
	}
	 @Override
	    public void initialize(URL url, ResourceBundle rb){}
}

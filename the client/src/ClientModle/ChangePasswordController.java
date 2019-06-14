package ClientModle;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
public class ChangePasswordController implements Initializable{
	@FXML 
	private TextField Old_Password;
	
	@FXML 
	private TextField New_Password;
	
	private Main application;
	
	public void setApp(Main app) {
        this.application = app;
    }
	
	@FXML 
	public void Is_Change(ActionEvent event)
	{
	//accesss
	//faile
	Alert done = new Alert(Alert.AlertType.CONFIRMATION,"成功:修改成功");		 
	done.showAndWait();
	application.gotoclient();
	/*Alert error = new Alert(Alert.AlertType.ERROR,"�ɹ�:�������޸�");		 
	error.showAndWait();
	application.gotochangepassword();*/
	}
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb){}
}
package view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import ClientModle.LoginUIController;
import ClientModle.CustomResp;
import ClientModle.HttpCommon;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
public class Change_PasswordController implements Initializable{
	@FXML JFXTextField Old_Password;
	@FXML JFXTextField New_Password;
	
	private Main application;
	
	public void setApp(Main app) {
        this.application = app;
    }
	
	@FXML 
	public void Is_Change(ActionEvent event)
	{

			String path = "/fund/update/password";
			String fundid = LoginUIController.fundid;
			String oldPassword = Old_Password.getText();
			String newPassword = New_Password.getText();
			CustomResp cr;
			path = path + '/' + fundid + '/' + newPassword;
			
			if(!oldPassword.equals(LoginUIController.oldpassword)) {
				Alert done = new Alert(Alert.AlertType.CONFIRMATION,"旧密码错误");		 
				done.showAndWait();
			}else {	
				cr = new HttpCommon().doHttp(path, "POST", null);
				Gson gson = new Gson();
				Map<String, Object> map = new HashMap<String, Object>();
				map = gson.fromJson(cr.getResultJSON(), map.getClass());
			
				if((boolean)map.get("status")) {
					Alert done = new Alert(Alert.AlertType.CONFIRMATION,"成功:修改成功");		 
					done.showAndWait();
					
				}else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ERROR");
					alert.setHeaderText("修改失败");
					alert.setContentText((String)map.get("cause"));
					alert.showAndWait();
				}
			}
	}
	 @Override
	    public void initialize(URL url, ResourceBundle rb){}
}
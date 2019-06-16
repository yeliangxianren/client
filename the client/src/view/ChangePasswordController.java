package view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import ClientModle.Client2Controller;
import ClientModle.CustomResp;
import ClientModle.HttpCommon;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
			String path = "/fund/update/password";
			String fundid = Client2Controller.fundid;
			String oldPassword = Old_Password.getText();
			String newPassword = New_Password.getText();
			CustomResp cr;
			path = path + '/' + fundid + '/' + newPassword;
//			System.out.print(path);
			
			if(!oldPassword.equals(Client2Controller.oldpassword)) {
				Alert done = new Alert(Alert.AlertType.CONFIRMATION,"旧密码错误");		 
				done.showAndWait();
				application.gotoclient();
			}else {	
				cr = new HttpCommon().doHttp(path, "POST", null);
				Gson gson = new Gson();
				Map<String, Object> map = new HashMap<String, Object>();
				map = gson.fromJson(cr.getResultJSON(), map.getClass());
			
				if((boolean)map.get("status")) {
					Alert done = new Alert(Alert.AlertType.CONFIRMATION,"成功:修改成功");		 
					done.showAndWait();
					application.gotoclient();
				}else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ERROR");
					alert.setHeaderText("修改失败");
					alert.setContentText((String)map.get("cause"));
					alert.showAndWait();
				}
			}
		/*Alert error = new Alert(Alert.AlertType.ERROR,"�ɹ�:�������޸�");		 
		error.showAndWait();
		application.gotochangepassword();*/
	}
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb){}
}
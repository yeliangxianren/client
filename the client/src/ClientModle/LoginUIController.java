package ClientModle;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import com.google.gson.Gson;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ClientModle.FundAccount;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import java.lang.Object;
public class LoginUIController implements Initializable {
	@FXML 
	private JFXPasswordField password;
	@FXML 
	private JFXTextField account;
	
	public static String fundid;
	public static String oldpassword;
	
	private Main application;
	
	public void setApp(Main app) {
        this.application = app;
    }
	
	@FXML 
	public void LOGIN_M(ActionEvent event)
	{
		FundAccount Login = new FundAccount();
		String Accounts = account.getText();
		String Passwords = password.getText();
		
		fundid = Accounts;
		oldpassword = Passwords;
		Login.setFundId(Integer.parseInt(Accounts));
	    Login.setPassword(Passwords);
		String json = new Gson().toJson(Login);
		CustomResp cr = new HttpCommon().doHttp("/client/login", "POST", json);
//     	new HttpCommon().doHttp("/stock/all", "GET", null);
     
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map = gson.fromJson(cr.getResultJSON(), map.getClass());
     
		if((boolean)map.get("status"))
			application.gotoclient();
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("登录失败");
			alert.setContentText((String)map.get("cause"));
			alert.showAndWait();
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb){}
}

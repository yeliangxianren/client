package ClientModle;

import java.net.URL;
import java.util.ResourceBundle;
import com.google.gson.Gson;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import ClientModle.FundAccount;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.lang.Object;
public class Client2Controller implements Initializable {
	@FXML 
	private PasswordField password;
	@FXML 
	private TextField account;
	
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
	Login.setFundId(Integer.parseInt(Accounts));
    Login.setPassword(Passwords);
	String json = new Gson().toJson(Login);
//
     CustomResp cr = new HttpCommon().doHttp("/client/login", "POST", json);
     		new HttpCommon().doHttp("/stock/all", "GET", null);
       	//if(cr.getResultJSON().r.equals("false"))
 System.out.println(cr.getResultJSON());

	application.gotoclient();
	
	}
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb){}
}

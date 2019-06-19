package view;

import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import ClientModle.LoginUIController;
import ClientModle.CustomResp;
import ClientModle.FundAccount;
import ClientModle.HttpCommon;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;



public class ClientUI_UserController implements Initializable {
	@FXML TextField Fund_id;
	@FXML TextField Securities_id;
	@FXML TextField Interest;
	@FXML TextField Balance;
	
	private Main application;
	public void setApp(Main app) {
        this.application = app;
    }
	
	
	@FXML
	public void Password_Change(ActionEvent event)
	{
		application.changepassword();
	}
	public void Back_Client(ActionEvent event)
	{
		application.gotoclient();
	}


	@Override
	 public void initialize(URL url, ResourceBundle rb){
		
		String path = "/fund/" + LoginUIController.fundid;
		CustomResp cr = new HttpCommon().doHttp(path, "GET", null);
	     
		Gson gson = new Gson();
		FundAccount fundAccount = new FundAccount();
		fundAccount = gson.fromJson(cr.getObjectJSON(), fundAccount.getClass());
		
		Fund_id.setText(fundAccount.getFundId() + "");
		Securities_id.setText(fundAccount.getSecuritiesId() + "");
		Balance.setText(fundAccount.getBalance() + "");
		Interest.setText(fundAccount.getInterest() + "");
		
	    }
}
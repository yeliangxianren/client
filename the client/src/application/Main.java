package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import view.ClientUI_UserController;
import view.Change_PasswordController;
import view.TheClientUIController;
import view.TheClientUI_SearchController;
import view.TheClientUI_BusinessController;
import ClientModle.LoginUIController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.InputStream;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert;

public class Main extends Application {
	private Stage stage;
	private Scene scene;
	
	@Override

	 public void start(Stage primarystage) {
		try {
		stage =  primarystage;
		stage.getIcons().clear();
        stage.getIcons().add(new Image("/img/title.jpg"));
        gotoclient();
        //gotoBusiness();
		stage.show();
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void gotologin()
	{
		try {
			stage.setTitle("登陆");
			LoginUIController login = (LoginUIController) replaceSceneContent("/view/LoginUI.fxml");
			login.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
public void changepassword()
{
	try {
	Stage primaryStage = new Stage();
	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Changepassword.fxml"));
	Parent root = loader.load();
	
	Change_PasswordController controller = loader.getController();
	//primaryStage.setTitle("修改密码");
	primaryStage.setScene(new Scene(root));
	primaryStage.show();
	}catch(Exception e) {
      e.printStackTrace();

}
}
	public void gotoclient_user()
	{
		try {
			stage.setTitle("客户端");
			ClientUI_UserController Client_user = (ClientUI_UserController) replaceSceneContent("/view/ClientUI_User.fxml");
			//Client_user.updateLabels();
			Client_user.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void gotoclient()
	{
		try {
			stage.setTitle("客户端");
			TheClientUIController client = (TheClientUIController) replaceSceneContent("/view/TheClientUI.fxml");
			//client.updateLabels();
			client.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void gotoBusiness()
	{
		try {
			stage.setTitle("客户端");
			TheClientUI_BusinessController client = (TheClientUI_BusinessController) replaceSceneContent("/view/TheClientUI_Business.fxml");
			//client.updateLabels();
			client.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void gotoStock_search()
	{
		try {
			stage.setTitle("客户端");
			TheClientUI_SearchController client = (TheClientUI_SearchController) replaceSceneContent("/view/TheClientUI_Search.fxml");
			client.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
	}

	  private Object replaceSceneContent(String fxml) {
		  FXMLLoader loader = new FXMLLoader();
          loader.setLocation(Main.class.getResource(fxml));
          BorderPane bp = null;
          try {
        	  bp = (BorderPane)loader.load();
          }catch(IOException e) {
              e.printStackTrace();
          }
          scene = new Scene(bp);
          stage.setScene(scene);
          stage.setResizable(false);
          
	      return loader.getController();
	    
	  }

    public static void main(String[] args) {
        launch(args);
    }
}
package application;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ClientModle.Client2Controller;
import ClientModle.TheClientController;
import view.ChangePasswordController;
import ClientModle.ClientSearchController;
import javafx.fxml.FXMLLoader;
import java.io.InputStream;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.scene.Scene;
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
		gotologin();
		stage.show();
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void gotologin()
	{
		try {
			stage.setTitle("Login");
			Client2Controller login = (Client2Controller) replaceSceneContent("/view/ClientUI3.fxml");
			login.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void gotoclient()
	{
		try {
			stage.setTitle("Client");
			TheClientController client = (TheClientController) replaceSceneContent("/view/Client.fxml");
			client.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void gotochangepassword()
	{
		try {
			stage.setTitle("Password");
			ChangePasswordController change = (ChangePasswordController) replaceSceneContent("/view/NewPassword.fxml");
			change.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public void gotoresualt()
	{
		try {
			stage.setTitle("Client");
			ClientSearchController search = (ClientSearchController) replaceSceneContent("/view/Client2.fxml");
			search.setApp(this);
		}catch(Exception e) {
            e.printStackTrace();
        }
    }
	public void SearchStock(String stock_id)
	{
		gotologin();
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
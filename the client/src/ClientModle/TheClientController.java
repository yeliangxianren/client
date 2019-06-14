package ClientModle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import application.Main;
import ClientModle.Stock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class TheClientController implements Initializable {
	
	@FXML 
	private Label UserNameLable;
	
	@FXML 
	private Label StockNumLable;
	
	@FXML 
	private Label StockTotlePriceLable;
	
	@FXML 
	private TextField stock_id;
	
	@FXML 
	private TableView<Stock> StockTable;
	
	@FXML
	private TableColumn<Stock, String> ID;
	
	@FXML
	private TableColumn<Stock, String> Nam;
	
	@FXML
	private TableColumn<Stock, String> Inc;
	
	@FXML
	private TableColumn<Stock, String> Num;
	
	@FXML
	private TableColumn<Stock, String> Pri;

	private Main application;
	public void setApp(Main app) {
        this.application = app;
    }	
	@FXML 
	public void SearchofStock(ActionEvent event)
	{
		application.gotoresualt();
	} 
	@FXML 
	public void Password_Change(ActionEvent event)
	{
		application.gotochangepassword();
	}
	
    public void initialize(URL url, ResourceBundle rb){
    	//ObservableList<Stock> cellData = FXCollections.observableArrayList();
		//cellData.add(new Stock("1","2","3","4","5"));
		//cellData.add(new Stock("5","2","3","4","5"));
		//StockTable.setItems(cellData);//
		//ID.setCellValueFactory(cellData -> cellData.getValue().getID());
		//Nam.setCellValueFactory(cellData -> cellData.getValue().getNam());
		//Inc.setCellValueFactory(cellData -> cellData.getValue().getInc());
		//Pri.setCellValueFactory(cellData -> cellData.getValue().getPri());
		//Num.setCellValueFactory(cellData -> cellData.getValue().getNum());
		
	
	}
}
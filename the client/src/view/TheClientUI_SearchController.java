package view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import com.jfoenix.controls.JFXTextField;

import ClientModle.Command;
import ClientModle.CustomResp;
import ClientModle.HttpCommon;
import ClientModle.LoginUIController;
import ClientModle.Stock;



public class TheClientUI_SearchController{
	@FXML 
	JFXTextField Search_id;
	
	@FXML
	private Button buy;
	
	private ObservableList<Stock> stockData = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Stock> stockTable;
	@FXML
	private TableColumn<Stock, String> stockCodeColumn;
	@FXML
	private TableColumn<Stock, String> stockNameColumn;
	@FXML
	private TableColumn<Stock, String> stockPriceColumn;
	@FXML
	private TableColumn<Stock, String> stockStateColumn;
	@FXML
	private TableColumn<Stock, String> stockLimitColumn;
	@FXML
	private TableColumn<Stock, String> closingPriceColumn;
	@FXML
	private TableColumn<Stock, String> stockAmountColumn;
	@FXML
	private TableColumn<Stock, String> stockTotalColumn;
	@FXML
	private TableColumn<Stock, String> averageTransprice;

	private int stockCount;
	private double stockPrice;
	public Stage primaryStage;
	
	@FXML
	private void initialize() {		
		stockTable.setItems(stockData);
		stockCodeColumn.setCellValueFactory(cellData -> cellData.getValue().stockCodeProperty());
		stockNameColumn.setCellValueFactory(cellData -> cellData.getValue().stockNameProperty());
		stockPriceColumn.setCellValueFactory(cellData -> cellData.getValue().stockPriceProperty());
		stockStateColumn.setCellValueFactory(cellData -> cellData.getValue().stockStateProperty());
		stockLimitColumn.setCellValueFactory(cellData -> cellData.getValue().stockLimitProperty());
		closingPriceColumn.setCellValueFactory(cellData -> cellData.getValue().closingPriceProperty());
		stockAmountColumn.setCellValueFactory(cellData -> cellData.getValue().stockAmountProperty());
		stockTotalColumn.setCellValueFactory(cellData -> cellData.getValue().stockTotalProperty());
		averageTransprice.setCellValueFactory(cellData -> cellData.getValue().averageTPProperty());
		
		getAllStock();
	}
	
	@FXML
	private void handleBuy() {
		int selectIndex = stockTable.getSelectionModel().getSelectedIndex();
		if(selectIndex >= 0) {
			int fundId;
			String stockCode;
			
			fundId = Integer.parseInt(LoginUIController.fundid);
			stockCode = stockTable.getSelectionModel().getSelectedItem().getStockCode();
			
			try {
				primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InputCmdInfo.fxml"));
				Parent root = loader.load();
				InputCmdInfoController controller = loader.getController();
				controller.setCsc(this);
				primaryStage.setScene(new Scene(root));
				primaryStage.showAndWait();
			}catch(Exception e) {
		      e.printStackTrace();
		}
			
			Command cmd = new Command();
			cmd.setFundId(fundId);
			cmd.setStockCode(stockCode);
			cmd.setCommandType(true);
			cmd.setStockCount(stockCount);
			cmd.setStockPrice(stockPrice);
			
			
			java.util.Date utilDate = new java.util.Date();
	        Date sqlDate = new Date(utilDate.getTime());
			cmd.setTime(sqlDate);
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();
			
			String jsonCommand = gson.toJson(cmd);
			CustomResp cr = new HttpCommon().doHttp("/command/upload", "POST", jsonCommand);
			
			Map<String, Object> mapResult = new HashMap<String, Object>();
			mapResult = new Gson().fromJson(cr.getResultJSON(), mapResult.getClass());
			System.out.print(mapResult);
			if((boolean)mapResult.get("status")) {
				Alert done = new Alert(Alert.AlertType.CONFIRMATION,"成功:操作成功");		 
				done.showAndWait();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText("操作失败");
				alert.setContentText((String)mapResult.get("cause"));
				alert.showAndWait();
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("操作失败");
			alert.setContentText("请先选中一个股票");
			alert.showAndWait();
		}
	}
	
	public void getAllStock() {
		CustomResp cr;
		cr = new HttpCommon().doHttp("/stock/all", "GET", null);
		String stringOrigin = cr.getObjectJSON();
		List<String> stocks = new ArrayList<String>();
		for(int i = 0; i < stringOrigin.length(); i++) {
			if(stringOrigin.charAt(i) != '{') 
				continue;
			for(int j = i; j < stringOrigin.length(); j++) {
				if(stringOrigin.charAt(j) != '}')
					continue;
				stocks.add(stringOrigin.substring(i, j +1));
				i = j;
				break;
			}
		}
		
		stockData.clear();
		for(int i = 0; i < stocks.size(); i++) {
			Map<String, Object> stockInfoTemp = new HashMap<String, Object>();
			Gson gson = new Gson();
			stockInfoTemp = gson.fromJson(stocks.get(i), stockInfoTemp.getClass());
			Stock stockTemp = new Stock(stockInfoTemp.get("stockCode") + "", stockInfoTemp.get("stockName") + "", stockInfoTemp.get("stockPrice") + "",
					stockInfoTemp.get("stockState") + "", stockInfoTemp.get("stockLimit") + "", 
					stockInfoTemp.get("closingPrice") + "",stockInfoTemp.get("stockAmount") + "",stockInfoTemp.get("stockTotal") + "");
			stockTemp.setAverageTP();
			stockData.add(stockTemp);
		}
	}
	
	public void setPara(int stockCount, double stockPrice) {
		this.stockCount = stockCount;
		this.stockPrice = stockPrice;
	}
	
	private Main application;
	public void setApp(Main app) {
        this.application = app;
    }
	
	@FXML
	public void Is_Search(ActionEvent event)
	{
		if(Search_id.getText().equals("")) {
			getAllStock();			
		}else {
			CustomResp cr;
			String stockCode = Search_id.getText();
			String jsonStockOne = new Gson().toJson(stockCode);
			cr = new HttpCommon().doHttp("/stock/one", "POST", jsonStockOne);
			Gson gson = new Gson();
			Map<String, Object> mapResult = new HashMap<String, Object>();
			mapResult = gson.fromJson(cr.getResultJSON(), mapResult.getClass());
		
			if((boolean)mapResult.get("status")) {
				Map<String, Object> mapObject = new HashMap<String, Object>();
				mapObject = gson.fromJson(cr.getObjectJSON(), mapObject.getClass());
				stockData.clear();
				Stock stockTemp = new Stock(mapObject.get("stockCode") + "", mapObject.get("stockName") + "", mapObject.get("stockPrice") + "",
						mapObject.get("stockState") + "", mapObject.get("stockLimit") + "", 
						mapObject.get("closingPrice") + "", mapObject.get("stockAmount") + "",mapObject.get("stockTotal") + "");
				stockTemp.setAverageTP();
				stockData.add(stockTemp);
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText("查询错误");
				alert.setContentText("输入格式不正确");
				alert.showAndWait();
			}
			
		}
	}
	public void Back_Client(ActionEvent event)
	{
		application.gotoclient();
	}

}

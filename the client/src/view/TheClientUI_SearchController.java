package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

import com.jfoenix.controls.JFXTextField;

import ClientModle.CustomResp;
import ClientModle.HttpCommon;
import ClientModle.Stock;



public class TheClientUI_SearchController{
	@FXML 
	JFXTextField Search_id;
	
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
		
		getAllStock();
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
			stockData.add(new Stock(stockInfoTemp.get("stockCode") + "", stockInfoTemp.get("stockName") + "", stockInfoTemp.get("stockPrice") + "",
					stockInfoTemp.get("stockState") + "", stockInfoTemp.get("stockLimit") + "", 
					stockInfoTemp.get("closingPrice") + "",stockInfoTemp.get("stockAmount") + "",stockInfoTemp.get("stockTotal") + ""));
		}
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
				stockData.add(new Stock(mapObject.get("stockCode") + "", mapObject.get("stockName") + "", mapObject.get("stockPrice") + "",
						mapObject.get("stockState") + "", mapObject.get("stockLimit") + "", 
						mapObject.get("closingPrice") + "", mapObject.get("stockAmount") + "",mapObject.get("stockTotal") + ""));
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

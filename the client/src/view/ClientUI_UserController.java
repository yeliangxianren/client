package view;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ClientModle.LoginUIController;
import ClientModle.Stock;
import ClientModle.Command;
import ClientModle.CustomResp;
import ClientModle.FundAccount;
import ClientModle.HttpCommon;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;



public class ClientUI_UserController implements Initializable {
	@FXML TextField Fund_id;
	@FXML TextField Securities_id;
	@FXML TextField Interest;
	@FXML TextField Balance;
	
	private String securityId;
	
	private ObservableList<Stock> stockData = FXCollections.observableArrayList();
	
	
	@FXML
	private Button refresh;
	@FXML
	private Button sell;
	
	private int stockCount;
	private double stockPrice;
	public Stage primaryStage;
	
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
	private TableColumn<Stock, String> averageHoldPrice;
	
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
	
	public void setPara(int stockCount, double stockPrice) {
		this.stockCount = stockCount;
		this.stockPrice = stockPrice;
	}
	
	@FXML
	private void handleRefresh() {
		stockData.clear();
		getUserStock();
	}
	
	@FXML
	private void handleSell() {
		int fundId;
		String stockCode;		
		
		int selectIndex = stockTable.getSelectionModel().getSelectedIndex();
		if(selectIndex >= 0) {
			fundId = Integer.parseInt(LoginUIController.fundid);
			stockCode = stockTable.getSelectionModel().getSelectedItem().getStockCode();
			
			try {
				primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InputCmdInfo.fxml"));
				Parent root = loader.load();
				InputCmdInfoController controller = loader.getController();
				controller.setCuc(this);
				primaryStage.setScene(new Scene(root));
				primaryStage.showAndWait();
			}catch(Exception e) {
		      e.printStackTrace();
		}
			
			Command cmd = new Command();
			cmd.setFundId(fundId);
			cmd.setStockCode(stockCode);
			cmd.setCommandType(false);
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
	
	public void getUserStock() {
		CustomResp cr;
		String path = "/securities/stock_connected/" + securityId;
		
		cr = new HttpCommon().doHttp(path, "GET", null);
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
			stockTemp.setAverageHP();
			stockData.add(stockTemp);
		}
	}

	@Override
	 public void initialize(URL url, ResourceBundle rb){
		
		String path = "/fund/" + LoginUIController.fundid;
		CustomResp cr = new HttpCommon().doHttp(path, "GET", null);
	     
		Gson gson = new Gson();
		FundAccount fundAccount = new FundAccount();
		fundAccount = gson.fromJson(cr.getObjectJSON(), fundAccount.getClass());
		
		Fund_id.setText(fundAccount.getFundId() + "");
		securityId = fundAccount.getSecuritiesId() + "";
		Securities_id.setText(securityId);
		Balance.setText(fundAccount.getBalance() + "");
		Interest.setText(fundAccount.getInterest() + "");
		
		stockTable.setItems(stockData);
		stockCodeColumn.setCellValueFactory(cellData -> cellData.getValue().stockCodeProperty());
		stockNameColumn.setCellValueFactory(cellData -> cellData.getValue().stockNameProperty());
		stockPriceColumn.setCellValueFactory(cellData -> cellData.getValue().stockPriceProperty());
		stockStateColumn.setCellValueFactory(cellData -> cellData.getValue().stockStateProperty());
		stockLimitColumn.setCellValueFactory(cellData -> cellData.getValue().stockLimitProperty());
		closingPriceColumn.setCellValueFactory(cellData -> cellData.getValue().closingPriceProperty());
		stockAmountColumn.setCellValueFactory(cellData -> cellData.getValue().stockAmountProperty());
		stockTotalColumn.setCellValueFactory(cellData -> cellData.getValue().stockTotalProperty());
		averageHoldPrice.setCellValueFactory(cellData -> cellData.getValue().averageHPProperty());
		
		getUserStock();
	}
}
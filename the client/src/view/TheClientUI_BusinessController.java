package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Optional;


import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import ClientModle.LoginUIController;
import ClientModle.Stock;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXTextField;

import ClientModle.Command;
import ClientModle.CustomResp;
import ClientModle.HttpCommon;
import ClientModle.TradeRecord;


public class TheClientUI_BusinessController{
	
	@FXML
    private ObservableList<TradeRecord> TradeData = FXCollections.observableArrayList();
	
	@FXML
	private TableView<TradeRecord> stockTable;
	@FXML
	private TableColumn<TradeRecord, String> tradeIdColumn;
	@FXML
	private TableColumn<TradeRecord, String> stockCodeColumn;
	@FXML
	private TableColumn<TradeRecord, String> typeColumn;
	@FXML
	private TableColumn<TradeRecord, String> stockaccountColumn;
	@FXML
	private TableColumn<TradeRecord, String> stockpriceColumn;
	@FXML
	private TableColumn<TradeRecord, String> timeColumn;
	@FXML
	private TableColumn<TradeRecord, String> statusColumn;;

	
	 @FXML
	    public void initialize(){
		 stockTable.setItems(TradeData);
		 tradeIdColumn.setCellValueFactory(cellData -> cellData.getValue().tradeIdProperty());
		 stockCodeColumn.setCellValueFactory(cellData -> cellData.getValue().stockCodeProperty());
		 typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
		 stockaccountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
		 stockpriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
		 timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
		 statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		 
		getTrading();
		 
	 }
	 @FXML
	 private void refresh(ActionEvent event) {
		 TradeData.clear();
		 getTrading();
	 }
	 @FXML
		private void Refuse(ActionEvent event) {
		 int selectIndex = stockTable.getSelectionModel().getSelectedIndex();
		 if(selectIndex >= 0) {
			 int fund_Id;
				String stock_Code;
				String State;
				
				fund_Id = Integer.parseInt(LoginUIController.fundid);
				stock_Code = stockTable.getSelectionModel().getSelectedItem().getStockCode();
                State = stockTable.getSelectionModel().getSelectedItem().getstatus();
                if(State.equals("正在交易"))
                {
				Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"确认撤销该交易?");
				confirmation.setTitle("提示");
				Optional<ButtonType> result = confirmation.showAndWait();
				if (result.get() == ButtonType.OK)
				{   
					
					Command Business_Refuse = new Command();
					Business_Refuse.setFundId(fund_Id);
					Business_Refuse.setStockCode(stock_Code);
					String jsonCommand = new Gson().toJson(Business_Refuse);
					CustomResp cr = new HttpCommon().doHttp("/command/revoke", "POST", jsonCommand);
					Gson gson = new Gson();
					Map<String, Object> map = new HashMap<String, Object>();
					map = gson.fromJson(cr.getResultJSON(), map.getClass());
			     
					if((boolean)map.get("status"))
					{
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("ERROR");
						alert.setHeaderText("撤销成功");
						alert.showAndWait();
					}
						
					else {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("ERROR");
						alert.setHeaderText("登录失败");
						alert.setContentText((String)map.get("cause"));
						alert.showAndWait();
					}
}
					else {  
						
					}
				}
                else
       		 {
       			 Alert alert = new Alert(AlertType.INFORMATION);
       				alert.setTitle("提示");
       				alert.setHeaderText("请选择正确的交易记录");
       				alert.showAndWait();
       		 }
		 }
		 else
		 {
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("提示");
				alert.setHeaderText("请选择一条交易记录");
				alert.showAndWait();
		 }
		
	 }
	public void getTrading()
	 {
		 String path = "/trade_record/" + LoginUIController.fundid;
		 CustomResp cr = new HttpCommon().doHttp(path, "GET", null);
		 String stringOrigin = cr.getObjectJSON();
		 System.out.println(stringOrigin);
			System.out.println(stringOrigin);
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
			
			TradeData.clear();
			for(int i = 0; i < stocks.size(); i++) {
				Map<String, Object> stockInfoTemp = new HashMap<String, Object>();
				Gson gson = new Gson();
				stockInfoTemp = gson.fromJson(stocks.get(i), stockInfoTemp.getClass());
					String BusinessType;
					String BusinessStatus;
					if(stockInfoTemp.get("status").equals(true))
						BusinessStatus = "已交易";
						else
						BusinessStatus = "正在交易";
					if(stockInfoTemp.get("type").equals(true))
						BusinessType = "购买";
					else
						BusinessType = "出售";
				TradeData.add(new TradeRecord(stockInfoTemp.get("stockCode") + "", stockInfoTemp.get("tradeId") + "", stockInfoTemp.get("amount") + "",
						stockInfoTemp.get("price") + "", BusinessType+ "",stockInfoTemp.get("time") + "",BusinessStatus + ""));
				}
	 }
	private Main application;
	public void setApp(Main app) {
        this.application = app;
    }
	public void Back_Client(ActionEvent event)
	{
		application.gotoclient();
	}

	
}

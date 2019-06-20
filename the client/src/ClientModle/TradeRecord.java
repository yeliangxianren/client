package ClientModle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TradeRecord {
	private final StringProperty stockCode;
	private final StringProperty tradeId;
	private final StringProperty amount;
    private final StringProperty price;
    private final StringProperty type;
    private final StringProperty time;
    private final StringProperty status;
    
    public TradeRecord(String stockCode, String tradeId, String amount,
    		String price, String type,String time,String status) {
    	this.stockCode = new SimpleStringProperty(stockCode);
    	this.tradeId = new SimpleStringProperty(tradeId);
    	this.amount = new SimpleStringProperty(amount);
    	this.price = new SimpleStringProperty(price);
    	this.type = new SimpleStringProperty(type);
    	this.time = new SimpleStringProperty(time);
    	this.status = new SimpleStringProperty(status);
    }

    public String getStockCode() {
    	return stockCode.get();
    }
    
    public StringProperty stockCodeProperty() {
    	return stockCode;
    }
    
    public String gettradeId() {
    	return tradeId.get();
    }
    
    public StringProperty tradeIdProperty() {
    	return tradeId;
    }
    
    public String getamount() {
    	return amount.get();
    }
    
    public StringProperty amountProperty() {
    	return amount;
    }
    
    public String getprice() {
    	return price.get();
    }
    
    public StringProperty priceProperty() {
    	return price;
    }
    
    public String gettype() {
    	return type.get();
    }
    
    public StringProperty typeProperty() {
    	return type;
    }
    
    public String gettime() {
    	return time.get();
    }
    
    public StringProperty timeProperty() {
    	return time;
    }
    public String getstatus() {
    	return status.get();
    }
    
    public StringProperty statusProperty() {
    	return status;
    }
    
    
}
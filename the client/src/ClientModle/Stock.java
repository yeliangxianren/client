package ClientModle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {
	private final StringProperty stockCode;
	private final StringProperty stockName;
	private final StringProperty stockPrice;
    private final StringProperty stockState;
    private final StringProperty stockLimit;
    private final StringProperty closingPrice;
    private final StringProperty stockAmount;
    private final StringProperty stockTotal;
    private StringProperty averageHP;
    private StringProperty averageTP;
    
    public Stock(String stockCode, String stockName, String stockPrice,
    		String stockState, String stockLimit, String closingPrice, 
    		String stockAmount, String stockTotal) {
    	this.stockCode = new SimpleStringProperty(stockCode);
    	this.stockName = new SimpleStringProperty(stockName);
    	this.stockPrice = new SimpleStringProperty(stockPrice);
    	this.stockState = new SimpleStringProperty(stockState);
    	this.stockLimit = new SimpleStringProperty(stockLimit);
    	this.closingPrice = new SimpleStringProperty(closingPrice);
    	this.stockAmount = new SimpleStringProperty(stockAmount);
    	this.stockTotal = new SimpleStringProperty(stockTotal);
    	
    	this.averageHP = new SimpleStringProperty("0.0");
    	this.averageTP = new SimpleStringProperty("0.0");
    }

    public String getStockCode() {
    	return stockCode.get();
    }
    
    public StringProperty stockCodeProperty() {
    	return stockCode;
    }
    
    public String getStockName() {
    	return stockName.get();
    }
    
    public StringProperty stockNameProperty() {
    	return stockName;
    }
    
    public String getStockPrice() {
    	return stockPrice.get();
    }
    
    public StringProperty stockPriceProperty() {
    	return stockPrice;
    }
    
    public String getStockState() {
    	return stockState.get();
    }
    
    public StringProperty stockStateProperty() {
    	return stockState;
    }
    
    public String getStockLimit() {
    	return stockLimit.get();
    }
    
    public StringProperty stockLimitProperty() {
    	return stockLimit;
    }
    
    public String getClosingPrice() {
    	return closingPrice.get();
    }
    
    public StringProperty closingPriceProperty() {
    	return closingPrice;
    }
    
    public String getStockAmount() {
    	return stockAmount.get();
    }
    
    public StringProperty stockAmountProperty() {
    	return stockAmount;
    }
    
    public String getStockTotal() {
    	return stockTotal.get();
    }
    
    public StringProperty stockTotalProperty() {
    	return stockTotal;
    }
    
    public StringProperty averageHPProperty() {
    	return averageHP;
    }
    
    public void setAverageHP() {
    	String averageTemp = getStockPrice();
    	if(Double.parseDouble(getStockAmount()) != 0){
    		averageTemp = Double.parseDouble(getStockTotal()) / Double.parseDouble(getStockAmount()) + "";
    	}
    	this.averageHP.set(averageTemp);
    }
    
    public void setAverageTP() {
    	String averageTemp = getStockPrice();
//    	if(Double.parseDouble(getStockAmount()) != 0){
//    		Todo
//    	}
    	this.averageTP.set(averageTemp);
    }

    public StringProperty averageTPProperty() {
    	return averageTP;
    }
}
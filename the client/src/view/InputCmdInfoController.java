package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InputCmdInfoController {
	public int stockCount;
	public double stockPrice;
	private ClientUI_UserController cuc;
	private TheClientUI_SearchController csc;
	
	@FXML
	private TextField stockCountTF;
	@FXML
	private TextField stockPriceTF;
	@FXML
	private Button sure;
	
	@FXML
	private void handleSure() {
		if(cuc != null) {
			cuc.setPara(Integer.parseInt(stockCountTF.getText()), Double.parseDouble(stockPriceTF.getText()));
			cuc.primaryStage.close();
		}else {
			csc.setPara(Integer.parseInt(stockCountTF.getText()), Double.parseDouble(stockPriceTF.getText()));
			csc.primaryStage.close();
		}
	}
	
	public void setCuc(ClientUI_UserController cuc) {
		this.cuc = cuc;
	}
	
	public void setCsc(TheClientUI_SearchController csc) {
		this.csc = csc;
	}
	
}

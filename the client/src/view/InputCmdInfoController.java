package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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
		if(stockCountTF.getText().equals("")||stockPriceTF.getText().equals("")) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText("输入信息不完整");
			alert.setContentText("请继续输入");
			alert.showAndWait();
		}else {
			if(cuc != null) {
				cuc.setPara(Integer.parseInt(stockCountTF.getText()), Double.parseDouble(stockPriceTF.getText()));
				cuc.primaryStage.close();
			}else {
				csc.setPara(Integer.parseInt(stockCountTF.getText()), Double.parseDouble(stockPriceTF.getText()));
				csc.primaryStage.close();
			}
		}
	}
	
	public void setCuc(ClientUI_UserController cuc) {
		this.cuc = cuc;
	}
	
	public void setCsc(TheClientUI_SearchController csc) {
		this.csc = csc;
	}
	
}

package ClientModle;
import javafx.beans.property.SimpleStringProperty;

public class Stock {

	private final SimpleStringProperty ID;
	private final SimpleStringProperty Nam;
	private final SimpleStringProperty Inc;
	private final SimpleStringProperty Pri;
	private final SimpleStringProperty Num;
	public Stock(String ID,String Nam,String Inc,String Pri,String Num) 
	{
		this.ID = new SimpleStringProperty(ID);
		this.Nam = new SimpleStringProperty(Nam);
		this.Inc = new SimpleStringProperty(Inc);
		this.Pri = new SimpleStringProperty(Pri);
		this. Num = new SimpleStringProperty( Num);
}
	public String getID() {

		return ID.get();

	}
	public String getNam() {

		return Nam.get();

	}
	
	public String getInc() {

		return Inc.get();

	}
	public String getPri() {

		return Pri.get();

	}
	public String getNum() {

		return Num.get();

	}
	public void setID(String ID) {

		this.ID.set(ID);

	}
	public void setNam(String Nam) {

		this.Nam.set(Nam);

	}
	public void setInc(String Inc) {

		this.Inc.set(Inc);

	}
	public void setPri(String Pri) {

		this.Pri.set(Pri);

	}
	public void setNum(String Num) {

		this.Num.set(Num);

	}
}
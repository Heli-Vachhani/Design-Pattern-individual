import java.util.*;



public class Offering {
	String theAuthor = "";
	String OfferingFileName = "";
	Date theSubmitData = new Date();
	int theView;
	private boolean marked = false;

	Offering() {
	}

	@Override
	public String toString() {
		String string;
		string = theAuthor + "  " + OfferingFileName + " View=" + getViewInt() + "  ";
		if (isMarked())
			string += "marked";
		else
			string += "not marked";

		return (string);
	}

	String getViewString() {
		if (isMarked())
			return "" + theView;
		else
			return "-1";
	}

	int getViewInt() {
		return theView;
	}

	void setMarked() {
		this.marked = true;
	}

	private boolean isMarked() {
		return marked;
	}
}
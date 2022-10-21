import java.util.*;


abstract public class Person {
	int type = 0; // type=0 : buyer, type=1 seller
	String UserName;
	ClassProductList ProductList;
	ProductMenu theProductMenu;
	Product CurrentProduct;

	Person() {
		ProductList = new ClassProductList();
	}

	abstract public void CreateProductMenu(Product theProduct, int theCategory);

	void showAddButton() {
		theProductMenu.ShowAddButtons();
	}

	void showViewButtons() {
		theProductMenu.ShowViewButtons();
	}

	void showComboxes() {theProductMenu.ShowComboxes();	}

	void showRadios() {
		theProductMenu.ShowRadios();
	}

	void show() {
		theProductMenu.setVisible(true);
	}

	boolean ifLogout() {
		return theProductMenu.ifLogout();
	}

	// show the trading list
	public boolean ShowMenu() {
		// Create a iterator for the trading list
//    Iterator theIter=new ListIterator(CurrentProduct.TraList );
//    Iterator theIter = CurrentProduct.tradingList.iterator();

		Iterator theIter = CurrentProduct.getTradingList().iterator();
		theProductMenu.theProduct = CurrentProduct;
		Trading theTrading;
		while (theIter.hasNext()) {
			theTrading = (Trading) theIter.next();
			theProductMenu.TradingCombox.addItem(theTrading);
		}
		return false;
	}

	ClassProductList GetProductList() {
		return ProductList;
	}

	void AddProduct(Product theProduct) {
		ProductList.add(theProduct);
	}
}
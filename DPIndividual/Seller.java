

public class Seller extends Person {
	Seller() {
		type = 1;// type=1 :seller
	}

	public void CreateProductMenu(Product theProduct, int theCategory) {
		if (theCategory == 0)/// 0: Meat (defined in ProductSeletDlg)
		{
			theProductMenu = new MeatProductMenu();
		} else/// 1: Produce
		{
			theProductMenu = new ProduceProductMenu();
		}
	}

	public boolean ShowMenu() {
		super.ShowMenu();
		showAddButton();
		showViewButtons();
		showComboxes();
		showRadios();
		show();
		return ifLogout();
	}
}
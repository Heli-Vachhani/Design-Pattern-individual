import java.io.*;

class Facade {
	private Product theSelecteProduct = null;
	private int nProductCategory = 0;
	ClassProductList theProductList;
	private Person thePerson;

	Facade() {
	}

	static boolean Login(UserInfoItem userinfoItem) {
		Login login = new Login();
		login.setModal(true);
		login.setVisible(true);
		userinfoItem.strUserName = login.GetUserName();
		userinfoItem.UserType = login.GetUserType();
		return login.isExit();
	}

/////////////////////////
//functions for ProductMenu
	/*
	 * When click the add button of the ProductMenu , call this function this
	 * function will new an trading fill the required infomation this function
	 * will call SellerTradingMenu or BuyerTradingMenu according to the
	 * type of the user it will not update the product menu. the productmenu need to
	 * refreshed outside the function
	 */

	void AddTrading(Product theProduct) {
		TradingMenu theTradingMenu;
		if (thePerson.type == 0)/// buyer
		{
			theTradingMenu = new BuyerTradingMenu();
		} else {
			theTradingMenu = new SellerTradingMenu();
		}
		Trading theTrading = new Trading();
		theTradingMenu.ShowMenu(theTrading, thePerson);
		theProduct.AddTrading(theTrading);
	}

	/*
	 * When click the view button of the ProductMenu , call this function and pass
	 * the pointer of the Trading and the person pointer to this function this
	 * function will new an trading fill the required infomation this function
	 * will call SellerTradingMenu or BuyerTradingMenu according to the
	 * type of the user
	 */
	void ViewTrading(Trading theTrading) {
		TradingMenu theTradingMenu;
		if (thePerson.type == 0)/// buyer
		{
			theTradingMenu = new BuyerTradingMenu();
		} else {
			theTradingMenu = new SellerTradingMenu();
		}

		theTradingMenu.ShowMenu(theTrading, thePerson);
	}

//functions for SellerTradingMenu
	/*
	 * this function will view the give Offering: theOffering this function calls
	 */

	////////////////////

	//////////
	void Remind() {
		Reminder theReminder = new Reminder();
		theReminder.showReminder(thePerson.GetProductList());
	}

	void CreateUser(UserInfoItem userinfoitem) {
		if (userinfoitem.UserType == UserInfoItem.USER_TYPE.Buyer) /// buyer
		{
			thePerson = new Buyer();
		} else /// seller
		{
			thePerson = new Seller();
		}
		thePerson.UserName = userinfoitem.strUserName;
	}

	/*
	 * create a product list and intitialize it with the file ProductInfo.txt
	 */
	void CreateProductList() {
		theProductList = new ClassProductList();
		theProductList.InitializeFromFile();
	}

	/*
	 * call this function after create user, create productlist read the
	 * UserProduct.txt file match the productname with theProductList attach the
	 * Matched product object to the new create user Facade.thePerson.ProductList
	 */
	void AttachProductToUser() {
		BufferedReader file;
		try {
			file = new BufferedReader(new FileReader("UserProduct.txt"));
			String aline, strUserName, strProductName;
			while ((aline = file.readLine()) != null) // not the EOF
			{
				strUserName = GetUserName(aline);
				strProductName = GetProductName(aline);
				if (strUserName.compareTo(thePerson.UserName) == 0) /// the UserName mateches
				{
					theSelecteProduct = FindProductByProductName(strProductName);
					if (theSelecteProduct != null) /// Find the Product in the ProductList--->attach
					{
						thePerson.AddProduct(theSelecteProduct);
					}
				}
			}
		} catch (Exception ignored) {
        }
	}

	/*
	 * get the user name from aline UserName:ProductName
	 */
	private String GetUserName(String aline) {
		int Sep = aline.lastIndexOf(':');
		return aline.substring(0, Sep);
	}

	/*
	 * get the ProductName from aline UserName:ProductName
	 */
	private String GetProductName(String aline) {
		int Sep = aline.lastIndexOf(':');
		return aline.substring(Sep + 1);
	}

	/*
	 * show the product selection dlg, show the product attatched to theperson and
	 * return the selected product and assign the product to the class member
	 * theSelecteProduct, the Product Level to ProductLevel ProductLeve=0 High,
	 * ProductLeve=1 Low
	 */
	boolean SelectProduct() {
		ProductSelectDlg theDlg = new ProductSelectDlg();
		theSelecteProduct = theDlg.ShowDlg(thePerson.ProductList);
		thePerson.CurrentProduct = theSelecteProduct;
		nProductCategory = theDlg.nProductCategory;
		return theDlg.isLogout();
	}

	/*
	 * call the thePerson.CreateProductMenu according to the really object(buyer or
	 * seller) and the nProductCategory it will call different menu creater and show
	 * the menu;
	 */

	boolean ProductOperation() {
		thePerson.CreateProductMenu(theSelecteProduct, nProductCategory);
		return thePerson.ShowMenu();//// 0: logout 1 select another product
	}

	/*
	 * find the product in theProductList that matches strProductName 1 create a
	 * ProductIterator for the List 2 Find the Product with the Iterator return the
	 * pointer of the Product if not fine, return null;
	 */
	private Product FindProductByProductName(String strProductName) {
		ProductIterator Iterator = new ProductIterator(theProductList);
		return (Product) Iterator.next(strProductName);
	}

}
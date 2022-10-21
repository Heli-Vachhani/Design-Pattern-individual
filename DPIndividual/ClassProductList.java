import java.util.ArrayList;
import java.io.*;


class ClassProductList extends ArrayList<Product> {

	ClassProductList() {
	}

	//// initialize the list by reading from the file.
	void InitializeFromFile() {
		try {
			BufferedReader file;
			String strProductName;
			file = new BufferedReader(new FileReader("ProductInfo.txt"));
			while ((strProductName = file.readLine()) != null) {
				Product theProduct;
				theProduct = new Product(strProductName, 0);
//      theProduct.ProductName= strProductName;
				add(theProduct);
			}
		} catch (Exception ignored) {
        }
	}

}
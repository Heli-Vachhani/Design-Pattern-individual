import java.util.ArrayList;
import java.io.*;


class ClassProductList extends ArrayList<Product> {

	ClassProductList() {
	}

	// Create Product-list by reading from the file.
	void InitializeFromFile() {
		try {
			BufferedReader file;
			String strProductName, aline, strProductCategory;
			file = new BufferedReader(new FileReader("ProductInfo.txt"));
			while ((aline = file.readLine()) != null) {

				int Sep1 = aline.lastIndexOf(':');
				strProductCategory = aline.substring(0, Sep1);
				strProductName = aline.substring(Sep1 + 1);

				Product theProduct;
				theProduct = new Product(strProductName, strProductCategory);

//      theProduct.ProductName= strProductName;
				add(theProduct);
			}
		} catch (Exception ignored) {
        }
	}

	public Product FindProductByProductCategoryAndName(String ProductCategory, String ProductName) {
		int nProductCategory = size();
		for (int i = 0; i < nProductCategory; i++) {
			Product theProduct;
			theProduct = (Product) get(i);
			if (theProduct.getProductCategory().compareTo(ProductCategory) == 0) {
				return theProduct;
			}
			else {
				return theProduct;
			}
		}
		return null;
	}

}
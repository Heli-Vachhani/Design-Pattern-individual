import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class ProductSelectDlg extends JDialog {
	private Product SelectedProduct;
	int nProductCategory = 0;
	private boolean m_bLogout = false;
	private JComboBox<Product> ProductNameCom = new JComboBox<>();
	private JRadioButton MeatRadio = new JRadioButton();
	private JRadioButton ProduceRadio = new JRadioButton();
	private JLabel jLabel1 = new JLabel();
	private JButton OKButton = new JButton();
	private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JButton buttonLogout = new JButton();

	ProductSelectDlg() {
		try {
			jbInit();
			setSize(420, 238);
			setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() {
		this.getContentPane().setLayout(null);
		ProductNameCom.setBounds(new Rectangle(155, 41, 203, 22));
		MeatRadio.setText("Meat");
		MeatRadio.setBounds(new Rectangle(50, 87, 103, 26));
		ProduceRadio.setToolTipText("");
		ProduceRadio.setSelected(true);
		ProduceRadio.setText("Produce");
		ProduceRadio.setBounds(new Rectangle(236, 88, 103, 26));
		jLabel1.setText("ProductName");
		jLabel1.setBounds(new Rectangle(39, 44, 85, 18));
		OKButton.setText("OK");
		OKButton.setBounds(new Rectangle(78, 139, 79, 29));
		OKButton.addActionListener(this::OKButton_actionPerformed);
		buttonLogout.setText("Logout");
		buttonLogout.setBounds(new Rectangle(224, 140, 73, 31));
		buttonLogout.addActionListener(this::buttonLogout_actionPerformed);
		this.getContentPane().add(ProductNameCom, null);
		this.getContentPane().add(jLabel1, null);
		this.getContentPane().add(MeatRadio, null);
		this.getContentPane().add(ProduceRadio, null);
		this.getContentPane().add(OKButton, null);
		this.getContentPane().add(buttonLogout, null);
		buttonGroup1.add(MeatRadio);
		buttonGroup1.add(ProduceRadio);
	}

	/*
	 * show the theProductList in a combox Show the Product type selection button
	 * return the pointer pointing to the Product object return the Product Type
	 */

	Product ShowDlg(ClassProductList productList) {

		// 0 Meat presentation 1 Produce Experiment
		ProductIterator theIterator = new ProductIterator(productList);
		Product theProduct;
		while ((theProduct = (Product) theIterator.next()) != null) /// end of the list
		{
			ProductNameCom.addItem(theProduct);
		}
		setVisible(true);
		return SelectedProduct;
	}

	private void OKButton_actionPerformed(ActionEvent e) {
		SelectedProduct = (Product) ProductNameCom.getSelectedItem();
		if (MeatRadio.isSelected())
			nProductCategory = 0; // meat product: 0
		else
			nProductCategory = 1; // produce product: 1
		dispose();
	}

	boolean isLogout() {
		return m_bLogout;
	}

	private void buttonLogout_actionPerformed(ActionEvent e) {
		m_bLogout = true;
		dispose();
	}
}
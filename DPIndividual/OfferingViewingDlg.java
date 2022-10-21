import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class OfferingViewingDlg extends JDialog {
	private Offering theOffering;
	private JLabel jLabel1 = new JLabel();
	private JTextField tfGrad = new JTextField();
	private JButton buttonOK = new JButton();
	private JLabel labelOfferingFileName = new JLabel();

	OfferingViewingDlg() {
		try {
			jbInit();
			setSize(316, 186);
			setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() {
		jLabel1.setText("Offering File Name");
		jLabel1.setBounds(new Rectangle(23, 30, 121, 18));
		this.getContentPane().setLayout(null);
		tfGrad.setBounds(new Rectangle(25, 66, 100, 22));
		buttonOK.setText("OK");
		buttonOK.setBounds(new Rectangle(217, 67, 79, 29));
		buttonOK.addActionListener(this::buttonOK_actionPerformed);
		labelOfferingFileName.setBounds(new Rectangle(212, 34, 163, 18));
		this.getContentPane().add(jLabel1, null);
		this.getContentPane().add(tfGrad, null);
		this.getContentPane().add(labelOfferingFileName, null);
		this.getContentPane().add(buttonOK, null);
	}

	void show(Offering offering) {
		theOffering = offering;
		tfGrad.setText("" + theOffering.getViewInt());
		labelOfferingFileName.setText(theOffering.OfferingFileName);
		setVisible(true);
	}

	private void buttonOK_actionPerformed(ActionEvent e) {
		theOffering.theView = Integer.parseInt(tfGrad.getText());
		dispose();
	}

}
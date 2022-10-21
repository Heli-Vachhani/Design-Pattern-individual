import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;


class Login extends JDialog {

	private boolean m_bExit = false;
	private JLabel jLabel1 = new JLabel();
	private JLabel jLabel2 = new JLabel();
	private JButton loginButton = new JButton();
	private JButton buttonExit = new JButton();
	private JTextField UserNameText = new JTextField();
	private JPasswordField PasswordText = new JPasswordField();
	private JRadioButton BuyerRadio = new JRadioButton();
	private JRadioButton SellerRadio = new JRadioButton();
	private ButtonGroup buttonGroup1 = new ButtonGroup();
////// Attributes Added By me
	private String UserBox = null;
	private UserInfoItem.USER_TYPE UserType = UserInfoItem.USER_TYPE.Buyer; // default to Buyer

	Login() {
		try {
			jbInit();
			setSize(300, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() {
		this.getContentPane().setLayout(null);
		jLabel1.setText("UserName");
		jLabel1.setBounds(new Rectangle(26, 52, 80, 18));
		jLabel2.setText("Password");
		jLabel2.setBounds(new Rectangle(23, 119, 80, 18));
		loginButton.setText("Login");
		loginButton.setBounds(new Rectangle(31, 212, 85, 28));
		loginButton.addActionListener(this::loginButton_actionPerformed);
		buttonExit.setText("Exit");
		buttonExit.setBounds(new Rectangle(180, 211, 97, 28));
		buttonExit.addActionListener(e -> buttonExit_actionPerformed());
		UserNameText.setBounds(new Rectangle(119, 52, 144, 22));
		PasswordText.setBounds(new Rectangle(118, 119, 147, 22));
		BuyerRadio.setSelected(true);
		BuyerRadio.setText("Buyer");
		BuyerRadio.setBounds(new Rectangle(37, 164, 103, 26));
		SellerRadio.setText("Seller");
		SellerRadio.setBounds(new Rectangle(177, 162, 103, 26));
		this.getContentPane().add(jLabel1, null);
		this.getContentPane().add(jLabel2, null);
		this.getContentPane().add(loginButton, null);
		this.getContentPane().add(buttonExit, null);
		this.getContentPane().add(UserNameText, null);
		this.getContentPane().add(PasswordText, null);
		this.getContentPane().add(BuyerRadio, null);
		this.getContentPane().add(SellerRadio, null);
		buttonGroup1.add(BuyerRadio);
		buttonGroup1.add(SellerRadio);
	}

	private void loginButton_actionPerformed(ActionEvent e) {
		BufferedReader file;
		m_bExit = false;
		System.out.println("login clicked");
		try {
			if (BuyerRadio.isSelected())//// buyer
			{
				UserType = UserInfoItem.USER_TYPE.Buyer; /// 0 for buyer
				file = new BufferedReader(new FileReader("BuyerInfo.txt"));
			} else// seller
			{
				UserType = UserInfoItem.USER_TYPE.Seller; // 1 for seller
				file = new BufferedReader(new FileReader("SellerInfo.txt"));
			}
			UserBox = UserNameText.getText();
			String PasswordBox = new String(PasswordText.getPassword());
			String LoginName = null;
			String aline, UserName, Password;
			while ((aline = file.readLine()) != null) {
				UserName = GetUserName(aline);
				Password = GetPassword(aline);
				if (UserName.compareTo(UserBox) == 0 && Password.compareTo(PasswordBox) == 0)
					LoginName = UserName;
			}
			if (LoginName != null) {
				this.dispose();
			}
		} catch (Exception ignored) {
		}

	}

	/*
	 * get the user name from aline UserName:Password
	 */
	private String GetUserName(String aline) {
		int Sep = aline.lastIndexOf(':');
		return aline.substring(0, Sep);
	}

	/*
	 * get the password from aline UserName:Password
	 */
	private String GetPassword(String aline) {
		int Sep = aline.lastIndexOf(':');
		return aline.substring(Sep + 1);
	}

	/* after login get the UserName of the login interface */
	String GetUserName() {
		return UserBox;
	}

	/* after login get the userType of the login interface */
	UserInfoItem.USER_TYPE GetUserType() {
		return UserType;
	}

	boolean isExit() {
		return m_bExit;
	}

	private void buttonExit_actionPerformed() {
		m_bExit = true;
		dispose();
	}
}
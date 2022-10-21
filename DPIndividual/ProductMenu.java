import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


abstract class ProductMenu extends JDialog
{
  Product theProduct;
  private boolean bLogout=true;

  JRadioButton TradingRadiao = new JRadioButton();
  JComboBox<Trading> TradingCombox = new JComboBox<>();
  JButton TradingViewButton = new JButton();
  JButton TradingAddButton = new JButton();
  JRadioButton OptionRadio = new JRadioButton();
  JComboBox OptionCombo = new JComboBox();
  JButton OptionViewButton = new JButton();
  JButton OptionAddButton = new JButton();
  private JButton buttonChangeProduct = new JButton();
  private JButton buttonLogout = new JButton();

  ProductMenu()
  {

    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    setModal(true);
    setSize(503,294);
  }

  private void jbInit() {
    buttonChangeProduct.setText("ChangeProduct");
    buttonChangeProduct.setBounds(new Rectangle(101, 211, 73, 37));
    buttonChangeProduct.addActionListener(this::buttonChangeProduct_actionPerformed);
    this.getContentPane().setLayout(null);
    this.setTitle("");
    buttonLogout.setText("Logout");
    buttonLogout.setBounds(new Rectangle(267, 215, 73, 37));
    buttonLogout.addActionListener(this::buttonLogout_actionPerformed);
    this.getContentPane().add(buttonChangeProduct, null);
    this.getContentPane().add(buttonLogout, null);
  }

  /* when the add button is pressed, call add trading
  function of facade, after that refresh window
  */

  /* when the add button is pressed, call ViewTrading
  function of facade, after that refresh window
  */

  abstract void ShowAddButtons();
  abstract void ShowViewButtons();
  abstract void ShowRadios();
  abstract void ShowComboxes();

  void TradingAddButton_actionPerformed()
  {
    Ptbs.theFacade.AddTrading(theProduct);
    refresh();
  }
  void TradingViewButton_actionPerformed()
  {
    Trading theTra=(Trading)TradingCombox.getSelectedItem() ;
    Ptbs.theFacade.ViewTrading(theTra);
  }
  void refresh()
  {
    TradingCombox.removeAllItems() ;
    for (Trading trading : theProduct.tradingList) {
      TradingCombox.addItem(trading);
    }
  }

  private void buttonChangeProduct_actionPerformed(ActionEvent e)
  {
    bLogout=false;
    dispose();
  }

  private void buttonLogout_actionPerformed(ActionEvent e)
  {
    bLogout=true;
    dispose();
  }
  boolean ifLogout()
  {
    return bLogout;
  }
}
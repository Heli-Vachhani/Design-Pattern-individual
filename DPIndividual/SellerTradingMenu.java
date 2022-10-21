import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DateFormat;


public class SellerTradingMenu extends TradingMenu
{
  private Trading theTrading;
  private JComboBox<Offering> CombOfferingList = new JComboBox<>();
////////////////////////


  private JTextField tbTradingName = new JTextField();
  private JTextField tbDueDate = new JTextField();
  private JTextField tbSuggestedOffering = new JTextField();

  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JButton buttonView = new JButton();
  private JButton buttonMark = new JButton();
  private JButton buttonClose = new JButton();

  SellerTradingMenu()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  private void jbInit() {
    jLabel1.setText("Trading Name");
    jLabel1.setBounds(new Rectangle(25, 31, 118, 18));
    this.getContentPane().setLayout(null);
    tbTradingName.setText("jTextField1");
    tbTradingName.setBounds(new Rectangle(192, 31, 341, 22));
    jLabel2.setText("Due Date");
    jLabel2.setBounds(new Rectangle(28, 90, 113, 18));
    tbDueDate.setText("tbDueDate");
    tbDueDate.setBounds(new Rectangle(195, 87, 337, 22));
    jLabel3.setText("Offering from Seller");
    jLabel3.setBounds(new Rectangle(28, 151, 150, 18));
    tbSuggestedOffering.setText("jTextField2");
    tbSuggestedOffering.setBounds(new Rectangle(197, 149, 339, 22));
    buttonView.setText("View");
    buttonView.setBounds(new Rectangle(458, 199, 79, 29));
    buttonView.addActionListener(this::buttonView_actionPerformed);
    buttonMark.setText("Mark");
    buttonMark.setBounds(new Rectangle(365, 249, 79, 29));
    buttonMark.addActionListener(this::buttonMark_actionPerformed);
    buttonClose.setText("Close");
    buttonClose.setBounds(new Rectangle(86, 253, 79, 29));
    buttonClose.addActionListener(this::buttonClose_actionPerformed);
    CombOfferingList.setBounds(new Rectangle(32, 204, 413, 22));
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(tbTradingName, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(tbDueDate, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(tbSuggestedOffering, null);
    this.getContentPane().add(buttonClose, null);
    this.getContentPane().add(CombOfferingList, null);
    this.getContentPane().add(buttonView, null);
    this.getContentPane().add(buttonMark, null);
  }
  public void ShowMenu(Trading trading, Person person)
  {
    theTrading=trading;
    tbTradingName.setText(theTrading.TraName );

    DateFormat theDateFormat=DateFormat.getDateInstance(DateFormat.SHORT );
    tbDueDate.setText(theDateFormat.format(theTrading.DueDate));
    tbSuggestedOffering.setText(theTrading.SuggestOffering.OfferingFileName );
    refreshOfferingList();
    setVisible(true);
  }

  private void buttonClose_actionPerformed(ActionEvent e)
  {
    theTrading.TraName = tbTradingName.getText() ;
    DateFormat tempDateFormat=DateFormat.getDateInstance(DateFormat.SHORT );
    try
    {
      theTrading.DueDate=tempDateFormat.parse(tbDueDate.getText() );
    }catch (Exception ignored){}
      theTrading.SuggestOffering.OfferingFileName =tbSuggestedOffering.getText() ;
    dispose();
  }

  private void buttonView_actionPerformed(ActionEvent e)
  {
    Offering theOffering=(Offering)CombOfferingList.getSelectedItem() ;
    if (theOffering==null)
       return;
    OfferingViewingDlg dlg=new OfferingViewingDlg();
    dlg.show(theOffering);
    refreshOfferingList();
  }

  private void buttonMark_actionPerformed(ActionEvent e)
  {
    OfferingIterator iter=new OfferingIterator(theTrading.theOfferingList );
    while(iter.hasNext() )
    {
      Offering aoffering=(Offering)iter.next();
      aoffering.setMarked();
    }
    refreshOfferingList();
  }
  private void refreshOfferingList()
  {
    CombOfferingList.removeAllItems() ;
    OfferingIterator SolIter=new OfferingIterator(theTrading.theOfferingList );
    while(SolIter.hasNext() )
    {
        Offering theOffering = (Offering) SolIter.next();
      CombOfferingList.addItem(theOffering);
    }
  }
}
import javax.swing.*;


abstract class TradingMenu extends JDialog
{
  abstract void ShowMenu(Trading ass,Person per);
  TradingMenu()
  {
    setModal(true);
    setSize(575,330);
  }
}
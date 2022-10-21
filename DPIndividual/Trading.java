import java.util.*;
import java.text.DateFormat;



public class Trading {

  String TraName;

  Date DueDate=new Date();
  OfferingList theOfferingList=new OfferingList();
  Offering SuggestOffering=new Offering();



  public Trading() {
  }

  ////add the theOffering to the Offeringlist
  void AddOffering(Offering theOffering)
  {
    theOfferingList.add(theOffering);
  }

  OfferingIterator GetOfferingIterator()
  {
    return new OfferingIterator(theOfferingList);
  }

  public String toString()
  {
    return TraName;
  }

  String getDueDateString()
  {
    DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT);
    return  dateFormat.format(DueDate);
  }

  void accept(NodeVisitor visitor)
  {
    visitor.visitTrading(this);
  }
}
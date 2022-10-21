import java.util.Iterator;


public class OfferingIterator implements Iterator
{
  private OfferingList offeringlist;

  ///  CurrentOfferingNumber: point to the location before the first element
  private int CurrentOfferingNumber=-1;

  OfferingIterator(OfferingList theofferinglist)
  {
    offeringlist=theofferinglist;
    MoveToHead();
  }

  private void MoveToHead()
  {
  ///  CurrentOfferingNumber: point to the location before the first element
    CurrentOfferingNumber=-1;
  }

  public boolean hasNext()
  {
    return CurrentOfferingNumber < offeringlist.size() - 1;
//    throw new java.lang.UnsupportedOperationException("Method hasNext() not yet implemented.");
  }
  public Object next()
  {
    if (hasNext())
    {
      CurrentOfferingNumber ++;
      return offeringlist.get(CurrentOfferingNumber);
    }
    else
    {
      return null;
    }
    //    throw new java.lang.UnsupportedOperationException("Method next() not yet implemented.");
  }

  // get the next Offering that fits the Username;
  Object next(String UserName)
  {
    Offering theOffering;
    theOffering=(Offering)next();
    while(theOffering!=null)
    {
      if(UserName.compareTo(theOffering.theAuthor)==0)
      {
        return theOffering;
      }
      theOffering=(Offering)next();
    }
    return null;
  }

  public void remove()
  {
    offeringlist.remove(CurrentOfferingNumber);
//    throw new java.lang.UnsupportedOperationException("Method remove() not yet implemented.");
  }


}
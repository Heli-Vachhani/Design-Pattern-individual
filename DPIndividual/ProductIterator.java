import java.util.Iterator;


public class ProductIterator implements Iterator
{
  private ClassProductList theProductList;
  private int CurrentProductNumber=-1;


  ProductIterator(ClassProductList productList)
  {
    theProductList=productList;
  }

  public boolean hasNext()
  {
    return CurrentProductNumber < theProductList.size() - 1;
  }

  public Object next()
  {
    if (hasNext())
    {
      CurrentProductNumber ++;
      return theProductList.get(CurrentProductNumber);
    }
    else
    {
      return null;
    }
  }
  public void remove()
  {
    theProductList.remove(CurrentProductNumber);
  }

// the next Product that fits the given ProductName
Object next(String ProductName)
  {
    Product theProduct;
    theProduct=(Product)next();
    while(theProduct!=null)
    {
      if(ProductName.compareTo(theProduct.toString())==0)
      {
        return theProduct;
      }
      theProduct=(Product)next();
    }
    return null;
  }


}
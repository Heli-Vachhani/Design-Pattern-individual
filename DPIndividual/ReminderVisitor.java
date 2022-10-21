import java.util.*;

/*
 * this class will iterate the product list attatched to on buyer and in turn
 * iterate the tradings of a product. after Function Visit(ProductList) it will
 * point to the location before the fist class, hasNext will retrun weather
 * there is next item. the next() will return the next Item Trading;
 */

public class ReminderVisitor extends NodeVisitor {

	private Reminder m_Reminder;

	ReminderVisitor(Reminder reminder) {
		m_Reminder = reminder;
	}

	public void visitFacade(Facade facade) {
		ProductIterator productList = new ProductIterator(facade.theProductList);
		while (productList.hasNext()) {
			Product product = (Product) productList.next();
			product.accept(this);
		}
	}

	public void visitProduct(Product product) {
		for (Trading trading : product.tradingList) {
			trading.accept(this);
		}
	}

	public void visitTrading(Trading trading) {
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);
		int ntoday = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.setTime(trading.DueDate);
		int nDueDate = calendar.get(Calendar.DAY_OF_YEAR);
		if (nDueDate <= (ntoday + 1) && nDueDate >= ntoday) /// upcoming
		{
			m_Reminder.listUpcoming.add("today is " + today.toString() + " " + trading.TraName + " Due Date is "
					+ trading.getDueDateString());
		}
		if (nDueDate < ntoday) {
			// put to the
			m_Reminder.listOverdue.add(trading.TraName + " Due Date is " + trading.getDueDateString());
		}

	}

}
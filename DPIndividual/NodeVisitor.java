

abstract public class NodeVisitor {

	NodeVisitor() {
	}

	abstract public void visitFacade(Facade facade);

	abstract public void visitProduct(Product product);

	abstract public void visitTrading(Trading trading);

}
package nn.expression;

import java.util.Iterator;
import nn.util.ArrayIterator;

public abstract class VariadicExpression extends Expression {
	protected Expression[] arguments;
	
	public VariadicExpression(Expression... arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public Iterator<Expression> iterator() {
		return new ArrayIterator<Expression>(arguments);
	}
}

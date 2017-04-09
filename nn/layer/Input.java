package nn.layer;

import nn.expression.Expression;

public class Input extends Expression {
	private static class Iterator implements java.util.Iterator<Expression> {
		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Expression next() {
			return null;
		}
	}
	
	@Override
	public java.util.Iterator<Expression> iterator() {
		return new Iterator();
	}

	@Override
	public double value() {
		throw new java.lang.IllegalArgumentException("Attempt to use unmapped value");
	}

	@Override
	public double derivative(Expression x) {
		throw new java.lang.IllegalArgumentException("Attempt to use unmapped value");
	}

	@Override
	public Expression newWithArgs(Expression... args) {
		throw new IllegalArgumentException("Attempt to pass new arguments to a value");
	}
}

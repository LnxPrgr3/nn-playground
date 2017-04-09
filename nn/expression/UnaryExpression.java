package nn.expression;

public abstract class UnaryExpression extends Expression {
	private static class Iterator implements java.util.Iterator<Expression> {
		private boolean hasNext;
		private Expression value;
		
		public Iterator(Expression value) {
			this.value = value;
			hasNext = true;
		}
		
		@Override
		public boolean hasNext() {
			return hasNext;
		}

		@Override
		public Expression next() {
			if(hasNext) {
				hasNext = false;
				return value;
			}
			return null;
		}
	}
	
	protected Expression argument;
	
	public UnaryExpression(Expression arg) {
		argument = arg;
	}
	
	@Override
	public java.util.Iterator<Expression> iterator() {
		return new Iterator(argument);
	}
}

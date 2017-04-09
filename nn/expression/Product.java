package nn.expression;

public class Product extends VariadicExpression {
	public Product(Expression... args) {
		super(args);
	}
	
	@Override
	public double value() {
		if(arguments.length > 0) {
			double product = 1;
			for(Expression e: this) {
				product *= e.value();
			}
			return product;
		}
		return 0;
	}

	@Override
	public double derivative(Expression x) {
		if(x == this)
			return 1;
		if(arguments.length == 0)
			return 0;
		double[] partials = new double[arguments.length];
		double values[] = new double[arguments.length];
		double value = 1;
		for(int i = 0; i < arguments.length; ++i) {
			partials[i] = arguments[i].derivative(x);
			values[i] = arguments[i].value();
			value *= values[i];
		}
		double sum = 0;
		for(int i = 0; i < arguments.length; ++i) {
			sum += partials[i] * (value / values[i]);
		}
		return sum;
	}

	@Override
	public Expression newWithArgs(Expression... args) {
		return new Product(args);
	}
}

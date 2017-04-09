package nn.expression;

public class Sum extends VariadicExpression {	
	public Sum(Expression... args) {
		super(args);
	}
	
	@Override
	public double value() {
		double res = 0;
		for(Expression e: arguments) {
			res += e.value();
		}
		return res;
	}

	@Override
	public double derivative(Expression x) {
		if(x == this)
			return 1;
		double sum = 0;
		for(Expression e: arguments) {
			sum += e.derivative(x);
		}
		return sum;
	}

	@Override
	public Expression newWithArgs(Expression... args) {
		return new Sum(args);
	}

}

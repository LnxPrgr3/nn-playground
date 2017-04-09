package nn.expression;

public class Negate extends UnaryExpression {
	public Negate(Expression arg) {
		super(arg);
	}

	@Override
	public double value() {
		return -argument.value();
	}

	@Override
	public double derivative(Expression x) {
		return x == this ? 1 :
			-1 * argument.derivative(x);
	}
}

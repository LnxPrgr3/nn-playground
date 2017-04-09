package nn.expression;

public class Reciprocal extends UnaryExpression {
	public Reciprocal(Expression arg) {
		super(arg);
	}

	@Override
	public double value() {
		return 1 / argument.value();
	}

	@Override
	public double derivative(Expression x) {
		return -argument.derivative(x) / (argument.value()*argument.value());
	}
}

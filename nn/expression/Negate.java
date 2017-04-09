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

	@Override
	public Expression newWithArgs(Expression... args) {
		if(args.length != 1) {
			throw new IllegalArgumentException("Negate cannot accept multiple arguments!");
		}
		return new Negate(args[0]);
	}
}

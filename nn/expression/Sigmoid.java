package nn.expression;

public class Sigmoid extends UnaryExpression {
	public Sigmoid(Expression arg) {
		super(arg);
	}

	private static double sigmoid(double x) {
		return 1 / (1 + Math.exp(-x));
	}
	
	@Override
	public double value() {
		return sigmoid(argument.value());
	}

	@Override
	public double derivative(Expression x) {
		return x == this ? 1 :
			sigmoid(argument.value()) * (1 - sigmoid(argument.value())) * argument.derivative(x);
	}
}

package nn.layer;

import nn.expression.Expression;
import nn.expression.UnaryExpression;

public class Output extends UnaryExpression {
	double error;

	public Output(Expression arg) {
		super(arg);
	}

	@Override
	public double value() {
		return argument.value();
	}
	
	public double error() {
		return error;
	}
	
	public void error(double x) {
		error = x;
	}

	@Override
	public double derivative(Expression x) {
		return argument.derivative(x) * error;
	}

	@Override
	public Expression newWithArgs(Expression... args) {
		if(args.length != 1) {
			throw new IllegalArgumentException("Output cannot accept multiple arguments!");
		}
		return new Output(args[0]);
	}
}

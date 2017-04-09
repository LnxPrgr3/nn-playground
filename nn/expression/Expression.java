package nn.expression;

public abstract class Expression implements Iterable<Expression> {
	public abstract double value();
	public abstract double derivative(Expression x);
	public abstract Expression newWithArgs(Expression... args);
	
	public static Expression sum(Expression... args) {
		return new Sum(args);
	}
	
	public static Expression product(Expression... args) {
		return new Product(args);
	}
	
	public static Expression negate(Expression arg) {
		return new Negate(arg);
	}
	
	public static Expression reciprocal(Expression arg) {
		return new Reciprocal(arg);
	}
	
	public static Expression sigmoid(Expression arg) {
		return new Sigmoid(arg);
	}
}

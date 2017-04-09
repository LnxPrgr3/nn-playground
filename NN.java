import nn.expression.Expression;

public class NN {
	private static class Variable extends Expression {
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
		
		private double value;
		
		public Variable(double x) {
			value = x;
		}
		
		public void value(double x) {
			value = x;
		}
		
		@Override
		public double value() {
			return value;
		}

		@Override
		public java.util.Iterator<Expression> iterator() {
			return new Iterator();
		}

		@Override
		public double derivative(Expression x) {
			return x == this ? 1 : 0;
		}
	}
	

	public static void main(String[] args) {
		Variable x = new Variable(2);
		Variable y = new Variable(-3);
		Expression f = Expression.product(x, y);
		System.out.printf("%f * %f = %f\n", x.value(), y.value(), f.value());
		System.out.printf("d_x = %f\n", f.derivative(x));
		System.out.printf("d_y = %f\n", f.derivative(y));
		x.value(-2);
		y.value(5);
		Variable z = new Variable(-4);
		f = Expression.product(Expression.sum(x, y), z);
		System.out.printf("(%f + %f) * %f = %f\n", x.value(), y.value(), z.value(), f.value());
		System.out.printf("d_x = %f\n", f.derivative(x));
		System.out.printf("d_y = %f\n", f.derivative(y));
		System.out.printf("d_z = %f\n", f.derivative(z));
		f = Expression.sum(x, Expression.negate(y));
		x.value(7);
		y.value(3);
		System.out.printf("%f - %f = %f\n", x.value(), y.value(), f.value());
		System.out.printf("d_x = %f\n", f.derivative(x));
		System.out.printf("d_y = %f\n", f.derivative(y));
		f = Expression.product(x, Expression.reciprocal(y));
		x.value(15);
		y.value(3);
		System.out.printf("%f / %f = %f\n", x.value(), y.value(), f.value());
		System.out.printf("d_x = %f\n", f.derivative(x));
		System.out.printf("d_y = %f\n", f.derivative(y));
		f = Expression.sigmoid(x);
		x.value(3);
		System.out.printf("sigmoid(%f) = %f\n", x.value(), f.value());
		System.out.printf("d_x = %f\n", f.derivative(x));
		Variable a = new Variable(1);
		Variable b = new Variable(2);
		Variable c = new Variable(-3);
		x.value(-1);
		y.value(3);
		f = Expression.sigmoid(Expression.sum(Expression.product(a, x), Expression.product(b, y), c));
		System.out.printf("circuit output: %f\n", f.value());
		double step_size = 0.01;
		System.out.printf("d_a = %f\n", f.derivative(a));
		System.out.printf("d_b = %f\n", f.derivative(b));
		System.out.printf("d_c = %f\n", f.derivative(c));
		System.out.printf("d_x = %f\n", f.derivative(x));
		System.out.printf("d_y = %f\n", f.derivative(y));
		a.value(a.value()+step_size*f.derivative(a));
		b.value(b.value()+step_size*f.derivative(b));
		c.value(c.value()+step_size*f.derivative(c));
		x.value(x.value()+step_size*f.derivative(x));
		y.value(y.value()+step_size*f.derivative(y));
		System.out.printf("circuit output after one backprop: %f\n", f.value());
	}
}

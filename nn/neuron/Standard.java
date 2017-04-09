package nn.neuron;

import nn.expression.*;
import nn.layer.*;

public class Standard extends Sigmoid {
	public Standard() {
		super(Expression.sum(Expression.product(new Input(), new Weight()), new Bias()));
	}
}

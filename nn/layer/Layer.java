package nn.layer;

import java.util.ArrayList;
import nn.expression.*;

public class Layer {
	private int numInputs;
	private int numOutputs;
	private int curOutput;
	double[] inputs;
	double[] weights;
	double[] biases;
	BoundWeight[] weightNodes;
	BoundBias[] biasNodes;
	Expression neuron;
	
	private class BoundInput extends Input {
		private int index;
		
		public BoundInput(int idx) {
			index = idx;
		}
		
		@Override
		public double value() {
			return inputs[index];
		}
	}
	
	private class BoundWeight extends Weight {
		private int index;
		
		public BoundWeight(int idx) {
			index = idx;
		}
		
		@Override
		public double value() {
			return weights[weightIndex(index, curOutput)];
		}
	}
	
	private class BoundBias extends Bias {
		@Override
		public double value() {
			return biases[curOutput];
		}
	}
	
	private BoundWeight[] weights() {
		if(weightNodes == null) {
			weightNodes = new BoundWeight[numInputs];
			for(int i = 0; i < numInputs; ++i) {
				weightNodes[i] = new BoundWeight(i);
			}
		}
		return weightNodes;
	}
	
	private Object bind(Expression neuron, BoundInput[] inputs) {
		if(neuron instanceof Input) {
			return inputs;
		} else if(neuron instanceof Weight) {
			return weights();
		} else if(neuron instanceof Bias) {
			return new BoundBias();
		} else {
			ArrayList<Expression> args = new ArrayList<Expression>();
			int include_weights = 0;
			int include_inputs = 0;
			for(Expression child: neuron) {
				Object bound = bind(child, inputs);
				if(bound instanceof Expression) {
					args.add((Expression)bound);
				} else if(bound instanceof BoundWeight[]) {
					++include_weights;
				} else if(bound instanceof BoundInput[]) {
					++include_inputs;
				} else {
					Expression[] boundArr = (Expression[])bound;
					for(Expression e: boundArr)
						args.add(e);
				}
			}
			if(include_weights > 0 || include_inputs > 0) {
				ArrayList<Expression> newArr = new ArrayList<Expression>();
				for(int i = 0; i < numInputs; ++i) {
					ArrayList<Expression> newArgs = new ArrayList<Expression>(args);
					for(int j = 0; j < include_inputs; ++j) {
						newArgs.add(inputs[i]);
					}
					for(int j = 0; j < include_weights; ++j) {
						newArgs.add(weights()[i]);
					}
					newArr.add(neuron.newWithArgs((Expression[])newArgs.toArray(new Expression[newArgs.size()])));
				}
				return newArr.toArray(new Expression[args.size()]);
			}
			return neuron.newWithArgs((Expression[])args.toArray(new Expression[args.size()]));
		}
	}
	
	private Expression bind(Expression neuron) {
		BoundInput[] inputArr = new BoundInput[numInputs];
		for(int i = 0; i < numInputs; ++i)
			inputArr[i] = new BoundInput(i);
		Object bound = bind(neuron, inputArr);
		if(!(bound instanceof Expression)) {
			throw new RuntimeException("Could not rebind expression");
		}
		return (Expression)bound;
	}
	
	public Layer(int inputs, int outputs, Expression neuron) {
		numInputs = inputs;
		numOutputs = outputs;
		weights = new double[inputs*outputs];
		biases = new double[outputs];
		this.neuron = (Expression)bind(neuron);
	}
	
	public int numInputs() { return numInputs; }
	public int numOutputs() { return numOutputs; }
	
	private int weightIndex(int input, int output) {
		return output*numInputs+input;
	}
	
	public double weight(int input, int output) {
		return weights[weightIndex(input, output)];
	}
	
	public void weight(int input, int output, double value) {
		weights[weightIndex(input, output)] = value;
	}
	
	public double bias(int output) {
		return biases[output];
	}
	
	public void bias(int output, double value) {
		biases[output] = value;
	}
	
	public double[] evaluate(double[] input, double[] output) {
		inputs = input;
		for(curOutput = 0; curOutput < numOutputs; ++curOutput) {
			output[curOutput] = neuron.value();
		}
		inputs = null;
		return output;
	}
	
	public double[] evaluate(double[] input) {
		return evaluate(input, new double[numOutputs]);
	}
}

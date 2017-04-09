package nn;

import nn.layer.Layer;

public class Network {
	private Layer[] layers;
	private double[][] outputs;
	
	public Network(Layer... layers) {
		this.layers = layers;
		outputs = new double[layers.length][];
		for(int i = 0; i < layers.length; ++i) {
			outputs[i] = new double[layers[i].numOutputs()];
		}
	}
	
	public double[] evaluate(double[] input) {
		for(int i = 0; i < layers.length; ++i) {
			input = layers[i].evaluate(input, outputs[i]);
		}
		return input;
	}
}

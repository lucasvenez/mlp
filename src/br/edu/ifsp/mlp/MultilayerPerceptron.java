package br.edu.ifsp.mlp;

public class MultilayerPerceptron {

	private Double[] inputs;

	private Double[] inputBiases;

	private Neuron[] hiddenLayer;

	private Neuron[] outputLayer;

	public MultilayerPerceptron(Double[] inputLayerValues, int hiddenLayerSize, int outputLayerSize) {

		this.inputs = inputLayerValues;

		this.inputBiases = new Double[inputLayerValues.length];

		this.hiddenLayer = new Neuron[hiddenLayerSize];

		for (int i = 0; i < hiddenLayerSize; i++)
			this.hiddenLayer[i] = new Neuron();

		this.outputLayer = new Neuron[outputLayerSize];

		for (int i = 0; i < outputLayerSize; i++)
			this.outputLayer[i] = new Neuron();
	}

	public Double[] getInputs() {
		return inputs;
	}

	public void setInputs(Double[] inputs) {
		this.inputs = inputs;
	}

	public Double[] getInputBiases() {
		return inputBiases;
	}

	public void setInputBiases(Double[] inputBiases) {
		this.inputBiases = inputBiases;
	}

	public Neuron[] getHiddenLayer() {
		return hiddenLayer;
	}

	public void setHiddenLayer(Neuron[] hiddenLayer) {
		this.hiddenLayer = hiddenLayer;
	}

	public Neuron[] getOutputLayer() {
		return outputLayer;
	}

	public void setOutputLayer(Neuron[] outputLayer) {
		this.outputLayer = outputLayer;
	}

	public MultilayerPerceptron(int inputLayerSize, int hiddenLayerSize, int outputLayerSize) {

		this.inputs = new Double[inputLayerSize];

		this.inputBiases = new Double[inputLayerSize];

		this.hiddenLayer = new Neuron[hiddenLayerSize];

		for (int i = 0; i < hiddenLayerSize; i++)
			this.hiddenLayer[i] = new Neuron();

		this.outputLayer = new Neuron[outputLayerSize];

		for (int i = 0; i < outputLayerSize; i++)
			this.outputLayer[i] = new Neuron();
	}

	public void setBiases(Double[] biases) {

		int j = 0;

		for (int i = 0; i < inputs.length; i++)
			this.inputBiases[i] = biases[j++];

		for (int i = 0; i < hiddenLayer.length; i++)
			hiddenLayer[i].setBias(biases[j++]);

		for (int i = 0; i < outputLayer.length; i++)
			outputLayer[i].setBias(biases[j++]);
	}

	public Double[] forward() throws Exception {

		Double[] inputOutput = this.inputs;

		Double[] hiddenOutput = new Double[this.hiddenLayer.length];

		for (int i = 0; i < this.hiddenLayer.length; i++)
			hiddenOutput[i] = this.hiddenLayer[i].activation(inputOutput);

		Double[] output = new Double[this.outputLayer.length];

		for (int i = 0; i < this.outputLayer.length; i++)
			output[i] = this.outputLayer[i].activation(hiddenOutput);

		return output;
	}

	public void setWeightsBetweenInputAndHidden(Double[] weights) {

		int q = 0;

		for (int i = 0; i < this.hiddenLayer.length; i++)
			for (int j = 0; j < this.inputs.length; j++)
				hiddenLayer[i].setWeight(j, weights[q++]);

		for (int i = 0; i < this.outputLayer.length; i++)
			for (int j = 0; j < this.hiddenLayer.length; j++)
				outputLayer[i].setWeight(j, weights[q++]);
	}
}

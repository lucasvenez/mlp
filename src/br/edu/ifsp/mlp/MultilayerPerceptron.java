package br.edu.ifsp.mlp;

public class MultilayerPerceptron {

	private Double[] inputs;

	private Neuron[][] hiddenLayers;

	private Neuron[] outputLayer;

	
	public MultilayerPerceptron(int inputLayerSize, int hiddenLayerSize, int[] neurosPerHiddenLayer, int outputLayerSize) {
		
	}
	
	/**
	 * This constructor infers the mlp has only one hidden layer.
	 * @param inputLayerValues
	 * @param hiddenLayerSize
	 * @param outputLayerSize
	 */
	public MultilayerPerceptron(int inputLayerSize, int hiddenLayerSize, int outputLayerSize) {

		this.inputs = new Double[inputLayerSize];

		this.hiddenLayers = new Neuron[1][hiddenLayerSize];

		for (int i = 0; i < hiddenLayerSize; i++)
			this.hiddenLayers[0][i] = new Neuron();

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

	public Neuron[][] getHiddenLayers() {
		return hiddenLayers;
	}

	public void setHiddenLayer(Neuron[][] hiddenLayers) {
		this.hiddenLayers = hiddenLayers;
	}

	public Neuron[] getOutputLayer() {
		return outputLayer;
	}

	public void setOutputLayer(Neuron[] outputLayer) {
		this.outputLayer = outputLayer;
	}

	public void setBiases(Double[] biases) {

		int k = 0;
		
		for (int i = 0; i < hiddenLayers.length; i++)
			for (int j = 0; j < hiddenLayers[i].length; j++)
				hiddenLayers[i][j].setBias(biases[k++]);

		for (int i = 0; i < outputLayer.length; i++)
			outputLayer[i].setBias(biases[k++]);
	}

	public Double[] forward() throws Exception {

		Double[] inputOutput = this.inputs;

		Double[] hiddenOutput = new Double[this.hiddenLayers.length];

		for (int i = 1; i < this.hiddenLayers.length; i++)
			for 
			hiddenOutput[i] = this.hiddenLayers[i].activation(inputOutput);

		Double[] output = new Double[this.outputLayer.length];

		for (int i = 0; i < this.outputLayer.length; i++)
			output[i] = this.outputLayer[i].activation(hiddenOutput);

		return output;
	}

	public void setWeightsBetweenInputAndHidden(Double[] weights) {

		int q = 0;

		for (int i = 0; i < this.hiddenLayers.length; i++)
			for (int j = 0; j < this.inputs.length; j++)
				hiddenLayers[i].setWeight(j, weights[q++]);

		for (int i = 0; i < this.outputLayer.length; i++)
			for (int j = 0; j < this.hiddenLayers.length; j++)
				outputLayer[i].setWeight(j, weights[q++]);
	}
}

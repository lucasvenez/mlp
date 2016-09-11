package io.github.lucasvenez.ann.mlp;

import static io.github.lucasvenez.utils.ArraysUtil.toObject;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOfRange;

import java.util.ArrayList;
import java.util.List;

import io.github.lucasvenez.ann.mlp.exception.NeuralNetworkBuildingException;
import io.github.lucasvenez.ann.mlp.exception.NeuralNetworkFowardException;
import io.github.lucasvenez.ann.mlp.function.ActivationFunction;
import io.github.lucasvenez.ann.mlp.function.IdentityFunction;
import io.github.lucasvenez.ann.mlp.function.SigmoidFunction;
import io.github.lucasvenez.ann.mlp.layer.InputLayer;
import io.github.lucasvenez.ann.mlp.layer.Layer;
import io.github.lucasvenez.ann.mlp.layer.ParallelProcessingLayer;
import io.github.lucasvenez.ann.mlp.layer.ProcessingLayer;
import io.github.lucasvenez.stat.random.UniformGenerator;

/**
 * 
 * @author <a href="http://lucasvenez.github.io">Lucas Venezian Povoa</a>
 *
 */
public class MultilayerPerceptron extends ArtificialNeuralNetwork {

	private InputLayer inputLayer;

	private final List<ProcessingLayer> hiddenLayers = new ArrayList<ProcessingLayer>();

	private ProcessingLayer outputLayer;

	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 * @throws NeuralNetworkBuildingException
	 */
	public void setInputLayer(int numberOfNeurons, ActivationFunction activationFunction)
			throws NeuralNetworkBuildingException {

		if (numberOfNeurons < 1)
			throw new NeuralNetworkBuildingException("The input layer should have at least one neuron.");

		if (activationFunction == null)
			throw new NeuralNetworkBuildingException("It is not possible set null to activation functions");

		this.inputLayer = new InputLayer(numberOfNeurons, activationFunction);

		if (hiddenLayers.size() > 0)
			hiddenLayers.get(0).setPreviousLayer(this.inputLayer);

		else if (this.outputLayer != null)
			this.outputLayer.setPreviousLayer(this.inputLayer);
	}

	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 */
	public void addHiddenLayer(int numberOfNeurons, ActivationFunction activationFunction)
			throws NeuralNetworkBuildingException {
		this.addHiddenLayer(new ProcessingLayer(numberOfNeurons, activationFunction));
	}

	/**
	 * 
	 * @param hiddenLayer
	 */
	public void addHiddenLayer(ProcessingLayer hiddenLayer) throws NeuralNetworkBuildingException {

		Layer previousLayer;

		if (this.hiddenLayers.size() > 0)
			previousLayer = this.hiddenLayers.get(this.hiddenLayers.size() - 1);
		else
			previousLayer = this.inputLayer;

		hiddenLayer.setPreviousLayer(previousLayer);

		this.hiddenLayers.add(hiddenLayer);

		if (this.outputLayer != null)
			this.outputLayer.setPreviousLayer(hiddenLayer);
	}

	/**
	 * 
	 * @param numberOfNeurons
	 * @param activationFunction
	 * @throws NeuralNetworkBuildingException
	 */
	public void setOutputLayer(int numberOfNeurons, ActivationFunction activationFunction)
			throws NeuralNetworkBuildingException {

		this.outputLayer = new ProcessingLayer(numberOfNeurons, activationFunction);

		Layer previousLayer;

		if (this.hiddenLayers.size() > 0)
			previousLayer = this.hiddenLayers.get(this.hiddenLayers.size() - 1);
		else
			previousLayer = this.inputLayer;

		this.outputLayer.setPreviousLayer(previousLayer);
	}

	/**
	 * 
	 * @param numberOfNeurons
	 */
	public void setOutputLayer(int numberOfNeurons) throws NeuralNetworkBuildingException {
		this.setOutputLayer(numberOfNeurons, new IdentityFunction());
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	@Override
	public Double[] apply(Double ... inputs) {
		return apply(asList(inputs)).toArray(new Double[outputLayer.getNumberOfNeurons()]);
	}

	/**
	 * 
	 * @param inputs
	 * @return
	 * @throws NeuralNetworkFowardException
	 */
	public List<Double> apply(List<Double> inputs) {

		List<Double> outputs = null;

		try {

			outputs = this.inputLayer.process(inputs);

			for (int i = 0; i < hiddenLayers.size(); i++)
				outputs = hiddenLayers.get(i).process(outputs);

			outputs = this.outputLayer.process(outputs);

		} catch (NeuralNetworkFowardException e) {
			e.printStackTrace();
		}

		return outputs;
	}

	/**
	 * 
	 */
	public void initializeWeightsRandomly() {

		for (ProcessingLayer h : this.hiddenLayers)
			h.initializeWeightsRandomly();

		outputLayer.initializeWeightsRandomly();
	}

	public void initializeWeightsWithRandomUniformDistribution() {

		UniformGenerator unifg = new UniformGenerator();

		double[] uniform = unifg.generate(this.getNumberOfConnections(), 0.0, 1.0);

		int offset = 0;

		for (ProcessingLayer p : this.getProcessingLayers()) {

			Double[] subset = toObject(copyOfRange(uniform, offset, offset + p.getNumberOfIncomingConnections()));

			p.setWeights(subset);

			offset += p.getNumberOfIncomingConnections();
		}
	}

	public int getNumberOfConnections() {

		final List<Layer> layers = this.getLayers();

		int result = 0;

		for (int i = 1; i < layers.size(); i++) {
			result += layers.get(i - 1).getNeurons().size() * layers.get(i).getNeurons().size();

			if (((ProcessingLayer) (layers.get(i))).hasBiases()) {
				result += layers.get(i).getNeurons().size();
			}
		}

		return result;
	}

	public List<Layer> getLayers() {

		final List<Layer> layers = new ArrayList<Layer>();

		layers.add(this.inputLayer);

		layers.addAll(this.hiddenLayers);

		layers.add(outputLayer);

		return layers;
	}

	public List<ProcessingLayer> getProcessingLayers() {

		final List<ProcessingLayer> layers = new ArrayList<ProcessingLayer>();

		layers.addAll(this.hiddenLayers);

		layers.add(outputLayer);

		return layers;
	}

	public ProcessingLayer getOutputLayer() {
		return this.outputLayer;
	}

	public ProcessingLayer getProcessingLayer(int index) {
		return this.getProcessingLayers().get(index);
	}

	public List<ProcessingLayer> getHiddenLayers() {
		return this.hiddenLayers;
	}

	public int getNumberOfHiddenLayers() {
		return this.hiddenLayers.size();
	}

	public void addParallelHiddenLayer(int numberOfNeurons, SigmoidFunction activationFunction)
			throws NeuralNetworkBuildingException {
		this.addHiddenLayer(new ParallelProcessingLayer(numberOfNeurons, activationFunction));
	}

	public void setParallelOutputLayer(int numberOfNeurons, ActivationFunction activationFunction) {

		this.outputLayer = new ParallelProcessingLayer(numberOfNeurons, activationFunction);

		Layer previousLayer;

		if (this.hiddenLayers.size() > 0)
			previousLayer = this.hiddenLayers.get(this.hiddenLayers.size() - 1);
		else
			previousLayer = this.inputLayer;

		this.outputLayer.setPreviousLayer(previousLayer);

	}
}

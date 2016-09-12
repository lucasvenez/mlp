# Machine Learning Framework for Java (MLFJ) 

It is an implementation of Multilayer Perceptron Neuron Network. The concepts are based on Simon Haykin (2009). Neural Networks and Learning Machines, 3rd edition. ISBN: 0-13-147139-2.

To define a MLP use:

```Java
MultilayerPerceptron mlp = new MultilayerPerceptron();
		
mlp.setInputLayer(10, new IdentityFunction());
		
mlp.addHiddenLayer(2, new SigmoidFunction());
	
HiddenLayer hiddenLayer = new HiddenLayer();

hiddenLayer.addNeuron(new HiddenNeuron(new SigmoidFunction()));
	
mlp.addHiddenLayer(hiddenLayer);
	
mlp.setOutputLayer(1, new ThresholdFunction(0.5));
```

To traine that MLP use:

```Java
Backpropagation back = new Backpropagation(mlp);

/*
 * Creating training data
 */
List<Integer[]> inputs = new ArrayList<Integer[]>();
List<Integer[]> outputs = new ArrayList<Integer[]>();

final int AND = 0, OR = 1, XOR = 2;

for (int i = 0; i < 2; i++)
	for (int j = 0; j < 2; j++) {
		inputs.add(new Integer[] {i,  j,  AND});
		outputs.add(new Integer[] {i + j == 2 ? 1 : 0});
		
		inputs.add(new Integer[] {i,  j,  OR});
		outputs.add(new Integer[] {i + j > 0 ? 1 : 0});
		
		inputs.add(new Integer[] {i,  j,  XOR});
		outputs.add(new Integer[] {i + j == 1 ? 1 : 0});
	}

back.setTraineInput(inputs);

back.setTraineOutput(outputs);

back.traine();
```
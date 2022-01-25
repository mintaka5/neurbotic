package org.white5moke.neural;

import java.time.Instant;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Neuron {
    public ArrayList<NeuralInput> inputs = new ArrayList<>();
    private double outputWeight = 0;

    private Instant starter;
    private Instant moment;

    public Neuron() throws InterruptedException {
        starter = Instant.now();

        while (true) {
            NeuralInput neuralInput = new NeuralInput();
            inputs.add(neuralInput);
            combinator();
            Thread.sleep(neuralInput.getTimeDelta());


        }
    }

    private void combinator() {
        /*
        TODO : this averages now. figure out how this is calculated from all input weights
         */
        setOutputWeight(inputs.stream().mapToDouble(x -> x.getWeight()).average().getAsDouble());

        try {
            // moving percentile to establish rememberance inputs
            double min = inputs.stream().mapToDouble(x -> x.getWeight()).min().orElse(-1.0);
            double max = inputs.stream().mapToDouble(x -> x.getWeight()).max().orElse(1.0);

            // keep forgetting
            inputs.removeIf(x -> (x.getWeight() < min || x.getWeight() > max));

            // let's only do a minimal poll
            // TODO : temp. remove later
            if(inputs.size() > 31) inputs.remove(0);

            System.out.print(String.format("avg. weight: %s; total inputs: %s\r",
                    inputs.stream().mapToDouble(x -> x.getWeight()).average().getAsDouble(),
                    inputs.size()
            ));
        } catch(NoSuchElementException e) {
            return;
        }
    }

    public double getOutputWeight() {
        return outputWeight;
    }

    public void setOutputWeight(double outputWeight) {
        this.outputWeight = outputWeight;
    }
}

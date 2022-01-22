package org.white5moke.neural;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Neuron {
    public List<NeuralInput> inputs = new ArrayList<>();
    public static final int INPUT_LIMIT = 5;
    private double outputWeight = 0;

    public Neuron() throws InterruptedException {
        while (true) {
            NeuralInput neuralInput = new NeuralInput();
            inputs.add(neuralInput);
            System.out.println(neuralInput);
            Thread.sleep(neuralInput.getTimeDelta());

            combinator();
        }
    }

    private void combinator() {
        /*
        TODO : this avgs now, but i need to figure out how this is calculated from all input weights
         */
        OptionalDouble avgWeight = inputs.stream()
                .mapToDouble(NeuralInput::getWeight)
                .reduce((a, b) -> a + b / inputs.size());
        setOutputWeight(avgWeight.getAsDouble());

        inputs.stream().filter(i -> (i.getWeight() < 0)).forEach(i -> {
            if(inputs.size() > 1) inputs.remove(i);
        });
    }

    public double getOutputWeight() {
        return outputWeight;
    }

    public void setOutputWeight(double outputWeight) {
        this.outputWeight = outputWeight;
    }
}

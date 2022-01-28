package org.white5moke.neural;

import java.time.Instant;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Neuron {
    public ArrayList<NeuralInput> inputs = new ArrayList<>();
    private double outputWeight = 0;

    private double rememberingFactor = 0;
    private double forgettingFactor = 0;

    private Instant starter;
    private Instant moment;

    public Neuron() throws InterruptedException {
        starter = Instant.now();

        while (true) {
            NeuralInput neuralInput = new NeuralInput();
            inputs.add(neuralInput);

            // this combines all inputs for aggregate knowledge
            combinator();
            Thread.sleep(neuralInput.getTimeDelta());
        }
    }

    public double getRememberingFactor() {
        return rememberingFactor;
    }

    public void setRememberingFactor(double rememberingFactor) {
        this.rememberingFactor = rememberingFactor;
    }

    public double getForgettingFactor() {
        return forgettingFactor;
    }

    public void setForgettingFactor(double forgettingFactor) {
        this.forgettingFactor = forgettingFactor;
    }

    private void combinator() {
        try {
            // moving percentile to establish rememberance inputs
            double min = inputs.stream().mapToDouble(x -> x.getWeight()).min().orElse(-1.0);
            double max = inputs.stream().mapToDouble(x -> x.getWeight()).max().orElse(1.0);

            /**
             * rememberance/forgetting if +n then strongly remembered
             * (keep in mind too strong needs to be forgotten, but
             * +n's tend to hold out longer than -n's)
             * else if -n then trends to be forgotten after minimum threshold.
             * maybe if -avg. then rememberance is weaker, and vice-versa
             */

            // keep forgetting
            inputs.removeIf(x -> (x.getWeight() < min || x.getWeight() > max));

            // let's only do a minimal poll
            // TODO : temp. remove later
            if(inputs.size() > 31) inputs.remove(0);

            // supply output weight
            // TODO : this averages now. figure out how this is calculated from all input weights
            setOutputWeight(inputs.stream().mapToDouble(x -> x.getWeight()).average().getAsDouble());

            /**
             * set remembering/forgetting factors
             */


            System.out.print(String.format(
                    "min: %s; max: %s; output wgt.: %s; # of polls: %s\r",
                    min, max, getOutputWeight(), inputs.size()
            ));


        } catch(NoSuchElementException e) {
            System.out.println("no weight values present from inputs");
            return;
        }
    }

    private double trendForgetting(double min, double outputWeight) {
        return 0;
    }

    private double trendRemembering(double max, double outputWeight) {
        return 0;
    }

    public double getOutputWeight() {
        return outputWeight;
    }

    public void setOutputWeight(double outputWeight) {
        this.outputWeight = outputWeight;
    }
}

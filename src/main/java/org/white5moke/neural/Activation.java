package org.white5moke.neural;

import org.hipparchus.analysis.function.Sigmoid;
import org.hipparchus.analysis.function.Tanh;

import java.util.List;
import java.util.Random;

public class Activation {
    public enum activationType {
        TANH, SIGMOID
    };
    private String type;
    private double weight = 0.0;

    public Activation() {
        setType(getRandomType());

        setWeight(new Random().doubles(-1.0, 1.0).findFirst().getAsDouble());

        trigger();
    }

    public void trigger() {
        switch (activationType.valueOf(getType())) {
            case TANH -> {
                Tanh t = new Tanh();
                setWeight((float) t.value(getWeight()));
            }
            case SIGMOID -> {
                Sigmoid s = new Sigmoid(-1, 1);
                setWeight((float) s.value(getWeight()));
            }
            default -> {}
        }
    }

    private String getRandomType() {
        List<activationType> v = List.of(activationType.values());
        int size = v.size();
        Random r = new Random();

        return v.get(r.nextInt(size)).toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

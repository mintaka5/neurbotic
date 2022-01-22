package org.white5moke.neural;

import org.white5moke.net.Listener5;
import org.white5moke.net.Sender5;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Base64;
import java.util.List;

public class NeuralInput {
    public String id;
    public double weight;

    public int timeDelta;

    public List<Listener5> listener;
    public Sender5 sender;

    private Activation activation;

    public NeuralInput() throws InterruptedException {
        // use a random string token for identifier
        setId(generateToken());

        setActivation(new Activation());

        setWeight(getActivation().getWeight());

        int t = (int) Math.floor(getWeight() * 10_000);
        setTimeDelta(Math.abs(t));
    }

    public Activation getActivation() {
        return activation;
    }

    public void setActivation(Activation activation) {
        this.activation = activation;
    }

    private void setTimeDelta(int a) {
        this.timeDelta = a;
    }

    public int getTimeDelta() {
        return this.timeDelta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public static String generateToken() {
        SecureRandom r = new SecureRandom();
        byte[] b = new byte[20];

        r.nextBytes(b);

        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();

        return encoder.encodeToString(b);
    }

    public String toString() {
        return String.format("%16s|%28s|%8s|%32s\r",
                getId(),
                getWeight(),
                getActivation().getType(),
                Instant.now().atZone(ZoneId.of("UTC"))
        );
    }
}

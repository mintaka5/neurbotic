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

    public List<Listener5> listener;
    public Sender5 sender;

    public NeuralInput() throws InterruptedException {
        while(true) {
            // use a random string token for identifier
            setId(generateToken());

            Activation activation = new Activation();

            setWeight(activation.getWeight());
            int t = (int) Math.floor(getWeight() * 10_000);
            System.out.println(String.format("%16s|%28s|%8s|%32s",
                    getId(),
                    getWeight(),
                    activation.getType(),
                    Instant.now().atZone(ZoneId.of("UTC"))
            ));
            Thread.sleep(Math.abs(t));
        }
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
}

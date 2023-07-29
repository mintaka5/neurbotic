package org.white5moke.neural.lab;

import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;

public class AwarenessEngine {
    public AwarenessEngine() throws InterruptedException {
        Impulse.getInstance()
                .withIdentity(RandomStringUtils.randomAlphanumeric(4).getBytes(StandardCharsets.UTF_8))
                .start();
        Impulse.getInstance()
                .withIdentity(RandomStringUtils.randomAlphanumeric(8).getBytes(StandardCharsets.UTF_8))
                .start();
        Impulse.getInstance()
                .withIdentity(RandomStringUtils.randomAlphanumeric(2).getBytes(StandardCharsets.UTF_8))
                .start();
    }
}

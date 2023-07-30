package org.white5moke.neural.lab;

import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class AwarenessEngine {
    AtomicLong globalResult = new AtomicLong(0L);

    public AwarenessEngine() {
        Arrays.stream(new int[] {4, 8, 2}).forEach((i) -> {
            Impulse.getInstance()
                    .present(globalResult)
                    .withIdentity(RandomStringUtils.randomAlphanumeric(i).getBytes(StandardCharsets.UTF_8))

                    .start();
        });
    }
}

package br.com.fiap.crypto;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RSA {

    private final BigInteger n;
    private final BigInteger e;
    private final BigInteger d;

    public RSA() {
        this.n = new BigInteger("377");
        this.e = new BigInteger("5");
        this.d = new BigInteger("269");
    }

    public String encrypt(String message) {
        return message.chars()
                .mapToObj(BigInteger::valueOf)
                .map(m -> m.modPow(e, n))
                .map(BigInteger::toString)
                .collect(Collectors.joining(","));
    }

    public String decrypt(String encryptedMessage) {
        String[] encryptedChars = encryptedMessage.split(",");

        return Arrays.stream(encryptedChars)
                .map(BigInteger::new)
                .map(c -> c.modPow(d, n))
                .map(m -> (char) m.intValue())
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public BigInteger getPublicKeyE() {
        return this.e;
    }

    public BigInteger getModulusN() {
        return this.n;
    }
}

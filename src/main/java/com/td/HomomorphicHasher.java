package com.td;

import java.math.BigInteger;

import static com.google.common.base.Preconditions.checkArgument;

public class HomomorphicHasher implements Hasher, Validator {

  private final BigInteger g;
  private final BigInteger p;
  private final BigInteger q;

  public HomomorphicHasher(int g, int p, int q) {
    checkArgument(p % q == 1, "p mod q has to be 1.");
    this.g = BigInteger.valueOf(g);
    this.p = BigInteger.valueOf(p);
    this.q = BigInteger.valueOf(q);
    checkArgument(this.q.isProbablePrime(1), "q has to be prime.");
    checkArgument(this.g.modPow(this.q, this.p).equals(BigInteger.ONE), "g^q mod p has to be 1.");
  }

  public int[] hashValue(int[] value) {
    for (int i = 0; i < value.length; i++) {
      value[i] = g.pow(value[i]).mod(p).intValue();
    }
    return value;
  }

  public boolean isValid(int[] original, int[] hashed) {
    if (original.length != hashed.length) {
      return false;
    }
    BigInteger sum = BigInteger.valueOf(0);
    for (int i = 0; i < original.length; i++) {
      sum = sum.add(BigInteger.valueOf(original[i]));
    }

    BigInteger a = g.modPow(sum.mod(q), p);

    BigInteger product = BigInteger.ONE;
    for (int i = 0; i < hashed.length; i++) {
      product = product.multiply(BigInteger.valueOf(hashed[i]));
    }
    return a.equals(product.mod(p));
  }
}

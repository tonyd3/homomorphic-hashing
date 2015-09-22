package com.td;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class HomomorphicHasherTest {

  @Test
  public void testBasicHashing() {
    Hasher hasher = new HomomorphicHasher(47, 1543, 257);

    int[] result = hasher.hashValue(new int[]{72, 101, 108, 108, 111});
    assertArrayEquals(new int[]{883, 958, 81, 81, 313}, result);
  }

  @Test
  public void testBasicValidation() {
    Validator validator = new HomomorphicHasher(47, 1543, 257);

    boolean result = validator.isValid(
        new int[]{72, 101, 108, 108, 111}, new int[]{883, 958, 81, 81, 313});

    assertTrue(result);
  }

}

package de.moyapro.netfrag.api;

import java.util.function.UnaryOperator;

/**
 * Some functions to be reused
 */
public class PredefinedFunction {

  public static final UnaryOperator<Integer> increment = i -> ++i;
}

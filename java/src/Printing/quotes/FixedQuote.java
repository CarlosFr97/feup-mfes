package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class FixedQuote {
  private static int hc = 0;
  private static FixedQuote instance = null;

  public FixedQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static FixedQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new FixedQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof FixedQuote;
  }

  public String toString() {

    return "<Fixed>";
  }
}

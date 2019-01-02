package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ColorsQuote {
  private static int hc = 0;
  private static ColorsQuote instance = null;

  public ColorsQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ColorsQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ColorsQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ColorsQuote;
  }

  public String toString() {

    return "<Colors>";
  }
}

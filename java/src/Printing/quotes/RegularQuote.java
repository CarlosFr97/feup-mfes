package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class RegularQuote {
  private static int hc = 0;
  private static RegularQuote instance = null;

  public RegularQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static RegularQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new RegularQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof RegularQuote;
  }

  public String toString() {

    return "<Regular>";
  }
}

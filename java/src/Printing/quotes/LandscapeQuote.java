package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class LandscapeQuote {
  private static int hc = 0;
  private static LandscapeQuote instance = null;

  public LandscapeQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static LandscapeQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new LandscapeQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof LandscapeQuote;
  }

  public String toString() {

    return "<Landscape>";
  }
}

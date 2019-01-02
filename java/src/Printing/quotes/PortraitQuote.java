package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PortraitQuote {
  private static int hc = 0;
  private static PortraitQuote instance = null;

  public PortraitQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PortraitQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PortraitQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PortraitQuote;
  }

  public String toString() {

    return "<Portrait>";
  }
}

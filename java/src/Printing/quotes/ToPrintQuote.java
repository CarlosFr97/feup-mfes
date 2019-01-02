package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ToPrintQuote {
  private static int hc = 0;
  private static ToPrintQuote instance = null;

  public ToPrintQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static ToPrintQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new ToPrintQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof ToPrintQuote;
  }

  public String toString() {

    return "<ToPrint>";
  }
}

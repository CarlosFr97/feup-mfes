package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PaperQuote {
  private static int hc = 0;
  private static PaperQuote instance = null;

  public PaperQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static PaperQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new PaperQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof PaperQuote;
  }

  public String toString() {

    return "<Paper>";
  }
}

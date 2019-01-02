package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class TonerQuote {
  private static int hc = 0;
  private static TonerQuote instance = null;

  public TonerQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static TonerQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new TonerQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof TonerQuote;
  }

  public String toString() {

    return "<Toner>";
  }
}

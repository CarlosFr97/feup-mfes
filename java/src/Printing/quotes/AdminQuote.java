package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AdminQuote {
  private static int hc = 0;
  private static AdminQuote instance = null;

  public AdminQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static AdminQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new AdminQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof AdminQuote;
  }

  public String toString() {

    return "<Admin>";
  }
}

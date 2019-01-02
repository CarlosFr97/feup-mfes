package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class OtherQuote {
  private static int hc = 0;
  private static OtherQuote instance = null;

  public OtherQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static OtherQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new OtherQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof OtherQuote;
  }

  public String toString() {

    return "<Other>";
  }
}

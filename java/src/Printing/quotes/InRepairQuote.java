package Printing.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class InRepairQuote {
  private static int hc = 0;
  private static InRepairQuote instance = null;

  public InRepairQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static InRepairQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new InRepairQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof InRepairQuote;
  }

  public String toString() {

    return "<InRepair>";
  }
}

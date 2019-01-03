package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Client extends User {
  private Number account = 0L;

  public void cg_init_Client_1(final String username, final String pass, final Number acc) {

    initUser(username, pass, Printing.quotes.ClientQuote.getInstance());
    account = acc;
    return;
  }

  public Client(final String username, final String pass, final Number acc) {

    cg_init_Client_1(username, pass, acc);
  }

  public void depositMoney(final Number value) {

    account = account.doubleValue() + value.doubleValue();
  }

  public void payDocument(final Document d) {

    account = account.doubleValue() - d.getPrice().doubleValue();
  }

  public Document addDocument(
      final Object size,
      final Object color,
      final Object type,
      final String name_1,
      final Document.Date date,
      final Number numPages) {

    Document newDoc =
        new Document(
            this,
            ((Object) size),
            ((Object) color),
            ((Object) type),
            name_1,
            Utils.copy(date),
            numPages);
    if (PrintManager.getInstance().addDocumentToQueue(newDoc)) {
      return newDoc;

    } else {
      return null;
    }
  }

  public Number getAccount() {

    return account;
  }

  public Client() {}

  public String toString() {

    return "Client{" + "account := " + Utils.toString(account) + "}";
  }
}

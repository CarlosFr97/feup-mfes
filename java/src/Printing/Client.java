package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Client extends User {
  private Number account = 0L;
  private VDMSet documents = SetUtil.set();

  public void cg_init_Client_1(final String username, final String pass, final Number acc) {

    initUser(username, pass);
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

    Document newDoc = null;
    for (Iterator iterator_8 = documents.iterator(); iterator_8.hasNext(); ) {
      Document doc = (Document) iterator_8.next();
      Boolean andResult_5 = false;

      if (Utils.equals(name_1, doc.getName())) {
        if (Utils.equals(date, doc.getDate())) {
          andResult_5 = true;
        }
      }

      if (andResult_5) {
        return null;
      }
    }
    newDoc =
        new Document(
            this,
            ((Object) size),
            ((Object) color),
            ((Object) type),
            name_1,
            Utils.copy(date),
            numPages);
    if (PrintManager.getInstance().addDocumentToQueue(newDoc)) {
      documents = SetUtil.union(Utils.copy(documents), SetUtil.set(newDoc));
      return newDoc;

    } else {
      return null;
    }
  }

  public Number getAccount() {

    return account;
  }

  public VDMSet getDocs() {

    return Utils.copy(documents);
  }

  public void deleteDoc(final Document doc) {

    documents = SetUtil.diff(Utils.copy(documents), SetUtil.set(doc));
    doc.delete();
  }

  public Client() {}

  public String toString() {

    return "Client{"
        + "account := "
        + Utils.toString(account)
        + ", documents := "
        + Utils.toString(documents)
        + "}";
  }
}

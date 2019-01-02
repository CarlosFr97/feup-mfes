package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Printer {
  private Number id = 0L;
  private VDMSet queues = SetUtil.set();
  private String location = SeqUtil.toStr(SeqUtil.seq());

  public void cg_init_Printer_1(final Number identifier, final String local, final VDMSet lists) {

    queues = Utils.copy(lists);
    id = identifier;
    location = local;
    return;
  }

  public Printer(final Number identifier, final String local, final VDMSet lists) {

    cg_init_Printer_1(identifier, local, Utils.copy(lists));
  }

  public Number getId() {

    return id;
  }

  public String getLocation() {

    return location;
  }

  public VDMSet queryPrintAsClient() {

    VDMSet docs = SetUtil.set();
    for (Iterator iterator_5 = queues.iterator(); iterator_5.hasNext(); ) {
      Queue queue = (Queue) iterator_5.next();
      docs = SetUtil.union(Utils.copy(docs), queue.getClientDocs());
    }
    return Utils.copy(docs);
  }

  public VDMSet queryPrint() {

    VDMSet docs = SetUtil.set();
    for (Iterator iterator_6 = queues.iterator(); iterator_6.hasNext(); ) {
      Queue queue = (Queue) iterator_6.next();
      docs = SetUtil.union(Utils.copy(docs), queue.getDocs());
    }
    return Utils.copy(docs);
  }

  public void print(final Document doc) {

    Client client = doc.getClient();
    client.payDocument(doc);
    doc.removeFromQueue();
  }

  public Printer() {}

  public String toString() {

    return "Printer{"
        + "id := "
        + Utils.toString(id)
        + ", queues := "
        + Utils.toString(queues)
        + ", location := "
        + Utils.toString(location)
        + "}";
  }
}

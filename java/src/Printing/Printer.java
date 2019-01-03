package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Printer {
  private Number id = 0L;
  private VDMMap queues = MapUtil.map();
  private String location = SeqUtil.toStr(SeqUtil.seq());

  public void cg_init_Printer_1(final Number identifier, final String local, final VDMSet lists) {

    for (Iterator iterator_16 = lists.iterator(); iterator_16.hasNext(); ) {
      Queue queue = (Queue) iterator_16.next();
      queues = MapUtil.munion(Utils.copy(queues), MapUtil.map(new Maplet(queue, 0L)));
    }
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
    for (Iterator iterator_17 = MapUtil.dom(Utils.copy(queues)).iterator();
        iterator_17.hasNext();
        ) {
      Queue queue = (Queue) iterator_17.next();
      docs = SetUtil.union(Utils.copy(docs), queue.getClientDocs());
    }
    return Utils.copy(docs);
  }

  public VDMSet queryPrint() {

    VDMSet docs = SetUtil.set();
    for (Iterator iterator_18 = MapUtil.dom(Utils.copy(queues)).iterator();
        iterator_18.hasNext();
        ) {
      Queue queue = (Queue) iterator_18.next();
      docs = SetUtil.union(Utils.copy(docs), queue.getDocs());
    }
    return Utils.copy(docs);
  }

  public void print(final Document doc) {

    Client client = doc.getClient();
    Queue newQueue = null;
    client.payDocument(doc);
    newQueue = doc.removeFromQueue();
    queues =
        MapUtil.override(
            Utils.copy(queues),
            MapUtil.map(
                new Maplet(newQueue, ((Number) Utils.get(queues, newQueue)).longValue() + 1L)));
  }

  public VDMMap getNumPrintedDocs() {

    return Utils.copy(queues);
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

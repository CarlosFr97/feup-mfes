package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Queue {
  private VDMSet documents = SetUtil.set();
  private Object size;
  private Object color;

  public void cg_init_Queue_1(final Object newSize, final Object newColor) {

    size = newSize;
    color = newColor;
    return;
  }

  public Queue(final Object newSize, final Object newColor) {

    cg_init_Queue_1(newSize, newColor);
  }

  public Object getSize() {

    return size;
  }

  public Object getColor() {

    return color;
  }

  public void addDocument(final Document d) {

    documents = SetUtil.union(Utils.copy(documents), SetUtil.set(d));
  }

  public void removeDocument(final Document d) {

    documents = SetUtil.diff(Utils.copy(documents), SetUtil.set(d));
  }

  public VDMSet getClientDocs() {

    VDMSet allDocs = SetUtil.set();
    User client = PrintManager.getInstance().getCurrentUser();
    for (Iterator iterator_19 = documents.iterator(); iterator_19.hasNext(); ) {
      Document document = (Document) iterator_19.next();
      if (Utils.equals(document.getClient(), client)) {
        allDocs = SetUtil.union(Utils.copy(allDocs), SetUtil.set(document));
      }
    }
    return Utils.copy(allDocs);
  }

  public VDMSet getDocs() {

    return Utils.copy(documents);
  }

  public Queue() {}

  public String toString() {

    return "Queue{"
        + "documents := "
        + Utils.toString(documents)
        + ", size := "
        + Utils.toString(size)
        + ", color := "
        + Utils.toString(color)
        + "}";
  }
}

package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Document {
  private Client client;
  private Object size = Printing.quotes.A4Quote.getInstance();
  private Object color = Printing.quotes.BlackWhiteQuote.getInstance();
  private Object type = Printing.quotes.PortraitQuote.getInstance();
  private Number price = 0L;
  private Object state = Printing.quotes.ToPrintQuote.getInstance();
  private String name;
  private Date date;
  private Queue queue = null;

  public void cg_init_Document_1(
      final Client cli,
      final Object siz,
      final Object col,
      final Object ty,
      final String nam,
      final Date dat) {

    client = cli;
    size = siz;
    color = col;
    type = ty;
    name = nam;
    date = Utils.copy(dat);
    calculatePrice();
    return;
  }

  public Document(
      final Client cli,
      final Object siz,
      final Object col,
      final Object ty,
      final String nam,
      final Date dat) {

    cg_init_Document_1(cli, siz, col, ty, nam, Utils.copy(dat));
  }

  public void setQueue(final Queue q) {

    queue = q;
  }

  public void removeFromQueue() {

    queue.removeDocument(this);
  }

  public Number getPrice() {

    return price;
  }

  public Client getClient() {

    return client;
  }

  public Object getColor() {

    return color;
  }

  public Object getSize() {

    return size;
  }

  public Queue getQueue() {

    return queue;
  }

  private void calculatePrice() {

    calculateSize();
    calculateColor();
  }

  private void calculateSize() {

    Object quotePattern_1 = size;
    Boolean success_1 = Utils.equals(quotePattern_1, Printing.quotes.A3Quote.getInstance());

    if (!(success_1)) {
      Object quotePattern_2 = size;
      success_1 = Utils.equals(quotePattern_2, Printing.quotes.A4Quote.getInstance());

      if (success_1) {
        price = price.doubleValue() + 0.1;
      }

    } else {
      price = price.doubleValue() + 0.2;
    }
  }

  private void calculateColor() {

    Object quotePattern_3 = color;
    Boolean success_2 = Utils.equals(quotePattern_3, Printing.quotes.BlackWhiteQuote.getInstance());

    if (!(success_2)) {
      Object quotePattern_4 = color;
      success_2 = Utils.equals(quotePattern_4, Printing.quotes.ColorsQuote.getInstance());

      if (success_2) {
        price = price.doubleValue() + 0.25;
      }

    } else {
      price = price.doubleValue() + 0.1;
    }
  }

  public Document() {}

  public String toString() {

    return "Document{"
        + "client := "
        + Utils.toString(client)
        + ", size := "
        + Utils.toString(size)
        + ", color := "
        + Utils.toString(color)
        + ", type := "
        + Utils.toString(type)
        + ", price := "
        + Utils.toString(price)
        + ", state := "
        + Utils.toString(state)
        + ", name := "
        + Utils.toString(name)
        + ", date := "
        + Utils.toString(date)
        + ", queue := "
        + Utils.toString(queue)
        + "}";
  }

  public static class Date implements Record {
    public Number day;
    public Number month;
    public Number year;

    public Date(final Number _day, final Number _month, final Number _year) {

      day = _day;
      month = _month;
      year = _year;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(day, other.day))
          && (Utils.equals(month, other.month))
          && (Utils.equals(year, other.year));
    }

    public int hashCode() {

      return Utils.hashCode(day, month, year);
    }

    public Date copy() {

      return new Date(day, month, year);
    }

    public String toString() {

      return "mk_Document`Date" + Utils.formatFields(day, month, year);
    }
  }

  public static Boolean inv_Date(final Date date) {

    Boolean orResult_2 = false;

    if (Utils.equals(Utils.mod(date.year.longValue(), 400L), 0L)) {
      orResult_2 = true;
    } else {
      Boolean andResult_1 = false;

      if (!(Utils.equals(Utils.mod(date.year.longValue(), 100L), 0L))) {
        if (!(Utils.equals(Utils.mod(date.year.longValue(), 4L), 0L))) {
          andResult_1 = true;
        }
      }

      orResult_2 = andResult_1;
    }

    if (orResult_2) {
      Boolean andResult_2 = false;

      if (date.month.longValue() <= 12L) {
        if (date.day.longValue()
            <= ((Number)
                    Utils.get(
                        SeqUtil.seq(31L, 29L, 31L, 30L, 31L, 30L, 31L, 31L, 30L, 31L, 30L, 31L),
                        date.month))
                .longValue()) {
          andResult_2 = true;
        }
      }

      return andResult_2;

    } else {
      Boolean andResult_3 = false;

      if (date.month.longValue() <= 12L) {
        if (date.day.longValue()
            <= ((Number)
                    Utils.get(
                        SeqUtil.seq(31L, 28L, 31L, 30L, 31L, 30L, 31L, 31L, 30L, 31L, 30L, 31L),
                        date.month))
                .longValue()) {
          andResult_3 = true;
        }
      }

      return andResult_3;
    }
  }
}

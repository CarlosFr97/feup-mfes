package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Malfunction {
  private User reportedBy;
  private Employee assignedTo = null;
  private Employee assignedBy = null;
  private Printer printer;
  private Object state = Printing.quotes.WaitingQuote.getInstance();
  private String description = SeqUtil.toStr(SeqUtil.seq());
  private Object problem;

  public void cg_init_Malfunction_1(
      final User user, final Printer print, final Object type, final String desc) {

    reportedBy = user;
    printer = print;
    description = desc;
    problem = type;
    return;
  }

  public Malfunction(final User user, final Printer print, final Object type, final String desc) {

    cg_init_Malfunction_1(user, print, type, desc);
  }

  public Object getState() {

    return state;
  }

  public User getReportedBy() {

    return reportedBy;
  }

  public Object getProblem() {

    return problem;
  }

  public String getDescription() {

    return description;
  }

  public Employee getAssignedBy() {

    return assignedBy;
  }

  public Employee getAssignedTo() {

    return assignedTo;
  }

  public Printer getPrinter() {

    return printer;
  }

  public void assignEmployee(final Employee emp) {

    Employee atomicTmp_1 = emp;
    Employee atomicTmp_2 = ((Employee) PrintManager.getInstance().getCurrentUser());
    Object atomicTmp_3 = Printing.quotes.InRepairQuote.getInstance();
    {
        /* Start of atomic statement */
      assignedTo = atomicTmp_1;
      assignedBy = atomicTmp_2;
      state = atomicTmp_3;
    } /* End of atomic statement */
  }

  public void changeState(final Object newState) {

    state = newState;
  }

  public Malfunction() {}

  public String toString() {

    return "Malfunction{"
        + "reportedBy := "
        + Utils.toString(reportedBy)
        + ", assignedTo := "
        + Utils.toString(assignedTo)
        + ", assignedBy := "
        + Utils.toString(assignedBy)
        + ", printer := "
        + Utils.toString(printer)
        + ", state := "
        + Utils.toString(state)
        + ", description := "
        + Utils.toString(description)
        + ", problem := "
        + Utils.toString(problem)
        + "}";
  }
}

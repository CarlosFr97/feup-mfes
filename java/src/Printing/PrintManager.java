package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PrintManager {
  private VDMSet clients = SetUtil.set();
  private VDMSet employees = SetUtil.set();
  private VDMSet printers = SetUtil.set();
  private VDMSet queues = SetUtil.set();
  private VDMSet malfunctions = SetUtil.set();
  private User currentUser = null;
  private static PrintManager printManager = new PrintManager();

  private Boolean clientExists(final String name) {

    for (Iterator iterator_7 = clients.iterator(); iterator_7.hasNext(); ) {
      Client client = (Client) iterator_7.next();
      if (Utils.equals(client.getName(), name)) {
        return true;
      }
    }
    return false;
  }

  private Boolean employeeExists(final String name) {

    for (Iterator iterator_8 = employees.iterator(); iterator_8.hasNext(); ) {
      Employee employee = (Employee) iterator_8.next();
      if (Utils.equals(employee.getName(), name)) {
        return true;
      }
    }
    return false;
  }

  public Client addClient(final String username, final String password, final Number account) {

    Client cli = new Client(username, password, account);
    clients = SetUtil.union(Utils.copy(clients), SetUtil.set(cli));
    return login(username, password);
  }

  public Employee addEmployee(final String username, final String password, final Object role) {

    Employee emp = new Employee(username, password, ((Object) role));
    employees = SetUtil.union(Utils.copy(employees), SetUtil.set(emp));
    return login(username, password);
  }

  public User login(final String username, final String password) {

    VDMSet users = SetUtil.union(Utils.copy(clients), Utils.copy(employees));
    for (Iterator iterator_9 = users.iterator(); iterator_9.hasNext(); ) {
      User user = (User) iterator_9.next();
      if (user.isLoginCorrected(username, password)) {
        currentUser = user;
        return currentUser;
      }
    }
    return currentUser;
  }

  public void logout() {

    currentUser = null;
  }

  public Queue addQueue(final Object size, final Object color) {

    Queue queue = new Queue(((Object) size), ((Object) color));
    queues = SetUtil.union(Utils.copy(queues), SetUtil.set(queue));
    return queue;
  }

  public Printer addPrinter(final Number id, final String local, final VDMSet listofQueues) {

    Printer aux = new Printer(id, local, Utils.copy(listofQueues));
    printers = SetUtil.union(Utils.copy(printers), SetUtil.set(aux));
    return aux;
  }

  public Boolean addDocumentToQueue(final Document doc) {

    for (Iterator iterator_10 = queues.iterator(); iterator_10.hasNext(); ) {
      Queue queue = (Queue) iterator_10.next();
      Boolean andResult_37 = false;

      if (Utils.equals(queue.getColor(), doc.getColor())) {
        if (Utils.equals(queue.getSize(), doc.getSize())) {
          andResult_37 = true;
        }
      }

      if (andResult_37) {
        queue.addDocument(doc);
        doc.setQueue(queue);
        return true;
      }
    }
    return false;
  }

  public Object reportPrinters() {

    throw new UnsupportedOperationException();
  }

  public Object reportClients() {

    throw new UnsupportedOperationException();
  }

  public Object reportEmployees(final String name) {

    VDMMap allMalfs =
        MapUtil.map(
            new Maplet(Printing.quotes.InRepairQuote.getInstance(), 0L),
            new Maplet(Printing.quotes.FixedQuote.getInstance(), 0L));
    Employee employee = getEmployee(name);
    for (Iterator iterator_11 = employee.getMalfunctions().iterator(); iterator_11.hasNext(); ) {
      Malfunction malfunction = (Malfunction) iterator_11.next();
      VDMMap pair = MapUtil.domResTo(SetUtil.set(malfunction.getState()), Utils.copy(allMalfs));
      Object state = malfunction.getState();
      VDMMap newPair =
          MapUtil.map(new Maplet(state, ((Number) Utils.get(pair, state)).longValue() + 1L));
      allMalfs = MapUtil.override(Utils.copy(allMalfs), Utils.copy(newPair));
    }
    return Utils.copy(allMalfs);
  }

  public static PrintManager getInstance() {

    return PrintManager.printManager;
  }

  public static void clearInstance() {

    printManager = new PrintManager();
  }

  public User getCurrentUser() {

    return currentUser;
  }

  public VDMSet getPrinters() {

    return Utils.copy(printers);
  }

  public VDMSet getQueues() {

    return Utils.copy(queues);
  }

  public VDMSet getMalfunctions() {

    return Utils.copy(malfunctions);
  }

  public void addMalfunction(final Malfunction malf) {

    malfunctions = SetUtil.union(Utils.copy(malfunctions), SetUtil.set(malf));
  }

  public Employee getEmployee(final String name) {

    for (Iterator iterator_12 = employees.iterator(); iterator_12.hasNext(); ) {
      Employee employee = (Employee) iterator_12.next();
      Boolean andResult_46 = false;

      if (Utils.equals(employee.getName(), name)) {
        if (!(Utils.equals(employee.getRole(), Printing.quotes.AdminQuote.getInstance()))) {
          andResult_46 = true;
        }
      }

      if (andResult_46) {
        return employee;
      }
    }
    return null;
  }

  public PrintManager() {}

  public String toString() {

    return "PrintManager{"
        + "clients := "
        + Utils.toString(clients)
        + ", employees := "
        + Utils.toString(employees)
        + ", printers := "
        + Utils.toString(printers)
        + ", queues := "
        + Utils.toString(queues)
        + ", malfunctions := "
        + Utils.toString(malfunctions)
        + ", currentUser := "
        + Utils.toString(currentUser)
        + ", printManager := "
        + Utils.toString(printManager)
        + "}";
  }
}

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

  public void cg_init_PrintManager_1() {

    Employee emp = new Employee("admin", "admin", Printing.quotes.AdminQuote.getInstance());
    employees = SetUtil.union(Utils.copy(employees), SetUtil.set(emp));
    return;
  }

  public PrintManager() {

    cg_init_PrintManager_1();
  }

  private Boolean clientExists(final String name) {

    for (Iterator iterator_5 = clients.iterator(); iterator_5.hasNext(); ) {
      Client client = (Client) iterator_5.next();
      if (Utils.equals(client.getName(), name)) {
        return true;
      }
    }
    return false;
  }

  private Boolean employeeExists(final String name) {

    for (Iterator iterator_6 = employees.iterator(); iterator_6.hasNext(); ) {
      Employee employee = (Employee) iterator_6.next();
      if (Utils.equals(employee.getName(), name)) {
        return true;
      }
    }
    return false;
  }

  public Client addClient(final String username, final String password, final Number account) {

    if (!(clientExists(username))) {
      Client cli = new Client(username, password, account);
      clients = SetUtil.union(Utils.copy(clients), SetUtil.set(cli));
      return ((Client) login(username, password));
    }

    return null;
  }

  public Boolean addEmployee(final String username, final String password, final Object role) {

    if (!(employeeExists(username))) {
      Employee emp = new Employee(username, password, ((Object) role));
      employees = SetUtil.union(Utils.copy(employees), SetUtil.set(emp));
      return true;
    }

    return false;
  }

  public User login(final String username, final String password) {

    VDMSet users = SetUtil.union(Utils.copy(clients), Utils.copy(employees));
    for (Iterator iterator_7 = users.iterator(); iterator_7.hasNext(); ) {
      User user = (User) iterator_7.next();
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

    for (Iterator iterator_8 = queues.iterator(); iterator_8.hasNext(); ) {
      Queue queue = (Queue) iterator_8.next();
      Boolean andResult_48 = false;

      if (Utils.equals(queue.getColor(), doc.getColor())) {
        if (Utils.equals(queue.getSize(), doc.getSize())) {
          andResult_48 = true;
        }
      }

      if (andResult_48) {
        queue.addDocument(doc);
        doc.setQueue(queue);
        return true;
      }
    }
    return false;
  }

  public VDMMap reportPrintedDocs() {

    VDMMap statistics = MapUtil.map();
    VDMMap temp = null;
    for (Iterator iterator_9 = queues.iterator(); iterator_9.hasNext(); ) {
      Queue queue = (Queue) iterator_9.next();
      statistics = MapUtil.override(Utils.copy(statistics), MapUtil.map(new Maplet(queue, 0L)));
    }
    for (Iterator iterator_10 = printers.iterator(); iterator_10.hasNext(); ) {
      Printer printer = (Printer) iterator_10.next();
      temp = printer.getNumPrintedDocs();
      for (Iterator iterator_11 = MapUtil.dom(Utils.copy(temp)).iterator();
          iterator_11.hasNext();
          ) {
        Queue queue = (Queue) iterator_11.next();
        statistics =
            MapUtil.override(
                Utils.copy(statistics),
                MapUtil.map(
                    new Maplet(
                        queue,
                        ((Number) Utils.get(temp, queue)).longValue()
                            + ((Number) Utils.get(statistics, queue)).longValue())));
      }
    }
    return Utils.copy(statistics);
  }

  public VDMMap reportClients() {

    VDMMap allClients = MapUtil.map();
    for (Iterator iterator_12 = clients.iterator(); iterator_12.hasNext(); ) {
      Client client = (Client) iterator_12.next();
      allClients =
          MapUtil.override(
              Utils.copy(allClients), MapUtil.map(new Maplet(client.getName(), client.getDocs())));
    }
    return Utils.copy(allClients);
  }

  public VDMMap reportEmployee(final Employee employee) {

    VDMMap allMalfs =
        MapUtil.map(
            new Maplet(Printing.quotes.InRepairQuote.getInstance(), 0L),
            new Maplet(Printing.quotes.FixedQuote.getInstance(), 0L));
    for (Iterator iterator_13 = employee.getMalfunctions().iterator(); iterator_13.hasNext(); ) {
      Malfunction malfunction = (Malfunction) iterator_13.next();
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

  public VDMSet getRegularEmployees() {

    VDMSet emp = SetUtil.set();
    for (Iterator iterator_14 = employees.iterator(); iterator_14.hasNext(); ) {
      Employee employee = (Employee) iterator_14.next();
      if (Utils.equals(employee.getRole(), Printing.quotes.RegularQuote.getInstance())) {
        emp = SetUtil.union(Utils.copy(emp), SetUtil.set(employee));
      }
    }
    return Utils.copy(emp);
  }

  public Employee getEmployee(final String name) {

    for (Iterator iterator_15 = employees.iterator(); iterator_15.hasNext(); ) {
      Employee employee = (Employee) iterator_15.next();
      Boolean andResult_63 = false;

      if (Utils.equals(employee.getName(), name)) {
        if (!(Utils.equals(employee.getRole(), Printing.quotes.AdminQuote.getInstance()))) {
          andResult_63 = true;
        }
      }

      if (andResult_63) {
        return employee;
      }
    }
    return null;
  }

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

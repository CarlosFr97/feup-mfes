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

    for (Iterator iterator_9 = clients.iterator(); iterator_9.hasNext(); ) {
      Client client = (Client) iterator_9.next();
      if (Utils.equals(client.getName(), name)) {
        return true;
      }
    }
    return false;
  }

  private Boolean employeeExists(final String name) {

    for (Iterator iterator_10 = employees.iterator(); iterator_10.hasNext(); ) {
      Employee employee = (Employee) iterator_10.next();
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
    for (Iterator iterator_11 = users.iterator(); iterator_11.hasNext(); ) {
      User user = (User) iterator_11.next();
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

    for (Iterator iterator_12 = queues.iterator(); iterator_12.hasNext(); ) {
      Queue queue = (Queue) iterator_12.next();
      Boolean andResult_50 = false;

      if (Utils.equals(queue.getColor(), doc.getColor())) {
        if (Utils.equals(queue.getSize(), doc.getSize())) {
          andResult_50 = true;
        }
      }

      if (andResult_50) {
        queue.addDocument(doc);
        doc.setQueue(queue);
        return true;
      }
    }
    return false;
  }

  public VDMMap reportPrintedDocs() {

    VDMMap statistics = MapUtil.map();
    VDMMap docs = null;
    VDMMap mapCompResult_1 = MapUtil.map();
    VDMSet set_4 = Utils.copy(queues);
    for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext(); ) {
      Queue queue = ((Queue) iterator_4.next());
      MapUtil.mapAdd(mapCompResult_1, new Maplet(queue, 0L));
    }
    statistics = MapUtil.override(Utils.copy(statistics), Utils.copy(mapCompResult_1));

    for (Iterator iterator_13 = printers.iterator(); iterator_13.hasNext(); ) {
      Printer printer = (Printer) iterator_13.next();
      docs = printer.getNumPrintedDocs();
      VDMMap mapCompResult_2 = MapUtil.map();
      VDMSet set_5 = MapUtil.dom(Utils.copy(docs));
      for (Iterator iterator_5 = set_5.iterator(); iterator_5.hasNext(); ) {
        Queue queue = ((Queue) iterator_5.next());
        if (SetUtil.inSet(queue, queues)) {
          MapUtil.mapAdd(
              mapCompResult_2,
              new Maplet(
                  queue,
                  ((Number) Utils.get(docs, queue)).longValue()
                      + ((Number) Utils.get(statistics, queue)).longValue()));
        }
      }
      statistics = MapUtil.override(Utils.copy(statistics), Utils.copy(mapCompResult_2));
    }
    return Utils.copy(statistics);
  }

  public VDMMap reportClients() {

    VDMMap allClients = MapUtil.map();
    VDMMap mapCompResult_3 = MapUtil.map();
    VDMSet set_6 = Utils.copy(clients);
    for (Iterator iterator_6 = set_6.iterator(); iterator_6.hasNext(); ) {
      Client client = ((Client) iterator_6.next());
      MapUtil.mapAdd(mapCompResult_3, new Maplet(client.getName(), client.getDocs()));
    }
    allClients = MapUtil.override(Utils.copy(allClients), Utils.copy(mapCompResult_3));

    return Utils.copy(allClients);
  }

  public VDMMap reportEmployee(final Employee employee) {

    VDMMap allMalfs =
        MapUtil.map(
            new Maplet(Printing.quotes.InRepairQuote.getInstance(), 0L),
            new Maplet(Printing.quotes.FixedQuote.getInstance(), 0L));
    for (Iterator iterator_14 = employee.getMalfunctions().iterator(); iterator_14.hasNext(); ) {
      Malfunction malfunction = (Malfunction) iterator_14.next();
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
    for (Iterator iterator_15 = employees.iterator(); iterator_15.hasNext(); ) {
      Employee employee = (Employee) iterator_15.next();
      if (Utils.equals(employee.getRole(), Printing.quotes.RegularQuote.getInstance())) {
        emp = SetUtil.union(Utils.copy(emp), SetUtil.set(employee));
      }
    }
    return Utils.copy(emp);
  }

  public Employee getEmployee(final String name) {

    for (Iterator iterator_16 = employees.iterator(); iterator_16.hasNext(); ) {
      Employee employee = (Employee) iterator_16.next();
      Boolean andResult_65 = false;

      if (Utils.equals(employee.getName(), name)) {
        if (!(Utils.equals(employee.getRole(), Printing.quotes.AdminQuote.getInstance()))) {
          andResult_65 = true;
        }
      }

      if (andResult_65) {
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

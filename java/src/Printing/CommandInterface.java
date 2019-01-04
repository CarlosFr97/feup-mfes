package Printing;
import java.time.LocalDateTime;
import Printing.Document.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

import Printing.PrintManager;
import Printing.quotes.ClientQuote;
import Printing.quotes.ColorsQuote;
import Printing.quotes.FixedQuote;
import Printing.quotes.InRepairQuote;
import Printing.quotes.LandscapeQuote;
import Printing.quotes.OtherQuote;
import Printing.quotes.PaperQuote;
import Printing.quotes.PortraitQuote;
import Printing.quotes.RegularQuote;
import Printing.quotes.TonerQuote;
import Printing.quotes.WaitingQuote;
import Printing.quotes.A3Quote;
import Printing.quotes.A4Quote;
import Printing.quotes.AdminQuote;
import Printing.quotes.BlackWhiteQuote;

import org.overture.codegen.runtime.*;


public class CommandInterface {

	
	
    private PrintManager manager = PrintManager.getInstance();
    
  
    public static void main(String[] args){
        CommandInterface gui = new CommandInterface();
        gui.mainMenu();
    }


    public void mainMenu(){
        int option;
        boolean running = true;

        while(running){
            
            MyUtils.clearScreen();
        
            // Display menu graphics
            System.out.println("============================");
            System.out.println("|     PRINTING SYSTEM      |");
            System.out.println("============================");
            System.out.println("| Main Menu:               |");
            System.out.println("|        1. Log In         |");
            System.out.println("|        2. Register       |");
            System.out.println("|        3. Exit           |");
            System.out.println("============================");
            option = MyUtils.inInt("Select Option: ");

            //Switch construct
            switch (option) {
                case 1:
                    System.out.println("Log in selected");
                    loginMenu();
                    break;
                case 2:
                    System.out.println("Register selected");
                    registerMenu();
                    break;
                case 3:
                    System.out.println("Thanks for using our printing System");
                    System.exit(0);
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }


    }

    public void loginMenu(){

        MyUtils.clearScreen();
        System.out.println("============================");
        System.out.println("|       LOGIN USER         |");
        System.out.println("============================");
        String username = MyUtils.inString("Username: ");
        String password = MyUtils.inPassword("Password: ");
        
        
        User log = manager.login(username,password);
        if(log == null){
            MyUtils.inChar("Invalid credentials");
            
        }else{
            if(log instanceof Client){
                clientMenu();
            }else if(((Employee)log).getRole().equals(AdminQuote.getInstance())){
                adminMenu();
            }else{
                employeeMenu();
            }
        }
       

    }

    public void registerMenu(){
        MyUtils.clearScreen();
        System.out.println("============================");
        System.out.println("|      REGISTER USER       |");
        System.out.println("============================");
        String username = MyUtils.inString("Username: ");
        String password = MyUtils.inPassword("Password: ");
        double account = MyUtils.inDouble("Starting money: ");

        if(manager.addClient(username,password,account) != null);
            clientMenu();

    }



    public void clientMenu() {
        int option;
        boolean running = true;

        while(running){

            MyUtils.clearScreen();
            
            // Display menu graphics
            System.out.println("============================");
            System.out.println("|     PRINTING SYSTEM      |");
            System.out.println("============================");
            System.out.println("| Money: " + ((Client) manager.getCurrentUser()).getAccount());
            System.out.println("| Client Menu:             |");
            System.out.println("|        1. Deposit Money  |");
            System.out.println("|        2. New Document   |");
            System.out.println("|        3. Print          |");
            System.out.println("|        4. New Malfunction|");
            System.out.println("|        5. Log Out        |");
            System.out.println("============================");
            option = MyUtils.inInt("Select Option: ");

            //Switch construct
            switch (option) {
                case 1:
                    System.out.println("Deposit Money");
                    depositMenu();
                    break;
                case 2:
                    System.out.println("Create New Document");
                    newDocMenu();
                    break;
                case 3:
                    System.out.println("Print Documents");
                    printDocumentsMenu();
                    break;
                case 4:
                    System.out.println("Report a Malfunction");
                    reportMalfunctionMenu();
                    break;
                case 5:
                    System.out.println("Log Out");
                    manager.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }

    public void depositMenu(){
        MyUtils.clearScreen();
        System.out.println("============================");
        System.out.println("|      DEPOSIT MONEY       |");
        System.out.println("============================");
        double money = MyUtils.inDouble("Money quantity: ");
        if(money <= 0){
            MyUtils.inChar("The funds to be add should be positive");
        }
        char sure = Character.toUpperCase(MyUtils.inChar("Are you sure you want to add " + money + " to your account? y/n"));

        if(sure == 'Y') {
             Client user = (Client) manager.getCurrentUser();
             user.depositMoney(money);
             MyUtils.inChar("Your money will be added to the account");
        }else {
            return;
        }  
    }

    public void newDocMenu(){
        int option;
        Object size;
        Object type;
        Object color;
        int pages;
        LocalDateTime currDate = LocalDateTime.now();
        Date date = new Date(currDate.getDayOfMonth(),currDate.getMonth().getValue(),currDate.getYear());
        System.out.println("============================");
        System.out.println("|       NEW DOCUMENT       |");
        System.out.println("============================");
     
        
        //Choose size
        System.out.println("    1 - A4        2 - A3    ");
        option = MyUtils.inInt("Choose size option: ");
        size = (option == 1) ? new A4Quote() : new A3Quote();

        System.out.println("\nSize: " + size.toString());
        System.out.println();
        System.out.println(" 1 - Portrait 2 - Landscape ");
        option = MyUtils.inInt("Choose type option: ");
        type = (option == 1) ? new PortraitQuote() : new LandscapeQuote();

        System.out.println("\nType: " + type.toString());
        System.out.println();
        System.out.println(" 1 - Black&White 2 - Colors ");
        option = MyUtils.inInt("Choose print color option: ");
        color = (option == 1) ? new BlackWhiteQuote() : new ColorsQuote();

        System.out.println("\nColor: " + color.toString());
        System.out.println();
        String name = MyUtils.inString("What is the name of the Document? ");
        pages = MyUtils.inInt("Number of pages of the document");
        char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to save " + name + " as a Document of size " + size + " displayed in " + type + " printed in " + color + "? y/n"));
        if(confirm == 'Y'){
            Client user = (Client) manager.getCurrentUser();
            user.addDocument(size,color,type,name,date,pages);

        }

        

    }

    public void printDocumentsMenu(){
        MyUtils.clearScreen();
       
        ArrayList<Object> printers = new ArrayList<>(manager.getPrinters());
        
        if(printers.size() == 0){
            MyUtils.inChar("There are no printers available in the system");
            return;
        }
            
        int i = 0;

        System.out.println("============================");
        System.out.println("|          PRINT           |");
        System.out.println("| Printers:                |");
        for(Object printer:printers){
            System.out.println("       " + i + " - " + ((Printer)printer).getId() + ":" +  ((Printer)printer).getLocation() + "      ");
            i++;
        }
        System.out.println("============================");

        int choose = MyUtils.inInt("Please choose the printer option: ");
        Printer choosenPrinter = (Printer) printers.get(0);
        if(choose >= 0 && choose < printers.size()){
            choosenPrinter = (Printer) printers.get(choose);
        }
        
    
        ArrayList<Object> documents = new ArrayList<>(choosenPrinter.queryPrintAsClient());
        
        if(documents.isEmpty()){
            MyUtils.inChar("You have no documents to print in " + choosenPrinter.getLocation());
            return;
        }
        	
      

        System.out.println("============================");
        System.out.println("|          PRINT           |");
        System.out.println("| Printer:                 |");
        System.out.println("       "  + choosenPrinter.getId() +":" +choosenPrinter.getLocation() + "         ");
        
        System.out.println("| Documents:               |");
        i = 0;
        for(Object doc:documents){
            System.out.println("          " + i + " - " + ((Document)doc).getName() + ":" + ((Document)doc).getDate().toString() +":" + ((Document)doc).getPrice() + "€:" 
            + ((Document)doc).getSize()+"/" + ((Document)doc).getColor());
            i++;
        }	
        System.out.println("============================");
        ArrayList<Document> choosenDocs = new ArrayList<>();
        String option[] = MyUtils.inString("Please choose the documents you wish to print (you can enumerate more than one number at a time, separated with spaces): ").split(" ");
        for(int a = 0; a < option.length; a++){
            if(MyUtils.isNumeric(option[a])){
                Integer number = new Integer(option[a]);
                if(number >= 0 && number < documents.size()){
                    if(!choosenDocs.contains((Document)documents.get(number)))
                        choosenDocs.add((Document)documents.get(number));
                }
            }
        }

        if(choosenDocs.isEmpty()){
            MyUtils.inChar("We could not find any valid selection");
            return;
        }
            

        MyUtils.clearScreen();

        System.out.println("============================");
        System.out.println("|          PRINT           |");
        System.out.println("| Printer:                 |");
        System.out.println("       "  + choosenPrinter.getId() + ":" + choosenPrinter.getLocation() + "         ");
        System.out.println("| Choosen:                 |");
        double price = 0;
        for(int a = 0; i < choosenDocs.size(); a++){
            System.out.println("        " + i + " - " + choosenDocs.get(a).getName() + ":" + choosenDocs.get(a).getDate().toString() + ":" + choosenDocs.get(a).getPrice() 
            + "€:" + choosenDocs.get(a).getSize()+"/" + choosenDocs.get(a).getColor());
            price = price + (choosenDocs.get(a).getPrice()).doubleValue();
        }
        
        System.out.println("Total price: " + price);

        char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to print? y/n"));

        if(confirm == 'Y'){
            boolean notEnough = false;
            for(Document doc:choosenDocs) {
                Client cli = (Client) manager.getCurrentUser();
                
                if(cli.getAccount().doubleValue() >= doc.getPrice().doubleValue())
                    choosenPrinter.print(doc);
                else{
                    notEnough = true;
                }
            }

            if(notEnough){
                MyUtils.inChar("Be aware that all documents solicited were not printed because you did not had sufficient funds.Press Enter to go back");
            }
        }
        
    }

    public void reportMalfunctionMenu(){

        MyUtils.clearScreen();

    	ArrayList<Object> printers = new ArrayList<>(manager.getPrinters());
        
        if(printers.size() == 0){
            MyUtils.inChar("There are no printers in the system");
            return;
        }
            
        int index= 0;
        System.out.println("============================");
        System.out.println("|    REPORT MALFUNCTION    |");
        System.out.println("| Printers:                |");
        for(Object printer:printers){
            System.out.println("       " + index + " - " + ((Printer)printer).getId() + ":"+((Printer)printer).getLocation() + "      ");
            index++;
        }
        System.out.println("============================");
        int choose = MyUtils.inInt("Please choose the printer option: ");
        Printer choosenPrinter = (Printer) printers.get(0);
        if(choose >= 0 && choose < printers.size()){
            choosenPrinter = (Printer) printers.get(choose);
        }

        System.out.println("\nPrinter: " + choosenPrinter.getLocation());
        System.out.println();
        System.out.println("    1 - Toner  2 - Paper 3 - Other  ");
        choose = MyUtils.inInt("What is the problem: ");
        Object problemType = new OtherQuote();
        if(choose == 1)
            problemType = new TonerQuote();
        else if(choose == 2)
            problemType = new PaperQuote();
        
        System.out.println("\nProblem type: " + problemType);
        System.out.println();
        String description = MyUtils.inString("Please write a short description of the problem: ");
        
        char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to report? y/n"));

        if(confirm == 'Y'){
            User user = manager.getCurrentUser();
            Malfunction malf = user.reportMalfunction(choosenPrinter, problemType, description);
            
          
        }
        

    }



    public void employeeMenu() {
        int option;
        boolean running = true;

        while(running){

            MyUtils.clearScreen();
            
            // Display menu graphics
            System.out.println("============================");
            System.out.println("|     PRINTING SYSTEM      |");
            System.out.println("============================");
            System.out.println("| Employee Menu:           |");
            System.out.println("|        1. Add Printer    |");
            System.out.println("|        2. Add Queue      |");
            System.out.println("|        3. Malfunctions   |");
            System.out.println("|        4. New Malfunction|");
            System.out.println("|        5. Log Out        |");
            System.out.println("============================");
            option = MyUtils.inInt("Select Option: ");

            //Switch construct
            switch (option) {
                case 1:
                    System.out.println("Add Printer");
                    addPrinterMenu();
                    break;
                case 2:
                    System.out.println("Add Queue");
                    addQueueMenu();
                    break;
                case 3:
                    System.out.println("Handle Malfunctions");
                    solveMalfunction();
                    break;
                case 4:
                    System.out.println("New malfunction");
                    reportMalfunctionMenu();
                    break;
                case 5:
                    System.out.println("Log Out");
                    manager.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }

    public void adminMenu() {
        int option;
        boolean running = true;

        while(running){

            MyUtils.clearScreen();
            
            // Display menu graphics
            System.out.println("=============================");
            System.out.println("|     PRINTING SYSTEM       |");
            System.out.println("=============================");
            System.out.println("| Admin Menu:               |");
            System.out.println("|        1. Add Employee    |");
            System.out.println("|        2. Assign Employee |");
            System.out.println("|        3. Malfunctions    |");
            System.out.println("|        4. New Malfunction |");
            System.out.println("|        5. Printers Report |");
            System.out.println("|        6. Clients Report  |");
            System.out.println("|        7. Employees Report|");
            System.out.println("|        8. Log Out         |");
            System.out.println("=============================");
            option = MyUtils.inInt("Select Option: ");

            //Switch construct
            switch (option) {
                case 1:
                    System.out.println("Add Employee");
                    addEmployeeMenu();
                    break;
                case 2:
                    System.out.println("Assign Employee to malfunction");
                    assignMalfunctionMenu();
                    break;
                case 3:
                    System.out.println("Handle Malfunctions");
                    solveMalfunction();
                    break;
                case 4:
                	System.out.println("Create new Malfunction");
                	reportMalfunctionMenu();
                	break;
                case 5:
                    System.out.println("Printers Report");
                    printersReport();
                    break;
                case 6:
                    System.out.println("Client Report");
                    clientsReport();
                    break;
                case 7:
                    System.out.println("Employees Report");
                    employeesReport();
                    break;
                case 8:
                    System.out.println("Log Out");
                    manager.logout();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }

    public void addEmployeeMenu(){

        MyUtils.clearScreen();
        System.out.println("============================");
        System.out.println("|       NEW EMPLOYEE       |");
        System.out.println("============================");
        Object employeeType;
        System.out.println("    1 - Regular        2 - Admin    ");
        int option = MyUtils.inInt("Choose what type of employee: ");
        employeeType = (option == 1) ? new RegularQuote() : new AdminQuote();

        String employeeName = MyUtils.inString("Write the employee name: ");
        String password = MyUtils.inPassword("Write employee password: ");

        manager.addEmployee(employeeName, password, employeeType);

    }
    
    
    public void assignMalfunctionMenu() {
        
         MyUtils.clearScreen();
         ArrayList<Object> employees = new ArrayList<>(manager.getRegularEmployees());
         
         employees.add(manager.getCurrentUser());
         int index = 0;
    	 System.out.println("============================");
         System.out.println("|     ASSIGN MALFUNCTION   |");
         System.out.println("| Employees:               |");
         for(Object employee:employees){
             if(index == employees.size()-1){
                System.out.println("       " + index + " - " + ((Employee)employee).getName() + "(yourself)      ");
             }else
                System.out.println("       " + index + " - " + ((Employee)employee).getName() + "      ");
             index++;
         }
         System.out.println("============================");
         int choose = MyUtils.inInt("Please choose the employee: ");
         Employee choosenEmployee = (Employee) employees.get(0);
         if(choose >= 0 && choose < employees.size()){
             choosenEmployee = (Employee) employees.get(choose);
         }
         
         MyUtils.clearScreen();
         
         ArrayList<Object> malfunctions = new ArrayList<>(manager.getMalfunctions());

         if(malfunctions.isEmpty()){
             MyUtils.inChar("There are no malfunctions");
            return;
         }
            
         
         System.out.println("============================");
         System.out.println("|     ASSIGN MALFUNCTION   |");
         System.out.println("| Employee:                 |");
         System.out.println("|        "  + choosenEmployee.getName() + "         ");
         
         System.out.println("| Malfunctions:               |");
         index = 0;
         ArrayList<Malfunction> filteredMalfunctions = new ArrayList<>();
         for(Object malfunction:malfunctions){
        	 if(((Malfunction)malfunction).getAssignedTo() == null) {
        		filteredMalfunctions.add((Malfunction)malfunction);
        		System.out.println("       " + index + " - " + ((Malfunction)malfunction).getProblem().toString() + ":" + ((Malfunction)malfunction).getPrinter().getLocation() + ":"+ ((Malfunction)malfunction).getDescription());
              	index++;
        	 }
        		
         }

         if(filteredMalfunctions.isEmpty()){
             MyUtils.inChar("There are no unassigned malfunctions");
             return;
         }

         ArrayList<Malfunction> choosenMalfunctions = new ArrayList<>();
         String option[] = MyUtils.inString("Please choose the malfunctions you wish to assign to " +  choosenEmployee.getName() + " (you can enumerate more than one number at a time, separated with spaces): ").split(" ");
         for(int a = 0; a < option.length; a++){
             if(MyUtils.isNumeric(option[a])){
                 Integer number = new Integer(option[a]);
                 if(number >= 0 && number < filteredMalfunctions.size()){
                     if(!choosenMalfunctions.contains(filteredMalfunctions.get(number)))
                        choosenMalfunctions.add(filteredMalfunctions.get(number));
                 }
             }
         }

         if(choosenMalfunctions.isEmpty()){
             MyUtils.inChar("We could not find any valid selection");
            return;
         }
            
         
         
         MyUtils.clearScreen();
         
         System.out.println("============================");
         System.out.println("|     ASSIGN MALFUNCTION   |");
         System.out.println("| Employee:                 |");
         System.out.println("|        "  + choosenEmployee.getName() + "         ");
         
         System.out.println("| Malfunctions:               |");
         for(Malfunction malfunction:choosenMalfunctions){
        		System.out.println("       "+ (malfunction).getProblem() + ":" + malfunction.getPrinter().getLocation() + ":" + malfunction.getDescription());	
         }
         
         char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to assign? y/n"));

         if(confirm == 'Y'){
        	 for(Malfunction malfunction:choosenMalfunctions) {
                 Employee currUser = (Employee) manager.getCurrentUser();
        		 currUser.assignMalfunction(malfunction,choosenEmployee);
        	 }
         }
        
    }


    public void addPrinterMenu(){

        MyUtils.clearScreen();
        ArrayList<Object> printers = new ArrayList<>(manager.getPrinters());
        System.out.println("============================");
        System.out.println("|       NEW PRINTER        |");
        System.out.println("|Existing Printers:        |");
        if(printers.size() == 0)
            System.out.println("|  Currently there are no printers        ");
        else{
            for(Object printer:printers){
                System.out.println("  Id:" + ((Printer)printer).getId() + " Location: " + ((Printer)printer).getLocation());
            }
        }
        System.out.println("============================");
        ArrayList<Object> queues = new ArrayList<>(manager.getQueues());
        if(queues.size() == 0){
            MyUtils.inChar("There are no queues available,make sure you create queues first");
            return;
        }

        int new_id = printers.size() + 1;
        String location = MyUtils.inString("Where is the printer located: ");

        System.out.println("Availabe queues: ");
        int index = 0;
        for(Object queue: queues){
            System.out.println("     "+ index + " - Color:" + ((Queue)queue).getColor() + "   Size:" + ((Queue)queue).getSize());
            index++;
        }
        ArrayList<Object> choosenQueues = new ArrayList<>();
        String option[] = MyUtils.inString("Please choose the queues you wish to add to the printer (you can enumerate more than one number at a time, separated with spaces): ").split(" ");
        for(int a = 0; a < option.length; a++){
            if(MyUtils.isNumeric(option[a])){
                Integer number = new Integer(option[a]);
                if(number >= 0 && number < queues.size()){
                    if(!choosenQueues.contains(queues.get(number)))
                        choosenQueues.add(queues.get(number));
                }
            }
        }

        if(choosenQueues.isEmpty()){
            MyUtils.inChar("We could not find any valid selection");
            return;
        }


        char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to create the printer? y/n"));

         if(confirm == 'Y'){
             VDMSet setQueues = new VDMSet();
             setQueues.addAll(choosenQueues);
             manager.addPrinter(new_id, location,setQueues);
        	 
         }

        
        

    }

    public void addQueueMenu(){

        MyUtils.clearScreen();
        ArrayList<Object> queues = new ArrayList<>(manager.getQueues());
        System.out.println("============================");
        System.out.println("|         NEW QUEUE        |");
        System.out.println("|Existing Queues:          |");
        if(queues.size() == 0)
            System.out.println("|     There are no queues available  |");
        else{
            for(Object queue: queues){
                System.out.println("        Color:" + ((Queue)queue).getColor() + "   Size:" + ((Queue)queue).getSize() );
            }
        }
        Object size;
        Object color;
        int option;
        System.out.println("    1 - A4        2 - A3    ");
        option = MyUtils.inInt("Choose size option: ");
        size = (option == 1) ? new A4Quote() : new A3Quote();

        System.out.println("\nSize: " + size.toString());
        System.out.println();

        System.out.println(" 1 - Black&White 2 - Colors ");
        option = MyUtils.inInt("Choose print color option: ");
        color = (option == 1) ? new BlackWhiteQuote() : new ColorsQuote();

        System.out.println("\nColor: " + color.toString());
        System.out.println();

        char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to add the queue of size " + size + " and color " + color  + "? y/n"));
        if(confirm == 'Y'){
            if(!queues.contains(new Queue(size,color))){
                manager.addQueue(size, color);
            }

        }

        
    }


    public void solveMalfunction(){
        Employee employee = (Employee) manager.getCurrentUser();
        ArrayList<Object>  malfs= new ArrayList<>(employee.getMalfunctions());
        ArrayList<Object> filteredMalfunctions = new ArrayList<>();
        System.out.println("============================");
        System.out.println("|         NEW QUEUE        |");
        System.out.println("|Your Malfunctions:        |");
        int index = 0;
        if(malfs.size() == 0){
            MyUtils.inChar("|     You have no malfunctions   |");
            return;
        }
        else{
            
            for(Object malfunction : malfs){
                if(((Malfunction)malfunction).getState().equals(InRepairQuote.getInstance())){
                    System.out.println("   " + index + " - Problem: " + ((Malfunction)malfunction).getProblem() + " Assign by: " 
                    + ((Malfunction)malfunction).getAssignedBy().getName() + "\nDescription " + ((Malfunction)malfunction).getDescription());
                    filteredMalfunctions.add(malfunction);
                    index++;
                }
                
            }
        }
        if(filteredMalfunctions.isEmpty()){
            MyUtils.inChar("You have no malfunctions to solve");
            return;
        }
            

        ArrayList<Malfunction> choosenMalfunctions = new ArrayList<>();
        String option[] = MyUtils.inString("Please choose the malfunctions you wish to mark as resolved (you can enumerate more than one number at a time, separated with spaces): ").split(" ");
        for(int a = 0; a < option.length; a++){
            if(MyUtils.isNumeric(option[a])){
                Integer number = new Integer(option[a]);
                if(number >= 0 && number < malfs.size()){
                    if(!choosenMalfunctions.contains((Malfunction) filteredMalfunctions.get(number)))
                        choosenMalfunctions.add((Malfunction) filteredMalfunctions.get(number));
                }
            }
        }

        if(choosenMalfunctions.isEmpty()){
            MyUtils.inChar("We could not find any valid selection");
            return;
        }
            

        MyUtils.clearScreen();

        System.out.println("Chosen malfunctions: ");
        for(Malfunction malfunction: choosenMalfunctions){
            System.out.println("  Problem: " + ((Malfunction)malfunction).getProblem() + " Assign by: " 
                + ((Malfunction)malfunction).getAssignedBy().getName() + "\nDescription " + ((Malfunction)malfunction).getDescription());
                
        }

        char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to mark them as solved? y/n"));

         if(confirm == 'Y'){
             for(Malfunction malfunction: choosenMalfunctions){
                 malfunction.changeState(new FixedQuote());
             }
        	 
         }
            
        



    }


    public void clientsReport(){
        MyUtils.clearScreen();

        System.out.println("=============================");
        System.out.println("      CLIENTS REPORT          ");
        HashMap<String,VDMSet> clientsInfo = new HashMap<>(manager.reportClients());
        if(clientsInfo.isEmpty()){
            MyUtils.inString("Nothing to show.Press enter to go back: ");
            return;
        }
        for(String name: clientsInfo.keySet()){
            System.out.println("Client: " + name);
            System.out.println("   Documents: ");
            ArrayList<Object> docs = new ArrayList<>(clientsInfo.get(name));
            if(docs.isEmpty()){
                System.out.println("     There are no documents available!");
                continue;
            }
            for(Object doc : docs){
                System.out.println("     Document: " + ((Document)doc).getName() + " Created at: " + ((Document)doc).getDate() +
                " Cost: " + ((Document)doc).getPrice() + " Status: " + ((Document)doc).getPrinted());
            }
            System.out.println();
        }

        MyUtils.inString("Press Enter to go back: ");


    }

    public void employeesReport(){
        MyUtils.clearScreen();
        System.out.println("=============================");
        System.out.println("      EMPLOYEES REPORT       ");

        ArrayList<Object> employees = new ArrayList<>(manager.getRegularEmployees());
        if(employees.isEmpty()){
            MyUtils.inChar("There is no regular employees in the system. Press enter to go back: ");
            return;
        }
        for(Object employee: employees){
            HashMap<Object,Object> employeeMalfunction = new HashMap<>(manager.reportEmployee((Employee)employee));
            System.out.println("Employee Name: " + ((Employee)employee).getName());
            System.out.println("   Overall: " );
            for(Object state: employeeMalfunction.keySet()){
                System.out.println( "     " + state + "->" + employeeMalfunction.get(state));
            }
            System.out.println("   All assigned malfunctions");
            ArrayList<Object> malfunctions = new ArrayList<>(((Employee)employee).getMalfunctions());
            if(malfunctions.isEmpty()){
                System.out.println("    Employee has no malfunctions assigned!");
                continue;
            }
            for(Object malfunction: malfunctions){
                System.out.println("      Printer:"  + ((Malfunction)malfunction).getPrinter() + "  Problem: " + ((Malfunction)malfunction).getProblem() 
                + "  Description: " + ((Malfunction)malfunction).getDescription() + " AssignBy: " + ((Malfunction)malfunction).getAssignedBy().getName() +
                " State: " + ((Malfunction)malfunction).getState());
            }
            System.out.println();
        }


        MyUtils.inChar("Press enter to go back: ");

    }


    public void printersReport(){

        MyUtils.clearScreen();
        System.out.println("=============================");
        System.out.println("      PRINTERS  REPORT       ");


        HashMap<Object,Object> reportedPrintedDocs = new HashMap<>(manager.reportPrintedDocs());
        
        if(reportedPrintedDocs.isEmpty()){
            MyUtils.inChar("There is no available info. Press Enter to go back: ");
            return;
        }
        System.out.println(" Queues:");
        for(Object queue: reportedPrintedDocs.keySet()){
            System.out.println("    Color: " + ((Queue)queue).getColor() + " Type: " + ((Queue)queue).getSize());
            System.out.println("    Number of printed documents: " + reportedPrintedDocs.get(queue));
            System.out.println("    Documents still in queue: ");
            ArrayList<Object> docs= new ArrayList<>(((Queue)queue).getDocs());
            if(docs.isEmpty()){
                System.out.println("There are no documents in queue!");
                continue;
            }
            for(Object doc: docs){
                System.out.println("     Document: " + ((Document)doc).getName() + " Created at: " + ((Document)doc).getDate() + " By: " + ((Document)doc).getClient() +
                " Cost: " + ((Document)doc).getPrice() + " Status: " + ((Document)doc).getPrinted());
            }
            System.out.println();
        }

        MyUtils.inChar("Press enter to go back: ");

    }

}



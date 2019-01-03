package Printing;
import java.time.LocalDateTime;
import Printing.Document.Date;
import java.util.ArrayList;

import Printing.PrintManager;
import Printing.quotes.ClientQuote;
import Printing.quotes.ColorsQuote;
import Printing.quotes.LandscapeQuote;
import Printing.quotes.OtherQuote;
import Printing.quotes.PaperQuote;
import Printing.quotes.PortraitQuote;
import Printing.quotes.RegularQuote;
import Printing.quotes.TonerQuote;
import Printing.quotes.A3Quote;
import Printing.quotes.A4Quote;
import Printing.quotes.AdminQuote;
import Printing.quotes.BlackWhiteQuote;

import org.overture.codegen.runtime.*;


public class CommandInterface {

	
	
    private PrintManager manager = new PrintManager();
    
    
    public CommandInterface() {
    	manager.addEmployee("admin", "admin", new AdminQuote());
    }
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
                    running = false;
                    break;
                case 2:
                    System.out.println("Register selected");
                    registerMenu();
                    running = false;
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
        System.out.println("============================");
        System.out.println("|       LOGIN USER         |");
        System.out.println("============================");
        String username = MyUtils.inString("Username: ");
        String password = MyUtils.inPassword("Password: ");
        
        
        // TODO DO LOG IN 
        User log = manager.login(username,password);
        if(log == null){
            System.out.println("Invalid credentials");
            return;
        }else{
            if(log.getRole().equals(ClientQuote.getInstance())){
                clientMenu();
            }else if(log.getRole().equals(AdminQuote.getInstance())){
                adminMenu();
            }else{
                employeeMenu();
            }
        }
       

    }

    public void registerMenu(){
        System.out.println("============================");
        System.out.println("|      REGISTER USER       |");
        System.out.println("============================");
        String username = MyUtils.inString("Username: ");
        String password = MyUtils.inPassword("Password: ");
        double account = MyUtils.inDouble("Starting money: ");

        // TODO DO REGISTER 
        manager.addClient(username,password,account);

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
            System.out.println("| Moneu: " + ((Client) manager.getCurrentUser()).getAccount());
            System.out.println("| Client Menu:             |");
            System.out.println("|        1. Deposit Money  |");
            System.out.println("|        2. New Document   |");
            System.out.println("|        3. Print          |");
            System.out.println("|        4. Report         |");
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
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }

    public void depositMenu(){
        System.out.println("============================");
        System.out.println("|      DEPOSIT MONEY       |");
        System.out.println("============================");
        double money = MyUtils.inDouble("Money quantity: ");
        char sure = Character.toUpperCase(MyUtils.inChar("Are you sure you want to add " + money + " to your account? y/n"));

        if(sure == 'Y') {
             //TODO ADD MONEY
             Client user = (Client) manager.getCurrentUser();
             user.depositMoney(money);
             System.out.println("Your money will be added to the account");
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
        
        //number of pages
        
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
            //TODO CREATE DOCUMENT
            Client user = (Client) manager.getCurrentUser();
            user.addDocument(size,color,type,name,date,pages);

        }

        

    }

    public void printDocumentsMenu(){
        MyUtils.clearScreen();

        //TODO GET PRINTERS

       
        ArrayList<Object> printers = new ArrayList<>(manager.getPrinters());
        
        if(printers.size() == 0)
            return;
        int i = 0;

        //TODO PRINTERS LIST SHOULD SHOW WHAT TYPE OF DOCUMENTS THEY CAN PRINT
        System.out.println("============================");
        System.out.println("|          PRINT           |");
        System.out.println("| Printers:                |");
        for(Object printer:printers){
            System.out.println("|       " + i + " - " + ((Printer)printer).getLocation() + "      |");
            i++;
        }
        System.out.println("============================");

        int choose = MyUtils.inInt("Please choose the printer option: ");
        Printer choosenPrinter = (Printer) printers.get(0);
        if(choose >= 0 && choose < printers.size()){
            choosenPrinter = (Printer) printers.get(choose);
        }
        
    
        

        //TODO GET DOCUMENTS
        ArrayList<Object> documents = new ArrayList<>(choosenPrinter.queryPrintAsClient());
        
        if(documents.size() == 0)
        	return;
      

        //TODO DOCUMENTS LIST SHOULD SHOW CHARACTERISTICS OF EACH
        System.out.println("============================");
        System.out.println("|          PRINT           |");
        System.out.println("| Printer:                 |");
        System.out.println("|        "  + choosenPrinter.getLocation() + "         |");
        
        System.out.println("| Documents:               |");
        i = 0;
        for(Object doc:documents){
            System.out.println("|          " + i + " - " + ((Document)doc).getName() + ":" + ((Document)doc).getDate().toString() +":" + ((Document)doc).getPrice()+ "|");
            i++;
        }	
        System.out.println("============================");
        ArrayList<Document> choosenDocs = new ArrayList<>();
        String option[] = MyUtils.inString("Please choose the documents you wish to print (you can enumerate more than one number at a time, separated with spaces): ").split(" ");
        for(int a = 0; a < option.length; a++){
            if(MyUtils.isNumeric(option[a])){
                Integer number = new Integer(option[a]);
                if(number >= 0 && number < documents.size()){
                    choosenDocs.add((Document)documents.get(number));
                }
            }
        }

        MyUtils.clearScreen();

        System.out.println("============================");
        System.out.println("|          PRINT           |");
        System.out.println("| Printer:                 |");
        System.out.println("|        "  + choosenPrinter + "         |");
        System.out.println("| Choosen:                 |");
        double price = 0;
        for(int a = 0; i < choosenDocs.size(); a++){
            System.out.println("|        " + i + " - " + choosenDocs.get(a).getName() + ":" + choosenDocs.get(a).getDate().toString() + ":" + choosenDocs.get(a).getPrice() + "|");
            price = price + (choosenDocs.get(a).getPrice()).doubleValue();
        }
        
        System.out.println("Total price: " + price);

        char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to print? y/n"));

        if(confirm == 'Y'){
            for(Document doc:choosenDocs) {
            	choosenPrinter.print(doc);
            }
        }
        
    }

    public void reportMalfunctionMenu(){

        //TODO GET PRINTERS

        //Temp printers;
    	ArrayList<Object> printers = new ArrayList<>(manager.getPrinters());
        
        if(printers.size() == 0)
            return;
        int index= 0;
        System.out.println("============================");
        System.out.println("|    REPORT MALFUNCTION    |");
        System.out.println("| Printers:                |");
        for(Object printer:printers){
            System.out.println("|       " + index + " - " + ((Printer)printer).getLocation() + "      |");
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
            //TODO CREATE REPORT
        	User user = manager.getCurrentUser();
        	user.reportMalfunction(choosenPrinter, problemType, description);
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
            System.out.println("|        4. Printers Report|");
            System.out.println("|        5. Log Out        |");
            System.out.println("============================");
            option = MyUtils.inInt("Select Option: ");

            //Switch construct
            switch (option) {
                case 1:
                    System.out.println("Add Printer");
                    break;
                case 2:
                    System.out.println("Add Queue");
                    break;
                case 3:
                    System.out.println("Handle Malfunctions");
                    break;
                case 4:
                    System.out.println("Printers Report");
                    break;
                case 5:
                    System.out.println("Log Out");
                    manager.logout();
                    running = false;
                    mainMenu();
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
                    break;
                case 3:
                    System.out.println("Handle Malfunctions");
                    break;
                case 4:
                	System.out.println("Create new Malfunction");
                	reportMalfunctionMenu();
                	break;
                case 5:
                    System.out.println("Printers Report");
                    break;
                case 6:
                    System.out.println("Client Report");
                    break;
                case 7:
                    System.out.println("Employees Report");
                    break;
                case 8:
                    System.out.println("Log Out");
                    manager.logout();
                    running = false;
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid Selection");
                    break;
            }
        }
    }

    public void addEmployeeMenu(){
        System.out.println("============================");
        System.out.println("|       NEW EMPLOYEE       |");
        System.out.println("============================");
        Object employeeType;
        System.out.println("    1 - Regular        2 - Admin    ");
        int option = MyUtils.inInt("Choose what tyoe of employee: ");
        employeeType = (option == 1) ? new RegularQuote() : new AdminQuote();

        String employeeName = MyUtils.inString("Write the employee name: ");
        String password = MyUtils.inPassword("Write employee password: ");

        //TODO create employee
        manager.addEmployee(employeeName, password, employeeType);

    }
    
    
    public void assignEmployeeMenu() {
    	
    	 Object Employee;
         ArrayList<Object> employees = new ArrayList<>(manager.getEmployees());
         
         employees.add(manager.getCurrentUser());
         int index = 0;
    	 System.out.println("============================");
         System.out.println("|     ASSIGN MALFUNCTION   |");
         for(Object employee:employees){
             System.out.println("|       " + index + " - " + ((Employee)employee).getName() + "      |");
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
         
         System.out.println("============================");
         System.out.println("|     ASSIGN MALFUNCTION   |");
         System.out.println("| Employee:                 |");
         System.out.println("|        "  + choosenEmployee.getName() + "         |");
         
         System.out.println("| Malfunctions:               |");
         index = 0;
         ArrayList<Malfunction> filteredMalfunctions = new ArrayList<>();
         for(Object malfunction:malfunctions){
        	 if(((Malfunction)malfunction).getAssignedTo() != null) {
        		filteredMalfunctions.add((Malfunction)malfunction);
        		System.out.println("|       " + index + " - " + ((Malfunction)malfunction).getProblem().toString() + ":" + ((Malfunction)malfunction).getPrinter().getLocation() + "|");
              	index++;
        	 }
        		
         }
         ArrayList<Malfunction> choosenMalfunctions = new ArrayList<>();
         String option[] = MyUtils.inString("Please choose the malfunctions you wish to assign to " +  choosenEmployee.getName() + " (you can enumerate more than one number at a time, separated with spaces): ").split(" ");
         for(int a = 0; a < option.length; a++){
             if(MyUtils.isNumeric(option[a])){
                 Integer number = new Integer(option[a]);
                 if(number >= 0 && number < filteredMalfunctions.size()){
                     choosenMalfunctions.add(filteredMalfunctions.get(number));
                 }
             }
         }
         
         
         MyUtils.clearScreen();
         
         System.out.println("============================");
         System.out.println("|     ASSIGN MALFUNCTION   |");
         System.out.println("| Employee:                 |");
         System.out.println("|        "  + choosenEmployee.getName() + "         |");
         
         System.out.println("| Malfunctions:               |");
         for(Malfunction malfunction:choosenMalfunctions){
        		System.out.println("|       "+ (malfunction).getProblem() + ":" + malfunction.getPrinter().getLocation() + "|");	
         }
         
         char confirm = Character.toUpperCase(MyUtils.inChar("Are you sure you want to assign? y/n"));

         if(confirm == 'Y'){
        	 for(Malfunction malfunction:choosenMalfunctions) {
        		 malfunction.assignEmployee(choosenEmployee);
        	 }
         }
         
    }




}



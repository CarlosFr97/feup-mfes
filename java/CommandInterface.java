import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommandInterface {


    public static void main(String[] args){
        CommandInterface gui = new CommandInterface();
        gui.mainMenu();
    }


    public void mainMenu(){
        int option;
        boolean running = true;

        while(running){
            
            Utils.clearScreen();
        
            // Display menu graphics
            System.out.println("============================");
            System.out.println("|     PRINTING SYSTEM      |");
            System.out.println("============================");
            System.out.println("| Main Menu:               |");
            System.out.println("|        1. Log In         |");
            System.out.println("|        2. Register       |");
            System.out.println("|        3. Exit           |");
            System.out.println("============================");
            option = Utils.inInt("Select Option: ");

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
        String username = Utils.inString("Username: ");
        String password = Utils.inPassword("Password: ");
        
        
        // TODO DO LOG IN 
       

    }

    public void registerMenu(){
        System.out.println("============================");
        System.out.println("|      REGISTER USER       |");
        System.out.println("============================");
        String username = Utils.inString("Username: ");
        String password = Utils.inPassword("Password: ");
        double account = Utils.inDouble("Starting money: ");

        // TODO DO REGISTER 

        clientMenu();

    }



    public void clientMenu() {
        int option;
        boolean running = true;

        while(running){

            Utils.clearScreen();
            
            // Display menu graphics
            System.out.println("============================");
            System.out.println("|     PRINTING SYSTEM      |");
            System.out.println("============================");
            System.out.println("| Client Menu:             |");
            System.out.println("|        1. Deposit Money  |");
            System.out.println("|        2. New Document   |");
            System.out.println("|        3. Print          |");
            System.out.println("|        4. Report         |");
            System.out.println("|        5. Log Out        |");
            System.out.println("============================");
            option = Utils.inInt("Select Option: ");

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
                    logOut();
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
        double money = Utils.inDouble("Money quantity: ");
        char sure = Character.toUpperCase(Utils.inChar("Are you sure you want to add " + money + " to your account? y/n"));

        if(sure == 'Y') {
             //TODO ADD MONEY
             System.out.println("Your money will be added to the account");
        }else {
            return;
        }  
    }

    public void newDocMenu(){
        int option;
        String size;
        String type;
        String color;
        String date = LocalDateTime.now().toString();
        System.out.println("============================");
        System.out.println("|       NEW DOCUMENT       |");
        System.out.println("============================");
        //Choose size
        System.out.println("    1 - A4        2 - A3    ");
        option = Utils.inInt("Choose size option: ");
        size = (option == 1) ? "<A4>" : "<A3>";

        System.out.println("\nSize: " + size);
        System.out.println();
        System.out.println(" 1 - Portrait 2 - Landscape ");
        option = Utils.inInt("Choose type option: ");
        type = (option == 1) ? "<Portrait>" : "<Landscape>";

        System.out.println("\nType: " + type);
        System.out.println();
        System.out.println(" 1 - Black&White 2 - Colors ");
        option = Utils.inInt("Choose print color option: ");
        color = (option == 1) ? "<BlackWhite>" : "<Colors>";

        System.out.println("\nColor: " + color);
        System.out.println();
        String name = Utils.inString("What is the name of the Document? ");
        char confirm = Character.toUpperCase(Utils.inChar("Are you sure you want to save " + name + " as a Document of size " + size + " displayed in " + type + " printed in " + color + "? y/n"));
        if(confirm == 'Y'){
            //TODO CREATE DOCUMENT
        }

        

    }

    public void printDocumentsMenu(){
        //First choose documents

        //TODO GET DOCUMENTS
        ArrayList<String> documents = new ArrayList<>();
        //temporary documents
        for(int i = 0; i < 10 ; i++){
            documents.add("Doc " + i);
        }

        //TODO DOCUMENTS LIST SHOULD SHOW CHARACTERISTICS OF EACH
        System.out.println("============================");
        System.out.println("|          PRINT           |");
        System.out.println("| Documents:               |");
        for(int i = 0; i < documents.size(); i++){
            System.out.println("|          " + i + " - " + documents.get(i) + "       |");
        }
        System.out.println("============================");
        ArrayList<String> choosenDocs = new ArrayList<>();
        String option[] = Utils.inString("Please choose the documents you wish to print (you can enumerate more than one number at a time, separated with spaces): ").split(" ");
        for(int i = 0; i < option.length; i++){
            System.out.println("Chosen: " + option[i]);
            if(Utils.isNumeric(option[i])){
                System.out.println("Is Integer");
                Integer number = new Integer(option[i]);
                if(number >= 0 && number < documents.size()){
                    choosenDocs.add(documents.get(number));
                }else{
                    System.out.println("Invalid Number");
                }
            }else{
                System.out.println("Is not an Integer");
            }
        }

        Utils.clearScreen();

        //TODO GET PRINTERS

        //Temp printers;
        ArrayList<String> printers = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            printers.add("Printer " + i);

        //TODO PRINTERS LIST SHOULD SHOW WHAT TYPE OF DOCUMENTS THEY CAN PRINT
        System.out.println("============================");
        System.out.println("|          PRINT           |");
        System.out.println("| Choosen:                 |");
        for(int i = 0; i < choosenDocs.size(); i++){
            System.out.println("|        "  + choosenDocs.get(i) + "             |");
        }
        System.out.println("| Printers:                |");
        for(int i = 0; i < printers.size(); i++){
            System.out.println("|       " + i + " - " + printers.get(i) + "      |");
        }
        System.out.println("============================");

        int choose = Utils.inInt("Please choose the printer option: ");
        String choosenPrinter = printers.get(0);
        if(choose >= 0 && choose < printers.size()){
            choosenPrinter = printers.get(choose);
        }
        
        System.out.println("\nPrinter: " + choosenPrinter);
        System.out.println();

        char confirm = Character.toUpperCase(Utils.inChar("Are you sure you want to print? y/n"));

        if(confirm == 'Y'){
            //TODO PRINT
        }
        
    }

    public void reportMalfunctionMenu(){

        //TODO GET PRINTERS

        //Temp printers;
        ArrayList<String> printers = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            printers.add("Printer " + i);

        if(printers.size() == 0)
            return;
        
        System.out.println("============================");
        System.out.println("|    REPORT MALFUNCTION    |");
        System.out.println("| Printers:                |");
        for(int i = 0; i < printers.size(); i++){
            System.out.println("|       " + i + " - " + printers.get(i) + "      |");
        }
        System.out.println("============================");
        int choose = Utils.inInt("Please choose the printer option: ");
        String choosenPrinter = printers.get(0);
        if(choose >= 0 && choose < printers.size()){
            choosenPrinter = printers.get(choose);
        }

        System.out.println("\nPrinter: " + choosenPrinter);
        System.out.println();
        System.out.println("    1 - Toner  2 - Paper 3 - Other  ");
        choose = Utils.inInt("What is the problem: ");
        String problemType = "<Other>";
        if(choose == 1)
            problemType = "<Toner>";
        else if(choose == 2)
            problemType = "<Paper>";
        
        System.out.println("\nProblem type: " + problemType);
        System.out.println();
        String description = Utils.inString("Please write a short description of the problem: ");
        
        char confirm = Character.toUpperCase(Utils.inChar("Are you sure you want to report? y/n"));

        if(confirm == 'Y'){
            //TODO CREATE REPORT
        }
        

    }



    public void employeeMenu() {
        int option;
        boolean running = true;

        while(running){

            Utils.clearScreen();
            
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
            option = Utils.inInt("Select Option: ");

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
                    logOut();
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

            Utils.clearScreen();
            
            // Display menu graphics
            System.out.println("=============================");
            System.out.println("|     PRINTING SYSTEM       |");
            System.out.println("=============================");
            System.out.println("| Admin Menu:               |");
            System.out.println("|        1. Add Employee    |");
            System.out.println("|        2. Assign Employee |");
            System.out.println("|        3. Malfunctions    |");
            System.out.println("|        4. Printers Report |");
            System.out.println("|        5. Clients Report  |");
            System.out.println("|        6. Employees Report|");
            System.out.println("|        7. Log Out         |");
            System.out.println("=============================");
            option = Utils.inInt("Select Option: ");

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
                    System.out.println("Printers Report");
                    break;
                case 5:
                    System.out.println("Client Report");
                    break;
                case 6:
                    System.out.println("Employees Report");
                    break;
                case 7:
                    System.out.println("Log Out");
                    logOut();
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
        String employeeType;
        System.out.println("    1 - Regular        2 - Admin    ");
        int option = Utils.inInt("Choose what tyoe of employee: ");
        employeeType = (option == 1) ? "<Regular>" : "<Admin>";

        String employeeName = Utils.inString("Write the employee name: ");
        String password = Utils.inPassword("Write employee password: ");

        //TODO create employee

    }



    public void logOut(){
        //TODO LOGOUT
    }


}



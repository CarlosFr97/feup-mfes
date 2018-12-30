import java.time.LocalDateTime;

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
        System.out.println();
        System.out.println(" 1 - Portrait 2 - Landscape ");
        option = Utils.inInt("Choose type option: ");
        type = (option == 1) ? "<Portrait>" : "<Landscape>";

        System.out.println("\nType: " + type);
        System.out.println();
        System.out.println();
        System.out.println(" 1 - Black&White 2 - Colors ");
        option = Utils.inInt("Choose print color option: ");
        color = (option == 1) ? "<BlackWhite>" : "<Colors>";

        System.out.println("\nColor: " + color);
        System.out.println();
        System.out.println();
        String name = Utils.inString("What is the name of the Document? ");
        char confirm = Character.toUpperCase(Utils.inChar("Are you sure you want to save " + name + " as a Document of size " + size + " displayed in " + type + " printed in " + color + "? y/n"));
        if(confirm == 'Y'){
            //TODO CREATE DOCUMENT
        }

        

    }

    public void printDocumentsMenu(){
        
    }

    public void logOut(){
        //TO DO LOGOUT
    }


}



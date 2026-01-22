import java.util.Scanner;

public class OptionsMenu {
    static int command;

    public static void showOptionsMenu(Scanner scanner){
        do{
            System.out.println("Select your choice and press 'Enter'\n"+"" +
                    "1: Show current settings\n"+
                    "2: Change settings\n"+
                    "3: Exit");
            command = scanner.nextInt();

            switch (command){
                case 1:
                    System.out.println("__________________________________________________________\n" +
                            "\n Current settings:\n"+
                            "rows: " + Main.sizeX+"\n"+
                            "columns: " + Main.sizeY+"\n"+
                            "enemies: " + Main.amountOfEnemies+"\n"+
                            "transistors: " + Main.transistorsNeeded+"\n"+
                            "moves: " + Main.moves+"\n"+
                            "flowers: " + Main.amountOfFlowers+"\n"+
                            "__________________________________________________________\n");
                    break;
                case 2:
                    System.out.println("Enter a new value for rows: ");
                    Main.sizeX = scanner.nextInt();
                    System.out.println("Enter a new value for columns: ");
                    Main.sizeY = scanner.nextInt();
                    System.out.println("Enter a new value for enemies: ");
                    Main.amountOfEnemies = scanner.nextInt();
                    System.out.println("Enter a new value for transistors: ");
                    Main.transistorsNeeded= scanner.nextInt();
                    System.out.println("Enter a new value for moves: ");
                    Main.moves = scanner.nextInt();
                    System.out.println("Enter a new value for flowers: ");
                    Main.amountOfFlowers = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("command not recognised, please try again!");
                    break;

            }
        }
        while(command!=3);
    }
}

import java.util.ArrayList;
import java.util.Random;

public class Game {
     private int sizeX;
     private int sizeY;
     private int amountOfEnemies;
     private int transistorsNeeded;
     private int transistorsGathered;
     private int movesLeft;
     private int flowersGathered;
     private Field field;
     private boolean isGameFinished = false;
     private int amountOfFlowers;
     private ArrayList<Flower> flowerArrayList = new ArrayList<Flower>();
     private Random randomNumber = new Random();


     public Game(int sizeX, int sizeY, int amountOfEnemies, int transistorsNeeded, int movesLeft, int amountOfFlowers) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.amountOfEnemies = amountOfEnemies;
        this.transistorsNeeded = transistorsNeeded;
        this.movesLeft = movesLeft;
        this.amountOfFlowers = amountOfFlowers;
        field = new Field(sizeX, sizeY);
    }

    public void fillFieldWithEmptyObjects(){
         for (int i=0;i<sizeX;i++){
             for(int j=0;j<sizeY;j++){
                 field.setFieldable(i, j, new Empty());
             }
         }
    }

    public void startGame(){
         placePlayer();
         placeEnemies();
         placeFlowers();
         
         while (!isGameFinished){
             showField();
             playerTurn();
             computerTurn();
             checkIfGameNotFinished();
         }
    }

    private void placeFlowers() {
        generateFlowers();
    }

    private void placeEnemies() {
    }

    private void placePlayer() {
    }

    private void checkIfGameNotFinished() {
        isGameFinished = true;
    }

    private void computerTurn() {
        System.out.println("Computer turn");
    }

    private void playerTurn() {
        System.out.println("Player turn");
    }

    private void generateFlowers(){
        for (int i=amountOfFlowers-flowerArrayList.size(); i > 0;){
            int flowerAmountOfTransistors = randomNumber.nextInt( 9)+1 ;
            int flowerRowPosition = randomNumber.nextInt(sizeX);
            int flowerColumnPosition =  randomNumber.nextInt(sizeY);

            if (field.getFieldable(flowerRowPosition, flowerColumnPosition)
                    instanceof Player){
                transistorsGathered = transistorsGathered + flowerAmountOfTransistors;
                i--;
            }

            else  if (field.getFieldable(flowerRowPosition, flowerColumnPosition)
                    instanceof Empty){
                Flower flower =  new Flower(flowerAmountOfTransistors, flowerRowPosition, flowerColumnPosition);
                field.setFieldable(flowerRowPosition, flowerColumnPosition, flower);
                flowerArrayList.add(flower);
                i--;

            }
//                else  if(field.getFieldable(flowerRowPosition, flowerColumnPosition)
//                        instanceof Enemy ||field.getFieldable(flowerRowPosition, flowerColumnPosition)
//                        instanceof Flower){
//
//                }
        }
    }

    public Field getField(){
         return this.field;
    }

    public ArrayList<Flower> getFlowerArrayList(){
         return this.flowerArrayList;
    }

    public void setTransistorsGathered(int transistorsToAdd){
         this.transistorsGathered += transistorsToAdd;
    }


    private void showField() {
        System.out.println("=== FIELD ===");
        field.showField();
    }

}

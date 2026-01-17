public class Game {
     private int sizeX;
     private int sizeY;
     private int amountOfEnemies;
     private int transistorsNeeded;
     private int movesLeft;
     private int flowersGathered;
     private Field field;
     private boolean isGameFinished = false;


     public Game(int sizeX, int sizeY, int amountOfEnemies, int transistorsNeeded, int movesLeft) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.amountOfEnemies = amountOfEnemies;
        this.transistorsNeeded = transistorsNeeded;
        this.movesLeft = movesLeft;
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
    }

    private void placeEnemies() {
    }

    private void placePlayer() {
    }

    private void checkIfGameNotFinished() {
    }

    private void computerTurn() {
    }

    private void playerTurn() {
    }

    private void showField() {
    }

}

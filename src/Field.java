public class Field {
    private int sizeX;
    private int sizeY;

    private Fieldable[][] field;

    public Field(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        field = new Fieldable[sizeX][sizeY];
    }

    //getters
    public int getSizeY() {
        return sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    //no setters(sizeX, sizeY) as when field is created, it's not possible to change it.
    //can be changed in next game with new sizeX and sizeY.

    public void setFieldable(int x, int y, Fieldable objectF){
        field[x][y] = objectF;
    }

    public Fieldable getFieldable(int x, int y){
        return field[x][y];
    }

    public void showField(){
        System.out.println();
        for (int i=0; i<sizeX;i++){
            System.out.println();
            for (int j =0; j<sizeY;j++){
                System.out.println(field[i][j].getSymbol());
            }
        }
        System.out.println();
    }
}

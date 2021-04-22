package dominio;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int widht = 6;
    private int length = 6;
    private Integer[][] completed;
    private int score = 0;
    private Element[][] elements;

    public Board(){
        completed = new Integer[length][widht];
        elements = new Element[length][widht];
        generateElement();
        score = 0;
    }
    public Board(int widht, int length){
        this.widht = widht;
        this.length = length;
        completed = new Integer[length][widht];
        elements = new Element[length][widht];
        generateElement();
        score = 0;
    }

    public Board(String[][] elements){
        widht = elements[0].length;
        length = elements.length;
        completed = new Integer[length][widht];
        this.elements = new Element[length][widht];
        for (int i = 0; i < length; i ++){
            for (int j = 0; j < widht; j ++){
                if(elements[i][j] == "Blue"){
                    Element element = new NormalJewel(0);
                    this.elements[i][j] = element;
                }
                else if(elements[i][j] == "Green"){
                    Element element = new NormalJewel(1);
                    this.elements[i][j] = element;
                }
                else if(elements[i][j] == "Coin"){
                    Element element = new NormalJewel(2);
                    this.elements[i][j] = element;
                }
                else if(elements[i][j] == "Cian skull"){
                    Element element = new NormalJewel(3);
                    this.elements[i][j] = element;
                }
                else if(elements[i][j] == "Yellow skull"){
                    Element element = new NormalJewel(4);
                    this.elements[i][j] = element;
                }
                else {
                    Random rand = new Random();
                    Element element = new NormalJewel(rand.nextInt(5));
                    this.elements[i][j] = element;
                }
                for (int i = 0; i < length; i++){
                    for (int j = 0; j < widht; j++){
                        checkForThree(i,j);
                        }
                    }
                }
                score = 0;
            }
        }
    }

    public boolean moveElement(int x, int y, char direction){
        x--;
        y--;
        //Usar excepciones para verificar elementos fuera de rango
        if (direction == 'u'){
           Element a = elements[x][y];
           Element b = elements[x][y - 1];
           elements [x][y] = b;
           elements [x][y - 1] = a;
            if (checkForThree(x,y) > 0 || checkForThree(x,y - 1) >0){
                return true;
            }
            else {
                return false;
            }
        }
        else if (direction == 'l'){
            Element a = elements[x][y];
            Element b = elements[x - 1][y];
            elements [x][y] = b;
            elements [x - 1][y] = a;
            if (checkForThree(x,y) > 0 || checkForThree(x - 1,y)>0){
                return true;
            }
            else {
                return false;
            }
        }
        else if (direction == 'd'){
            Element a = elements[x][y];
            Element b = elements[x][y + 1];
            elements [x][y] = b;
            elements [x][y + 1] = a;
            if (checkForThree(x,y) > 0 || checkForThree(x,y + 1)>0){
                return true;
            }
            else {
                return false;
            }
        }
        else if (direction == 'r'){
            Element a = elements[x][y];
            Element b = elements[x + 1][y];
            elements [x][y] = b;
            elements [x + 1][y] = a;
            if (checkForThree(x,y) > 0 || checkForThree(x + 1,y)>0){
                return true;
            }
            else {
                return false;
            }
        }
        /*
        else {
            throw fuera de rango
        }
         */
        return false;
    }

    /**
     * Generate an element on each null position of the board
     */
    public void generateElement(){
        Random rand = new Random();
        ArrayList<int[]> newElements = new ArrayList<>();
        for (int i = 0; i < length; i++){
            for (int j = 0; j < widht; j++){
                if (elements[i][j] == null){
                    Element newElement = new NormalJewel(rand.nextInt(5));
                    elements[i][j] = newElement;
                    int[] pos = new int[]{i,j};
                    newElements.add(pos);
                }
            }
        }
        if (newElements.size() > 0){
            for (int[] newElement: newElements){
                checkForThree(newElement[0],newElement[1]);
            }
        }
    }

    /**
     * remove an element in a position of the board
     * @param x position x of the element
     * @param y position y of the element
     */
    public void removeElement(int x, int y){
        x--;
        y--;
        elements[x][y] = null;
    }

    public int getWidth(){return widht;}

    public int getLength(){return  length;}

    public String[][] getElements(){
        String[][] elementsStrings = new String[length][widht];
        for (int i = 0; i < length; i ++){
            for (int j = 0; j < widht; j++){
                if (elements[i][j] != null){
                    elementsStrings[i][j] = elements[i][j].getName();
                }
            }
        }
        return elementsStrings;
    }

    public int getScore(){return score;}

    private void setScore(int score){this.score = score;}

    public Integer[][] getCompleted(){return completed;}

    /**
     * look for winner element in a complete column
     * @param column that we are looking for winner pieces
     */
    public void checkWinColumn(int column){
        for(int i =0;i<length;i++){
            checkWinByPositionColumn(i,column);
            checkWinByPositionRow(i,column);
        }
    }
    /**
     * Check if there are winner pieces given a position
     * @param x position x in the board
     * @param y position y in the board
     */
    public boolean checkWinByPositionColumn(int x, int y){
        if(completed[x-1][y].equals(completed[x][y]) && completed[x+1][y].equals(completed[x][y])){
            removeElement(x-1,y);
            removeElement(x,y);
            removeElement(x+1,y);
            return true;
        }
        return false;
    }
    public boolean checkWinByPositionRow(int x, int y){
        if(completed[x][y-1].equals(completed[x][y]) && completed[x][y+1].equals(completed[x][y])){
            removeElement(x,y-1);
            removeElement(x,y);
            removeElement(x,y+1);
            return true;
        }
        return false;
    }
    public void refreshColumn(int column){

    }

    /**
     * checks if exists three elements given a position
     * @param x position x
     * @param y position y
     * @return
     */
    public int checkForThree(int x, int y){


        return 1;
    }

    public ArrayList<Element> getAdjoints(int x, int y) {
        ArrayList<Element> adjoints = new ArrayList<>();
        int[] coordenada_i = {0,-1,0,1};
        int[] coordenada_j = {-1,0,1,0};
        int nueva_posicion_i = 0;
        int nueva_posicion_j = 0;
        for (int k = 0; k < 4 ; k++) {
            nueva_posicion_i = x + coordenada_i[k];
            nueva_posicion_j = y + coordenada_j[k];
            if((nueva_posicion_i >= 0 && nueva_posicion_i <length) && (nueva_posicion_j >= 0 && nueva_posicion_j <widht)) {
                adjoints.add(elements[nueva_posicion_i][nueva_posicion_j]);
            }

        }
        return  adjoints;
    }


}

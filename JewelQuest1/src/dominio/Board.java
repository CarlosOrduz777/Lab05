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
                for (int k = 0; k < length; k++){
                    for (int l = 0; l < widht; l++){
                        checkForThree(k,l);
                        }
                    }
                }
                score = 0;
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



    public void refreshColumn(int x,int column){
        Element[] columnElements = getColumn(x,column);
        int contadorNulls = 0;
       ArrayList<Element> elements1 = new ArrayList<>();
        for (int j = 0; j <columnElements.length ; j++) {
            if(columnElements[j] != null){
                elements1.add(columnElements[j]);
            }else{
                contadorNulls += 1;
            }
        }
        Element[] resultado = new Element[elements1.size() + contadorNulls];
        Object[] nulls = new Object[contadorNulls];
        Element[] elements2 = elements1.toArray(new Element[0]);
        int j = 0;
        for (int i = 0; i < nulls.length; i++) {
            resultado[i] = null;
            j++;
        }
        for (int i = 0; i < elements2.length ; i++) {
            resultado[j] = elements2[i];
            j++;
        }

        //we replace the new column in the old column
        for (int i =0 ; i<=x; i++){
            elements[i][column] = resultado[i];
        }

        this.generateElement();

    }

    /**
     * checks if exists three equal elements given a position
     * @param x position x
     * @param y position y
     * @return
     */
    public int checkForThree(int x, int y){
        ArrayList<Element> adjoints = getAdjoints(x,y);
        for(Element e:adjoints){
            if(isUp(x,y,e) && elements[x][y].equals(e)){
                if(elements[x][y].equals(elements[x-2][y])){
                    removeElement(x,y);
                    removeElement(x-1,y);
                    removeElement(x-2,y);
                    refreshColumn(x,y);
                    completed[x][y] = 1;
                    completed[x-1][y] = 1;
                    completed[x-2][y] = 1;
                }
                if(elements[x][y].equals(elements[x+1][y])){
                    removeElement(x,y);
                    removeElement(x-1,y);
                    removeElement(x+1,y);
                    refreshColumn(x+1,y);

                    completed[x][y] = 1;
                    completed[x-1][y] = 1;
                    completed[x+1][y] = 1;

                }
            }else if(isDown(x,y,e) && elements[x][y].equals(e)){
                if(elements[x][y].equals(elements[x+2][y])){
                    removeElement(x,y);
                    removeElement(x+1,y);
                    removeElement(x+2,y);
                    refreshColumn(x,y);

                    completed[x][y] = 1;
                    completed[x+1][y] = 1;
                    completed[x+2][y] = 1;
                }
                if(elements[x][y].equals(elements[x-1][y])){
                    removeElement(x,y);
                    removeElement(x+1,y);
                    removeElement(x-1,y);
                    refreshColumn(x+1,y);

                    completed[x][y] = 1;
                    completed[x+1][y] = 1;
                    completed[x-1][y] = 1;
                }

            }else if(isRight(x,y,e) && elements[x][y].equals(e)){
                if(elements[x][y].equals(elements[x][y+2])){
                    removeElement(x,y);
                    removeElement(x,y+1);
                    removeElement(x,y+2);

                    refreshColumn(x,y);
                    refreshColumn(x,y+1);
                    refreshColumn(x,y+2);

                    completed[x][y] = 1;
                    completed[x][y+1] = 1;
                    completed[x][y+2] = 1;

                }
                if(elements[x][y].equals(elements[x][y-1])){
                    removeElement(x,y);
                    removeElement(x,y-1);
                    removeElement(x,y+1);

                    refreshColumn(x,y);
                    refreshColumn(x,y-1);
                    refreshColumn(x,y+1);

                    completed[x][y] = 1;
                    completed[x][y-1] = 1;
                    completed[x][y+1] = 1;
                }

            }else if(isLeft(x,y,e) && elements[x][y].equals(e)){
                if(elements[x][y].equals(elements[x][y-2])){
                    removeElement(x,y);
                    removeElement(x,y-1);
                    removeElement(x,y-2);

                    refreshColumn(x,y);
                    refreshColumn(x,y-1);
                    refreshColumn(x,y-2);

                    completed[x][y] = 1;
                    completed[x][y-1] = 1;
                    completed[x][y-2] = 1;

                }
                if (elements[x][y].equals(elements[x][y+1])){
                    removeElement(x,y);
                    removeElement(x,y-1);
                    removeElement(x,y+1);

                    refreshColumn(x,y);
                    refreshColumn(x,y-1);
                    refreshColumn(x,y+1);

                    completed[x][y] = 1;
                    completed[x][y-1] = 1;
                    completed[x][y+1] = 1;
                }
            }
        }
        return 1;
    }
    public Element[] getColumn(int x, int column){
        Element[] columnElements = new Element[x];

        for (int i = 0; i <= x; i++) {
            columnElements[i] = elements[i][column];
        }
        return columnElements;
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
    public Element getElement(int x ,int y){
        return elements[x][y];
    }

    public boolean isUp(int x ,int y,Element e){
        return elements[x-1][y].equals(e);
    }
    public boolean isDown(int x,int y,Element e){
        return elements[x+1][y].equals(e);
    }
    public boolean isRight(int x , int y,Element e){
        return elements[x][y+1].equals(e);
    }
    public boolean isLeft(int x, int y,Element e){
        return  elements[x][y-1].equals(e);
    }


}

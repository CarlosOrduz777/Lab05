package dominio;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private int widht = 6;
    private int length = 6;
    private final int[][] completed;
    private int score = 0;
    private final Element[][] elements;

    public Board(){
        completed = new int[length][widht];
        elements = new Element[length][widht];
        generateElement();
        score = 0;
    }
    public Board(int widht, int length){
        this.widht = widht;
        this.length = length;
        completed = new int[length][widht];
        elements = new Element[length][widht];
        generateElement();
        score = 0;
    }
    public Board(String[][] elements){
        widht = elements[0].length;
        length = elements.length;
        completed = new int[length][widht];
        this.elements = new Element[length][widht];
        for (int i = 0; i < length; i ++){
            for (int j = 0; j < widht; j ++){
                switch (elements[i][j]) {
                    case "Blue" -> {
                        Element element = new NormalJewel(0);
                        this.elements[i][j] = element;
                    }
                    case "Green" -> {
                        Element element = new NormalJewel(1);
                        this.elements[i][j] = element;
                    }
                    case "Coin" -> {
                        Element element = new NormalJewel(2);
                        this.elements[i][j] = element;
                    }
                    case "Cian skull" -> {
                        Element element = new NormalJewel(3);
                        this.elements[i][j] = element;
                    }
                    case "Yellow skull" -> {
                        Element element = new NormalJewel(4);
                        this.elements[i][j] = element;
                    }
                    default -> {
                        Random rand = new Random();
                        Element element = new NormalJewel(rand.nextInt(5));
                        this.elements[i][j] = element;
                    }
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

    public boolean moveElement(int y, int x, char direction){
        y--;
        x--;
        //Usar excepciones para verificar elementos fuera de rango
        if (direction == 'u'){
           Element a = elements[y][x];
           Element b = elements[y-1][x];
           elements [y][x] = b;
           elements [y-1][x] = a;
            if(checkForThree(y, x) > 0 || checkForThree(y-1, x ) > 0){
                return true;
            }
            else {
                Element c = elements[y][x];
                Element d = elements[y-1][x];
                elements [y][x] = d;
                elements [y-1][x] = c;
            }
        }
        else if (direction == 'l'){
            Element a = elements[y][x];
            Element b = elements[y][x-1];
            elements [y][x] = b;
            elements [y][x-1] = a;
            if(checkForThree(y, x) > 0 || checkForThree(y, x-1) > 0){
                return true;
            }
            else {
                Element c = elements[y][x];
                Element d = elements[y][x-1];
                elements [y][x] = d;
                elements [y][x-1] = c;
            }
        }
        else if (direction == 'd'){
            Element a = elements[y][x];
            Element b = elements[y+1][x];
            elements [y][x] = b;
            elements [y+1][x] = a;
            if(checkForThree(y, x) > 0 || checkForThree(y, x + 1) > 0){
                return true;
            }
            else {
                Element c = elements[y][x];
                Element d = elements[y][x + 1];
                elements [y][x] = d;
                elements [y][x + 1] = c;
            }
        }
        else if (direction == 'r'){
            Element a = elements[y][x];
            Element b = elements[y + 1][x];
            elements [y][x] = b;
            elements [y + 1][x] = a;
            if(checkForThree(y, x) > 0 || checkForThree(y+1, x) > 0){
                return true;
            }
            else {
                Element c = elements[y][x];
                Element d = elements[y + 1][x];
                elements [y][x] = d;
                elements [y + 1][x] = c;
            }
        }
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
     * @param x position y of the element
     */
    public void removeElement(int y, int x){
        elements[y][x] = null;
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

    public void updateScore(){
        for (int[] ints:completed){
            for (int inte: ints){
                if (inte == 1) {
                    score += 1;
                }
            }
        }
    }

    public int[][] getCompleted(){return completed;}

    public void refreshColumn(int x,int column){
        Element[] columnElements = getColumn(x,column);
        int contadorNulls = 0;
       ArrayList<Element> elements1 = new ArrayList<>();
        for (Element columnElement : columnElements) {
            if (columnElement != null) {
                elements1.add(columnElement);
            } else {
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
        for (Element element : elements2) {
            resultado[j] = element;
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
     * @param x position y
     */
    public int checkForThree(int y, int x){
        ArrayList<Element> adjoints = getAdjoints(y,x);
        if (elements[y][x] != null) {
            for (Element e : adjoints) {
                if (e != null){
                    try {
                        if (isUp(y, x, e) && elements[y][x].equalss(e)) {
                            try {
                                if (elements[y][x].equalss(elements[y - 2][x])) {

                                    removeElement(y, x);
                                    removeElement(y - 1, x);
                                    removeElement(y - 2, x);
                                    refreshColumn(y, x);
                                    completed[y][x] = 1;
                                    completed[y - 1][x] = 1;
                                    completed[y - 2][x] = 1;
                                }
                            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                            }
                            try {
                                if (elements[y][x].equalss(elements[y + 1][x])) {
                                    removeElement(y, x);
                                    removeElement(y - 1, x);
                                    removeElement(y + 1, x);
                                    refreshColumn(y + 1, x);

                                    completed[y][x] = 1;
                                    completed[y - 1][x] = 1;
                                    completed[y + 1][x] = 1;

                                }
                            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                            }
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {}
                    try {
                        if (isDown(y, x, e) && elements[y][x].equalss(e)) {
                            try {
                                if (elements[y][x].equalss(elements[y + 2][x])) {
                                    removeElement(y, x);
                                    removeElement(y + 1, x);
                                    removeElement(y + 2, x);
                                    refreshColumn(y, x);

                                    completed[y][x] = 1;
                                    completed[y + 1][x] = 1;
                                    completed[y + 2][x] = 1;
                                }
                            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                            }
                            try {
                                if (elements[y][x].equalss(elements[y - 1][x])) {
                                    removeElement(y, x);
                                    removeElement(y + 1, x);
                                    removeElement(y - 1, x);
                                    refreshColumn(y + 1, x);

                                    completed[y][x] = 1;
                                    completed[y + 1][x] = 1;
                                    completed[y - 1][x] = 1;
                                }
                            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                            }

                        }
                    }
                    catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {}
                    try {
                        if (isRight(y, x, e) && elements[y][x].equalss(e)) {
                            try {
                                if (elements[y][x].equalss(elements[y][x + 2])) {
                                    removeElement(y, x);
                                    removeElement(y, x + 1);
                                    removeElement(y, x + 2);

                                    refreshColumn(y, x);
                                    refreshColumn(y, x + 1);
                                    refreshColumn(y, x + 2);

                                    completed[y][x] = 1;
                                    completed[y][x + 1] = 1;
                                    completed[y][x + 2] = 1;

                                }
                            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                            }
                            try {
                                if (elements[y][x].equalss(elements[y][x - 1])) {
                                    removeElement(y, x);
                                    removeElement(y, x - 1);
                                    removeElement(y, x + 1);

                                    refreshColumn(y, x);
                                    refreshColumn(y, x - 1);
                                    refreshColumn(y, x + 1);

                                    completed[y][x] = 1;
                                    completed[y][x - 1] = 1;
                                    completed[y][x + 1] = 1;
                                }
                            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                            }

                        }
                    }
                    catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {}
                    try {
                        if (isLeft(y, x, e) && elements[y][x].equalss(e)) {
                            try {
                                if (elements[y][x].equalss(elements[y][x - 2])) {
                                    removeElement(y, x);
                                    removeElement(y, x - 1);
                                    removeElement(y, x - 2);

                                    refreshColumn(y, x);
                                    refreshColumn(y, x - 1);
                                    refreshColumn(y, x - 2);

                                    completed[y][x] = 1;
                                    completed[y][x - 1] = 1;
                                    completed[y][x - 2] = 1;

                                }
                            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                            }
                            try {
                                if (elements[y][x].equalss(elements[y][x + 1])) {
                                    removeElement(y, x);
                                    removeElement(y, x - 1);
                                    removeElement(y, x + 1);

                                    refreshColumn(y, x);
                                    refreshColumn(y, x - 1);
                                    refreshColumn(y, x + 1);

                                    completed[y][x] = 1;
                                    completed[y][x - 1] = 1;
                                    completed[y][x + 1] = 1;
                                }
                            } catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {
                            }
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException | NullPointerException ignored) {}
                }
            }
            updateScore();
            return 1;
        }
        else {
            return 0;
        }
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
        int nueva_posicion_i;
        int nueva_posicion_j;
        for (int k = 0; k < 4 ; k++) {
            nueva_posicion_i = x + coordenada_i[k];
            nueva_posicion_j = y + coordenada_j[k];
            if((nueva_posicion_i >= 0 && nueva_posicion_i <length) && (nueva_posicion_j >= 0 && nueva_posicion_j <widht)) {
                adjoints.add(elements[nueva_posicion_i][nueva_posicion_j]);
            }

        }
        return  adjoints;
    }
    public boolean isUp(int x ,int y,Element e){
        try {
            return elements[x - 1][y].equalss(e);
        }
        catch (ArrayIndexOutOfBoundsException  | NullPointerException r){
            return false;
        }
    }
    public boolean isDown(int x,int y,Element e){
        try{
            return elements[x+1][y].equalss(e);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException r){
            return false;
        }
    }
    public boolean isRight(int x , int y,Element e){
        try{
            return elements[x][y+1].equalss(e);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException r){
                return false;
        }
    }
    public boolean isLeft(int x, int y,Element e){
        try{
            return  elements[x][y-1].equalss(e);
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException r){
            return false;
        }
    }


}

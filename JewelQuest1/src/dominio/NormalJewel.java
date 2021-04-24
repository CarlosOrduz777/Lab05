package dominio;

public class NormalJewel implements Element{
    private String name;
    private int points = 0;


    public NormalJewel(int opt){
        if (opt == 0){
            name = BLUE;
            points = 1;
        }
        else if (opt == 1){
            name = GREEN;
            points = 3;
        }
        else if (opt == 2){
            name = COIN;
            points = 5;
        }
        else if (opt == 3){
            name = SKULL;
            points = 10;
        }
        else if (opt == 4) {
            name = SKULL_0;
            points = 15;
        }
        /*
        else {
            throw fuera de rango
        }
         */
    }


    public String getName(){
        return name;
    }

    @Override
    public boolean equalss(Element e){
        return this.getName() == e.getName();
    }

    public int getPoints(){
        return points;
    }

}

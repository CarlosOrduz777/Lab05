package pruebas;

import aplicacion.*;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;

public class JewelQuestTest {
    JewelQuest jq;

    @Test
    public void shouldCreate(){
        jq = new JewelQuest();
        assertTrue(jq.getBoard() != null);
    }

    @Test
    public void shouldCreateWithSize(){
        jq = new JewelQuest(15,12);
        assertEquals(jq.getBoard().getWidth(),15);
        assertEquals(jq.getBoard().getLength(),12);
    }

    @Test
    public void shouldCreateWithElements() {
        String[][] elements;
        elements = new String[][]{{"Blue", "Green", "Green"},
                {"Coin", "Blue", "Blue"}, {"Blue", "Coin", "Green"}};
        jq = new JewelQuest(elements);
        assertArrayEquals(elements, jq.getBoard().getElements());
    }
    /*
    String[][] elements;
        elements = new String[][]{{"Blue", "Green", "Green"},
                {"Coin", "Blue", "Blue"}, {"Blue", "Coin", "Green"}};
        jq = new JewelQuest(elements);
     */

    @Test
    public void shouldMoveElement(){
        String[][] elements;
        elements = new String[][]{
                {"Blue", "Green", "Green"},
                {"Coin", "Blue", "Blue"},
                {"Blue", "Coin", "Green"}};
        jq = new JewelQuest(elements);
        assertTrue(jq.moveElement(2,2,'l'));
    }

    @Test
    public void shouldScore(){
        String[][] elements;
        elements = new String[][]{
                {"Blue", "Green", "Green"},
                {"Coin", "Blue", "Blue"},
                {"Blue", "Coin", "Green"}};
        jq = new JewelQuest(elements);
        jq.moveElement(2,2,'l');
        assertTrue(jq.getScore() > 0);
    }
    @Test
    public void shouldWin(){
        String[][] elements;
        elements = new String[][]{
                {"Blue", "Green", "Coin","Blue"},
                {"Green", "Blue", "Blue","Coin"},
                {"Blue", "Green", "Coin","Blue"}};
        jq = new JewelQuest(elements);
        jq.moveElement(2,2,'l');
        jq.moveElement(4,2,'l');
        assertTrue(jq.getStatus());
    }





}

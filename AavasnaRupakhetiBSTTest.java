package proj5;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * Unit testings for BST class.
 *
 * @author Aavasna Rupakheti
 * @version March 6th, 2023
 */
public class AavasnaRupakhetiBSTTest {

    @Test
    public void testInsertToBST1()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("10");

        assertEquals(tree.size(), 1);
        assertEquals("(  10  )",tree.toString());
    }

    @Test
    public void testInsertToBST2()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("10");
        tree.insert("78");
        tree.insert("51");
        assertEquals(tree.size(), 3);
        assertEquals("(  10  ((  51  )  78  ))",tree.toString());
    }

    @Test
    public void testInsertToBST3()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("10");
        tree.insert("47");
        tree.insert("32");
        tree.insert("92");
        tree.insert("5");
        assertEquals(tree.size(), 5);
        assertEquals("(  10  ((  32  )  47  ((  5  )  92  )))",tree.toString());
    }


    @Test
    public void testSearchEmptyBST()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertFalse(tree.search("1"));
    }

    @Test
    public void testSearchNonEmptyBSTCorrectElements()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("10");
        tree.insert("74");
        tree.insert("1");
        tree.insert("97");
        tree.insert("7");
        tree.insert("41");
        tree.insert("60");

        assertTrue(tree.search("10"));
        assertTrue(tree.search("60"));
        assertTrue(tree.search("41"));
        assertTrue(tree.search("7"));
        assertTrue(tree.search("97"));
        assertTrue(tree.search("1"));
        assertTrue(tree.search("74"));
    }

    @Test
    public void testSearchNonEmptyBSTFalseElements()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("10");
        tree.insert("74");
        tree.insert("1");
        tree.insert("97");
        tree.insert("7");
        tree.insert("41");
        tree.insert("60");

        assertFalse(tree.search("1101"));
        assertFalse(tree.search("6123"));
        assertFalse(tree.search("4123"));
        assertFalse(tree.search("714"));
        assertFalse(tree.search("a"));
        assertFalse(tree.search("b"));
        assertFalse(tree.search("z"));
    }

    @Test
    public void testSizeEmptyBST()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertEquals(tree.size(), 0);
    }

    @Test
    public void testSizeNonEmptyBST()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("1");
        tree.insert("7");
        tree.insert("20");

        assertEquals(tree.size(), 3);
        tree.insert("97");
        tree.insert("5");
        tree.insert("69");
        tree.insert("7");
        assertEquals(tree.size(), 7);
    }

    @Test
    public void testGetElementNonEmptyBSTCorrectElements()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("3");
        tree.insert("81");
        tree.insert("6");
        tree.insert("30");
        tree.insert("9");
        tree.insert("12");
        tree.insert("15");

        assertEquals(tree.getElement("3"), "3");
        assertEquals(tree.getElement("6"), "6");
        assertEquals(tree.getElement("12"), "12");
        assertEquals(tree.getElement("9"), "9");
        assertEquals(tree.getElement("30"), "30");
        assertEquals(tree.getElement("81"), "81");
        assertEquals(tree.getElement("15"), "15");
    }

    @Test
    public void testGetElementNonEmptyBSTWrongElements()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("3");
        tree.insert("81");
        tree.insert("6");
        tree.insert("30");
        tree.insert("9");
        tree.insert("12");
        tree.insert("15");

        assertNull(tree.getElement("13"));
        assertNull(tree.getElement("46"));
        assertNull(tree.getElement("80"));

    }

    @Test
    public void testGetElementEmptyBST()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        assertNull(tree.getElement("1"));
        assertNull(tree.getElement("89"));
    }

    @Test
    public void testDeleteNonEmptyTreeCorrectElement()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("3");
        tree.insert("47");
        tree.insert("21");
        tree.insert("97");
        tree.insert("7");
        tree.insert("40");
        tree.insert("6");

        assertEquals(tree.delete("3"), "3");
        assertEquals(tree.toString(), "((  21  )  40  (  47  (((  6  )  7  )  97  )))");
        assertEquals(tree.delete("40"), "40");
        assertEquals(tree.toString(), "((  21  )  47  (((  6  )  7  )  97  ))");
        assertEquals(tree.delete("21"), "21");
        assertEquals(tree.toString(), "(  47  (((  6  )  7  )  97  ))");
    }

    @Test
    public void testDeleteNonEmptyTreeWrongElement()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("10");
        tree.insert("47");
        tree.insert("32");
        tree.insert("92");
        tree.insert("5");
        assertEquals(tree.size(), 5);


        assertNull(tree.delete("11"));
        assertEquals("(  10  ((  32  )  47  ((  5  )  92  )))",tree.toString());
        assertNull(tree.delete("40"));
        assertEquals("(  10  ((  32  )  47  ((  5  )  92  )))",tree.toString());
        assertNull(tree.delete("20"));
        assertEquals("(  10  ((  32  )  47  ((  5  )  92  )))",tree.toString());
    }

    @Test
    public void testDeleteEmptyTree()
    {
        BinarySearchTree<String> tree = new BinarySearchTree<>();


        assertNull(tree.delete("1"));
        assertEquals(tree.toString(), "");
        assertNull(tree.delete("89"));
        assertEquals(tree.toString(), "");
    }

}

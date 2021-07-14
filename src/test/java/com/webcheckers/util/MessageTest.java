package com.webcheckers.util;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The testing suite for Message
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("Util")
public class MessageTest
{
    //Component under Test
    Message CuT;

    /**
     * Test for creating an error message
     */
    @Test
    public void test_error() {
        String eText = "test";
        Message.Type eType = Message.Type.ERROR;

        CuT = Message.error("test");

        String aText = CuT.getText();
        Message.Type aType = Message.Type.ERROR;

        assertEquals(eText, aText);
        assertSame(aType, eType);
    }

    /**
     * Test for creating an info message
     */
    @Test
    public void test_info() {
        String eText = "test";
        Message.Type eType = Message.Type.INFO;

        CuT = Message.info("test");

        String aText = CuT.getText();
        Message.Type aType = Message.Type.INFO;

        assertEquals(eText, aText);
        assertSame(aType, eType);
    }

    /**
     * Test for isSuccessful when true
     */
    @Test
    public void test_isSuccessful_true() {
        CuT = Message.info("test");

        boolean result = CuT.isSuccessful();

        assertTrue(result);
    }

    /**
     * Test for isSuccessful when false
     */
    @Test
    public void test_isSuccessful_false() {
        CuT = Message.error("test");

        boolean result = CuT.isSuccessful();

        assertFalse(result);
    }

    /**
     * Test for toString
     */
    @Test
    public void test_toString() {
        String expected = "{Msg INFO 'hi'}";
        CuT = Message.info("hi");

        String actual = CuT.toString();

        assertEquals(expected, actual);
    }

    /**
     * Test for getTest
     */
    @Test
    public void test_getText() {
        CuT = Message.info("hi");
        String expected = "hi";

        String actual = CuT.getText();

        assertEquals(expected, actual);
    }

    /**
     * Test for getType
     */
    @Test
    public void test_getType() {
        CuT = Message.info("hi");
        Message.Type expected = Message.Type.INFO;

        Message.Type actual = CuT.getType();

        assertSame(expected, actual);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chsoft;

import com.chsoft.Triangle;
import com.chsoft.TriangleException;
import com.chsoft.TriangleType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ajas
 */
public class TriangleTest {
    
    public TriangleTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of triangle method, of class Main.
     */
    @Test
    public void testTriangle() {
        System.out.println("TRIANGLES");
        
        Triangle fig = new Triangle();
        assertEquals(TriangleType.EQUILATERAL, fig.triangle(2, 2, 2));
        assertEquals(TriangleType.ISOSCELES, fig.triangle(2, 3, 2));
        assertEquals(TriangleType.SCALENE, fig.triangle(2, 4, 5));
        
        assertThrows(TriangleException.class, () -> fig.triangle(1, 2, 0));

    }
    
}

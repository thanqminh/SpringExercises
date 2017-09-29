package com.example.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RootControllerTest {
	private RootController controller;
	
    @Before
    public void setUp() {
        controller = new RootController();
    }

    @Test
    public void testRedirectLoginPage() {
        String view = controller.index();
        assertEquals("redirect:/login",view);
    }
    
}

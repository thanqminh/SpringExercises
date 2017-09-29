package com.example.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;

import com.example.DemoApplication;
import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WithMockUser
@WebMvcTest(DemoApplication.class)
@ComponentScan("com.example.*")
@WebAppConfiguration
public class BookControllerTest {
	
	@Inject
	private BookController controller;
	
	@Mock
	private Model model;	
	
	@Test
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	public void testIndex() {
    		String view = controller.index(model);
        assertEquals("book/index",view);
	}
	
	@Test
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	public void testShow() {
    		String view = controller.show(model,1);
        assertEquals("book/show",view);
	}
}



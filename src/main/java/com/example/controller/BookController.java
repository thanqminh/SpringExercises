package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.model.Book;
import com.example.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	private static final String MSG_SUCESS_INSERT = "Book inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Book successfully changed.";
	private static final String MSG_SUCESS_DELETE = "Deleted Book successfully.";
	private static final String MSG_ERROR = "Error.";

	@Autowired
	private BookService bookService;

	@GetMapping
	public String index(Model model) {
		List<Book> all = bookService.findAll();
		model.addAttribute("listBook", all);
		return "book/index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if (id != null) {
			Book book = bookService.findOne(id);
			model.addAttribute("book", book);
		}
		return "book/show";
	}

	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Book entity) {
		model.addAttribute("book", entity);
		return "book/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Book entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Book book = null;
		try {
			book = bookService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/books/" + book.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if (id != null) {
				Book entity = bookService.findOne(id);
				model.addAttribute("book", entity);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "book/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Book entity, BindingResult result, RedirectAttributes redirectAttributes) {
		Book book = null;
		try {
			book = bookService.save(entity);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		return "redirect:/books/" + book.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if (id != null) {
				Book entity = bookService.findOne(id);
				bookService.delete(entity);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/books/index";
	}

}

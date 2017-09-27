package com.example.model;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.String; 


@Entity
@Table(name = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

	public void setTitle(String title) {this.title = title;}
	public String getTitle() {return title;}
	public void setAuthor(String author) {this.author = author;}
	public String getAuthor() {return author;}
	public void setDescription(String description) {this.description = description;}
	public String getDescription() {return description;}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
}
package it.epicode.be.library.common.util.dto;

import java.util.List;

public class BookDto {

	private String title;
	private int publicationYear;
	private double price;
	private List<AuthorDto> authors;
	private List<CategoryDto> categories;
}

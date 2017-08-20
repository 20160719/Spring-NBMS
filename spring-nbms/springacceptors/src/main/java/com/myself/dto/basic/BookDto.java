package com.myself.dto.basic;

public class BookDto extends BasicDto {
	
	private String bookTypeId;
	
	private String bookName;
	
	private String bookAuthor;
	
	private String bookImage;
	
	private Float bookPrice;
	
	private Integer bookNum;

	public String getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(String bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	public Float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Integer getBookNum() {
		return bookNum;
	}

	public void setBookNum(Integer bookNum) {
		this.bookNum = bookNum;
	}

}

package com.myself.persistences.entity.basic;

import java.sql.Timestamp;

import com.myself.persistences.entity.TargetHis;

public class Book extends TargetHis {

	private static final long serialVersionUID = 1L;
	// 图书类型ID
	private String bookTypeId;
	// 图书ID
	private String bookId;
	// 图书名称
	private String bookName;
	// 图书作者
	private String bookAuthor;
	// 图书图片
	private String bookImage;
	// 图书出版时间
	private Timestamp bookPubTime;
	// 图书标价
	private Float bookPrice;
	// 图书数量
	private String bookNum;
	// 图书注释
	private String bookRemark;

	public String getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(String bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
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

	public Timestamp getBookPubTime() {
		return bookPubTime;
	}

	public void setBookPubTime(Timestamp bookPubTime) {
		this.bookPubTime = bookPubTime;
	}

	public Float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	public String getBookNum() {
		return bookNum;
	}

	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}

	public String getBookRemark() {
		return bookRemark;
	}

	public void setBookRemark(String bookRemark) {
		this.bookRemark = bookRemark;
	}

	@Override
	public String toString() {
		return String.format(
				"Book [bookTypeId=%s, bookId=%s, bookName=%s, bookAuthor=%s, bookImage=%s, bookPubTime=%s, bookPrice=%s, bookNum=%s, bookRemark=%s]",
				bookTypeId, bookId, bookName, bookAuthor, bookImage, bookPubTime, bookPrice, bookNum, bookRemark);
	}

	
}

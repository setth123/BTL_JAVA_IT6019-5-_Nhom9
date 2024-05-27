package backend.controllers;

import backend.utils.ReadData;

import java.time.LocalDate;
import java.util.List;

import backend.models.Book;
import backend.models.BorrowSlip;

public class Statics {
	public static int books() {
		return ReadData.readBook("/DemoDB/Book.txt").size();
		
	}
	public static int users() {
		return ReadData.readAccount("/DemoDB/user-account.txt").size();
	}
	public static int violations() {
		return ReadData.readAccount("/DemoDB/Violation.txt").size();
	}
	public static int borrowingBook() {
		List<Book> books=ReadData.readBook("/DemoDB/Book.txt");
		int count=0;
		for(Book b: books) {
			if(b.getBorrow()==true) {
				count++;
			}
		}
		return count;
	}
	public static int expiredBook() {
		List<BorrowSlip> bs=ReadData.readBorrowSlip("/DemoDB/borrow-slip.txt");
		int count=0;
		Book b;
		for(BorrowSlip s: bs) {
			b=Book.getBookByTitle(s.getMaSach());
			if(s.getNgayTra().isBefore(LocalDate.now())&&b.getBorrow()) {
				count++;
			}
		}
		return count;
	}
}

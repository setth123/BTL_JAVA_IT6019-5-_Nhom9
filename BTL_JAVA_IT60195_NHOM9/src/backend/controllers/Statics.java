package backend.controllers;

import backend.utils.ReadData;

import java.util.List;
import backend.models.BorrowSlip;

public class Statics {
	public static int books() {
		return ReadData.readBook("/DemoDB/Book.txt").size();
		
	}
	public static int users() {
		return ReadData.readAccount("/DemoDB/user-account.txt").size();
	}
	public static int violations() {
		return ReadData.readViolation("/DemoDB/Violation.txt").size();
	}
	public static int borrowingBook() {
		List<BorrowSlip> bs=ReadData.readBorrowSlip("/DemoDB/borrow-slip.txt");
		int count=0;
		for(BorrowSlip s: bs) {
			System.out.println(s.getTrangThai());
			if(s.getTrangThai().equals("Approved")||s.getTrangThai().equals("Expired")||s.getTrangThai().equals("Lost")) {
				count++;
			}
		}
		return count;
	}
	public static int expiredBook() {
		List<BorrowSlip> bs=ReadData.readBorrowSlip("/DemoDB/borrow-slip.txt");
		int count=0;
		for(BorrowSlip s: bs) {
			System.out.println(s.getTrangThai());
			if(s.getTrangThai().equals("Expired")) {
				count++;
			}
		}
		return count;
	}
}

package backend.utils;

import java.util.ArrayList;
import java.util.List;

import backend.models.Account;
import backend.models.Book;
import backend.models.BorrowSlip;
import backend.models.Violation;

public class SearchBE {
	static List<Book> books=new ArrayList<>();
	static List<Account> accounts=new ArrayList<>();
	static List<BorrowSlip> bs=new ArrayList<>(); 
	static List<Violation> violations=new ArrayList<>();
	
	public static List<Book> findB(String keyword){
		books=ReadData.readBook("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt");
		ArrayList<Book> result=new ArrayList<>();
		for(Book b : books) {
			if(b.getTenSach().startsWith(keyword)) {
				result.add(b);
			}
		}
		return result;
	}
	public static List<Account> findA(String keyword){
		accounts=ReadData.readAccount("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/user-account.txt");
		ArrayList<Account> result=new ArrayList<>();
		for(Account a : accounts) {
			if(a.getTenDangNhap().startsWith(keyword)) {
				result.add(a);
			}
		}
		return result;
	}
	public static boolean findV(String maPhieu,String maUser) {
		bs=ReadData.readBorrowSlip("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/user-account.txt");
		for(BorrowSlip b : bs) {
			if(b.getMaPhieuMuon().equals(maPhieu)&&b.getMaTaiKhoan().equals(maUser)) {
				return true;
			}
		}
		return false;
	}
	public static int countV(String maTaiKhoan) {
		violations=ReadData.readViolation("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/violation.txt");
		int count=0;
		for(Violation v : violations) {
			if(v.getMaTaiKhoan().equals(maTaiKhoan))count++;
		}
		return count;
	}
}

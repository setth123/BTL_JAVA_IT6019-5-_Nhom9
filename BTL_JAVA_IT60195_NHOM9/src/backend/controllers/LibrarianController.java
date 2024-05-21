package backend.controllers;
import backend.models.Book; 
import backend.utils.ReadData;
import backend.utils.WriteData;
import backend.models.Librarian;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class LibrarianController {
	    public static List<Book> books;	    
	    public static List<Librarian> librarians;
	    
		public static Librarian login(String l_accountName,String l_password) {
		librarians=ReadData.readLibrarian("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Librarian.txt");
		for(Librarian l : librarians) {
			if(l_accountName.equals(l.getAccountName()) && l_password.equals(l.getPassword())) {
				return l;
			}
		}
		return null;
	    }
		
		public static boolean changePassword(Librarian l,String n_password) {
			librarians=ReadData.readLibrarian("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Librarian.txt");
			for(Librarian ls : librarians) {
				if(l.getAccountName().equals(ls.getAccountName())) {
					ls.setPassword(n_password);
				}
			}
			WriteData.writeLibrarian(librarians,"/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Librarian.txt");
			return true;
		}
		
	    public static boolean addBook(String maSach, String tenSach,String nxb, int nam,int thang,int ngay, String theLoai, int soLuong, double gia) {
	     String nph=String.format("%02d/%02d/%04d", ngay,thang,nam);
	     books=ReadData.readBook("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt");
		 boolean bookFound = false;
	        for (Book book : books) {
	            if (book.getMaSach().equals(maSach)) {
	                bookFound = true;
	                book.setSl(book.getSl() + soLuong); 
	                break;
	            }
	        }
	        if (!bookFound) {
				books.add(new Book(maSach, tenSach, nxb, nph, theLoai, soLuong, gia,false));
	        }
	    WriteData.writeBook(books,"/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt");
	    return true;
	    }
		public static boolean delBook(String maSach) {
			 books=ReadData.readBook("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt");
			 boolean bookFound = false;
			 Iterator<Book> iterator = books.iterator();
			    while (iterator.hasNext()) {
			        Book book = iterator.next();
			        if (book.getMaSach().equals(maSach)) {
			            iterator.remove();
			            bookFound = true;
			        }
			    }
			 if(bookFound) {
				 WriteData.writeBook(books,"/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt");
			 }
		     return false;
		}
		public static boolean editBook(String maSach,String n_masach,String n_tenSach, String n_NXB, int year,int month,int day, String n_theLoai,int n_sl, double n_gia) {
			String nph=String.format("%02d/%02d/%04d", day,month,year);
			books=ReadData.readBook("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt");
			boolean bookFound = false;
			for (Book book : books) {
		        if (book.getMaSach().equals(maSach)) {
		            book.setMaSach(n_masach);
		            book.setTenSach(n_tenSach);
		            book.setNXB(n_NXB);
		            book.setNph(nph);
		            book.setTheLoai(n_theLoai);
		            book.setSl(n_sl);
		            book.setGia(n_gia);
		            bookFound = true;
		            break; // Break the loop once the book is found and updated
		        }
			}
		    if (bookFound) {
		    	WriteData.writeBook(books, "/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt");
		    	return true;
		    }
			return false;
	}
		public static ArrayList<Book> find(String keyword){
			books=ReadData.readBook("/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Book.txt");
			ArrayList<Book> result=new ArrayList<>();
			for(Book b : books) {
				if(b.getTenSach().startsWith(keyword)) {
					System.out.println(b.getTenSach().startsWith(keyword));
					result.add(b);
				}
			}
			return result;
		}
}		

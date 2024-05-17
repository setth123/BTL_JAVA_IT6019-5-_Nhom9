package backend.controllers;
import backend.models.Book; 
import backend.models.Librarian;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
public class LibrarianController {
	    public static List<Book> books = new ArrayList<>();
	    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    
		public Librarian login(String l_accountName,String l_password) {
		//load database
		 try (BufferedReader br = new BufferedReader(new FileReader("Librarian.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                //authentication
	                    String accountName = parts[0];
	                    String password = parts[1];
	                    String sdt=parts[2];
	                    if(accountName.equals(l_accountName)&&password.equals(l_password)) {
	                    Librarian l=new Librarian(accountName,password,sdt);
	                    return l;
	                   }
	                
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	        }
		return null;
	}
	    public boolean addBook(String maSach, String tenSach, String tieuDe, String NXB, Date nph, String theLoai, int soLuong, double gia) {
		try (BufferedReader br = new BufferedReader(new FileReader("Book.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                Book book = new Book(parts[0], parts[1], parts[2], parts[3], dateFormat.parse(parts[4]), parts[5], Integer.parseInt(parts[6]), Double.parseDouble(parts[7]));
                books.add(book);
                   } 
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
           catch(ParseException e) {
        	   System.err.println("Error parsing data: "+e.getMessage());
           }
		 boolean bookFound = false;
	        for (Book book : books) {
	            if (book.getMaSach().equals(maSach)) {
	                bookFound = true;
	                book.setSl(book.getSl() + soLuong); 
	                break;
	            }
	        }
	        if (!bookFound) {
	            books.add(new Book(maSach, tenSach, tieuDe, NXB, nph, theLoai, soLuong, gia));
	        }

	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Book.txt"))) {
	            for (Book book : books) {
	                bw.write(book.toString());
	                bw.newLine();
	                return true;
	            }
	        } catch (IOException e) {
	            System.err.println("Error writing file: " + e.getMessage());
	        }
	        return false;
	    }
		public boolean delBook(String maSach) {
			try (BufferedReader br = new BufferedReader(new FileReader("Book.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                Book book = new Book(parts[0], parts[1], parts[2], parts[3], dateFormat.parse(parts[4]), parts[5], Integer.parseInt(parts[6]), Double.parseDouble(parts[7]));
	                books.add(book);
	                   } 
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	        }
	           catch(ParseException e) {
	        	   System.err.println("Error parsing data: "+e.getMessage());
	           }
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
		        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Book.txt"))) {
		            for (Book book : books) {
		                bw.write(book.toString());
		                bw.newLine();
		            }
		            return true;
		        } catch (IOException e) {
		            System.err.println("Error writing file: " + e.getMessage());
		        }
		        }
		        return false;
		}
		public boolean changeBook(String maSach,String n_masach,String n_tenSach, String n_tieuDe, String n_NXB, Date n_nph, String n_theLoai, double n_gia) {
			try (BufferedReader br = new BufferedReader(new FileReader("Book.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split(",");
	                Book book = new Book(parts[0], parts[1], parts[2], parts[3], dateFormat.parse(parts[4]), parts[5], Integer.parseInt(parts[6]), Double.parseDouble(parts[7]));
	                books.add(book);
	                   } 
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	        }
	         catch(ParseException e) {
	        	   System.err.println("Error parsing data: "+e.getMessage());
	        }
			boolean bookFound = false;
			for (Book book : books) {
		        if (book.getMaSach().equals(maSach)) {
		            book.setMaSach(n_masach);
		            book.setTieuDe(n_tieuDe);
		            book.setNXB(n_NXB);
		            book.setNph(n_nph);
		            book.setTheLoai(n_theLoai);
		            book.setGia(n_gia);
		            bookFound = true;
		            break; // Break the loop once the book is found and updated
		        }
		    if (bookFound) {
		            try (BufferedWriter bw = new BufferedWriter(new FileWriter("Book.txt"))) {
		                for (Book b : books) {
		                    bw.write(b.toString());
		                    bw.newLine();
		                }
		                return true;
		            } catch (IOException e) {
		                System.err.println("Error writing file: " + e.getMessage());
		            }
		        }
			}
			return false;
	}
}		

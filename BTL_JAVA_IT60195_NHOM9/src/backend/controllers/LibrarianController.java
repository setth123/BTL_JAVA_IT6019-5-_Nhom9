package backend.controllers;
//import backend.models.Book;
import backend.models.Librarian;
import java.io.*;
public class LibrarianController {
	public Librarian login(String l_accountName,String l_password) {
		//load database
		 try (BufferedReader reader = new BufferedReader(new FileReader("Librarian.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",");
	                //authentication
	                    String accountName = parts[0];
	                    String password = parts[1];
	                    if(accountName.equals(l_accountName)&&password.equals(l_password)) {
	                    Librarian l=new Librarian(accountName,password);
	                    return l;
	                   }
	                
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	        }
		return null;
	}
}

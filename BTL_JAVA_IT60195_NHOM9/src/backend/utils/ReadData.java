package backend.utils;

import backend.models.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadData {
	
	//convert relative path to absolute path.Ex: fileName="/BTL_JAVA_IT60195_NHOM9/src/backend/DemoDB/Librarian.txt"
	public static String f_path(String fileName) {
		String projectDirectory = System.getProperty("user.dir");
        File file=new File(projectDirectory,fileName);
        return file.getAbsolutePath();
	}
	
    public static List<Account> readAccount(String fileName) {
        List<Account> accounts = new ArrayList<>();
        String path=f_path(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] data = line.split("\\|");
                Account account = new Account(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim());
                accounts.add(account);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return accounts;
    }

    public static List<Book> readBook(String fileName) {
    	
        List<Book> books = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String path=f_path(fileName);

        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                Date nph = dateFormat.parse(data[4].trim());
                Book book = new Book(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), nph, data[5].trim(), Integer.parseInt(data[6].trim()), Double.parseDouble(data[7].trim()));
                books.add(book);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace(System.err);
        }

        return books;
    }

    public static List<BorrowSlip> readBorrowSlip(String fileName) {
        List<BorrowSlip> slips = new ArrayList<>();
        
        String path=f_path(fileName);

        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                BorrowSlip slip = new BorrowSlip(data[0].trim(), LocalDate.parse(data[1].trim()), data[2].trim());
                slips.add(slip);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return slips;
    }

    public static List<BorrowSlipDetail> readBorrowSlipDetail(String fileName) {
        List<BorrowSlipDetail> details = new ArrayList<>();
        
        String path=f_path(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                BorrowSlipDetail detail = new BorrowSlipDetail(data[0].trim(), data[1].trim(),
                        LocalDate.parse(data[2].trim()), LocalDate.parse(data[3].trim()), Integer.parseInt(data[4].trim()));
                details.add(detail);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return details;
    }

    public static List<Category> readCategory(String fileName) {
        List<Category> categories = new ArrayList<>();
        String path=f_path(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                Category category = new Category(data[0].trim(), data[1].trim());
                categories.add(category);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return categories;
    }

    public static List<Violation> readViolation(String fileName) {
        List<Violation> violations = new ArrayList<>();
        String path=f_path(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                Violation violation = new Violation(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(),
                        Integer.parseInt(data[4].trim()), Double.parseDouble(data[5].trim()));
                violations.add(violation);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return violations;
    }
    
    public static List<Librarian> readLibrarian(String fileName) {
        List<Librarian> librarians = new ArrayList<>();
        String path=f_path(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                Librarian librarian = new Librarian(data[0].trim(), data[1].trim(), data[2].trim());
                librarians.add(librarian);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return librarians;
    }
}

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
import java.util.Locale;

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
//                Account account = new Account(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim());
                boolean isActive;
                if(data[6].equals("true"))isActive=true;
                else isActive=false;
                Account account = new Account(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(),isActive);
                accounts.add(account);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return accounts;
    }

    public static List<Book> readBook(String fileName) {
    	
        List<Book> books = new ArrayList<>();
        
        String path=f_path(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");
                Boolean isBorrow;
                if(data[7].trim().equals("true"))isBorrow=true;
                else isBorrow=false;
                Book book = new Book(data[0].trim(), data[1].trim(), data[2].trim(), LocalDate.parse(data[3].trim()), data[4].trim(), Integer.parseInt(data[5].trim()), Double.parseDouble(data[6].trim()),isBorrow);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return books;
    }

    public static List<BorrowSlip> readBorrowSlip() {
        List<BorrowSlip> borrowSlips = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f_path("src\\backend\\DemoDB\\borrow-slip.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    String maPhieuMuon = parts[0].trim();
                    LocalDate ngayMuon = LocalDate.parse(parts[1].trim());
                    LocalDate ngayTra = LocalDate.parse(parts[2].trim());
                    String maTaiKhoan = parts[3].trim();
                    boolean trangThai = parts[4].trim().equalsIgnoreCase("Active");
                    String tenSach = parts[5].trim();
                    Book sachMuon = getBookByTitle(tenSach);
                    if (sachMuon != null) {
                        borrowSlips.add(new BorrowSlip(maPhieuMuon, ngayMuon, maTaiKhoan, sachMuon, trangThai));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return borrowSlips;
    }

    private static Book getBookByTitle(String tenSach) {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\backend\\DemoDB\\Book.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split("\\|");
                if (parts.length >= 8 && parts[1].trim().equals(tenSach)) {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String author = parts[2].trim();
                    String releaseDate = parts[3].trim();
                    String category = parts[4].trim();
                    int quantity = Integer.parseInt(parts[5].trim());
                    double price = Double.parseDouble(parts[6].trim());
                    return new Book(code, name, author, LocalDate.parse(releaseDate), category, quantity, price);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return null;
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
        String path = f_path(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.substring(1, line.length() - 1);
                String[] data = line.split("\\|");
                if (data.length == 7) {
                    String maViPham = data[1].trim();
                    String maPhieuMuon = data[2].trim();
                    String maTaiKhoan = data[3].trim();
                    String lyDo = data[4].trim();
                    int soNgayViPham = Integer.parseInt(data[5].trim());
                    double soTienPhat = Double.parseDouble(data[6].trim());

                    Violation violation = new Violation(maViPham, maPhieuMuon, maTaiKhoan, lyDo, soNgayViPham, soTienPhat);
                    violations.add(violation);
                }
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
                Librarian librarian = new Librarian(data[0].trim(), data[1].trim());
                librarians.add(librarian);
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return librarians;
    }
}

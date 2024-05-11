package backend.utils;

import backend.models.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteData {
    public static void writeAccount(List<Account> accounts, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Account account : accounts) {
                bw.write(account.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void writeBook(List<Book> books, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Book book : books) {
                bw.write(book.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void writeBorrowSlip(List<BorrowSlip> slips, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (BorrowSlip slip : slips) {
                bw.write(slip.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void writeBorrowSlipDetail(List<BorrowSlipDetail> details, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (BorrowSlipDetail detail : details) {
                bw.write(detail.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void writeCategory(List<Category> categories, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Category category : categories) {
                bw.write(category.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static void writeViolation(List<Violation> violations, String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Violation violation : violations) {
                bw.write(violation.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }
}


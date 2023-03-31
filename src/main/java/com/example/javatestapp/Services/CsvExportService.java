package com.example.javatestapp.Services;

import com.example.javatestapp.Models.Book;
import com.example.javatestapp.Models.Token;
import com.example.javatestapp.Models.User;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class CsvExportService {
    public static void exportTokens (HttpServletResponse servletResponse, List<Token> tokens, String name) {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"" + name +".csv\"");

        try (CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Token","User UID");
            for (Token token : tokens) {
                csvPrinter.printRecord(
                        token.getId(),
                        token.getToken(),
                        token.getUid()
                );
            }
        } catch (IOException e) { System.out.println(e); }
    }

    public static void exportUsers (HttpServletResponse servletResponse, List<User> users, String name) {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"" + name +".csv\"");

        try (CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Name", "Surname","User UID","Email","Login","Password");
            for (User user : users) {
                csvPrinter.printRecord(
                        user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getUid(),
                        user.getEmail(),
                        user.getLogin(),
                        user.getPassword()
                );
            }
        } catch (IOException e) { System.out.println(e); }
    }

    public static void exportBooks (HttpServletResponse servletResponse, List<Book> books, String name) {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\"" + name +".csv\"");

        try (CSVPrinter csvPrinter = new CSVPrinter(servletResponse.getWriter(), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Name", "ISBN","Author UID","Last edit date");
            for (Book book : books) {
                csvPrinter.printRecord(
                        book.getId(),
                        book.getName(),
                        book.getIsbn(),
                        book.getAuthorUid(),
                        book.getLastEditDate()
                );
            }
        } catch (IOException e) { System.out.println(e); }
    }
}

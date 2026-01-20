package student.app;

import java.io.*;
import java.util.*;

public class FileIO {
    private static final String FILE_PATH = "Students.txt";

    public static void load(Student[] students) {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (Scanner sc = new Scanner(file)) {
            int i = 0;
            while (sc.hasNextLine() && i < students.length) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(";");
                if (data.length == 3) {
                    students[i++] = new Student(data[0], data[1], Double.parseDouble(data[2]));
                }
            }
        } catch (Exception e) {
            System.out.println("Load failed: " + e.getMessage());
        }
    }

    public static void save(Student[] students) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Student s : students) {
                if (s != null) pw.println(s.toFileString());
            }
        } catch (Exception e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }
}
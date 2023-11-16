import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class ClassRoster {
    private ArrayList<Student> students;

    public ClassRoster() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student " + student.getName() + " added.");
    }

    public void removeStudent(String studentName) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getName().equals(studentName)) {
                iterator.remove();
                System.out.println("Student " + studentName + " removed.");
                return;
            }
        }
        System.out.println("Student " + studentName + " not found.");
    }

    public void inputGrades(Scanner scanner) {
        System.out.print("Enter student name: ");
        scanner.nextLine(); // Consume the newline character
        String studentName = scanner.nextLine();

        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                try {
                    System.out.print("Enter grade for " + studentName + ": ");
                    int grade = scanner.nextInt();
                    student.addGrade(grade);
                    System.out.println("Grade added for " + studentName);
                    return;
                } catch (java.util.InputMismatchException e) {
                    scanner.nextLine(); // Consume the invalid input
                    logError("Invalid input. Please enter a valid integer for the grade.", e);
                    return;
                }
            }
        }

        System.out.println("Student " + studentName + " not found.");
    }

    public void displayClassAverage() {
        if (students.isEmpty()) {
            System.out.println("No students in the class.");
            return;
        }

        double classSum = 0;
        for (Student student : students) {
            classSum += student.getAverageGrade();
        }

        double classAverage = classSum / students.size();
        System.out.println("Class Average: " + classAverage);
    }

    public void displayStudentGrades() {
        if (students.isEmpty()) {
            System.out.println("No students in the class.");
            return;
        }

        System.out.println("Student Grades:");
        for (Student student : students) {
            System.out.println(student.getName() + ": " + student.getAverageGrade());
        }
    }

    public void displayStudentDetails(Scanner scanner) {
        System.out.print("Enter student name: ");
        scanner.nextLine(); // Consume the newline character
        String studentName = scanner.nextLine();

        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                System.out.println("Student Details for " + studentName + ":");
                System.out.println("Average Grade: " + student.getAverageGrade());
                System.out.println("Individual Grades: " + student.getGrades());
                return;
            }
        }

        System.out.println("Student " + studentName + " not found.");
    }

    public void modifyGrade(Scanner scanner) {
        System.out.print("Enter student name: ");
        scanner.nextLine(); // Consume the newline character
        String studentName = scanner.nextLine();

        for (Student student : students) {
            if (student.getName().equals(studentName)) {
                System.out.print("Enter the index of the grade to modify (starting from 1): ");
                int index = scanner.nextInt();
                if (index >= 1 && index <= student.getGrades().size()) {
                    System.out.print("Enter the new grade: ");
                    int newGrade = scanner.nextInt();
                    student.getGrades().set(index - 1, newGrade);
                    System.out.println("Grade modified for " + studentName);
                    return;
                } else {
                    System.out.println("Invalid index.");
                }
            }
        }

        System.out.println("Student " + studentName + " not found.");
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
            System.out.println("Data saved to file: " + fileName);
        } catch (IOException e) {
            logError("Error saving data to file: " + fileName, e);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (ArrayList<Student>) ois.readObject();
            System.out.println("Data loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            logError("Error loading data from file: " + fileName, e);
        }
    }

    // Helper method for logging errors
    void logError(String message, Exception e) {
        Logger logger = Logger.getLogger(ClassRoster.class.getName());

        try {
            FileHandler fileHandler = new FileHandler("error.log", true);
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.severe(message);

            // Check if the exception message is not null before logging
            if (e != null && e.getMessage() != null) {
                logger.severe(e.getMessage());
            } else {
                logger.severe("Exception message is null.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
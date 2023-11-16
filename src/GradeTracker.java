import java.util.Scanner;

public class GradeTracker {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        ClassRoster classRoster = new ClassRoster();
        String fileName = "class_data.txt";

        while (true) {
            System.out.println("""
                    1. Add Student
                    2. Remove Student
                    3. Input Grades
                    4. Display Class Average
                    5. Display Student Grades
                    6. Display Student Details
                    7. Modify Grade
                    8. Save to File
                    9. Load from File
                    10. Exit""");
            System.out.print("Enter your choice: ");
            int choice;

            try {
                choice = userInput.nextInt();
            } catch (Exception e) {
                classRoster.logError("Invalid input. Please enter a number.", e);
                userInput.nextLine(); // Consume the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    addStudent(userInput, classRoster);
                    break;

                case 2:
                    removeStudent(userInput, classRoster);
                    break;

                case 3:
                    classRoster.inputGrades(userInput);
                    break;

                case 4:
                    classRoster.displayClassAverage();
                    break;

                case 5:
                    classRoster.displayStudentGrades();
                    break;

                case 6:
                    classRoster.displayStudentDetails(userInput);
                    break;

                case 7:
                    classRoster.modifyGrade(userInput);
                    break;

                case 8:
                    classRoster.saveToFile(fileName);
                    break;

                case 9:
                    classRoster.loadFromFile(fileName);
                    break;

                case 10:
                    System.out.println("Exiting program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void addStudent(Scanner userInput, ClassRoster classRoster) {
        System.out.print("Enter student name: ");
        userInput.nextLine(); // Consume the newline character
        String studentName = userInput.nextLine();
        Student newStudent = new Student(studentName);
        classRoster.addStudent(newStudent);
    }

    private static void removeStudent(Scanner userInput, ClassRoster classRoster) {
        System.out.print("Enter student name to remove: ");
        userInput.nextLine(); // Consume the newline character
        String studentToRemove = userInput.nextLine();
        classRoster.removeStudent(studentToRemove);
    }
}
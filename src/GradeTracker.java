import java.util.Scanner;

public class GradeTracker {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        ClassRoster classRoster = new ClassRoster();

        while (true) {
            System.out.println("""

                    1. Add Student
                    2. Remove Student
                    3. Display Class Average
                    4. Display Student Grades
                    5. Exit""");
            System.out.print("Enter your choice: ");
            int choice = userInput.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    userInput.nextLine(); // Consume the newline character
                    String studentName = userInput.nextLine();
                    Student newStudent = new Student(studentName);
                    classRoster.addStudent(newStudent);
                    break;

                case 2:
                    System.out.print("Enter student name to remove: ");
                    userInput.nextLine(); // Consume the newline character
                    String studentToRemove = userInput.nextLine();
                    classRoster.removeStudent(studentToRemove);
                    break;

                case 3:
                    classRoster.displayClassAverage();
                    break;

                case 4:
                    classRoster.displayStudentGrades();
                    break;

                case 5:
                    System.out.println("Exiting program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

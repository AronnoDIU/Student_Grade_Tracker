import java.util.ArrayList;
import java.util.Iterator;

class ClassRoster {
    private final ArrayList<Student> students;

    public ClassRoster() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
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
}
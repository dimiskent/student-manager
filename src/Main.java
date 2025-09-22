import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StudentUtils studentUtils = new StudentUtils();
        Input input = new Input();
        int choice;
        do {
            Student[] students = studentUtils.getStudents();
            System.out.println("0) Exit");
            int x = 1;
            for(Student student : students) {
                System.out.printf("%d) %s (Grade: %.1f/100)\n", x++, student.studentName, student.grade);
            }
            System.out.println(x + ") New Student");
            System.out.print("Enter choice: ");
            choice = (int) input.getNumber(0, (studentUtils.getStudentNumber() + 1));
            if(choice > studentUtils.getStudentNumber()) {
                studentUtils.createStudent();
            } else if(choice > 0) {
                studentUtils.manageStudent(students[choice-1]);
            }
        } while (choice != 0);
        input.close();
    }
}

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class StudentUtils {
    private File folder = new File("students");
    private File[] studentsFiles = folder.listFiles();
    public int getStudentNumber() {
        return studentsFiles.length;
    }
    public void saveStudent(String name, double grade) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("students/" + name + ".txt"));
        writer.write(Double.toString(grade));
        writer.close();
    }
    public Student createStudent() throws IOException {
        Input input = new Input();
        String name = input.getString();
        System.out.print("Enter a current grade for the new student!\nGrades are /100\n=> ");
        double num = input.getNumber(0, 100);
        Student myStudent = new Student(name, num);
        saveStudent(name, num);
        studentsFiles = folder.listFiles(); // reload
        return myStudent;
    }
    public Student[] getStudents() throws IOException {
        Student[] students = {};
        if(getStudentNumber() == 0) {
            students = new Student[]{
                    createStudent()
            };
            return students;
        }
        students = Arrays.copyOf(students, getStudentNumber());
        int x = 0;
        for(File studentFile : studentsFiles) {
            String name = studentFile.getName();
            name = name.substring(0, name.length()-4);
            Scanner scanner = new Scanner(studentFile);
            Student myStudent = new Student(name, Double.parseDouble(scanner.next()));
            students[x++] = myStudent;
            scanner.close();
        }
        return students;
    }
    private void deleteStudent(String name) {
        File student = new File("students/" + name + ".txt");
        if(student.exists()) {
            if(student.delete()) {
                System.out.println("Student deleted");
            }
        }
    }
    private void renameStudent(String name, String originalName) {
        File student = new File("students/" + originalName + ".txt");
        if(student.exists()) {
            if(student.renameTo(new File("students/" + name + ".txt"))) {
                System.out.println("Student renamed to " + name);
            }
        }
    }
    public void manageStudent(Student student) throws IOException {
        System.out.println("Options for " + student.studentName);
        System.out.println("0) Exit");
        System.out.println("1) Edit Grade");
        System.out.println("2) Rename Student");
        System.out.println("3) Delete Student");
        Input input = new Input();
        int choice = (int) input.getNumber(0, 3);
        switch (choice) {
            case 1:
                System.out.print("Enter a new grade (n/100): ");
                student.grade = input.getNumber(0, 100);
                saveStudent(student.studentName, student.grade);
                break;
            case 2:
                input.clear();
                String oldName = student.studentName;
                student.studentName = input.getString();
                renameStudent(student.studentName, oldName);
                break;
            case 3:
                deleteStudent(student.studentName);
                break;
            default:
                return;
        }
        studentsFiles = folder.listFiles(); // rebuild student list
    }
}

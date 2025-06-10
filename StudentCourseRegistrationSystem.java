import java.io.*;
import java.util.*;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    int enrolled;
    String schedule;

    Course(String code, String title, String desc, int cap, String schedule) {
        this.courseCode = code;
        this.title = title;
        this.description = desc;
        this.capacity = cap;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    boolean hasSlot() {
        return enrolled < capacity;
    }

    void enroll() {
        enrolled++;
    }

    void drop() {
        if (enrolled > 0) enrolled--;
    }

    @Override
    public String toString() {
        return courseCode + " - " + title + " (" + schedule + ") - Slots Left: " + (capacity - enrolled);
    }

    public String basicInfo() {
        return courseCode + " - " + title + " (" + schedule + ")";
    }
}

class Student {
    String studentId;
    String name;
    List<Course> registeredCourses;

    Student(String id, String name) {
        this.studentId = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerCourse(Course course) {
        registeredCourses.add(course);
        course.enroll();
        FileLogger.log(studentId, name, "REGISTERED", course);
    }

    void dropCourse(Course course) {
        registeredCourses.remove(course);
        course.drop();
        FileLogger.log(studentId, name, "DROPPED", course);
    }

    void viewRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }
        System.out.println("Registered Courses:");
        for (Course c : registeredCourses) {
            System.out.println(" - " + c.basicInfo());
        }
    }
}

class FileLogger {
    private static final String FILE_NAME = "student_courses.txt";

    public static void log(String studentId, String name, String action, Course course) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)))) {
            String log = String.format("%s | Student ID: %s | Name: %s | Course: %s",
                    action, studentId, name, course.courseCode);
            out.println(log);
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    public static void loadData(Map<String, Student> studentDB, Map<String, Course> courseDB) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse log line
                String[] parts = line.split(" \\| ");
                if (parts.length < 4) continue;

                String action = parts[0].trim();
                String studentId = parts[1].split(":")[1].trim();
                String name = parts[2].split(":")[1].trim();
                String courseCode = parts[3].split(":")[1].trim();

                // Get or create student
                Student student = studentDB.getOrDefault(studentId, new Student(studentId, name));
                studentDB.putIfAbsent(studentId, student);

                // Get course
                Course course = courseDB.get(courseCode);
                if (course == null) continue; // Course must exist

                if (action.equals("REGISTERED") && !student.registeredCourses.contains(course)) {
                    student.registeredCourses.add(course);
                    course.enroll();
                } else if (action.equals("DROPPED")) {
                    student.registeredCourses.remove(course);
                    course.drop();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

public class StudentCourseRegistrationSystem {
    static Map<String, Course> courseDB = new HashMap<>();
    static Map<String, Student> studentDB = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        addSampleCourses();
        FileLogger.loadData(studentDB, courseDB); // ✅ load from file

        System.out.println("Welcome to Student Course Registration System");

        while (true) {
            System.out.println("\n1. List Courses");
            System.out.println("2. Register Student to Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Student's Registered Courses");
            System.out.println("5. Register a New Student");
            System.out.println("6. Exit");
            System.out.print("Enter option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {
                case 1 -> listCourses();
                case 2 -> registerCourse();
                case 3 -> dropCourse();
                case 4 -> viewStudentCourses();
                case 5 -> registerNewStudent();
                case 6 -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void addSampleCourses() {
        courseDB.put("CS101", new Course("CS101", "Intro to CS", "Basics of CS", 3, "Mon 10AM"));
        courseDB.put("MA201", new Course("MA201", "Calculus", "Advanced Math", 2, "Tue 2PM"));
        courseDB.put("PH301", new Course("PH301", "Physics", "Mechanics", 2, "Wed 11AM"));
    }

    static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course c : courseDB.values()) {
            System.out.println(c);
        }
    }

    static void registerCourse() {
        System.out.print("Enter Student ID: ");
        String sid = scanner.nextLine();
        Student student = studentDB.get(sid);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        listCourses();
        System.out.print("Enter Course Code to Register: ");
        String code = scanner.nextLine();
        Course course = courseDB.get(code);

        if (course == null) {
            System.out.println("Course not found.");
        } else if (!course.hasSlot()) {
            System.out.println("Course is full.");
        } else if (student.registeredCourses.contains(course)) {
            System.out.println("Already registered in this course.");
        } else {
            student.registerCourse(course);
            System.out.println("Registered successfully.");
        }
    }

    static void dropCourse() {
        System.out.print("Enter Student ID: ");
        String sid = scanner.nextLine();
        Student student = studentDB.get(sid);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.viewRegisteredCourses();
        System.out.print("Enter Course Code to Drop: ");
        String code = scanner.nextLine();
        Course course = courseDB.get(code);

        if (course != null && student.registeredCourses.contains(course)) {
            student.dropCourse(course);
            System.out.println("Course dropped successfully.");
        } else {
            System.out.println("Course not registered or invalid code.");
        }
    }

    static void viewStudentCourses() {
        System.out.print("Enter Student ID: ");
        String sid = scanner.nextLine();
        Student student = studentDB.get(sid);

        if (student != null) {
            System.out.println("Student Name: " + student.name);
            student.viewRegisteredCourses();
        } else {
            System.out.println("Student not found.");
        }
    }

    static void registerNewStudent() {
        System.out.print("Enter New Student ID: ");
        String id = scanner.nextLine();
        if (studentDB.containsKey(id)) {
            System.out.println("Student ID already exists.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Student newStudent = new Student(id, name);
        studentDB.put(id, newStudent);
        System.out.println("Student registered successfully!");

        System.out.print("Register a course now? (yes/no): ");
        String answer = scanner.nextLine().toLowerCase();
        if (answer.equals("yes")) {
            registerCourseForStudent(newStudent);
        }
    }

    static void registerCourseForStudent(Student student) {
        listCourses();
        System.out.print("Enter Course Code to Register: ");
        String code = scanner.nextLine();
        Course course = courseDB.get(code);

        if (course == null) {
            System.out.println("Course not found.");
        } else if (!course.hasSlot()) {
            System.out.println("Course is full.");
        } else if (student.registeredCourses.contains(course)) {
            System.out.println("Already registered.");
        } else {
            student.registerCourse(course);
            System.out.println("Registered successfully.");
        }
    }
}

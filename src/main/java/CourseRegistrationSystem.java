import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CourseRegistrationSystem extends JFrame implements ActionListener {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private JComboBox<Student> studentComboBox;
    private JComboBox<Course> courseComboBox;
    private JTextArea courseDetailsArea;
    private JButton registerButton;
    private JButton dropButton;
    private JTextArea registeredCoursesArea;
    private JTextArea availableCoursesArea;
    private JTextArea allRegisteredCoursesArea;

    public CourseRegistrationSystem() {
        setTitle("Course Registration System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        courses = new ArrayList<>();
        students = new ArrayList<>();

        // Sample data
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30, "MWF 10-11AM"));
        courses.add(new Course("MATH201", "Calculus I", "Differentiation and integration", 40, "TTh 9-10:30AM"));
        courses.add(new Course("ENG101", "English Literature", "Study of English literature", 20, "MWF 11-12PM"));

        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
        students.add(new Student("S003", "Charlie"));

        studentComboBox = new JComboBox<>(students.toArray(new Student[0]));
        courseComboBox = new JComboBox<>(courses.toArray(new Course[0]));
        courseDetailsArea = new JTextArea(5, 20);
        registerButton = new JButton("Register");
        dropButton = new JButton("Drop");
        registeredCoursesArea = new JTextArea(10, 30);
        availableCoursesArea = new JTextArea(10, 30);
        allRegisteredCoursesArea = new JTextArea(10, 30);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Student:"));
        topPanel.add(studentComboBox);
        topPanel.add(new JLabel("Select Course:"));
        topPanel.add(courseComboBox);

        JPanel middlePanel = new JPanel();
        middlePanel.add(new JLabel("Course Details:"));
        middlePanel.add(new JScrollPane(courseDetailsArea));

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(registerButton);
        bottomPanel.add(dropButton);
        bottomPanel.add(new JLabel("Registered Courses:"));
        bottomPanel.add(new JScrollPane(registeredCoursesArea));
        bottomPanel.add(new JLabel("Available Courses:"));
        bottomPanel.add(new JScrollPane(availableCoursesArea));
        bottomPanel.add(new JLabel("All Registered Courses:"));
        bottomPanel.add(new JScrollPane(allRegisteredCoursesArea));

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        courseComboBox.addActionListener(this);
        registerButton.addActionListener(this);
        dropButton.addActionListener(this);

        updateCourseDetails();
        updateRegisteredCourses();
        updateAvailableCourses();
        updateAllRegisteredCourses();
    }

    private void updateCourseDetails() {
        Course selectedCourse = (Course) courseComboBox.getSelectedItem();
        if (selectedCourse != null) {
            courseDetailsArea.setText(
                    "Code: " + selectedCourse.getCode() + "\n" +
                    "Title: " + selectedCourse.getTitle() + "\n" +
                    "Description: " + selectedCourse.getDescription() + "\n" +
                    "Capacity: " + selectedCourse.getCapacity() + "\n" +
                    "Schedule: " + selectedCourse.getSchedule()
            );
        }
    }

    private void updateRegisteredCourses() {
        Student selectedStudent = (Student) studentComboBox.getSelectedItem();
        if (selectedStudent != null) {
            registeredCoursesArea.setText("");
            for (Course course : selectedStudent.getRegisteredCourses()) {
                registeredCoursesArea.append(course.toString() + "\n");
            }
        }
    }

    private void updateAvailableCourses() {
        availableCoursesArea.setText("");
        for (Course course : courses) {
            availableCoursesArea.append(course.toString() + "\n");
        }
    }

    private void updateAllRegisteredCourses() {
        allRegisteredCoursesArea.setText("");
        for (Student student : students) {
            allRegisteredCoursesArea.append(student.getName() + ":\n");
            for (Course course : student.getRegisteredCourses()) {
                allRegisteredCoursesArea.append("\t" + course.toString() + "\n");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == courseComboBox) {
            updateCourseDetails();
        } else if (e.getSource() == registerButton) {
            Student selectedStudent = (Student) studentComboBox.getSelectedItem();
            Course selectedCourse = (Course) courseComboBox.getSelectedItem();
            if (selectedStudent != null && selectedCourse != null && selectedCourse.getCapacity() > 0) {
                selectedStudent.registerCourse(selectedCourse);
                selectedCourse.setCapacity(selectedCourse.getCapacity() - 1);
                updateRegisteredCourses();
                updateCourseDetails();
                updateAvailableCourses();
                updateAllRegisteredCourses();
            } else {
                JOptionPane.showMessageDialog(this, "Course is full or selection is invalid!");
            }
        } else if (e.getSource() == dropButton) {
            Student selectedStudent = (Student) studentComboBox.getSelectedItem();
            Course selectedCourse = (Course) courseComboBox.getSelectedItem();
            if (selectedStudent != null && selectedCourse != null && selectedStudent.getRegisteredCourses().contains(selectedCourse)) {
                selectedStudent.dropCourse(selectedCourse);
                selectedCourse.setCapacity(selectedCourse.getCapacity() + 1);
                updateRegisteredCourses();
                updateCourseDetails();
                updateAvailableCourses();
                updateAllRegisteredCourses();
            } else {
                JOptionPane.showMessageDialog(this, "Course not found in registered courses or selection is invalid!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseRegistrationSystem().setVisible(true));
    }
}

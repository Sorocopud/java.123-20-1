// Пакет model
package model;

public abstract class Human {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Sex sex;

    public Human(String firstName, String lastName, String patronymic, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Sex getSex() {
        return sex;
    }
}

enum Sex {
    MALE,
    FEMALE
}

package model;

public class Student extends Human {
    private Group group;

    public Student(String firstName, String lastName, String patronymic, Sex sex, Group group) {
        super(firstName, lastName, patronymic, sex);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}

package model;

        import java.util.ArrayList;
        import java.util.List;

public class Group {
    private String name;
    private List<Student> students;

    public Group(String name) {
        this.name = name;
        this.students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}

package model;

        import java.util.ArrayList;
        import java.util.List;

public class Faculty {
    private String name;
    private List<Department> departments;

    public Faculty(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }
}

package model;

        import java.util.ArrayList;
        import java.util.List;

public class University {
    private String name;
    private List<Faculty> faculties;

    public University(String name) {
        this.name = name;
        this.faculties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public void removeFaculty(Faculty faculty) {
        faculties.remove(faculty);
    }
}

package controller;

        import model.*;

public interface Creator<T> {
    T create();
}

package controller;

        import model.*;

public class StudentCreator implements Creator<Student> {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Sex sex;
    private Group group;

    public StudentCreator(String firstName, String lastName, String patronymic, Sex sex, Group group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.sex = sex;
        this.group = group;
    }

    @Override
    public Student create() {
        return new Student(firstName, lastName, patronymic, sex, group);
    }
}

package controller;

        import model.*;

public class FacultyCreator implements Creator<Faculty> {
    private String name;

    public FacultyCreator(String name) {
        this.name = name;
    }

    @Override
    public Faculty create() {
        return new Faculty(name);
    }
}

package controller;

        import model.*;

public class GroupCreator implements Creator<Group> {
    private String name;

    public GroupCreator(String name) {
        this.name = name;
    }

    @Override
    public Group create() {
        return new Group(name);
    }
}

package controller;

        import model.*;

public class Run {
    public static void main(String[] args) {
        createTypicalUniversity();
    }

    public static void createTypicalUniversity() {
        University university = new University("Typical University");

        Faculty faculty1 = new Faculty("Faculty of Computer Science");
        Faculty faculty2 = new Faculty("Faculty of Business Administration");
        Department department1 = new Department("Department of Artificial Intelligence");
        Department department2 = new Department("Department of Data Science");
        Department department3 = new Department("Department of Marketing");
        Department department4 = new Department("Department of Finance");

        Group group1 = new Group("CS101");
        Group group2 = new Group("CS102");
        Group group3 = new Group("DS101");
        Group group4 = new Group("DS102");

        StudentCreator studentCreator = new StudentCreator("John", "Doe", "Smith", Sex.MALE, group1);
        Student student1 = studentCreator.create();
        group1.addStudent(student1);

        studentCreator = new StudentCreator("Alice", "Johnson", "Smith", Sex.FEMALE, group1);
        Student student2 = studentCreator.create();
        group1.addStudent(student2);

        studentCreator = new StudentCreator("Bob", "Williams", "Johnson", Sex.MALE, group2);
        Student student3 = studentCreator.create();
        group2.addStudent(student3);

        studentCreator = new StudentCreator("Emily", "Brown", "Davis", Sex.FEMALE, group3);
        Student student4 = studentCreator.create();
        group3.addStudent(student4);

        department1.addGroup(group1);
        department1.addGroup(group2);
        department2.addGroup(group3);
        department2.addGroup(group4);

        faculty1.addDepartment(department1);
        faculty1.addDepartment(department2);
        faculty2.addDepartment(department3);
        faculty2.addDepartment(department4);

        university.addFaculty(faculty1);
        university.addFaculty(faculty2);

        System.out.println("University Structure:");
        System.out.println(university.getName());

        for (Faculty faculty : university.getFaculties()) {
            System.out.println("- " + faculty.getName());

            for (Department department : faculty.getDepartments()) {
                System.out.println("  - " + department.getName());

                for (Group group : department.getGroups()) {
                    System.out.println("    - " + group.getName());

                    for (Student student : group.getStudents()) {
                        System.out.println("      - " + student.getFirstName() + " " + student.getLastName());
                    }
                }
            }
        }
    }
}

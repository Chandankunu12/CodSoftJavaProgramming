package com.CodSoft.InternshipJavaProgramming5;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}

class StudentManagementSystem {
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int rollNumber) {
        students.removeIf(student -> student.getRollNumber() == rollNumber);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students);
            System.out.println("Student data saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (ArrayList<Student>) ois.readObject();
            System.out.println("Student data loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class StudentManagementSystem_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem system = new StudentManagementSystem();

        Student student1 = new Student("John Doe", 101, "A");
        Student student2 = new Student("Jane Smith", 102, "B");

        system.addStudent(student1);
        system.addStudent(student2);

        int choice;

        do {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save to File");
            System.out.println("6. Load from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student name: ");
                    String name = sc.next();
                    System.out.print("Enter roll number: ");
                    int rollNumber = sc.nextInt();
                    System.out.print("Enter grade: ");
                    String grade = sc.next();

                    Student newStudent = new Student(name, rollNumber, grade);
                    system.addStudent(newStudent);
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    System.out.print("Enter roll number to remove: ");
                    int removeRollNumber = sc.nextInt();
                    system.removeStudent(removeRollNumber);
                    System.out.println("Student removed successfully!");
                    break;
                case 3:
                    System.out.print("Enter roll number to search: ");
                    int searchRollNumber = sc.nextInt();
                    Student foundStudent = system.searchStudent(searchRollNumber);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("All Students:");
                    system.displayAllStudents();
                    break;
                case 5:
                    System.out.print("Enter file name to save: ");
                    String saveFileName = sc.next();
                    system.saveToFile(saveFileName);
                    break;
                case 6:
                    System.out.print("Enter file name to load: ");
                    String loadFileName = sc.next();
                    system.loadFromFile(loadFileName);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }

        } while (choice != 7);

        sc.close();
    }
}

package student.app;

public class Student {
    private String id;
    private String name;
    private double cgpa;

    public Student() {}

    public Student(String id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getCgpa() { return cgpa; }

    public void setName(String name) { this.name = name; }
    public void setCgpa(double cgpa) { this.cgpa = cgpa; }

    public String toFileString() {
        return id + ";" + name + ";" + cgpa;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | CGPA: " + cgpa;
    }
}
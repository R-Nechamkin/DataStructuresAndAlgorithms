package Sorting;

public class Student implements Comparable<Student>{
    String name;
    Double grade;

    public Student(String name){
        this.name = name;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public Double getGrade() {
        return grade;
    }

    @Override
    public int compareTo(Student o) {
        if(this.grade == null || o.grade == null){
            throw new IllegalStateException("One of the students to be compared has no grade set.");
        }
        return Double.compare(this.grade, o.grade);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": ");
        sb.append(String.format("%,.0f", grade));
        return sb.toString();
    }
}

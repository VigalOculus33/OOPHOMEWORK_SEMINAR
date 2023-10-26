import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Employee implements Comparable<Employee> {
    private String name;
    private int age;
    private double salary;
    private String department;

    public Employee(String name, int age, double salary, String department) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public int compareTo(Employee other) {
        // Сортировка по возрастанию возраста
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }

    // Внешний компаратор для сортировки по убыванию зарплаты
    public static Comparator<Employee> salaryComparatorDesc = (e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary());

    // Внешний компаратор для сортировки по наименованию отдела
    public static Comparator<Employee> departmentComparator = Comparator.comparing(Employee::getDepartment);
}

public class Homework {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 35, 50000, "HR"));
        employees.add(new Employee("Alice", 28, 60000, "Finance"));
        employees.add(new Employee("Bob", 30, 55000, "IT"));
        employees.add(new Employee("Eve", 25, 58000, "Finance"));
        employees.add(new Employee("Mike", 40, 62000, "Sales"));
        employees.add(new Employee("Olivia", 32, 52000, "HR"));
        employees.add(new Employee("Daniel", 27, 53000, "IT"));
        employees.add(new Employee("Sophia", 29, 59000, "Sales"));
        employees.add(new Employee("William", 33, 57000, "IT"));
        employees.add(new Employee("Emma", 31, 61000, "Finance"));
        employees.add(new Employee("Liam", 26, 54000, "Sales"));
        employees.add(new Employee("Ava", 34, 51000, "IT"));
        employees.add(new Employee("Noah", 38, 64000, "HR"));
        employees.add(new Employee("Mia", 22, 55000, "Sales"));
        employees.add(new Employee("James", 37, 63000, "Finance"));
        employees.add(new Employee("Charlotte", 23, 56000, "IT"));
        employees.add(new Employee("Benjamin", 24, 57000, "Sales"));
        employees.add(new Employee("Amelia", 36, 60000, "HR"));
        employees.add(new Employee("Lucas", 39, 65000, "IT"));
        employees.add(new Employee("Isabella", 28, 58000, "Sales"));


        // Сортировка по возрастанию возраста (по умолчанию, так как Employee implements Comparable)
        Collections.sort(employees);
        System.out.println("Сортировка по возрастанию возраста:");
        employees.forEach(System.out::println);

        // Сортировка по убыванию зарплаты (с использованием внешнего компаратора)
        Collections.sort(employees, Employee.salaryComparatorDesc);
        System.out.println("\nСортировка по убыванию зарплаты:");
        employees.forEach(System.out::println);


        Collections.sort(employees, Employee.departmentComparator);
        System.out.println("\nСортировка по наименованию отдела:");
        employees.forEach(System.out::println);
    }
}

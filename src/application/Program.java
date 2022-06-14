package application;

import entities.Employee;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full path: ");
        String path = sc.nextLine();

        System.out.print("Enter salary: ");
        double minSalary = sc.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            List<Employee> employees = new ArrayList<>();

            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                String name = fields[0];
                String email = fields[1];
                double salary = Double.parseDouble(fields[2]);

                employees.add(new Employee(name, email, salary));

                line = br.readLine();
            }

            System.out.println("Email of people whose salary is more than " + minSalary + ":");
            List<String> emails = employees.stream()
                    .filter(x -> x.getSalary() > minSalary)
                    .map(Employee::getEmail)
                    .sorted().toList();

            emails.forEach(System.out::println);

            double sum = employees.stream()
                    .filter(x -> x.getName().charAt(0) == 'M')
                    .map(Employee::getSalary)
                    .reduce(0.0, (x, y) -> x + y);

            System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));

        }catch (IOException e){
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
    }
}

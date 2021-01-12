package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employees;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Employees> list = new ArrayList<>();
			String line = br.readLine();
			
			while (line != null) {
				String[] lineSplit = line.split(",");
				list.add(new Employees(lineSplit[0], lineSplit[1], Double.parseDouble(lineSplit[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> newList = list.stream()
					.filter(x -> x.getSalary() > salary)
					.map(y -> y.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			newList.forEach(System.out::println);
			
			List<Double> newList2 = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(y -> y.getSalary())
					.collect(Collectors.toList());
			double sum = newList2.stream().reduce(0.0, (x, y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}

}

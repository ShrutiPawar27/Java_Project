package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
class Employee implements Serializable{
	int Id;
	String Name;
	float salary;
	long contact_no;
	String email_id;
	public Employee(int Id, String Name, float salary, long contact_no, String email_id) {
		
		this.Id = Id;
		this.Name = Name;
		this.salary = salary;
		this.contact_no = contact_no;
		this.email_id = email_id;
	}
	@Override
	public String toString() {
		return "\nEmployee [id=" + Id + ", name=" + Name + ", salary=" + salary + ", contact_no=" + contact_no
				+ ", email_id=" + email_id + "]";
	}
	
}
public class EmployeeManagement {

	static void display(ArrayList<Employee> al)
	{
		System.out.println("\n      -----------Employee List-----------");
		System.out.println(String.format("%-10s%-15s%-10s%-20s%-10s","Id","Name","salary","contact_no","email_id"));
		for (Employee employee : al) {
			System.out.println(String.format("%-5s%-20s%-10s%-15s%-10s", employee.Id,employee.Name,employee.salary,employee.contact_no,employee.email_id));
		}
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NullPointerException {
		int id;
		String name;
		float salary;
		long contact_no;
		String email_id;
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Employee> al =new ArrayList<Employee>();
		
		File f = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			
		f= new File("C:\\Chaitu\\Employee.txt");
		if(f.exists())
			{
				fis = new FileInputStream(f);
				ois = new ObjectInputStream(fis);
				al = (ArrayList<Employee>)ois.readObject();
			}
		
		}
		catch(Exception exp) {
			System.out.println(exp);
		}
		do {
			System.out.println("\n*******Welcome to the Employee Management System*******\n");
			System.out.println("1.Add Employee\n"+
							   "2.Search Employee\n"+
							   "3.Edit Employee Details\n"+
							   "4.Delete Employee details\n"+
							   "5.Display record of all employee\n"+
							   "6.Exit");
			System.out.println("Enter your choice :");
			int ch =sc.nextInt();
			
			switch(ch)
			{
			case 1: System.out.println("Enter the following details to add list :\n");
				System.out.println("Enter ID");
				id = sc.nextInt();
				System.out.println("Enter Name");
				name = sc.next();
				System.out.println("Enter Salary");
				salary = sc.nextFloat();
				System.out.println("Enter Contact Number");
				contact_no = sc.nextLong();
				System.out.println("Enter Email_id");
				email_id = sc.next();
				al.add(new Employee(id, name, salary, contact_no, email_id));
				display(al);
				break;
				
			case 2:
				System.out.println("Enter Employee ID to search :");
				id = sc.nextInt();
				int i=0;
				for (Employee e : al) {
					if(id == e.Id)
					{
						System.out.println(e+"\n");
						i++;
					}
				}
				if(i == 0)
				{
					System.out.println("\nEmployee details are not available, Please enter valid ID:");
				}
				break;
				
			case 3: System.out.println("\nEnter Employee Id to edit details:");
					id = sc.nextInt();
					int j=0;
					for (Employee e : al) {
						if(id == e.Id)
						{
							j++;
							do{
								int ch1 = 0;
								System.out.println("\nEdit Employee details :\n"+
													"1.Employee ID\n"+
													"2.Name\n"+
													"3.Salary\n"+
													"4.Contact Number\n"+
													"5.Email_id\n"+
													"6.Back\n");
								System.out.println("Enter your choice :");
								ch1 = sc.nextInt();
								switch(ch=1)
								{
								case 1 :System.out.println("Enter new Employee ID:");
										e.Id = sc.nextInt();
										System.out.println(e+"\n");
										break;
										
								case 2 :System.out.println("Enter new Employee Name:");
										e.Name = sc.nextLine();
										System.out.println(e+"\n");
										break;
								
								case 3 :System.out.println("Enter new Employee Salary:");
										e.salary = sc.nextFloat();
										System.out.println(e+"\n");
										break;
										
								case 4 :System.out.println("Enter new Employee Contact Number:");
										e.contact_no = sc.nextLong();
										System.out.println(e+"\n");
										break;
										
								case 5 :System.out.println("Enter new Employee Email_id:");
										e.email_id = sc.next();
										System.out.println(e+"\n");
										break;
								
								case 6: j++;
										break;
								
								default : System.out.println("\nEnter a correct choice from list");
										  break;
								}
							}
							while(j==1);
						}
					}
				if(j == 0) {
					System.out.println("\nEmployee details are not available, Please enter valid ID!");
				}
				break;
				
			case 4: System.out.println("\nEnter Employee ID to delete from database :");
					id = sc.nextInt();
					int k=0;
					try {
					for (Employee e : al) {
						if(id == e.Id) {
							al.remove(e);
							display(al);
							k++;
						}
					}
					if(k==0) {
						System.out.println("\nEmployee details are not available, Please enter valid ID!");
					}
					}
					catch(Exception ex) {
						System.out.println(ex);
					}
					break;
			case 5: try {
				al = (ArrayList<Employee>)ois.readObject();
			} catch(ClassCastException e2) {
				System.out.println(e2);	
			}catch (Exception e2) {
				System.out.println(e2);	
			}
			display(al);
			break;
			
			case 6:try {
				
					fos = new FileOutputStream(f);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(al);
				} catch(IOException e1){
					e1.printStackTrace();
				}
				catch(Exception e2) {
					e2.printStackTrace();
				}
			finally {
				try {
					fis.close();
					ois.close();
					fos.close();
					oos.close();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			System.out.println("\nYou have choosen exit , saving files and closing the tool");
			sc.close();
			System.exit(0);
			break;
			
		default : System.out.println("Enter correct choice from list :");
				  break;
				  
			}
		}
		while(true);
	}
}





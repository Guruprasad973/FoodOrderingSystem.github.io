package com.java.food_delivery;
import java.sql.*;
import java.util.Scanner;
public class Order
{
	public static void main(String[] args)
	{
		Order o = new Order();
		o.back();
	}
	public static void back()
	{
		Order o = new Order();
		Admin ad = new Admin();
		Register r = new Register();
		System.out.println("Welcome to online food delivery application enter your option\n1.Admin\n2.User");
		Scanner Scan = new Scanner(System.in);
		int a = Scan.nextInt();
			switch(a)
			{
				case 1:
				ad.addfood();
					break;
				case 2:
					r.toregister();
					break;
			}
		}
}
class Admin
{
	public static void addfood()
	{
		Order o = new Order();
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your option \n1.Add menu\n2.View menu\n3.Delete menu\n4.View orders\n5.Add location\n6.Delete location\n7.View_loc\n8.back");
		int a =Scan.nextInt();
		switch(a)
		{
		case 1:
			Add_menu();
			break;
		case 2:
			View_menu();
			break;
		case 3:
			Delete_menu();
			break;
		case 4:
			View_orders();
			break;
		case 5:
			Add_loc();
			break;
		case 6:
			Del_menu();
			break;
		case 7:
			View_loc();
			break;
		case 8:
			Order.back();
			break;
		case 9:
			default:
			System.out.println("You have enter a wrong input please enter valid input ! and try again");
			Admin.addfood();
			break;
		}
	}
	public static void Add_menu()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your menu name");
		String menu_name  = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		
		String sql = "insert into menu values(?)";
		boolean bool = searchMenuName(menu_name);
		if(bool == true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,menu_name);
				pst.executeUpdate();
				System.out.println("You have successfully added new menu");
				addfood();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Menu name is already Exist ! try with another menu name");
			addfood();
		}
	}
	public static boolean searchMenuName(String searchMenu)
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select menuname from menu where menuname = ?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,searchMenu);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public static void View_menu()
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user = "root";
		String password = "Guru@1997";
		
		String sql = "select * from menu";
		
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery(sql);
			
			while(rs.next())
			{
				System.out.println(rs.getString("menuname"));
			}
			addfood();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void Delete_menu()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your menu name");
		String menu_name = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "delete from menu where menuname = ?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,menu_name);
			pst.executeUpdate();
			System.out.println("You have successfully deleted your menu");
			addfood();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void View_orders()
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select * from menu";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next())
			{
				System.out.println(rs.getString("menuname"));
			}
			addfood();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void Add_loc()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your location");
		String loc = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "insert into loction values(?)";
		
		
		boolean bool = searchLocation(loc);
		if(bool == false)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, loc);
				pst.executeUpdate();
				System.out.println("Successfully added your location");
				addfood();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Your area already present ! Try again");
			Add_loc();
		}
}
	public static boolean searchLocation(String loc)
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select area from location where area = ?";
		
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, loc);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	public static  void Del_menu()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your location");
		String loc = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "Delete from loction where area = ?";
		
		boolean bool = searchLocation(loc);
		if(bool == true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, loc);
				pst.executeUpdate();
				System.out.println("Successfully added your location");
				addfood();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Your area already present ! Try again");
			Del_menu();
		}
	}
	public static void View_loc()
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select * from location";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			int i =0;
			while(rs.next())
			{
				++i;
				System.out.println(i+" "+rs.getString("area"));
			}
			addfood();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
class Register
{
	public static void back()
	{
		Register r = new Register();
		r.toregister();
	}
	public void toregister()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your option\n1.Register\n2.login\n3.back");
		int a =  Scan.nextInt();
		switch(a)
		{
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 3:
				Order.back();
		}
	}
	public void register()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your user name");
		String user_name  = Scan.nextLine();
		System.out.println("Enter your password");
		String user_password = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "insert into customer values(?,?)";
		
		boolean bool = searchUserName(user_name);
		if(bool == true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,user_name);
				pst.setString(2,user_password);
				pst.executeUpdate();
				System.out.println("You have successfully registered");
				back();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("User name is already Exist ! try with another user name or login");
			back();
		}
	}
	public boolean searchUserName(String searchUser)
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select username from customer where username = ?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,searchUser);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	public void login()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your user name");
		String user_name  = Scan.nextLine();
		System.out.println("Enter your user name");
		String user_password = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select username from customer where username=? and password=?";

		boolean bool = LoginUserName(user_name,user_password);
		if(bool == true)
		{
			try
			{
				Connection con = DriverManager.getConnection(url,user,password);
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1,user_name);
				pst.setString(2,user_password);
				pst.executeQuery();
				System.out.println("You have successfully login");
				
				System.out.println("Enter your option \n1.Menu");
				int a = Scan.nextInt();
				switch(a)
				{
				case 1:
					Menu();
					break;
				case 2:
					default :
						System.out.println("You have enter wrong input! please enter valid input and try again");
					break;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("You have insert wrong input ! try with another user name or go to new register");
			back();
		}
	}
	public boolean LoginUserName(String searchUserName,String searchUserPassword)
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select username from customer where username = ? and password = ?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,searchUserName);
			pst.setString(2, searchUserPassword);

			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public static void Menu()
	{
		Scanner Scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select * from menu";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			int i=0;
			while(rs.next())
			{
				++i;
				System.out.println(i+"."+rs.getString("menuname"));
			}
			System.out.println("Want to order press 1");
			int a = Scan.nextInt();
			switch(a)
			{
			case 1 :
				location();
				break;
			case 2:
				default :
					System.out.println("you have entered wrong input ! please enter valid input again");
					Menu();
					break;
			}
			//location();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void location()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("select your location in a given list \n1.mejastic\n2.jp nagar\n3.silk board\n4.jayanagar\n5.yeswanthpur\n6.nagasandra\n7.byappanahalli\n8.nagar bhavi\n9.BTM layout\n10.nayandnalli");
		int a  = Scan.nextInt();
	}
	public static void mejastic()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Want to know list of menu press 1 or to continue press 2");
		int a = Scan.nextInt();
		switch(a)
		{
		case 1:
			view_loc();
			break;
		case 2:
			ordering();
			break;
		case 3:
			default:
				System.out.println("You have enter wrong input please enter valid input and try again");
				break;
		}
	}
	public static void view_loc()
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select * from menu";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			int i=0;
			while(rs.next())
			{
				++i;
				System.out.println(i+"."+rs.getString("menuname"));
			}
			mejastic();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void ordering()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Select your location in a given list \n1.press 1 to view the list");
		int a = Scan.nextInt();
		switch(a)
		{
		case 1:
			customer_loc();
			break;
		case 2:
			default:
				System.out.println("You have enter wrong input ! please try again with correct input");
			ordering();
				break;
		}
	}
	public static void customer_loc()
	{
		Scanner Scan = new Scanner(System.in);
		System.out.println("Enter your location");
		String loc = Scan.nextLine();
		System.out.println("Enter your foodname");
		String foodname = Scan.nextLine();
		System.out.println("Enter your username");
		String username = Scan.nextLine();
		System.out.println("Enter your location");
		String custLocation = Scan.nextLine();
		
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "insert into orderdetails values(?,?,?,?)";
		boolean bool = searchfoodname(foodname,loc,username);
		if(bool==true)
		{
				try
				{
					Connection con = DriverManager.getConnection(url,user,password);
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, loc);
					pst.setString(2,foodname);
					pst.setString(3,username);
					pst.setString(4, custLocation);
					pst.executeUpdate();
					System.out.println("Your order is successfully ordered");
					//Total_order_details();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}
		else
		{
			System.out.println("You have entered invalid foodname or invalid location ! please try again");
			customer_loc();
		}
	}
	public static boolean searchfoodname(String fname, String loc,String username)
	{
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql1 = "select menuname from menu where  menuname = ?";
		String sql2 = "select area from location where area = ?";
		String sql3 = "select username from customer where username = ?";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			PreparedStatement pst1 = con.prepareStatement(sql1);
			PreparedStatement pst2 = con.prepareStatement(sql2);
			PreparedStatement pst3 = con.prepareStatement(sql3);
			pst1.setString(1,fname);
			pst2.setString(1,loc);
			pst3.setString(1, username);
			ResultSet rs1 = pst1.executeQuery();
			ResultSet rs2 = pst2.executeQuery();
			ResultSet rs3 = pst3.executeQuery();
			if(rs1.next()&& rs2.next()&& rs3.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	public static void Total_order_details()
	{
		
		String url = "jdbc:mysql://localhost:3306/orders";
		String user ="root";
		String password = "Guru@1997";
		String sql = "select * from orderdetails";
		try
		{
			Connection con = DriverManager.getConnection(url,user,password);
			Statement pst = con.createStatement();
			ResultSet rs = pst.executeQuery(sql);
			while(rs.next()) 
			{
				System.out.println(rs.getObject("foodname")+","+rs.getObject("orderloc")+" "+rs.getObject("cust_name")+" "+rs.getObject("custaddress"));
			}
			System.out.println("Your order is successfully ordered");
			FinalBill();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void FinalBill()
	{
		System.out.println("Thank you for ordering\n you order is successfully ordered");
	}
}
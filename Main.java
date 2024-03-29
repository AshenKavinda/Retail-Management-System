import java.util.Scanner;
import java.lang.Thread;

public class Main {
    public static void main(String[] args) {

        //creating main objects
        SupplierCollection supplierCollection = new SupplierCollection();
        ItemCollection itemcollection = new ItemCollection(supplierCollection);
        Bill bill = new Bill(itemcollection);
        CoustomerCollection coustomer = new CoustomerCollection();
        Scanner scanner = new Scanner(System.in);
        
        //add customers
        coustomer.addCoustomer("2004205216",774237,"Kavinda","kavinda@gmail.com");
        coustomer.addCoustomer("2004205217",774238,"Nimal","Nimal@gmail.com"); 
        coustomer.addCoustomer("2004205218",774239,"Jagath","Jagath@gmail.com");

        //add items
            //countable Items
        itemcollection.addItem("1000", "milk          ", 100, 150, 250,"sunil","200307200v","774237");
        itemcollection.addItem("1001", "Sprite 200ml  ", 100, 180, 200,"kamal","200307201v","774237");
        itemcollection.addItem("1002", "samaposha 500g", 100, 200, 280,"Ranil","200307202v","774237");
        itemcollection.addItem("1003", "battry 1.5v   ", 200, 50, 60,"Kumara","200307203v","774237");
            //uncountable Items
        itemcollection.addItem("2000","Rice           ","KG",500,90f,120f,"sugath","200307204","774237");
        itemcollection.addItem("2001","dal            ","KG",250,100f,140f,"Saman","200307205","774237");

        //starting point
        mainMenu(itemcollection,supplierCollection,bill,coustomer,scanner);

        
    }

    public static void cls()
    {
	try
	{	
		new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
	}catch(Exception E)
		{
			System.out.println(E);
		}
    }

    public static void sleep(int value) 
    {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void mainMenu(ItemCollection itemcollection,SupplierCollection suppliercollection,Bill bill,CoustomerCollection coustomer,Scanner scanner)
    {  
        int choice = 0 ;
        while (choice!=99) {
            cls();
            System.out.println("1. store Management");
            System.out.println("2. Billing");
            System.out.println("3. Customer Management");
            System.out.println();
            System.out.print("Choice : ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    storeManagement(itemcollection, suppliercollection,scanner);
                    break;
                case 2:
                    billManagement(bill, coustomer, scanner);
                    break;
                case 3:
                    customerManagement(coustomer, scanner);
                    break;
            }
        }
    }

    public static void billManagement(Bill bill,CoustomerCollection coustomer,Scanner scanner)
    {
        int choice = 0 ;
        while (choice!=99) {
            cls();
            System.out.print("Phone Number : ");
            int pNo = scanner.nextInt();
            scanner.nextLine();
            if (pNo==99) {
                break;
            }else {
                if (coustomer.getArrLocation(pNo)<0) {
                    System.out.println("Invalied Phone NUmber (continue 1 or Re-enter 2)");
                    if (scanner.nextInt()==2) {
                        continue ;
                    }
                    scanner.nextLine();
                }
            }         
            bill.getUserInputAddToBill(scanner);
            System.out.print("press 1 to conform payment : ");
            choice = scanner.nextInt();
            if (choice==1) {
                System.out.print("Enter balance : ");
                float balance = scanner.nextFloat();
                float point = bill.printFinalBill(balance);
                System.out.println();
                System.out.println("Point For That bill : "+point);
                coustomer.setPointByNumber(pNo, point);
                point = coustomer.getPointByNumber(pNo);
                bill.displayPoint(point);
                
                System.out.println("THANK YOU ....");
            }
            else {
                System.out.println("THANK YOU ....");
            }
            bill.resetBill();
            System.out.print("NEXT ENTER eny key : ");
            scanner.nextInt(); 
        }
    }

    //For customer handling
    public static void customerManagement(CoustomerCollection coustomer,Scanner scanner)
    {  
        int choice = 0 ;
        while (choice!=99) {
            cls();
            System.out.println("1. Get user Details");
            System.out.println("2. Get All Customer Details");
            System.out.println("3. Add Customer");
            System.out.print("Choice : ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Phone No : ");
                    int pNo = scanner.nextInt();
                    coustomer.getUserByNumber(pNo);
                    sleep(4000);
                    break;
                case 2:
                    coustomer.getAllUser();
                    System.out.print("To exit enter 99 : ");
                    choice = scanner.nextInt();
                    break;
                case 3:
                    coustomer.getUserInputAddCoustomer(scanner);
                    break;
            }
        }
    }

    //Store manegement menu
    public static void storeManagement(ItemCollection itemcollection,SupplierCollection suppliercollection,Scanner scanner)
    {
        int choice = 0 ;
        while (choice!=99) {
            cls();
            System.out.println("1. Item");
            System.out.println("2. Supplier");
            System.out.print("Choice : ");
            choice = scanner.nextInt() ;
            switch (choice) {
                case 1:
                    item(itemcollection, suppliercollection, scanner);
                    break;
                case 2:
                    supplier(suppliercollection, scanner);
                    break;
            }
        }
    }

    //For supplier handling
    public static void supplier(SupplierCollection suppliercollection,Scanner scanner)
    {
        int choice = 0 ;
        while (choice!=99) {
            cls();
            System.out.println("1. Add Supplier : ");
            System.out.println("2. Add More Item to Suppliern : ");
            System.out.println("3. Search Supliers by Item");
            System.out.print("Choice : ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    suppliercollection.getUserInputAddSuplier(scanner);
                    break;
                case 2:
                    suppliercollection.getUserInputaddMoreItemToSuplier(scanner);
                    break;
                case 3:
                    String itemGroupCode ;
                    System.out.println("ItemCollection Code : ");
                    itemGroupCode=scanner.next();
                    suppliercollection.printSuplierListByItemGroupId(itemGroupCode);
                    sleep(4000);
                    break;
            }
        }
    }

    //For itemcollection handling
    public static void item(ItemCollection itemcollection,SupplierCollection suppliercollection,Scanner scanner)
    {
        int choice = 0 ;
        while (choice!=99) {
            cls();
            System.out.println("1. Add Countable Item");
            System.out.println("2. Add Uncountable Item");
            System.out.println("3. Update Item Quntity");
            System.out.print("Choice : ");
            choice = scanner.nextInt() ;
            
            switch (choice) {
                case 1:
                    itemcollection.getUserInputAddItem(suppliercollection, 1, scanner);
                    break;
                case 2:
                    itemcollection.getUserInputAddItem(suppliercollection, 2, scanner);
                    break;
                case 3:
                    itemcollection.getUserInputUpdateQuntity(scanner);
                    break;
            }
        }
    }

}

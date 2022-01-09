
import java.text.DecimalFormat;
import java.util.Scanner;

public class PointOfSale {
    static Product[] p = new Product[10];
    static Scanner input = new Scanner(System.in);
    static DecimalFormat d = new DecimalFormat("0.00");
    static String pId;
    
    static int pCount = 0,cnt=0;     // pCount is actual number of product records created, max is 10
    static int numProd=0;      // numProd is number of products purchased
    static int[] pIndex = new int[20];       // purchased products indices
    static int[] purchaseQty = new int[20]; // purchased products quantity
    static int[] numHS = new int[20];
    static double[] amountDue =  new double[20]; // purchased products amounts
    
    // method to create a new product record
    static void createNewProduct(){             
        String ans;
        
        String pName = null ,supplier = null;
        int quantity; 
        double price;
        
        do{
        	System.out.println("\nCreating a New Product Record #"+(pCount+1)+"\nEnter the following Product Details: ");
        	System.out.print("Product ID: "); pId = input.next();
            System.out.print("Product Name: "); pName = input.next();
            System.out.print("Product Quantity: "); quantity = input.nextInt();
            System.out.print("Product Price: "); price = input.nextDouble();
            System.out.print("Product Supplier: "); supplier = input.next();
            
            p[pCount++] = new Product(pId,pName,quantity,price,supplier);
           
            System.out.println("Create more product?[y/n]");
            ans=input.next();
          } while (ans.equals("y"));
    }
    
    // returns the index of product record whose ID is same as the input ID, otherwise returns -1
    static int productSearch(String xId)
    {
    	for (int i=0;i<pCount;i++) // scan all created product records for a product with ID same as the input ID
    	{
    		if (p[i].productId.equals(xId))
    			return i;  // ID is found, return the product index
    	}
    	return -1;    	
    }
   
    static int productSearchBS(int xId)
    {
    	for (int i=0;i<pCount;i++) // scan all created product records for a product with ID same as the input ID
    	{
    		if (p[i].numberSold==xId)
    			return i;  // ID is found, return the product index
    	}
    	return -1;    	
    }

    // method to make a purchase
    static void purchaseProduct(){
        String ans;
        int i; 
        double cash, totalAmountDue=0, change;        
                      
        do{
        	System.out.println("Enter Product ID: "); 
        	pId = input.next();
        	i=productSearch(pId);
        	if (i<0) // product is not found
        	{
        		System.out.println("Product ID is not found.");
        	}
        	else if (p[i].quantityAvailable<=0) // product is found but check the available quantity   		       		
        	{ // check if product's available quantity is zero, if so then it's out of stock & purchase is not allowed.
        		System.out.println("Product is out of stock.");        			                  	
        	}
        	else
        	{  // product's available quantity is >= zero, so purchase is allowed. Display selected product's name, available quantity & ask quantity to purchase 
        			System.out.println("Product Name: "+p[i].productName);
        			System.out.println("Product Price: "+d.format(p[i].price));
        			System.out.println("Product Available Quantity: "+p[i].quantityAvailable);        			
        			do {
        				System.out.println("Enter Quantity to Purchase: ");
        				purchaseQty[numProd] = input.nextInt();
        				if (purchaseQty[numProd]>p[i].quantityAvailable)
        					System.out.println("You may purchase a maximum of "+p[i].quantityAvailable+" only.");
        			} while (purchaseQty[numProd]>p[i].quantityAvailable);
                        
        			pIndex[numProd]=i; // remember purchased product index
        			amountDue[numProd] = purchaseQty[numProd] * p[i].price; // compute amount due to customer for this product
        			totalAmountDue += amountDue[numProd]; // compute total amount due for all products purchased                                                                       
        			System.out.println("Amount Due: "+d.format(amountDue[numProd]));        			
        			numProd++;
        	}
        	System.out.println("Add more products to your cart? [y/n]");
            ans=input.next();           	
        } while (ans.equals("y"));
         
         if (totalAmountDue>0) 
         {
        	 System.out.println("Total Amount Due: "+d.format(totalAmountDue));             
             do 
             {
            	 System.out.print("Enter Customer Cash: ");
                 cash = input.nextInt();
                 if (cash<totalAmountDue)
                     System.out.println("Cash tendered is lacking.");
             } while (cash<totalAmountDue);
             change = cash - totalAmountDue;
             System.out.println("Cash Tendered: "+d.format(cash));
             System.out.println("Change: "+d.format(change));
             System.out.println("Thank you. Please come again!");
             // After purchased products total amount is paid, update records of those purchased products.  
             upDatePurchasedProducts();  
             // clear details of previous purchase made
             clearPreviousPurchaseDetails();
         }
         else
        	 System.out.println("No purchase made.");                               	                                 
    }
    
    static void upDatePurchasedProducts()
    {    
    	for (int i=0 ; i<numProd ; i++) 
    	{
    		p[pIndex[i]].decreaseQuantityBy(purchaseQty[i]);
    		p[pIndex[i]].increaseSales(amountDue[i]);
    	}
    }
  /*  static void Addquant() {
    	int i ;
       	for( i = 0 ; i<pCount; i++) {
     		if(p[i].quantityAvailable==0) {
     			for( i = 0 ; i<pCount; i++) { 
     				System.out.println("List of out stock products");
         		System.out.println("\nProduct Number: "+(i+1)); 
         		System.out.println("Product ID: "+p[i].productId); 
		            System.out.println("Product Name: "+p[i].productName); 
     		}
     			AddquantProcess();}
     		
		      }   
       	         
     	
     	
     		}*/
    	
    static void AddquantProcess(int i) {
    	
     
		System.out.println("\nProduct Number: "+(i+1)); 
		System.out.println("Product ID: "+p[i].productId); 
         System.out.println("Product Name: "+p[i].productName); 
         System.out.println("Enter how many quantity you want to add:");
         int Aqty =input.nextInt();
        p[i].quantityAvailable=p[i].Aqty( Aqty, p[i].quantityAvailable);
    
    	
     	
    }
    static void BS() {
    	int i ;
    for( i = 0 ; i<pCount;i++) {
        numHS[i]=p[i].numberSold;
        cnt++;
		 }
  	 int n = cnt; 
    for ( i = 0; i < n-1; i++) 
        for (int j = 0; j < n-i-1; j++) 
            if (numHS[j] < numHS[j+1]) 
            { 
                // swap temp and arr[i] 
                int temp = numHS[j]; 
                numHS[j] = numHS[j+1]; 
                numHS[j+1] = temp; 
            } 
    i=productSearchBS(numHS[0]);
    if(p[i].numberSold<=0) {
   	 System.out.println("No product has been sold");
   	   
    }
    else {
    System.out.println("The Best seller product is "+p[i].productName+" with "+p[i].numberSold +" sold items");
   
    }
    }
    static void clearPreviousPurchaseDetails()
    {    
    	for (int i=0 ; i<numProd ; i++) 
    	{
    		pIndex[i]=0; 
    		purchaseQty[i] = 0;
    		amountDue[i] = 0.0; 
    	}
    	numProd = 0;
    }
    
    
    public static void main(String[] args) {
        
        int option,i;
        do{
        	System.out.println("\nWELCOME to "+Product.storeName);
        	System.out.println("\nSelect your transaction:");
        	System.out.println("1-Create a new product record");
            System.out.println("2-Make a purchase");
            System.out.println("3-Display all product records details");
            System.out.println("4-Display all out-of-stock products (zero quantity)");
            System.out.println("5-Display Bestseller products");
            System.out.println("6-Add quantity to out of stock products");
            System.out.println("7-Display all products total sales");
            System.out.println("8-Exit");
            System.out.print("\n-> ");
            option = input.nextInt();
            if (option==1)
            {                
            	createNewProduct();                   
            }
            else if (option==2)
            {
                if (pCount<=0) { //pCount is no. of product records created
                    System.out.println("\nNo available product to purchase.");
                 }
                else 
                	purchaseProduct();
                	
            }     
            else if (option==3)
            {
                 if (pCount<=0) { //pCount is no. of product records created
                    	System.out.println("\nNo available product to display.");
                 }
                 else
                 {
                	System.out.println("Displaying a total of "+pCount+" Products."); 
                    for(i = 0 ; i<pCount ; i++) {
                        p[i].displayProductDetails();
                    }
                 }
            }    
            else if (option==4)
            {
                 if(pCount<=0){
                    System.out.println("\nNo available product to display.");
                 }
                 else
                 {
                	int outOfStock = 0;
                    for(i = 0;i<pCount;i++){
                        if(p[i].quantityAvailable<=0){
                            p[i].displayProductDetails();
                            outOfStock++;
                        }
                    }
                    System.out.println("There are "+outOfStock+" out-of-stock product(s).");
                 }
             }       
             else if (option==5)
             {

            	  if (pCount<=0) { //pCount is no. of product records created
                  	System.out.println("\nNo available product to display.");
                  
               }
            	  else {
            		  BS();
            	  }
             }  
             else if (option==6)
             {
            	 if (pCount<=0) { //pCount is no. of product records created
                 	System.out.println("\nNo available product to display.");
              }
            	 else {
            		 
                	 System.out.println("\nEnter product's id number: ");
                        pId = input.next();
                 	i=productSearch(pId);
                 	if (p[i].quantityAvailable>0) // product is not found
                 	{
                 		System.out.println("Product has quantity");
                 	}
                 	else if(p[i].quantityAvailable<=0) {
                 		 AddquantProcess(i);
                 	}
            	     		} 
            		 
	   
            		 }
             else if (option==7)
             {
            	 if (pCount<=0) { //pCount is no. of product records created
                 	System.out.println("\nNo available product to display.");
              }
            	 else {
            		 double Psales=0;
            		 System.out.println("Displaying a total of "+pCount+" Products."); 
                     for(i = 0 ; i<pCount ; i++) {
                         p[i].displayProductDetails();
                         Psales+=p[i].sales;
                     }
                     System.out.println("\nAll Products total sales "+d.format(Psales)); 
                	} 
            		 
	   
            		 }
             
             else
                System.out.println("\nThank you for using the program");
             
        } while (option<=7);
        
    }    
    
}

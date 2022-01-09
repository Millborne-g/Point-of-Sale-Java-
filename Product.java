
public class Product {
    static {
        storeName = "USTP-University Mall";
    }
    Product(String xproductId, String xproductName, int xqAvailable, double xprice, String xsupplier){
        productId = xproductId;
        productName = xproductName;
        quantityAvailable = xqAvailable;
        price = xprice;
        supplier = xsupplier;
        numberSold=0;
        sales=0;
    }
   
    String productId, productName;
    int quantityAvailable, numberSold;
    double price,sales;
    static String storeName;
    String supplier;
    
    void decreaseQuantityBy(int qty) // decrease available quantity & increase number of pieces sold
    {                                // after a purchase of this product
    	quantityAvailable -= qty;
    	numberSold += qty;
    }
    
    void increaseSales(double amt) // increase sales after a purchase of this product
    {
    	sales += amt;
    }
    int Aqty(int qty,int qty1) 
    {                                
    	qty1 += qty ;
    	return qty1;
    }
                
    void displayProductDetails(){
    	System.out.println("\nID: "+productId);
        System.out.println("Name: "+productName);
        System.out.println("Available Quantity: "+quantityAvailable);
        System.out.println("Price: "+price);
        System.out.println("Quantity Sold: "+numberSold);
        System.out.println("Total Sales: "+sales);
        System.out.println("Supplier: "+supplier);
    }    
    void displayProductDetails1(){
    	System.out.println("\nID: "+productId);
        System.out.println("Name: "+productName);
        System.out.println("Total Sales: "+sales);
      
    }
}




package damianshw;
import java.util.Scanner;

public class dmanshw {

	//the main array
	public static Stock[] stockList = new Stock[10];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//main logic
		//create the pregenerated array of stocks
		//create the stocks and add them to the array
		stockList[0] = new Stock("Amazon", "AMZN",  1889.15, 3552.25, 3054.00);
		stockList[1] = new Stock("Lululemon", "LULU", 177.77, 399.90, 311.16);
		stockList[2] = new Stock("Tesla", "TSLA", 89.28, 900.40, 635.62);
		stockList[3] = new Stock("Disney", "DIS", 92.56, 203.02, 185.54);
		
		//main loop that will allow the user to add a stock, get stats, and exit
		
		int exit = 0;
		while(exit == 0) {
			
			//ask the user what they want to do
			Scanner in = new Scanner(System.in);
			System.out.println("Please choose between the 3 options: \n0 - Add a stock to the database\n1 - Get statistics about a stock\n2 - Exit the program");
			int userinput = in.nextInt();
			
			//switch statement to decide what to do
			switch(userinput) {
				case 0:
					//add a stock to the database
					addStock(stockList);
					break;

				case 1:
					//displays stats of a stock for the user
					displayStockStats(stockList);
					break;

				case 2:
					//exit the program
					System.out.println("Thank you for using the program!");
					exit = 1;
					break;

				default:
					break;
			}
		}
	}
	
	//method to add stock to stocklist
	public static void addStock(Stock[] arr) {
		//check to make sure the array isnt full
		if(arr.length > 10)
		{
			//cant do anything because array is full
			System.out.println("Sorry the database is currently full, please try something else.");
		}else {
			//get the information from the user
			Scanner input = new Scanner(System.in);
			
			System.out.println("Please enter the name of the stock: ");
			String tempName = input.nextLine();
			
			System.out.println("Please enter the symbol of the stock: ");
			String tempSymbol = input.nextLine();
			
			System.out.println("Please enter the last recorded price of the stock: ");
			double tempLastPrice = input.nextDouble();
			
			System.out.println("Please enter the highest recorded price in the past year of the stock: ");
			double tempHighPrice = input.nextDouble();
			
			System.out.println("Please enter the lowest recorded price in the past year of the stock: ");
			double tempLowPrice = input.nextDouble();
			
			//build the stock
			Stock tempStock = new Stock(tempName, tempSymbol, tempLowPrice, tempHighPrice, tempLastPrice);
			
			//add it to the array, the first instance of the array having a null index
			for(int i = 0; i < arr.length - 1; i++)
			{
				//find an index that is null
				if(arr[i] == null) {
					//add the stock that was created to this array
					arr[i] = tempStock;
					System.out.println("the stock was added");
					break;
				}
			}
		}
	}
	
	//method to check if a stock is in the main list
	public static int isStockInList(Stock[] stockArray, String stocksymbol) 
		{
			//compare the stocksymbol to all of the symbols in the array, if there is a match return the index
			int num = 0;
			for(int i = 0; i < stockArry.length; i ++)
			{
				if(stockArray[i].getSymbol().equals(stocksymbol))
				{
					num = i;
				}
			}
			
			return num;
				
		}
	
	//method to display stock stats
	public static void displayStockStats(Stock[] arr) {
		//display all of the stats for that stock
		
		//ask what stock they want to see information for
		System.out.println("What stock are you interested in? Please enter the symbol");
		Scanner input = new Scanner(System.in);
		String next = input.nextLine();
		
		//find that stock in the array
		for(Stock x : arr) {
			if(x.getSymbol().equals(next)) {
				//ask the user for the current price of the stock
				System.out.println("What is the current price of this stock?");
				double next1 = input.nextDouble();
				
				//display the information to the user
				System.out.println("Stock stats for " + x.getName() + 
						":\n\nCurrent Price: " + next1 + 
						"\nLast Recorded Price: " + x.getLastPrice() + 
						"\nLast Year High: " + x.getYearHigh() + 
						"\nLast Year Low: " +x.getYearLow());
				
				//set the current price as the last recorded price
				x.setLastPrice(next1);
				break;
			}
		}
	}
	
	//stock class that is the main object
	static class Stock
	{
		//data fields
		private String name, symbol;
		private double lastPrice, yearLow, yearHigh;
		private int numStocks;
		
		//constructor
		Stock(String name, String symbol, double yearLow, double yearHigh, double lastPrice)
		{
			this.name = name;
			this.symbol = symbol;
			this.yearHigh = yearHigh;
			this.yearLow = yearLow;
			this.lastPrice = lastPrice;
		}
		
		
		//methods
		
		//getters
		public String getName() {
			return name;
		}
		
		public String getSymbol() {
			return symbol;
		}
		
		public double getLastPrice() {
			return lastPrice;
		}
		
		public double getYearLow() {
			return yearLow;
		}
		
		public double getYearHigh() {
			return yearHigh;
		}
		
		public int getNumStocks() {
			return numStocks;
		}
		
		//set last price
		public void setLastPrice(double input) {
			this.lastPrice = input;
		}	
	}
}

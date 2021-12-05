package damianshw;

import java.util.Scanner;

import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.File;
import javax.swing.filechooser.FileSystemView;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//testing to see if this comment made it in
public class dmanshw {

	// the main array
	public static Stock[] stockList = new Stock[10];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// main logic

		// run the method to find the file that needs to be read from
		importStocks();

		// main loop that will allow the user to add a stock, get stats, and exit
		int exit = 0;
		while (exit == 0) {

			/*
			 * ask the user what they want to do
			 * Scanner in = new Scanner(System.in);
			 * System.out.println("Please choose between the 3 options:
			 * \n0 - Add a stock to the database
			 * \n1 - Get statistics about a stock
			 * \n2 - Exit the program");
			 * int userinput = in.nextInt();
			 */

			// trying to add joption as the user interface instead of just the console
			// add the string to be shown

			// get an answer from the user
			int choice = getIntegerInput(true, 0, true, 3, "Please choose between the 3 options: \n0 - Add a stock to the database \n1 - Get statistics about a stock\n2 - Export the stocks to a file\n3 - Exit the program\n\nPlease enter your choice: ", "That is not a valid input");

			// switch statement to decide what to do
			switch (choice) {
				case 0:
					// add a stock to the databasef
					addStock(stockList);
					break;

				case 1:
					// displays stats of a stock for the user
					displayStockStats(stockList);

					break;

				case 2:
					// this case will export the data in the array to an output file
					exportStocks(stockList);
					break;

				case 3:
					// exit the program
					JOptionPane.showMessageDialog(null, "Thank you for using the program! ");
					exit = 1;
					break;

				default:
					break;
			}
		}
	}

	// proper method for getting integer inputs
	public static int getIntegerInput(boolean lowerLimitFlag, int lowerLimit, boolean upperLimitFlag,
			int upperLimit, String prompt, String errorMsg) {

		boolean lowerLimitFlag1 = lowerLimitFlag;
		int lowerLimit1;
		boolean upperLimitFlag1 = upperLimitFlag;
		int upperLimit1;
		String prompt1 = prompt;
		String errorMsg1 = errorMsg;
		String input;
		int temp = 0;
		Boolean soso = true;

		if (lowerLimitFlag1 == true) {
			lowerLimit1 = lowerLimit;
		}

		if (upperLimitFlag == true) {
			upperLimit1 = upperLimit;
		}

		while (soso) {

			try {
				input = JOptionPane.showInputDialog(prompt1);
				int userInput = Integer.parseInt(input);
				temp = userInput;

				if (upperLimitFlag) {
					if (temp > upperLimit) {
						continue;

					}
				}
				if (lowerLimitFlag) {
					if (temp < lowerLimit) {
						continue;
					}
				}
				soso = false;
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, errorMsg1);

			} catch (Exception e) {

			}
		}
		return temp;
	}

	// method to import the stock data from a file instead of hard coding it
	public static void importStocks() {
		// use j file chooser to find the file the user wants to read from, then import
		// that as the stock data
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();

			// now we have the file, read each line and split by comma into an array
			// create a file Reder
			try {
				FileReader inStream = new FileReader(selectedFile);
				BufferedReader fileReader = new BufferedReader(inStream);

				// read the file and put each line into an array
				String read;
				while ((read = fileReader.readLine()) != null) {
					// testing
					System.out.println("The line that is being read in is: " + read);

					// split the line up
					String[] line = read.split(",");

					// trim all of the whitespace from the strings
					for (String x : line) {
						x = x.trim();
					}

					// get rid of any whitespaces present in the beginning of the string
					for (String y : line) {
						y = y.strip();
					}

					// then set all of the array indexes to variables to construct a stock object
					String name = line[0].trim();
					System.out.println(name);

					String symbol = line[1].trim();
					System.out.println(symbol);
					double lastPrice = Double.parseDouble(line[2].trim());

					double yearLow = Double.parseDouble(line[3].trim());

					double yearHigh = Double.parseDouble(line[4].trim());

					// build the object
					Stock temp = new Stock(name, symbol, yearLow, yearHigh, lastPrice);

					// add the stock to the main stock array
					for (int i = 0; i < stockList.length; i++) {
						if (stockList[i] == null) {
							stockList[i] = temp;

							break;
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		// print the stock list to test if everything is there
		/*
		 * for(Stock var : stockList)
		 * {
		 * System.out.println("name of the stock is: " + var.name);
		 * System.out.println("symbol of the stock is: " + var.symbol);
		 * System.out.println("last price of the stock is" + var.lastPrice);
		 * System.out.println("highprice of the stock is " + var.yearHigh);
		 * System.out.println("lowprice of the stock is" + var.yearLow);
		 * }
		 */
	}

	// method to export the data from the array in to a file
	public static void exportStocks(Stock[] arr) {
		try {
			// create the file that we will write too
			File exportFile = new File("stocks.txt");

			// create the filewriter
			FileWriter writer = new FileWriter(exportFile);

			// now go through each index of the array and write it to a file in the same
			// format
			// as the input file

			for (Stock x : stockList) {
				// write each element of the stock to the file separated by a comma and a space
				writer.write(x.getName() + ", " + x.getSymbol() + ", " + x.getLastPrice() + ", " + x.getYearLow() + ", "
						+ x.getYearHigh());

				// DO THE SAME THING WITH PRINT OUT TO TEST
				System.out.println(x.name + ", " + x.symbol + ", " + x.lastPrice + ", " + x.yearLow + ", " + x.yearHigh);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException n){
			n.printStackTrace();
		}
	}

	// method to delete a stock from the list
	public static void deleteStock(Stock[] arr) {
		// ask the user what stock they would like to delete
		String symbol = JOptionPane.showInputDialog(null,
				"Please enter the symbol of the stock you would like to delete: ");

		// check to make sure the database isnt empty
		if (arr.length < 1) {
			JOptionPane.showMessageDialog(null, "Sorry the database is completely empty, please try something else.");
		} else {
			// delete the stock from the database
			for (int i = 0; i < arr.length; i++) {
				// run if statment to match the symbols
				if ((arr[i].symbol).equalsIgnoreCase(symbol)) {
					// delete the stock from the database (set it to null)
					arr[i] = null;
					JOptionPane.showMessageDialog(null, "Successfully deleted " + symbol.toUpperCase());
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Sorry, that stock was not found.");
					break;
				}
			}
		}
	}

	// method to add stock to stocklist
	public static void addStock(Stock[] arr) {
		// check to make sure the array isnt full
		if (arr.length > 10) {
			// cant do anything because array is full
			JOptionPane.showMessageDialog(null, "Sorry the database is currently full, please try something else.");
		} else {

			// joption version
			String tempName = JOptionPane.showInputDialog(null, "Please enter the name of the stock: ");

			String tempSymbol = JOptionPane.showInputDialog(null, "Please enter the symbol of the stock: ");

			double tempLastPrice = Double
					.parseDouble(JOptionPane.showInputDialog(null, "Please enter the last recorded price of the stock: "));

			double tempHighPrice = Double.parseDouble(
					JOptionPane.showInputDialog(null, "Please enter the highest recorded price in the past year of the stock: "));

			double tempLowPrice = Double.parseDouble(
					JOptionPane.showInputDialog(null, "Please enter the lowest recorded price in the past year of the stock: "));

			// build the stock
			Stock tempStock = new Stock(tempName, tempSymbol, tempLowPrice, tempHighPrice, tempLastPrice);

			// add it to the array, the first instance of the array having a null index
			for (int i = 0; i < arr.length - 1; i++) {
				// find an index that is null
				if (arr[i] == null) {
					// add the stock that was created to this array
					arr[i] = tempStock;
					JOptionPane.showMessageDialog(null, "The stock was added to the database.");
					break;
				}
			}
		}
	}

	// method to check if a stock is in the main list
	public static int isStockInList(Stock[] stockArray, String stocksymbol) {
		// compare the stocksymbol to all of the symbols in the array, if there is a
		// match return the index
		int num = 0;
		for (int i = 0; i < stockArry.length; i++) {
			if (stockArray[i].getSymbol().equalsIgnoreCase(stocksymbol)) {
				num = i;
			}
		}

		return num;

	}

	// method to display stock stats
	public static void displayStockStats(Stock[] arr) {
		// display all of the stats for that stock

		// ask what stock they want to see information for
		String next = JOptionPane.showInputDialog(null, "What stock are you interested in? Please enter the symbol: ");

		// find that stock in the array
		for (Stock x : arr) {

			if (x.getSymbol().equalsIgnoreCase(next)) {
				// ask the user for the current price of the stock
				double next1 = Double
						.parseDouble(JOptionPane.showInputDialog(null, "What is the current price of this stock? "));

				// display the information to the user
				JOptionPane.showMessageDialog(null, "Stock stats for " + x.getName() +
						":\n\nCurrent Price: " + next1 +
						"\nLast Recorded Price: " + x.getLastPrice() +
						"\nLast Year High: " + x.getYearHigh() +
						"\nLast Year Low: " + x.getYearLow());

				// set the current price as the last recorded price
				x.setLastPrice(next1);
				break;
			}
		}
	}

	// stock class that is the main object
	static class Stock {
		// data fields
		private String name, symbol;
		private double lastPrice, yearLow, yearHigh;
		private int numStocks;

		// constructor
		Stock(String name, String symbol, double yearLow, double yearHigh, double lastPrice) {
			this.name = name;
			this.symbol = symbol;
			this.yearHigh = yearHigh;
			this.yearLow = yearLow;
			this.lastPrice = lastPrice;
		}

		// methods

		// getters
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

		// set last price
		public void setLastPrice(double input) {
			this.lastPrice = input;
		}
	}
}

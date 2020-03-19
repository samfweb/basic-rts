import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
/**
 * Populates the world with inital objects.
 */
public class Populate {

	public static final int NAME_LIST_SIZE = 1000;
	//string formats to read from objects.csv for inital world population
	public static final String COMM_CENTRE = "command_centre";
	public static final String METAL_MINE = "metal_mine";
	public static final String UNOBTAINIUM_MINE = "unobtainium_mine";
	public static final String PYLON = "pylon";
	public static final String ENGINEER = "engineer";
	
	/**
	 * Populates the world with inital objects from csv.
	 *
	 * @param world the world to populate
	 * @param csvPath the path to the csv
	 */
	public static void world(World world, String csvPath) {
		//reads objects.csv file
		try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	String cells[] = line.split(",");
		        String type = cells[0];
		        int x = Integer.parseInt(cells[1]);
		        int y = Integer.parseInt(cells[2]);
		        
		        //Creates units
		        if (type.equals(COMM_CENTRE)) {
		        	new CommandCentre(world, x, y);
		        }
		        else if (type.equals(METAL_MINE)) {
		        	new MetalMine(MetalMine.METAL_DEFAULT_AMOUNT, world, x, y);
		        }
		        else if (type.equals(UNOBTAINIUM_MINE)) {
		        	new UnobtainiumMine(UnobtainiumMine.UNOBTAINIUM_DEFAULT_AMOUNT, world, x, y);
		        }
		        else if (type.equals(PYLON)) {
		        	new Pylon(world, x, y);
		        }
		        else if (type.equals(ENGINEER)) {
		        	new Engineer(world, x, y);
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * Populates the name list
	 *
	 * @param path the path to the name list csv
	 * @return the name list as an array of strings
	 * @throws FileNotFoundException file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String[] nameList(String path) throws FileNotFoundException, IOException {
		
		String[] nameList = new String[NAME_LIST_SIZE];
		
		Random rand = new Random();
		int count = rand.nextInt(1000);
		
		//reads csv of randomly generated names
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String text;
		    while ((text = br.readLine()) != null) {
		    	String cells[] = text.split(",");
		        nameList[count] = cells[0]+" "+cells[1];
		        if (count >= 999) {
		        	count = 0;
		        	}
		        else {count++;}
		    }
		}
		
		return nameList;
	}
}

import org.newdawn.slick.Graphics;
/**
 * Handles the player's text display and resource counts.
 */
public class Player {
	
	public static final int[] RESOURCE_DISPLAY_XY = {32, 32};
	public static final String METAL_DISPLAY = "Metal:  ";
	public static final String UNOBTAINIUM_DISPLAY = "Unobtanium:  ";
	public static final int[] INSTRUCTION_DISPLAY_XY = {32, 100};
	public static final String COMMAND_CENTRE_TEXT = "1- Create Scout\n2- Create Builder\n"
													+ "3- Create Engineer";
	public static final String FACTORY_TEXT = "1- Create Truck";
	public static final String TRUCK_TEXT = "1- Create Command Centre";
	public static final String BUILDER_TEXT = "1- Create Factory";
	
	private int metalCount = 1500;
	private int unobtainiumCount = 0;
	private int activePylonCount = 0;
	private SelectableObject selectedObject = null; 
	
	/**
	 * Updates the selected object.
	 *
	 * @param world the world
	 */
	public void update(World world) {
		this.selectedObject = world.getSelection();
	}
	
	/**
	 * Renders the player text overlay.
	 *
	 * @param g the Slick2D graphics handler
	 */
	public void render(Graphics g) {
		//Draw the player's metal count
		g.drawString(METAL_DISPLAY+metalCount+"\n"+UNOBTAINIUM_DISPLAY+unobtainiumCount, 
				RESOURCE_DISPLAY_XY[0], RESOURCE_DISPLAY_XY[1]);
		//renders relevant instructions
		renderInstructions(g);
	}
	
	/**
	 * Renders relevant instructions based on selection.
	 *
	 * @param g the g
	 */
	public void renderInstructions(Graphics g) {
		int x = INSTRUCTION_DISPLAY_XY[0];
		int y = INSTRUCTION_DISPLAY_XY[1];
		if (selectedObject instanceof CommandCentre) {
			g.drawString(COMMAND_CENTRE_TEXT, x, y);
		}
		else if (selectedObject instanceof Factory) {
			g.drawString(FACTORY_TEXT, x, y);
		}
		else if (selectedObject instanceof Truck) {
			g.drawString(TRUCK_TEXT, x, y);
		}
		else if (selectedObject instanceof Builder) {
			g.drawString(BUILDER_TEXT, x, y);
		}
	}
	
	
	/* Getters and setters ------------------------------------------ */
	
	/**
	 * Gets player metal count.
	 *
	 * @return the metal count
	 */
	public int getMetal() {
		return this.metalCount;
	}
	
	/**
	 * Gets player unobtanium count.
	 *
	 * @return the unobtanium count
	 */
	public int getUnobtanium() {
		return this.unobtainiumCount;
	}
	
	/**
	 * Gets player active pylon count.
	 *
	 * @return the active pylons count
	 */
	public int getActivePylons() {
		return this.activePylonCount;
	}
	
	/**
	 * Adds one active pylon.
	 */
	public void addActivePylon() {
		this.activePylonCount++;
	}
	
	/**
	 * Adds amount to player's metal count.
	 *
	 * @param amount the amount of metal to add
	 */
	public void addMetal(int amount) {
		this.metalCount += amount;
	}
	
	/**
	 * Adds amount to player's unobtainium count.
	 *
	 * @param amount the amount of unobtainium to add
	 */
	public void addUnobtainium(int amount) {
		this.unobtainiumCount += amount;
	}
	
	/**
	 * Spends metal.
	 *
	 * @param amount the amount of metal to spend
	 */
	public void spendMetal(int amount) {
		this.metalCount -= amount;
	}
	
	/**
	 * Spend unobtainium.
	 *
	 * @param amount the amount of unobtainium to spend
	 */
	public void spendUnobtainium(int amount) {
		this.unobtainiumCount -= amount;
	}
}


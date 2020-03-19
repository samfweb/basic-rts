import org.newdawn.slick.SlickException;
/**
 * An object that holds Unobtainium for the player to collect from. 
 */
public class UnobtainiumMine extends Resource {
	
	public final static String UNOBTAINIUM_MINE_PATH = "assets/resources/unobtainium_mine.png";
	public static final int UNOBTAINIUM_DEFAULT_AMOUNT = 50;
	
	/**
	 * Instantiates a new unobtanium mine.
	 *
	 * @param resourceRemaining the resource remaining
	 * @param world the world
	 * @param x the x coordinate 
	 * @param y the y coordinate
	 * @throws SlickException
	 */
	public UnobtainiumMine(int resourceRemaining, World world, int x, int y) throws SlickException {
		super(resourceRemaining, world, x, y);
		this.setImage(UNOBTAINIUM_MINE_PATH);
	}
}

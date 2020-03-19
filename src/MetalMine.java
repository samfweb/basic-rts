import org.newdawn.slick.SlickException;
/**
 * An object that holds Metal for the player to collect from. 
 */
public class MetalMine extends Resource {
	public final static String METAL_MINE_PATH = "assets/resources/metal_mine.png";
	public static final int METAL_DEFAULT_AMOUNT = 2;

	/**
	 * Instantiates a new metal mine.
	 *
	 * @param resourceRemaining the resource remaining
	 * @param world the world
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @throws SlickException
	 */
	public MetalMine(int resourceRemaining, World world, int x, int y) throws SlickException {
		super(resourceRemaining, world, x, y);
		this.setImage(METAL_MINE_PATH);
	}
}

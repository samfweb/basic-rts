import org.newdawn.slick.SlickException;
/**
 * Provides the highlight path for all buildings.
 */
public abstract class Building extends SelectableObject {	
	
	public static final String LARGE_HIGHLIGHT_PATH = "assets/highlight_large.png";
	
	/**
	 * Instantiates a new building.
	 *
	 * @param world the world the building is in
	 * @param x the x coordinate to spawn
	 * @param y the y coordinate to spawn
	 * @throws SlickException
	 */
	public Building(World world, int x, int y) throws SlickException {
		super(world, x, y);
		setHighlight(LARGE_HIGHLIGHT_PATH);
		world.addToBuildingList(this);
	}
}

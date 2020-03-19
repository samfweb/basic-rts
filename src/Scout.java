import org.newdawn.slick.SlickException;
/*
 * The scout sprite.
 */
public class Scout extends Sprite {
	public final static String SCOUT_PATH = "assets/units/scout.png";
	
	//Speed of scout sprite in pixels/millisecond
	public static final double SCOUT_SPEED = 0.3;

	/**
	 * Instantiates a new scout.
	 *
	 * @param world the world
	 * @param x the x coordinate for spawn
	 * @param y the y coordinate for spawn
	 * @throws SlickException
	 */
	public Scout(World world, int x, int y) throws SlickException {
		super(world, x, y);
		this.setImage(SCOUT_PATH);
		this.setSpeed(SCOUT_SPEED);
	}
}

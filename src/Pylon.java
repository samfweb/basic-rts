import org.newdawn.slick.SlickException;
/**
 * A building that can be activated to raise the amount of resource collected.
 */
public class Pylon extends Building {
	public final static String PYLON_PATH_INACTIVE = "assets/buildings/pylon.png";
	public final static String PYLON_PATH_ACTIVE = "assets/buildings/pylon_active.png";
	public final static String PYLON_NAME_INACTIVE = "Unactivated Pylon";
	public final static String PYLON_NAME_ACTIVE = "Activated Pylon";
	public static final int PYLON_RANGE = 32; //how far sprite can be before Pylon activated
	
	private boolean inactive = true;


	/**
	 * Instantiates a new pylon.
	 * 
	 * @param world the world
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @throws SlickException
	 */
	public Pylon(World world, int x, int y) throws SlickException {
		super(world, x, y);
		this.setName(PYLON_NAME_INACTIVE);
		this.setImage(PYLON_PATH_INACTIVE);
	}
	
	/** 
	 * Checks for and updates pylon activation
	 * @see WorldObject#update(World)
	 */
	@Override
	public void update(World world) {
		super.update(world);
		WorldObject closestSprite = Calculate.closestWorldObject(world, Calculate.SPRITE, getX(), getY());
		if (inactive) {
			if (closestSprite != null) {
				double closestSpriteDist = Calculate.distance(this, closestSprite);
				if (closestSpriteDist < PYLON_RANGE) {
					this.activate();
					world.getPlayer().addActivePylon();
				}
			}
		}
	}
	
	/**
	 * Activates the pylon.
	 */
	public void activate() {
		try {
			this.setImage(PYLON_PATH_ACTIVE);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.setName(PYLON_NAME_ACTIVE);
		this.inactive = false;
	}
}

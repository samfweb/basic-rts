import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 * Object that can exist in world. Has x and y coordinates.
 */
public abstract class WorldObject {
	
	public static final int NAME_X_OFFSET = -32;
	public static final int NAME_Y_OFFSET = 32;
	public static final int LABEL_X_OFFSET = -50;
	public static final int LABEL_Y_OFFSET = 48;
	
	private double xLocation, yLocation;
	private float xOnScreen, yOnScreen;
	private World world;
	private Image image;
	private Player player;
	private Camera camera;
	private Map map;
	//If true, the world object is scheduled for removal
	private boolean removed = false;
	//Creator object for world object creation, only assigned with setCreator() method
	private Creator creator;
	
	/**
	 * Instantiates a new world object.
	 *
	 * @param world the world to instantiate in
	 * @param x x coordinate to spawn
	 * @param y y coordinate to spawn
	 */
	public WorldObject(World world, int x, int y) {
		//Sets world object's inital attributes
		this.world = world;
		this.camera = world.getCamera();
		this.map = world.getMap();
		this.player = world.getPlayer();
		this.xLocation = x;
		this.yLocation = y;
		//Notifies observer that world object list needs updating
		world.modifyWorldObjects();
	}
	
	/**
	 * Updates the object's on-screen location and creator (is applicable).
	 *
	 * @param world the world the object is in.
	 */
	public void update(World world) {
		updateOnScreenLocation();
		if (creator != null) {
			creator.update(world.getDelta());
		}
	}
	
	/**
	 * Renders the object on-screen.
	 *
	 * @param g the Slick2D Graphics object
	 */
	public void render(Graphics g) {
		image.drawCentered(xOnScreen, yOnScreen);
		//Draws a countdown if the object is creating
		if (creator != null && creator.isCreating()) {
			g.drawString(" <CREATING>\nTime left: "+creator.getTimeRemaining(), 
					getXOnScreen()+LABEL_X_OFFSET, getYOnScreen()+LABEL_Y_OFFSET);
		}
	}
	
	/**
	 * Update's the WorldObject's on screen location based on camera location.
	 */
	public void updateOnScreenLocation() {
		xOnScreen = (float)camera.globalXToScreenX(this.getX());
		yOnScreen = (float)camera.globalYToScreenY(this.getY());
	}
	
	/**
	 * Schedules the removal of an object to occur after object updates.
	 */
	public void scheduleRemoval() {
		world.scheduleRemoval(this);
		world.modifyWorldObjects();
	}
	
	/* Getters and setters ---------------------------------------------- */
	
	/**
	 * Sets the image.
	 *
	 * @param imagePath the new image
	 * @throws SlickException
	 */
	public void setImage(String imagePath) throws SlickException {
		this.image = new Image(imagePath);
	}
	
	/**
	 * Sets the creator.
	 *
	 * @return the creator
	 */
	public Creator setCreator() {
		this.creator = new Creator(world, this);
		return creator;
	}
	
	public boolean isCreating() {
		if (creator != null) {
			return creator.isCreating();
		}
		else {
			return false;
		}
	}
	
	/**
	 * Sets the x location.
	 *
	 * @param x the new x location
	 */
	public void setX(double x) {
		this.xLocation = x;
	}
	
	/**
	 * Sets the y location.
	 *
	 * @param y the new y location
	 */
	public void setY(double y) {
		this.yLocation = y;
	}
	
	/**
	 * Gets the x location.
	 *
	 * @return the x location
	 */
	public double getX() {
		return this.xLocation;
	}
	
	/**
	 * Gets the y location.
	 *
	 * @return the y location
	 */
	public double getY() {
		return this.yLocation;
	}
	
	/**
	 * Gets the x on screen.
	 *
	 * @return the x on screen
	 */
	public float getXOnScreen() {
		return this.xOnScreen;
	}
	
	/**
	 * Gets the y on screen.
	 *
	 * @return the y on screen
	 */
	public float getYOnScreen() {
		return this.yOnScreen;
	}
	
	/**
	 * Gets the world.
	 *
	 * @return the world the object is in.
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Gets the camera.
	 *
	 * @return the camera
	 */
	public Camera getCamera() {
		return this.camera;
	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map the object is on.
	 */
	public Map getMap() {
		return this.map;
	}
	
	/**
	 * Gets the player.
	 *
	 * @return the player.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Checks if removed.
	 *
	 * @return true, if WorldObject is scheduled to be removed
	 */
	public boolean isRemoved() {
		return this.removed;
	}
}

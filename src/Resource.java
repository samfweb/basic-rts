import org.newdawn.slick.Graphics;
/**
 * Provides a framework for objects that the player may collect resources from.
 */
public abstract class Resource extends WorldObject {
	
	private int resourceRemaining;
	
	/**
	 * Instantiates a new resource.
	 *
	 * @param resourceRemaining the amount of resource held
	 * @param world the world the resource is in
	 * @param x the x coordinate for spawn
	 * @param y the y coordinate for spawn
	 */
	public Resource(int resourceRemaining, World world, int x, int y) {
		super(world, x, y);
		this.resourceRemaining = resourceRemaining;
		
		world.addToResourceList(this);
	}
	
	/** 
	 * Updates the object's on-screen location. <br>
	 * Additionally removes the resource from the world if resource remaining is 0.
	 * @see WorldObject#update(World)
	 */
	@Override
	public void update(World world) {
		super.update(world);
		if (resourceRemaining <= 0) {
			this.scheduleRemoval();
		}
	}
	
	/**
	 * @see WorldObject#render(org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		//Show's player the amount of resources remaining
		g.drawString(""+resourceRemaining, getXOnScreen(), getYOnScreen());
	}
	
	/* Getters & Setters --------------------------------------------------------- */
	
	/**
	 * Gets the resource remaining.
	 *
	 * @return the resource remaining
	 */
	public double getResourceRemaining() {
		return this.resourceRemaining;
	}
	
	/**
	 * Loses resource.
	 *
	 * @param amount the amount of resource to lose
	 */
	public void loseResource(int amount) {
		this.resourceRemaining -= amount;
	}
}

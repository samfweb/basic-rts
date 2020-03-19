import org.newdawn.slick.SlickException;
/**
 * Provides the framework for a sprite that can build the factory. 
 */
public class Builder extends Sprite {
	public final static String BUILDER_PATH = "assets/units/builder.png";
	
	//Speed of sprite in pixels/millisecond
	public static final double BUILDER_SPEED = 0.1;
	public static final int FACTORY_TIME_COST = 10000;
	public static final int FACTORY_METAL_COST = 100;
	
	private Creator creator;

	/**
	 * Instantiates a new builder.
	 *
	 * @param world the world the builder is in
	 * @param x the x coordinate to spawn
	 * @param y the y coordinate to spawn
	 * @throws SlickException the slick exception
	 */
	public Builder(World world, int x, int y) throws SlickException {
		super(world, x, y);
		this.setImage(BUILDER_PATH);
		this.setSpeed(BUILDER_SPEED);
		creator = setCreator();
	}
	
	/** 
	 * @see SelectableObject#action1()
	 */
	@Override
	public void action1() {
		//Begins building a factory
		creator.beginCreating(Creator.FACTORY, FACTORY_METAL_COST, FACTORY_TIME_COST);
	}
}

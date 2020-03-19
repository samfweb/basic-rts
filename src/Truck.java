import org.newdawn.slick.SlickException;
/**
 * A sprite that can be deployed into a Command Centre.
 */
public class Truck extends Sprite {
	public final static String TRUCK_PATH = "assets/units/truck.png";
	//Speed of sprite in pixels/millisecond
	public static final double TRUCK_SPEED = 0.25;
	public final static int COMMAND_CENTRE_METAL_COST = 0;
	public final static int COMMAND_CENTRE_TIME_COST = 15000; //time cost in milliseconds
	
	private boolean hasDeployed = false;
	private Creator creator;

	/**
	 * Instantiates a new truck.
	 *
	 * @param world the world
	 * @param x the x coordinate for spawn
	 * @param y the y coordinate for spawn
	 * @throws SlickException
	 */
	public Truck(World world, int x, int y) throws SlickException {
		super(world, x, y);
		this.setImage(TRUCK_PATH);
		this.setSpeed(TRUCK_SPEED);
		this.creator = setCreator();
	}
	
	/**
	 * Updates truck deploy time if deploying
	 * @see Sprite#update(World)
	 */
	@Override
	public void update(World world) {
		super.update(world);

		if (creator.isCreating()) {
			hasDeployed = true;
		}
		//Once the truck has finished deploying a command centre, remove it
		if (!creator.isCreating() && hasDeployed) {
			this.scheduleRemoval();
		}
	}
	
	/**
	 * @see SelectableObject#action1()
	 */
	@Override
	public void action1() {
		creator.beginCreating(Creator.COMMAND_CENTRE, 
				COMMAND_CENTRE_METAL_COST, COMMAND_CENTRE_TIME_COST);
	}
}

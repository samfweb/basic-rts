import org.newdawn.slick.SlickException;

/**
 * Can be used to create world objects.
 */
public class Creator {	
	
	public final static int NO_BUILDING  	= 0;
	public final static int COMMAND_CENTRE  = 1;
	public final static int FACTORY		    = 2;
	public final static int SCOUT    		= 3;
	public final static int BUILDER  		= 4;
	public final static int ENGINEER 		= 5;
	public final static int TRUCK 	 		= 6;
	
	private World world;
	private WorldObject worldObject;
	private int creatingTimeRequired;
	private int creatingType;
	private boolean isCreating=false;
	private int timeCreating = 0, timeRemaining;
	
	/**
	 * Instantiates a new creator.
	 *
	 * @param world the world
	 * @param worldObject the world object
	 */
	public Creator(World world, WorldObject worldObject) {
		this.world = world;
		this.worldObject = worldObject;
	}
	
	/**
	 * Begins creating a new worldObject.
	 *
	 * @param creatingType the creating type
	 * @param creatingCost the creating cost
	 * @param creatingTimeRequired the creating time required
	 */
	public void beginCreating(int creatingType, int creatingCost, int creatingTimeRequired) {
		//If possible to build
		if (world.getPlayer().getMetal() > creatingCost
				&& !(world.getMap().isOccupied(worldObject.getX(), worldObject.getY()))
				&& isCreating == false) {
			//Spend metal
			world.getPlayer().spendMetal(creatingCost);
			//Begin building
			this.creatingType = creatingType;
			this.creatingTimeRequired = creatingTimeRequired;
			this.isCreating = true;
		}
	}
	
	/**
	 * Updates the creator's creating time, and creates once threshold is reached.
	 *
	 * @param delta the time in milliseconds to update by.
	 */
	public void update(int delta) {
		if (isCreating) {
			timeCreating += delta;
			//Calculates time remaining, /1000 to convert to seconds and +1 to round up
			timeRemaining = ((creatingTimeRequired-timeCreating)/1000) + 1;
			if (timeCreating >= creatingTimeRequired) {
				create(creatingType);
				reset();
			}
		}
	}
	
	/**
	 * Creates a new WorldObject.
	 *
	 * @param creatingType the type to create
	 */
	public void create(int creatingType) {
		try {
			switch (creatingType) {
			
			case COMMAND_CENTRE:
				new CommandCentre(world, (int)worldObject.getX(), (int)worldObject.getY());
				break;
			case FACTORY:
				new Factory(world, (int)worldObject.getX(), (int)worldObject.getY());
				break;
			case SCOUT:
				new Scout(world, (int)worldObject.getX(), (int)worldObject.getY());
				break;
			case BUILDER:
				new Builder(world, (int)worldObject.getX(), (int)worldObject.getY());
				break;
			case ENGINEER:
				new Engineer(world, (int)worldObject.getX(), (int)worldObject.getY());
				break;
			case TRUCK:
				new Truck(world, (int)worldObject.getX(), (int)worldObject.getY());
				break;
			default:
				break;
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Resets training.
	 */
	public void reset() {
		this.isCreating = false;
		this.timeCreating = 0;
	}
	
	/* Getters ------------------------------------------------------------ */

	/**
	 * Checks if is building.
	 *
	 * @return true, if is building
	 */
	public boolean isCreating() {
		return isCreating;
	}
	
	/**
	 * Gets the time remaining.
	 *
	 * @return the time remaining
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}
}

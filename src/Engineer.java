import org.newdawn.slick.SlickException;
/**
 * A sprite that can mine resources.
 */
public class Engineer extends Sprite {
	public static final String ENGINEER_PATH = "assets/units/engineer.png";
	public static final double ENGINEER_SPEED = 0.1;
	public static final int BASE_CAPACITY = 2;
	//Required amount of time in milliseconds to spend near resource before collection
	public static final int REQUIRED_COLLECTION_TIME = 5000;
	//Required distance to interact with resources/command centre
	public static final int COLLECT_DIST = 32;
	//Numeric integers corresponding to different resources
	public static final int NO_RESOURCE = 0;
	public static final int METAL = 1;
	public static final int UNOBTANIUM = 2;
	
	private World world;
	private Player player;
	private int resourceCount = 0;
	private int resourceType = 0;
	private double miningTime = 0;
	private Resource targetResource = null;

	/**
	 * Instantiates a new engineer.
	 *
	 * @param world the world the engineer is in
	 * @param x the x coordainte for spawn
	 * @param y the y coordinate for spawn
	 * @throws SlickException
	 */
	public Engineer(World world, int x, int y) throws SlickException {
		super(world, x, y);
		this.world = world;
		this.player = world.getPlayer();
		this.setImage(ENGINEER_PATH);
		this.setSpeed(ENGINEER_SPEED);
	}
	
	/**
	 * Sets target resource to resource near global mouse location
	 * @see Sprite#actionRightClick(double, double)
	 */
	@Override
	public void actionRightClick(double targetX, double targetY) {
		//Assign offset mouse coordinates as target
		super.actionRightClick(targetX, targetY);
		//Check if there's a resource close to click
		WorldObject closestResource = Calculate.closestWorldObject(world, Calculate.RESOURCE, 
																targetX, targetY);
		double closestResourceDist = Calculate.distance(targetX, targetY, 
				closestResource.getX(), closestResource.getY());
		//There is a resource close to click
		if (closestResourceDist < COLLECT_DIST) {
			//Set resource to target resource
			this.targetResource = (Resource)closestResource;
		}
	}
	
	/**
	 * Updates resource collection/deposit
	 * @see Sprite#update(World)
	 */
	@Override
	public void update(World world) {
		super.update(world);
		miningHandler(world);
		depositHandler(world);
	}
	
	/**
	 * Handles the resource mining process for the update method
	 *
	 * @param world the world
	 */
	public void miningHandler(World world) {
		//Make sure target resource exists
		if (targetResource != null && world.getWorldObjectList().contains(targetResource)) {	
			//Check if engineer is near target resource
			double targetResourceDist = Calculate.distance(this.getX(), this.getY(), 
					targetResource.getX(), targetResource.getY());
			
			//If the engineer is close enough and hasn't mined, mine 
			if (targetResourceDist < COLLECT_DIST) {
				//Hasn't mined
				if (resourceType == NO_RESOURCE) {
					this.mine(world.getDelta());
				}
				//Has mined, go deposit
				else {
					goToNearestCommandCentre();
				}
			}
			//If not close, do not mine and clear collection time
			else {
				miningTime = 0;
			}
		}
	}
	
	/**
	 * Handles resource depositing for the update method.
	 *
	 * @param world the world
	 */
	public void depositHandler(World world) {
		//Check if engineer near command centre
		WorldObject closestCommand = Calculate.closestWorldObject(world, Calculate.COMMAND_CENTRE,
									getX(), getY());
		double distance = Calculate.distance(this, closestCommand); 
		//If the engineer is close enough and has resources, deposit
		if (distance < COLLECT_DIST) {
			this.depositResource();
		}
	}
	
	/**
	 * Mines a resource.
	 *
	 * @param timeSpent the time spent mining since last mine (delta)
	 */
	public void mine(int timeSpent) {
		//Update time spent mining
		miningTime += timeSpent;
		//Collect if timeSpent is enough
		if (miningTime >= REQUIRED_COLLECTION_TIME) {
			collectResource(targetResource);
		}
	}
	
	
	/**
	 * Collects a resource and sends engineer to nearest command centre.
	 *
	 * @param resource the resource to collect
	 */
	public void collectResource(Resource resource) {
		//Assign resource type if engineer doesn't have resource
		if(resourceType == NO_RESOURCE) {
			if (resource instanceof MetalMine) {
				resourceType = METAL;
			}
			if (resource instanceof UnobtainiumMine) {
				resourceType = UNOBTANIUM;
			}
		}
		//Collect the resource if it exists
		while (resourceCount < BASE_CAPACITY+player.getActivePylons() 
				//The engineer doesn't have a resource
				&& resourceType != NO_RESOURCE 
				//The resource has resources remaining
				&& resource.getResourceRemaining() > 0) {
			resourceCount += 1;
			resource.loseResource(1);
		}
		//Send engineer back to command centre
		goToNearestCommandCentre();
	}
	
	/**
	 * Deposits engineer's resources to player.
	 */
	public void depositResource() {
		if (resourceType == METAL) {
			player.addMetal(resourceCount);
		}
		else if (resourceType == UNOBTANIUM) {
			player.addUnobtainium(resourceCount);
		}
		//Reset carrying to zero
		this.resourceCount = 0;
		this.resourceType = NO_RESOURCE;
		
		//Sends engineer back to resource if he isn't heading somewhere else
		if (targetResource != null && !(isMoving()) ) {
			this.setTarget(targetResource.getX(), targetResource.getY());
		}
	}
	
	/* Small helper functions --------------------------------------------------------- */
	
	/**
	 * Send sprite to nearest command centre.
	 */
	public void goToNearestCommandCentre() {
		WorldObject closestCommandCentre 
		= Calculate.closestWorldObject(world, Calculate.COMMAND_CENTRE, getX(), getY());
	this.setTarget(closestCommandCentre.getX(), closestCommandCentre.getY());
	}
	
	/* Getters ------------------------------------------------------------------------ */
	
	/**
	 * Gets the resource count held by sprite.
	 *
	 * @return the resource count
	 */
	public int getResourceCount() {
		return this.resourceCount;
	}
}

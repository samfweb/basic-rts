import org.newdawn.slick.SlickException;
/** 
 * Provides a framework for player-controlled sprites and their movement.
 */
public abstract class Sprite extends SelectableObject {	
	
	public static final String HIGHLIGHT_PATH = "assets/highlight.png";
	
	private double targetX, targetY;
	private double speedFactor;
	private boolean isMoving = false; 
	
	/**
	 * Instantiates a new Sprite.
	 *
	 * @param world the world
	 * @param x x coordinate to spawn
	 * @param y y coordinate to spawn
	 * @throws SlickException
	 */
	public Sprite(World world, int x, int y) throws SlickException {
		super(world, x, y);
		//Assigns the next name to the sprite
		this.setName(world.getNameList()[world.getWorldObjectList().size()]);
		this.setHighlight(HIGHLIGHT_PATH);
		//Add to sprite list
		world.addToSpriteList(this);	
	}
	
	/**
	 * Updates sprite on-screen location. <br>
	 * Moves the sprite if it has a destination.
	 * @see WorldObject#update(World)
	 * 
	 */
	@Override
	public void update(World world) {
		super.update(world);
		//Move the sprite if it has a destination and isn't creating
    	if (this.isMoving() && !this.isCreating()) {
			this.moveTo(targetX, targetY);
		}
	}
	
	/**
	 * Sets the movement target of the sprite
	 * @see SelectableObject#actionRightClick(double, double)
	 */
	@Override
	public void actionRightClick(double targetX, double targetY) {
		//Assign offset mouse coordinates as target
		this.targetX = targetX;
		this.targetY = targetY;
		this.startMoving();
	}
	
	/**Moves sprite to a location
	* @param targetX - destination in x coordinate
	* @param targetY - destination in y coordinate */
	public void moveTo(double targetX, double targetY) {
		
		double spriteX = getX();
		double spriteY = getY();
		double spriteSpeed = speedFactor*(getWorld().getDelta());

    	double dist = Calculate.distance(spriteX, spriteY, targetX, targetY);
    	
    	//Calculate angle
		double theta = Calculate.getTheta(getX(), getY(), targetX, targetY);
		
		//Calculate distance to move
		double dx = Math.cos(theta)*spriteSpeed;
		double dy = Math.sin(theta)*spriteSpeed;
		
		//Calculate next tile to check if solid
		int nextXTile = this.getMap().getXTileID(spriteX + dx);
		int nextYTile = this.getMap().getYTileID(spriteY + dy);
		
		//If tile is solid
		if ((getMap().isSolidTile(nextXTile, nextYTile))
				|| Math.abs(dist) < 1) { //or sprite is close to target
			//stop sprite
			stopMoving();
			targetX = getX();
			targetY = getY();
		}
		else {
			startMoving();
			moveX(dx);
			moveY(dy);
		}
		
	}
	/* Getters and Setters --------------------------------------------------- */
	
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(double speed) {
		this.speedFactor = speed;
	}
		
	/**
	 * Sets the target.
	 *
	 * @param x the target's x coordinate
	 * @param y the target's y coordinate
	 */
	public void setTarget(double x, double y) {
		this.targetX = x;
		this.targetY = y;
		this.startMoving();
	}
	
	/**
	 * Start the sprite moving.
	 */
	public void startMoving() {
		this.isMoving = true;
	}
	
	/**
	 * Stop the sprite moving.
	 */
	public void stopMoving() {
		this.isMoving = false;
	}
		
	/**
	 * Checks if is moving.
	 *
	 * @return true, if is moving
	 */
	public boolean isMoving() {
		return this.isMoving;
	}
	
	/**
	 * Moves the sprite in the x axis
	 *
	 * @param x the x value to modify sprite's xLocation by
	 */
	public void moveX(double x) {
		this.setX(this.getX()+x);
	}
	
	/**
	 * Moves the sprite in the y axis
	 *
	 * @param y the y value to modify sprite's yLocation by
	 */
	public void moveY(double y) {
		this.setY(this.getY()+y);
	}
}

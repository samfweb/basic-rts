import org.newdawn.slick.Input;

/**
 * Controls what the players sees. <br>
 * 
 * This class has been copied from and subsequently modified using the sample solutions
 * provided for assignment 1. 
 */
public class Camera {
	
	public static final double CAMERA_SPEED = 0.4;
	
	private double x = 0;
	private double y = 0;
	private SelectableObject target;
	private boolean followingTarget = false;
	
	/**
	 * Updates the camera's location
	 *
	 * @param world the world
	 */
	public void update(World world) {

		double targetX=x, targetY=y;
		
		// camera is following a target
		if (followingTarget == true) {
			targetX = target.getX() - App.WINDOW_WIDTH / 2;
			targetY = target.getY() - App.WINDOW_HEIGHT / 2;
		}
		
		// camera is being moved by player
		else {
			double camMovement = world.getDelta()*CAMERA_SPEED;
			if ((world.getInput()).isKeyDown(Input.KEY_W)) {
				targetY -= camMovement;
			}
			else if (world.getInput().isKeyDown(Input.KEY_S)) {
				targetY += camMovement;
			}
			else if (world.getInput().isKeyDown(Input.KEY_A)) {
				targetX -= camMovement;
			}
			else if (world.getInput().isKeyDown(Input.KEY_D)) {
				targetX += camMovement;
			}
		}
		
		// Make sure camera doesn't go beyond edge of map
		x = Math.min(targetX, world.getMapWidth() -	 App.WINDOW_WIDTH);
		x = Math.max(x, 0);
		y = Math.min(targetY, world.getMapHeight() - App.WINDOW_HEIGHT);
		y = Math.max(y, 0);
	}
	
	/**
	 * Tells the camera to follow a target.
	 *
	 * @param target the target
	 */
	public void followObject(SelectableObject target) {
		if (target != null) {
			this.target = target;
			this.followingTarget = true;
		}
		else {
			this.followingTarget = false;
		}
	}
	
	/**
	 * Tells the camera to follow player input.
	 */
	public void followPlayerInput() {
		this.followingTarget = false;
	}
	
	/**
	 * Calculates the screen x coordinate based on the global x
	 *
	 * @param x the global x coordinate
	 * @return the screen x coordinate
	 */
	public double globalXToScreenX(double x) {
		return x - this.x;
	}
	
	/**
	 * Calculates the screen y coordinate based on the global y
	 *
	 * @param y the global y coordinate
	 * @return the screen y coordinate
	 */
	public double globalYToScreenY(double y) {
		return y - this.y;
	}

	
	/**
	 * Calculates the global x coordinate based on the screen x
	 *
	 * @param x the x
	 * @return the global x coordinate
	 */
	public double screenXToGlobalX(double x) {
		return x + this.x;
	}
	
	/**
	 * Calculates the global y coordinate based on the screen y
	 *
	 * @param y the y
	 * @return the global y coordinate
	 */
	public double screenYToGlobalY(double y) {
		return y + this.y;
	}
	
	
}

/** 
 * Provides common calculations such as distance.
 */
public class Calculate {
	
	public final static int SPRITE 			= 1;
	public final static int BUILDING 		= 2;
	public final static int RESOURCE		= 3;
	public final static int COMMAND_CENTRE 	= 4;
	public final static int SELECTABLE 		= 5;
	
	
	/**
	 * Gets the angle theta.
	 *
	 * @param currentX the current X
	 * @param currentY the current Y
	 * @param targetX the target X
	 * @param targetY the target Y
	 * @return the angle theta
	 */
	public static double getTheta(double currentX, double currentY, double targetX, double targetY) {
		
		return Math.atan2(targetY - currentY, targetX - currentX);
	}
	
	/**
	 * Calculate distance between two points
	 *
	 * @param currentX the current X
	 * @param currentY the current Y
	 * @param targetX the target X
	 * @param targetY the target Y
	 * @return the distance between two points as a souble
	 */
	public static double distance(double currentX, double currentY, double targetX, double targetY) {
		
		return (double)Math.sqrt(Math.pow((targetX - currentX),2) + Math.pow((targetY - currentY),2));
	}
	
	/**
	 * Calculate distance between two objects
	 *
	 * @param object1 the 1st object
	 * @param object2 the 2nd object
	 * @return the distance between the two objects
	 */
	public static double distance(WorldObject object1, WorldObject object2) {
		
		return (double)Math.sqrt(Math.pow((object2.getX() - object1.getX()),2) 
				+ Math.pow((object2.getY() - object1.getY()),2));
	}
	
	
	/**
	 * Finds the closest world object, of specified object type.
	 *
	 * @param world the world
	 * @param objectType the object type
	 * @param targetX the target X
	 * @param targetY the target Y
	 * @return the world object that is closest
	 */
	public static WorldObject closestWorldObject(World world, int objectType, 
		
		//Set default closest object and distance	
		double targetX, double targetY) {
		WorldObject closestObject = null;
		double closestDistance = Double.MAX_VALUE;
		
		//for each world object
		for (WorldObject worldObject : world.getWorldObjectList()) {
			double distance = distance(worldObject.getX(), worldObject.getY(), 
					   targetX, targetY);
			//for the specified type
			switch (objectType) {
				case (SPRITE):
					//If it's of specified type and closer, assign as closest
					if (worldObject instanceof Sprite && distance < closestDistance) {
						closestObject = worldObject;
						closestDistance = distance;
					}
					break;
				case (BUILDING):
					if (worldObject instanceof Building && distance < closestDistance) {
						closestObject = worldObject;
						closestDistance = distance;
					}
					break;
				case (RESOURCE):
					if (worldObject instanceof Resource && distance < closestDistance) {
						closestObject = worldObject;
						closestDistance = distance;
					}
					break;
				case (COMMAND_CENTRE):
					if (worldObject instanceof CommandCentre && distance < closestDistance) {
						closestObject = worldObject;
						closestDistance = distance;
					}
					break;
				case (SELECTABLE):
					//If object is selectable, and within selection distance
					if (worldObject instanceof SelectableObject && distance < World.SELECT_DIST) {
						//return first sprite
						if (worldObject instanceof Sprite) {
							return worldObject;
						}
						//else assign closest building
						else if (worldObject instanceof Building && distance < closestDistance) {
							closestObject = worldObject;
							closestDistance = distance;
						}
					}
					break;
				default:
					break;
			}
		}
		return closestObject;
	}
}


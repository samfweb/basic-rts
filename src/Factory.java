import org.newdawn.slick.SlickException;
/**
 * A building that can create Trucks.
 */
public class Factory extends Building {
	public final static String FACTORY_PATH = "assets/buildings/factory.png";
	public final static String FACTORY_NAME = "Factory";
	public final static int TRUCK_COST 	= 150;
	//Required training time in milliseconds
	public final static int REQUIRED_TRAINING_TIME = 5000;
	
	private Creator creator;
	/**
	 * Instantiates a new factory.
	 *
	 * @param world the world
	 * @param x the x coordinate for spawn
	 * @param y the y coordinate for spawn
	 * @throws SlickException
	 */
	public Factory(World world, int x, int y) throws SlickException {
		super(world, x, y);
		this.setName(FACTORY_NAME);
		this.setImage(FACTORY_PATH);
		creator = setCreator();
	}
	
	/**
	 * @see SelectableObject#action1()
	 */
	@Override
	public void action1() {
		//Begins training a truck
		creator.beginCreating(Creator.TRUCK, TRUCK_COST, REQUIRED_TRAINING_TIME);
	}
}

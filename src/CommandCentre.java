import org.newdawn.slick.SlickException;
/**
 * A building that can create the Scout, Engineer and Builder sprites
 */
public class CommandCentre extends Building {
	public final static String COMMAND_CENTRE_PATH = "assets/buildings/command_centre.png";
	public final static String COMMAND_CENTRE_NAME = "Command Centre";
	public final static int SCOUT_COST    = 5;
	public final static int BUILDER_COST  = 10;
	public final static int ENGINEER_COST = 20;
	//Required training time in milliseconds
	public final static int REQUIRED_TRAINING_TIME = 5000; 

	private Creator creator;
	
	/**
	 * Instantiates a new command centre.
	 *
	 * @param world the world the command centre is in
	 * @param x the x coordinate for spawn
	 * @param y the y coordinate for spawn
	 * @throws SlickException
	 */
	public CommandCentre(World world, int x, int y) throws SlickException {
		super(world, x, y);
		this.setName(COMMAND_CENTRE_NAME);
		this.setImage(COMMAND_CENTRE_PATH);
		this.creator = setCreator();
	}
	
	/**
	 * @see SelectableObject#action1()
	 */
	@Override
	public void action1() {
		//Begins training a scout
		creator.beginCreating(Creator.SCOUT, SCOUT_COST, REQUIRED_TRAINING_TIME);
	}
	
	/**
	 * @see SelectableObject#action2()
	 */
	@Override
	public void action2() {
		//Begins training a builder
		creator.beginCreating(Creator.BUILDER, BUILDER_COST, REQUIRED_TRAINING_TIME);
	}
	
	/**
	 * @see SelectableObject#action3()
	 */
	@Override
	public void action3() {
		//Begin training an engineer
		creator.beginCreating(Creator.ENGINEER, ENGINEER_COST, REQUIRED_TRAINING_TIME);
	}
}

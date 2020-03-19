import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 * Allows objects to be selected.
 */
public abstract class SelectableObject extends WorldObject {

	//Offsets for name flavour text
	public static final int NAME_X_OFFSET = -55;
	public static final int NAME_Y_OFFSET = 32;
	
	private String name;
	private Image highlight;
	private boolean isSelected = false;
	
	
	/**
	 * Instantiates a new selectable object.
	 *
	 * @param world the world the object is in
	 * @param x the x coordinate to spawn
	 * @param y the y coordinate to spawn
	 */
	public SelectableObject(World world, int x, int y) {
		super(world, x, y);
	}
	
	/**
	 * Action 1 to occur on 1 key press
	 */
	public void action1() {	
	}
	
	/**
	 * Action 2 to occur on 2 key press
	 */
	public void action2() {
	}
	
	/**
	 * Action 3 to occur on 3 key press
	 */
	public void action3() {	
	}
	
	/**
	 * Action to occur on right click
	 * @param targetX global x coordinate of click
	 * @param targetY global y coordinate of click
	 */
	public void actionRightClick(double targetX, double targetY) {
	}
	
	/**
	 * Renders the object, additionally highlighting it if selected.
	 * 
	 * @see WorldObject#render(org.newdawn.slick.Graphics) 
	 */
	@Override
	public void render(Graphics g) {
		//Draw highlight if selected
		if (isSelected) {
			highlight.drawCentered(getXOnScreen(), getYOnScreen());
			g.drawString(this.name, getXOnScreen()+NAME_X_OFFSET, getYOnScreen()+NAME_Y_OFFSET);
		}
		super.render(g);
	}
	
	/* Getters and setters ---------------------------------------------- */
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Selects the selectable object.
	 */
	public void select() {
		this.isSelected = true;
	}
	
	/**
	 * Deselects the selectable object.
	 */
	public void deselect() {
		this.isSelected = false;
	}
	
	/**
	 * Checks if selectable object is selected.
	 *
	 * @return true, if is selected
	 */
	public boolean isSelected() {
		return isSelected;
	}
	
	/**
	 * Sets the highlight for the selectable object.
	 *
	 * @param highlightPath the highlight image path
	 * @throws SlickException
	 */
	public void setHighlight(String highlightPath) throws SlickException {
		this.highlight = new Image(highlightPath);
	}
	
}

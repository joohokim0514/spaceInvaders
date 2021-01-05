package project;

import sedgewick.StdDraw;

public class Alien implements Moveable {
	
	private double posX;
	private double posY;
	private final double startX;
	private final double startY;
	private final double width = 0.15;
	private final double height = 0.15;
	private double speed;	
	private boolean upDown;
	private boolean isAlive = true;
	
	/**
	 * Creates an Alien object to be implemented in the game
	 * @param x- x-coordinate of alien (center)
	 * @param y- y-coordinate of alien (center)
	 * @param speed- speed at which the alien moves 
	 * @param upDown- true if alien moves up/down pattern; false if alien moves side-to-side pattern
	 */
	public Alien(double x, double y, double speed, boolean upDown) {
		this.posX = x;
		this.posY = y;
		this.startX = x;
		this.startY = y;
		this.speed = -speed;
		this.upDown = upDown;
	}
	
	/**
	 * @return get x coordinate of alien
	 */
	public double getPosX() {
		return this.posX;
	}
	
	/**
	 * @return get y coordinate of alien
	 */
	public double getPosY() {
		return this.posY;
	}
	
	/**
	 * @return get width of the alien
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * @return get height of the alien
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Draws the alien
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
	}
	
	/**
	 * Moves the alien
	 * If upDown is true, it moves the alien vertically at a constant speed
	 * If false, it movfes the alien horizontally at a constant speed
	 */
	public void move() {
		if (isOffScreen()) {
			speed *= -1;
		}
		if (upDown) {
			this.posY += speed;
		} else {
			this.posX += speed;
		}

	}
	
	/**
	 * Indicates if the alien is off screen or not
	 * @return true or false
	 */
	public boolean isOffScreen() {
		return (this.posX > 1 || this.posX < -1 || this.posY > 1 || this.posY < -1);
	}
	
	/**
	 * Initial positions for the aliens
	 */
	public void moveToStart() {
		this.posX = startX;
		this.posY = startY;
	}
	
	/**
	 * Checks if the Alien is alive
	 * @return true or false
	 */
	public boolean isAlive() {
		return this.isAlive;
	}
	
	/**
	 * If the alien dies, then isAlive becomes false
	 */
	public void die() {
		this.isAlive = false;
	}
	

}

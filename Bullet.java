package project;

import sedgewick.StdDraw;

public class Bullet implements Moveable{
	
	private double posX;
	private double posY; 
	private double width = 0.05;
	private double height = 0.05;
	private double speed;
	private boolean isOffScreen = false;
	
	/**
	 * Creates a Bullet object to be implemented in the game
	 * @param x- x-coordinate of bullet (center)
	 * @param y- y-coordinate of bullet (center)
	 * @param speed- speed at which the bullet moves 
	 */
	public Bullet(double x, double y, double speed) {
		this.posX = x;
		this.posY = y;
		this.speed = speed;
	}
	
	/**
	 * Draws the bullets 
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
	}
	
	/**
	 * Moves the bullets up at a constant speed
	 */
	public void move() {
		this.posY += speed;
	}
	
	/**
	 * @return get x coordinate of bullet
	 */
	public double getPosX() {
		return this.posX;
	}
	
	/**
	 * @return get y coordinate of bullet
	 */
	public double getPosY() {
		return this.posY;
	}
	
	/**
	 * Determine if bullet is out of bounds
	 */
	public boolean getIsOffScreen() {
		return this.isOffScreen;	
	}
	
	/**
	 * Remove bullet if off screen or collides with alien
	 */
	public void setOffScreen() {
		this.isOffScreen = true;
	}
	
	/**
	 * Determine if a bullet and alien collide based on comparing upper left and bottom right coordinates of each
	 * @param a- alien that bullet potentially collided with
	 * @return true if collision occurred
	 */
	public boolean collide(Alien a) {	
		double myTopLeftX = posX - width/2;
		double myTopLeftY = posY + height/2;
		double myBottomRightX = posX + width/2;
		double myBottomRightY = posY - height/2;
		
		double otherTopLeftX = a.getPosX() - a.getWidth()/2;
		double otherTopLeftY = a.getPosY() + a.getHeight()/2;
		double otherBottomRightX = a.getPosX() + a.getWidth()/2;
		double otherBottomRightY = a.getPosY() - a.getHeight()/2;
		
		return (myTopLeftY >= otherBottomRightY && myBottomRightY <= otherTopLeftY && myBottomRightX >= otherTopLeftX && myTopLeftX <= otherBottomRightX);
	}
	
	/**
	 * Determine if a bullet and obstacle collide based on comparing upper left and bottom right coordinate of each
	 * @param o - obstacle that bullet potentially collided with
	 * @return true of collision occured
	 */
	public boolean collide2(Obstacles o) {
		double myTopLeftX = posX - width/2;
		double myTopLeftY = posY + height/2;
		double myBottomRightX = posX + width/2;
		double myBottomRightY = posY - height/2;
		
		double otherTopLeftX = o.getPosX() - o.getWidth()/2;
		double otherTopLeftY = o.getPosY() + o.getHeight()/2;
		double otherBottomRightX = o.getPosX() + o.getWidth()/2;
		double otherBottomRightY = o.getPosY() - o.getHeight()/2;
		
		
		
		return (myTopLeftY >= otherBottomRightY && myBottomRightY <= otherTopLeftY && myBottomRightX >= otherTopLeftX && myTopLeftX <= otherBottomRightX);	
	}
	
	

}

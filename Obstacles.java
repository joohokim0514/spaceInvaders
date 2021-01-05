package project;

import sedgewick.StdDraw;
import java.awt.Color;

public class Obstacles implements Moveable {
	
	private double posX;
	private double posY;
	private double width = 0.3;
	private double height = 0.1;
	private boolean isAlive = true;
	private int lives;
	
	/**
	 * Creates an Obstacle object to be implemented in the game
	 * @param x- x-coordinate of alien (center)
	 * @param y- y-coordinate of alien (center)
	 * @param lives = how many times you can hit the obstacle befor eit disappears
	 */
	public Obstacles (double x, double y, int lives) {
		this.posX = x;
		this.posY = y;
		this.lives = lives;
	}
	
	/**
	 * @return gets the x - coordinate of the obstacle
	 */
	public double getPosX() {
		return this.posX;
	}
	
	/**
	 * @return gets the lives of the obstacles
	 */
	public double getLives() {
		return this.lives;
	}
	
	/**
	 * @return gets the y - coordinate of the obstacle
	 */
	public double getPosY() {
		return this.posY;
	}
	
	/**
	 * @return gets the width of the obstacle
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * @return gets the height of the obstacle
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * draws the Obstacle
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
	}
	
	public void move() {
	}
	
	/**
	 * If the obstacle has disappeared are not
	 * @return true or false
	 */
	public boolean isAlive() {
		if(this.lives == 0){
			this.isAlive = false;
		}
		return this.isAlive;
	}
	
	/**
	 * Lives of the obstacle goes down by 1 and the obstacle shrinks
	 * until it disappears
	 */
	public void die() {
		this.lives--;
		if (lives != 0) {
			this.width -= 0.01;
			this.height -= 0.01;
		}
	}

	
	
	
	
}

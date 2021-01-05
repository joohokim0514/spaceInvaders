package project;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

import sedgewick.StdDraw;

/**
 * Plays game by using all created objects and Moveable interface
 * @author Zachary Polsky
 *
 */


public class Game {
	
	private LinkedList<Alien> aliens;
	private LinkedList<Moveable> move;
	private LinkedList<Bullet> bullets;
	private LinkedList<Obstacles> obstacle;
	private Player player;
	private double alienSpeed;
	private int score;
	private int highestScore;
	private int gamesPlayed;
	
	/**
	 * Constructor that initializes the class
	 * for all the variables.
	 */
	public Game(int highestScore, int gamesPlayed) {
		aliens = new LinkedList<Alien>();
		move = new LinkedList<Moveable>();
		bullets = new LinkedList<Bullet>();
		obstacle = new LinkedList<Obstacles>();
		StdDraw.setScale(-1, 1);
		player = new Player(0, -.9, .04, 3);
		move.add(player);
		alienSpeed = 0.04;
		addAliens();
		addObstacles();
		score = 0;
		this.highestScore = highestScore;
		this.gamesPlayed = gamesPlayed;
	}
	
	/**
	 * @return gets the highest score of the game
	 */
	public int getHighestScore() {
		return highestScore;
	}

	/**
	 * @return gets the number of games played
	 */
	public int getGamesPlayed() {
		return gamesPlayed;
	}

	/**
	 * Draws the score board and the black background
	 * for the game
	 * @param score - score of the player
	 */
	public void drawBoard(int score) {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(0.75, 0.9, "Score: " + score);
	}
	
	/**
	 * Boolean that checks if the game is over
	 * @return Whether the game is over or not as 
	 * true or false
	 */
	public boolean isOver() {
		return (!(player.getLives() > 0));
	}
	
	/**
	 * Adds Aliens to the game when called
	 */
	public void addAliens(){
		addAlien(0.5, 0.5, alienSpeed, true);
		addAlien(-0.5, 0.5, alienSpeed, true);
		addAlien(-0.9, 0.5, alienSpeed, false);
		addAlien(-0.9, -0.2, alienSpeed, false);
	}
	
	/**
	 * Adds obstacles to the game when called
	 */
	public void addObstacles(){
		addObstacle(0.0, 0.0, 5);
		addObstacle(-0.8, -0.4, 5);
		addObstacle(0.8, 0.3, 5);
	}
	
	/**
	 * Adds one specific alien to the game
	 * @param x - x coordinate of the alien
	 * @param y - y coordinate of the alien
	 * @param speed - speed of the alien
	 * @param upDown - whether the alien moves up or down
	 */
	private void addAlien(double x, double y, double speed, boolean upDown)
	{
		Alien a = new Alien(x, y, speed, upDown);
		aliens.add(a);
		move.add(a);
	}
	
	/**
	 * Adds one specific obstacle to the game
	 * @param x - x coordinate of the obstacle
	 * @param y - y coordinate of the obstacle
	 * @param lives - number of times shrinking before disappearing
	 */
	private void addObstacle(double x, double y, int lives)
	{
		Obstacles o = new Obstacles(x, y, lives);
		obstacle.add(o);
		move.add(o);
	}
	
	/**
	 * Makes the game operate when called
	 */
	public void play(){
		
		// Draws the score board and the black background
		// Moves and draws every element of the game
		drawBoard(score);
		for (Moveable m : move) {
			m.move();
			m.draw();
		}
		
		// Checks if there is less than three bullets on the screen
		// and lets you add more if there is less than 3
		if (player.fire() && bullets.size() < 3) {
			Bullet b = new Bullet(player.getPosX(), player.getPosY() + .15, .05);
			move.add(b);
			bullets.add(b);
		}
		
		//
		/*CODE A*/ // start
		
		// Checks if there is collision
		// and if the player collides with the alien
		// 10 points are subtracted from the score and 
		// changes the color of the player
		// and subtracts one from lives.
		for (Alien a : aliens) {
			if (player.collide(a)) {
				player.die();
				score -= 10;
			}
			
			// Checks if there is a collision
			// between the bullet and the alien 
			// and if there is, the alien disappears
			// and the the bullet goes off screen
			// and the score increments by 50
			// The else checks if it doesn't hit anything
			// if the bullet does not hit anything, 
			//the bullet is removed from the screen.
			for (Bullet b : bullets) {
				if (b.collide(a)) {
					a.die();
					b.setOffScreen();
					score += 50;
				}
				else if (b.getPosY() >= 1){
					b.setOffScreen();
				}
				
			}
		}
		
		// Checks if there is a collision
		// between the bullet an the obstacle
		// and if there is, the obstacle shrinks
		// and the obstacle goes off screen when hit 5 times
		// the bullet is removed from the screen
		for (Obstacles o : obstacle) {
			for (Bullet b : bullets) {
				if (b.collide2(o)) {
					o.die();
					b.setOffScreen();
				}
				else if (b.getPosY() >= 1){
					b.setOffScreen();
				}
				
				
			}
		}
		
		
		
		/*CODE A*/ //end
		
		// Used to prevent concurrent modification errors
		Iterator<Alien> alienIter = aliens.iterator();
		while (alienIter.hasNext()) {
		    Alien a = alienIter.next();
		    if (!a.isAlive()) {
		    	alienIter.remove();
		    	move.remove(a);
		    }
		}
		
		// Used to prevent concurrent modification errors
		Iterator<Bullet> bulletIter = bullets.iterator();
		while (bulletIter.hasNext()) {
		    Bullet b = bulletIter.next();
		    if (b.getIsOffScreen()) {
		    	bulletIter.remove();
		    	move.remove(b);
		    }
		}
		
		// Used to prevent concurrent modification errors
		Iterator<Obstacles> obstacleIter = obstacle.iterator();
		while (obstacleIter.hasNext()) {
		    Obstacles o = obstacleIter.next();
		    if (!o.isAlive()) {
		    	obstacleIter.remove();
		    	move.remove(o);
		    }
		}
		
		//If the all the aliens are gone,
		//then the speed of the aliens increments
		//and aliens arae reset
		levelUp();
		StdDraw.show(60);
		
		// Draws the game over screen
		if (isOver()){
			drawGameEnd();
		}
	}
	
	/**
	 * If all the aliens are gone,
	 * then the speed of the alien increments
	 * and aliens are reset
	 */
	public void levelUp() {
		if (aliens.isEmpty()) {
			alienSpeed *= 1.5;
			addAliens();
		}
	}
	
	/**
	 * Draws the game over screen
	 */
	public void drawGameEnd()
	{
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(0, 0, "GAME OVER");
		StdDraw.text(0, -0.25, "Press Q to play again");
		StdDraw.text(0, -0.5, "Highest score: " + this.score);
		StdDraw.text(0, -0.75, "Games Played: " + this.gamesPlayed);
		StdDraw.show(100);
	}
	
	/**
	 * Calculates the high score of all games played
	 * @return highest score
	 */
	public int score() {
		if (this.highestScore < this.score) {
			return this.score;
		}
		else {
			return this.highestScore;
		}
		
	}
	
	/**
	 * Restarts the game after the game is over once called
	 */
	public void again() {
		while (true) {
				if(StdDraw.hasNextKeyTyped()){
					char gotKey = StdDraw.nextKeyTyped();
					if (gotKey == 'q' || gotKey == 'Q') {
						Game game2 = new Game(this.score, this.gamesPlayed+1);
						while (!game2.isOver()){
							game2.play();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game(0, 1);
		while (!game.isOver()){
			game.play();
		}
		game.again();
	}

}

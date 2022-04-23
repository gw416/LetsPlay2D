package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;

	public Entity(float x, float y, int width, int height, String name) {
		System.out.println("Entity.Entity()....................... Creating Entity super class: --> " + name);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	protected void drawHitbox(Graphics g) {
		//for debugging hitbox
		g.setColor(Color.PINK);
		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}

	protected void initHitbox(float x, float y, int width, int height) {
		System.out.println("Entity.initHitbox()................... Creating Player hitbox");
		
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
}

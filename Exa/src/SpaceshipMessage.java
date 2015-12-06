
public class SpaceshipMessage extends Message {

	public int health; //current health
	public int shields; //current shields
	public int healthPotential; //upgradable stats
	public int shieldPotential; //regeneration rate
	public int weaponPotential; //damage impacted by bullets
	public int speedPotential; //top speed
	public boolean fireButtonHeld;
	public int cycles;
	
	public SpaceshipMessage(Spaceship s) {
		super(s);
		health = s.health;
		shields = s.shields;
		healthPotential = s.healthPotential;
		shieldPotential = s.shieldPotential;
		weaponPotential = s.weaponPotential;
		speedPotential = s.speedPotential;
		fireButtonHeld = s.fireButtonHeld;
		cycles = s.currentCycles;
		// TODO Auto-generated constructor stub
	}

}

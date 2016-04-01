package utils;

import java.util.Random;

import entities.Character;
import weapons.Weapon;

public class BattleInstance {
	
	Character attacker, defender;
	int attackerSpeedThreshold = 5;
	Weapon attackerWeapon;
	Weapon defenderWeapon;
	Stats attackerStats;
	Stats defenderStats;
	
	public BattleInstance(Character attacker, Character defender){
		this.attacker = attacker;
		this.defender = defender;
		attackerWeapon = attacker.getEquipped();
		defenderWeapon = defender.getEquipped();
		attackerStats = attacker.getStats();
		defenderStats = defender.getStats();
	}

	public void act(){
		int numAttacks = 1;
		
		if(attackerStats.speed - defenderStats.speed >= attackerSpeedThreshold){
			numAttacks++;
		}
		
		for(int i = 0; i < numAttacks; i++){
			doAttack();
		}
	}
	
	private void doAttack(){
		Random r = new Random();
		
		int hitChance = attackerStats.skill - defenderStats.speed - attackerWeapon.hitPenalty;
		
		if(r.nextInt(attackerStats.skill) < hitChance){
			defender.damage(Math.max(attackerStats.strength + attackerWeapon.damage - defenderStats.defense, 0));
			attackerWeapon.currentDurability--;
			
			if(attackerWeapon.currentDurability <= 0){
				attacker.getInventory().removeItem(attackerWeapon);
			}
		}
	}
}
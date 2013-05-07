package commands.attack;

import commands.Command;

import game.Base;

public class AttackBase extends Command{
	
	Base enemy;

	public AttackBase(Base enemy) {
		this.enemy = enemy;
	}

	@Override
	public void doCommand() {
		System.out.println(this+" do something");	
	}

}

package bagMock;

import Skill.Skill;
import bag.Bag;

public class BagDummy implements Bag{

	public BagDummy() {
		
	}
	@Override
	public void addSkill(Skill skill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdrawSkill(Skill skill) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Skill getAttackingSkill() {
		return null;
	}
	@Override
	public Skill getCounterAttackingSkill() {
		return null;
	}

}

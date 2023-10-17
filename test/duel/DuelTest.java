package duel;
import Skill.Skill;
import SkillMock.SkillDummy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exception.DeadDuelistCanNotDuelException;
import duelistMock.DuelistSpy;
import duelistMock.DuelistStub;

class DuelTest {

	@Test
	void givenADuelWithTwoFighters_ifDuelistNotAlive_duelWillNotStart() {
		//Arrange
		DuelistStub attacker = new DuelistStub();
		DuelistStub counterAttacker = new DuelistStub();
		attacker.isAlive = true;
		counterAttacker.isAlive = false;
		//Act
		assertThrows(DeadDuelistCanNotDuelException.class, ()-> {
			Duel duel = new Duel(attacker,counterAttacker);
		});
		//Assert
	}
	
	@Test
	void givenADuelWithTwoFighters_whenFightStart_AttackMethodAndCounterAttackMethodIsCalled() {
		//Arrange
		DuelistSpy attacker = new DuelistSpy();
		DuelistSpy counterAttacker = new DuelistSpy();
		Skill skill = new SkillDummy();
		attacker.isAlive = true;
		counterAttacker.isAlive = true;
		Duel duel = new Duel(attacker, counterAttacker);
		//Act
		duel.fight(skill);
		//Assert
		assertTrue(attacker.attackHasBeenCalled);
		assertTrue(counterAttacker.counterAttackHasBeenCalled);
	}
	
	@Test
	void givenADuelWithStrongerAttacker_whenFight_attackerWinAndCounterAttackerLose() {
		//Arrange
		DuelistSpy attacker = new DuelistSpy();
		DuelistSpy counterAttacker = new DuelistSpy();
		Skill skill = new SkillDummy();
		attacker.isAlive = true;
		counterAttacker.isAlive = true;
		attacker.power = 1;
		counterAttacker.power = 0;
		Duel duel = new Duel(attacker, counterAttacker);
		
		//Act
		duel.fight(skill);
		
		//Assert
		assertTrue(attacker.rewardHasBeenCalled);
		assertTrue(counterAttacker.penalizeHasBeenCalled);
	}
	
	@Test
	void givenADuelWithStrongerCounterAttacker_whenFight_counterAttackerWinAndAttackerLose() {
		//Arrange
		DuelistSpy attacker = new DuelistSpy();
		DuelistSpy counterAttacker = new DuelistSpy();
		Skill skill = new SkillDummy();
		attacker.isAlive = true;
		counterAttacker.isAlive = true;
		attacker.power = 0;
		counterAttacker.power = 1;
		Duel duel = new Duel(attacker, counterAttacker);
		
		//Act
		duel.fight(skill);
		
		//Assert
		assertTrue(attacker.penalizeHasBeenCalled);
		assertTrue(counterAttacker.rewardHasBeenCalled);
	}
	
	@Test
	void givenADuelWithEvenFighters_whenFight_counterAttackerWinAndAttackerLose() {
		//Arrange
		DuelistSpy attacker = new DuelistSpy();
		DuelistSpy counterAttacker = new DuelistSpy();
		Skill skill = new SkillDummy();
		attacker.isAlive = true;
		counterAttacker.isAlive = true;
		attacker.power = 0;
		counterAttacker.power = 0;
		Duel duel = new Duel(attacker, counterAttacker);
		
		//Act
		duel.fight(skill);
		
		//Assert
		assertTrue(attacker.penalizeHasBeenCalled);
		assertTrue(counterAttacker.rewardHasBeenCalled);
	}
	
	@Test
	void givenADuelWithWinningAttacker_whenFightEnds_attackerGetsRewarded() {
		//Arrange
		DuelistSpy attacker = new DuelistSpy();
		DuelistSpy counterAttacker = new DuelistSpy();
		attacker.isAlive = true;
		counterAttacker.isAlive = true;
		attacker.power = 5;
		counterAttacker.power = 0;
		Duel duel = new Duel(attacker,counterAttacker);
		Skill skill = new SkillDummy();
		
		//Act
		duel.fight(skill);
		
		//Assert
		assertEquals(skill,attacker.skill);
		assertEquals(1,attacker.points);
		
	}
	
	@Test
	void givenADuelWithWinningAttacker_whenFightEnds_counterAttackerGetsPenalized() {
		//Arrange
		DuelistSpy attacker = new DuelistSpy();
		DuelistSpy counterAttacker = new DuelistSpy();
		attacker.isAlive = true;
		counterAttacker.isAlive = true;
		attacker.power = 5;
		counterAttacker.power = 0;
		Duel duel = new Duel(attacker,counterAttacker);
		Skill skill = new SkillDummy();
		
		//Act
		duel.fight(skill);
		
		//Assert
		assertEquals(95,counterAttacker.hp);
		assertEquals(-1,counterAttacker.points);
		
	}
}

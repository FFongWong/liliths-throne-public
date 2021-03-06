package com.lilithsthrone.game.combat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public enum Spell {

	// OFFENSIVE SPELLS:

	SLAM_1(SpellSchool.EARTH,
			SpellType.OFFENSIVE,
			1,
			"slam",
			"spellSlam",
			DamageType.PHYSICAL,
			20,
			DamageVariance.MEDIUM,
			10,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.DAZED, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(), damageVariance, isCritical);
			float cost = getModifiedCost(caster);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
						"<p>"
							+ "Summoning a swirling vortex of arcane energy around your [pc.arm], you focus its raw power into a wall of pure force before launching it at [npc.name]."
						+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
						"<p>"
							+ "Summoning a swirling vortex of arcane energy around [npc.her] [npc.arm], [npc.she] focuses its raw power into a wall of pure force before launching it directly at you!"
						+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(caster, -damage));
				applyStatusEffects(caster, target, isCritical);
			}

			caster.incrementMana(-cost);
			
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a wave of crushing force that slams into an unlucky target.";
		}
	},
	
	FIREBALL_1(SpellSchool.FIRE,
			SpellType.OFFENSIVE,
			1,
			"fireball",
			"spellFireball",
			DamageType.FIRE,
			15,
			DamageVariance.LOW,
			10,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.BURN_WEAK, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(), damageVariance, isCritical);
			float cost = getModifiedCost(caster);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
							"<p>"
								+ "Summoning a swirling vortex of arcane fire around your [pc.arm], you focus its raw power into a ball of roiling flames before launching it at [npc.name]."
							+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of arcane fire around [npc.her] [npc.arm], [npc.she] focuses its raw power into a ball of roiling flames before launching it directly at you!"
							+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(caster, -damage));
				applyStatusEffects(caster, target, isCritical);
			}

			caster.incrementMana(-cost);
						
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a ball of flames that can be launched at a target.";
		}
	},
	
	FIRE_INFERNO(SpellSchool.FIRE,
			SpellType.OFFENSIVE,
			2,
			"inferno",
			"spellFireball",
			DamageType.FIRE,
			20,
			DamageVariance.LOW,
			25,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.BURN_WEAK, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(), damageVariance, isCritical);
			float cost = getModifiedCost(caster);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
							"<p>"
								+ "Summoning a swirling vortex of arcane fire around your [pc.arm], you focus its raw power into a ball of roiling flames before launching it at [npc.name]."
							+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of arcane fire around [npc.her] [npc.arm], [npc.she] focuses its raw power into a ball of roiling flames before launching it directly at you!"
							+ "</p>"));
			}

			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(caster, -damage));
				applyStatusEffects(caster, target, isCritical);
			}

			caster.incrementMana(-cost);
						
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a ball of flames that can be launched at a target.";
		}
	},

	ICESHARD_1(SpellSchool.WATER,
			SpellType.OFFENSIVE,
			1,
			"ice shard",
			"spellIceShard",
			DamageType.ICE,
			10,
			DamageVariance.LOW,
			10,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(
					StatusEffect.CHILL,
					2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(), damageVariance, isCritical);
			float cost = getModifiedCost(caster);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
							"<p>"
								+ "Summoning a swirling vortex of arcane ice around your [pc.arm], you focus its raw power into a shard of freezing ice before launching it at [npc.name]."
							+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of arcane ice around [npc.her] [npc.arm], [npc.she] focuses its raw power into a shard of freezing ice before launching it directly at you!"
							+ "</p>"));
			}

			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(caster, -damage));
				applyStatusEffects(caster, target, isCritical);
			}

			caster.incrementMana(-cost);
						
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shard of ice that can be launched at a target.";
		}
	},

	POISON_NOVA_1(SpellSchool.AIR,
			SpellType.OFFENSIVE,
			1,
			"poison nova",
			"spellPoisonNova",
			DamageType.POISON,
			5,
			DamageVariance.MEDIUM,
			5,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.POISON_WEAK, 2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float damage = Attack.calculateSpellDamage(caster, target, damageType, this.getDamage(), damageVariance, isCritical);
			float cost = getModifiedCost(caster);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(UtilText.parse(target,
							"<p>"
								+ "Summoning a swirling vortex of arcane poison around your [pc.arm], you focus its raw power into a cloud of toxic miasma before launching it at [npc.name]."
							+ "</p>"));
			} else {
				descriptionSB = new StringBuilder(UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of arcane poison around [npc.her] [npc.arm], [npc.she] focuses its raw power into a cloud of toxic miasma before launching it directly at you!"
							+ "</p>"));
			}

			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, damage, isHit, isCritical));
			
			// If attack hits, apply damage and effects:
			if (isHit) {
				descriptionSB.append(target.incrementHealth(caster, -damage));
				applyStatusEffects(caster, target, isCritical);
			}

			caster.incrementMana(-cost);
						
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a cloud of toxic miasma that can be launched at a target.";
		}
	},

	// DEFENSIVE SPELLS:
	ARCANE_SHIELD(SpellSchool.EARTH,
			SpellType.DEFENSIVE,
			1,
			"arcane shield",
			"specialAttackIcon",
			DamageType.PHYSICAL,
			0,
			DamageVariance.NONE,
			5,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(
					StatusEffect.ARCANE_SHIELD,
					2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);

			applyStatusEffects(caster, caster, isCritical);

			caster.incrementMana(-cost);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(
						"<p>"
							+ "Summoning a swirling vortex of pure energy around your [pc.arm], you focus its raw power into an"
							+ " <b style='color: "+ Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>arcane shield</b> that quickly surrounds you, providing improved protection from physical attacks."
						+ "</p>");
			} else {
				descriptionSB = new StringBuilder(
						UtilText.parse(caster,
								"<p>"
									+ "Summoning a swirling vortex of pure energy around [npc.her] [npc.arm], [npc.she] focuses its raw power into an"
										+ " <b style='color: " + Attribute.DAMAGE_PHYSICAL.getColour().toWebHexString() + ";'>" + "arcane shield</b> that quickly surrounds [npc.herHim], providing improved protection from physical attacks."
								+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, -1, true, isCritical));
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shield of pure energy that protects the caster from physical attacks.";
		}
		
		@Override
		public boolean isBeneficial() {
			return true;
		}
	},

	FIRE_SHIELD(SpellSchool.FIRE,
			SpellType.DEFENSIVE,
			1,
			"fire shield",
			"specialAttackIcon",
			DamageType.FIRE,
			0,
			DamageVariance.NONE,
			5,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(
					StatusEffect.FIRE_SHIELD,
					2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);

			applyStatusEffects(caster, caster, isCritical);

			caster.incrementMana(-cost);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(
						"<p>"
							+ "Summoning a swirling vortex of arcane flames around your [pc.arm], you focus its raw power into a"
								+ " <b style='color: "+ Attribute.DAMAGE_FIRE.getColour().toWebHexString() + ";'>fire shield</b> that quickly surrounds you, providing improved protection from fire attacks."
						+ "</p>");
			} else {
				descriptionSB = new StringBuilder(
						UtilText.parse(caster,
								"<p>"
									+ "Summoning a swirling vortex of arcane flames around [npc.her] [npc.arm], [npc.she] focuses its raw power into a"
										+ " <b style='color: " + Attribute.DAMAGE_FIRE.getColour().toWebHexString() + ";'>" + "fire shield</b> that quickly surrounds [npc.herHim], providing improved protection from fire attacks."
								+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, -1, true, isCritical));
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shield of arcane fire that protects the caster from fire attacks.";
		}
		
		@Override
		public boolean isBeneficial() {
			return true;
		}
	},
	
	ICE_SHIELD(SpellSchool.WATER,
			SpellType.DEFENSIVE,
			1,
			"ice shield",
			"specialAttackIcon",
			DamageType.ICE,
			0,
			DamageVariance.NONE,
			5,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(
					StatusEffect.ICE_SHIELD,
					2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);

			applyStatusEffects(caster, caster, isCritical);

			caster.incrementMana(-cost);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(
						"<p>"
							+ "Summoning a swirling vortex of arcane ice around your [pc.arm], you focus its raw power into an" + " <b style='color: "
								+ Attribute.DAMAGE_ICE.getColour().toWebHexString() + ";'>" + "ice shield</b> that quickly surrounds you, providing improved protection from cold attacks."
						+ "</p>");
			} else {
				descriptionSB = new StringBuilder(
						UtilText.parse(caster,
								"<p>"
									+ "Summoning a swirling vortex of arcane ice around [npc.her] [npc.arm], [npc.she] focuses its raw power into an" + " <b style='color: "
										+ Attribute.DAMAGE_ICE.getColour().toWebHexString() + ";'>" + "ice shield</b> that quickly surrounds [npc.herHim], providing improved protection from cold attacks."
								+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, -1, true, isCritical));
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shield of arcane ice that protects the caster from cold attacks.";
		}
		
		@Override
		public boolean isBeneficial() {
			return true;
		}
	},

	POISON_SHIELD(SpellSchool.AIR,
			SpellType.DEFENSIVE,
			1,
			"poison shield",
			"specialAttackIcon",
			DamageType.POISON,
			0,
			DamageVariance.NONE,
			5,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(
					StatusEffect.POISON_SHIELD,
					2))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);

			applyStatusEffects(caster, caster, isCritical);

			caster.incrementMana(-cost);

			if (caster.isPlayer()) {
				descriptionSB = new StringBuilder(
						"<p>"
							+ "Summoning a swirling vortex of toxic miasma around your [pc.arm], you focus its raw power into a"
								+ " <b style='color: "+ Attribute.DAMAGE_POISON.getColour().toWebHexString() + ";'>" + "poison shield</b> that quickly surrounds you, providing improved protection from poison attacks."
						+ "</p>");
			} else {
				descriptionSB = new StringBuilder(
						UtilText.parse(caster,
							"<p>"
								+ "Summoning a swirling vortex of toxic miasma around [npc.her] [npc.arm], [npc.she] focuses its raw power into a"
									+ " <b style='color: " + Attribute.DAMAGE_POISON.getColour().toWebHexString() + ";'>" + "poison shield</b> that quickly surrounds [npc.herHim], providing improved protection from poison attacks."
							+ "</p>"));
			}
			
			descriptionSB.append(getDamageAndCostDescription(caster, target, cost, -1, true, isCritical));
			return descriptionSB.toString();
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Summons a shield of toxic miasma that protects the caster from poison attacks.";
		}
		
		@Override
		public boolean isBeneficial() {
			return true;
		}
	},

//	CLEANSE(SpellSchool.AIR,
//			SpellType.DEFENSIVE,
//			2,
//			"cleanse",
//			"specialAttackIcon",
//			DamageType.MISC,
//			0,
//			DamageVariance.NONE,
//			10,
//			null) {
//		@Override
//		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
//			return "";
//		}
//
//		@Override
//		public String getDescription(GameCharacter caster, int level) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//		@Override
//		public boolean isBeneficial() {
//			return true;
//		}
//	},
//
//	BLIND(SpellSchool.AIR,
//			SpellType.OFFENSIVE,
//			1,
//			"blind",
//			"specialAttackIcon",
//			DamageType.MISC,
//			0,
//			DamageVariance.NONE,
//			10,
//			null) {
//		@Override
//		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
//			return "";
//		}
//
//		@Override
//		public String getDescription(GameCharacter caster, int level) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	},
//
//	SILENCE(SpellSchool.WATER,
//			SpellType.OFFENSIVE,
//			1,
//			"silence",
//			"specialAttackIcon",
//			DamageType.MISC,
//			0,
//			DamageVariance.NONE,
//			10,
//			null) {
//		@Override
//		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
//			return "";
//		}
//
//		@Override
//		public String getDescription(GameCharacter caster, int level) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	},
//
//	HEAL(SpellSchool.WATER,
//			SpellType.DEFENSIVE,
//			1,
//			"heal",
//			"specialAttackIcon",
//			DamageType.MISC,
//			0,
//			DamageVariance.NONE,
//			10,
//			null) {
//		@Override
//		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
//			return "";
//		}
//
//		@Override
//		public String getDescription(GameCharacter caster, int level) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//		@Override
//		public boolean isBeneficial() {
//			return true;
//		}
//	},
//
//	// MISC SPELLS:
//	CHARM(SpellSchool.ARCANE,
//			SpellType.DEFENSIVE,
//			1,
//			"charm",
//			"specialAttackIcon",
//			DamageType.MISC,
//			0,
//			DamageVariance.NONE,
//			10,
//			null) {
//		@Override
//		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
//			return "";
//		}
//
//		@Override
//		public String getDescription(GameCharacter caster, int level) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	},
//
//	STUN(SpellSchool.EARTH,
//			SpellType.OFFENSIVE,
//			1,
//			"stun",
//			"specialAttackIcon",
//			DamageType.MISC,
//			0,
//			DamageVariance.NONE,
//			10,
//			null) {
//		@Override
//		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
//			return "";
//		}
//
//		@Override
//		public String getDescription(GameCharacter caster, int level) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	},
	
	WITCH_SEAL(SpellSchool.AIR,
			SpellType.OFFENSIVE,
			1,
			"Witch's Seal",
			"spell_witch_seal",
			DamageType.MISC,
			0,
			DamageVariance.NONE,
			25,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.WITCH_SEAL, 2))) {
		
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {

			float cost = getModifiedCost(caster);

			applyStatusEffects(caster, target, isCritical);

			caster.incrementMana(-cost);
			
			if (caster.isPlayer()) {
				return UtilText.parse(target,
						"<p>"
							+ "Concentrating on the arcane power within your broomstick, you summon forth a powerful seal, which traps [npc.name] in place!"
						+ "</p>");
			} else {
				return UtilText.parse(caster,
						"<p>"
							+ "Concentrating on the arcane power within [npc.her] broomstick, [npc.name] summons forth a powerful seal, which traps you in place!"
						+ "</p>");
			}
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Places an arcane seal upon the target, preventing them from taking any action for two turns.";
		}
	},
	
	WITCH_CHARM(SpellSchool.ARCANE,
			SpellType.DEFENSIVE,
			1,
			"Witch's Charm",
			"spell_witch_charm",
			DamageType.MISC,
			0,
			DamageVariance.NONE,
			20,
			Util.newHashMapOfValues(new Value<StatusEffect, Integer>(StatusEffect.WITCH_CHARM, 5))) {
		@Override
		public String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical) {
			applyStatusEffects(caster, caster, isCritical);

			caster.incrementMana(-getModifiedCost(caster));
			
			if (caster.isPlayer()) {
				return UtilText.parse(target,
						"<p>"
							+ "Concentrating on the arcane power within your broomstick, you cast a bewitching charm upon yourself!"
						+ "</p>");
			} else {
				return UtilText.parse(caster,
						"<p>"
							+ "Concentrating on the arcane power within [npc.her] broomstick, [npc.name] casts a bewitching charm upon [npc.herself]!"
						+ "</p>");
			}
		}

		@Override
		public String getDescription(GameCharacter caster, int level) {
			return "Places an arcane enchantment upon the target, causing them to appear irresistibly beautiful to anyone who looks upon them.";
		}
		
		@Override
		public boolean isBeneficial() {
			return true;
		}
	};
	
	private static Map<SpellSchool, List<Spell>> spellsFromSchoolMap = new HashMap<>();
	
	static {
		for(SpellSchool school : SpellSchool.values()) {
			spellsFromSchoolMap.put(school, new ArrayList<>());
		}
		for(Spell s : Spell.values()) {
			spellsFromSchoolMap.get(s.getSpellSchool()).add(s);
		}
		for(List<Spell> spellList : spellsFromSchoolMap.values()) {
			spellList.sort((s1, s2) -> s1.getSpellLevelRequired()-s2.getSpellLevelRequired());
		}
	}
	
	private static StringBuilder descriptionSB;
	
	private SpellSchool spellSchool;
	private SpellType type;
	private int spellLevelRequired;
	private String name;
	protected int damage;
	protected int spellCost;
	protected DamageType damageType;
	protected DamageVariance damageVariance;
	private Map<StatusEffect, Integer> statusEffects;

	private String SVGString;

	private Spell(SpellSchool spellSchool,
			SpellType type,
			int spellLevelRequired,
			String name,
			String pathName,
			DamageType damageType,
			int damage,
			DamageVariance damageVariance,
			int spellCost,
			Map<StatusEffect, Integer> statusEffects) {
		
		this.spellSchool = spellSchool;
		this.type = type;
		
		this.spellLevelRequired = spellLevelRequired;
		
		this.name = name;
		this.damageType = damageType;

		this.damage = damage;
		this.damageVariance = damageVariance;
		
		this.spellCost = spellCost;

		this.statusEffects = statusEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/combat/" + pathName + ".svg");
			SVGString = Util.inputStreamToString(is);

			SVGString = SVGString.replaceAll("#ff2a2a", damageType.getMultiplierAttribute().getColour().getShades()[0]);
			SVGString = SVGString.replaceAll("#ff5555", damageType.getMultiplierAttribute().getColour().getShades()[1]);
			SVGString = SVGString.replaceAll("#ff8080", damageType.getMultiplierAttribute().getColour().getShades()[2]);
			SVGString = SVGString.replaceAll("#ffaaaa", damageType.getMultiplierAttribute().getColour().getShades()[3]);
			SVGString = SVGString.replaceAll("#ffd5d5", damageType.getMultiplierAttribute().getColour().getShades()[4]);
			
			SVGString += "<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"
							+ SVGImages.SVG_IMAGE_PROVIDER.getSpellOverlay()
						+ "</div>";
			
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract String applyEffect(GameCharacter caster, GameCharacter target, boolean isHit, boolean isCritical);
	
	public boolean isBeneficial() {
		return false;
	}
	
	public float getModifiedCost(GameCharacter caster) {
		float calculatedCost = spellCost;
		
		calculatedCost *= ((100 - Util.getModifiedDropoffValue(caster.getAttributeValue(Attribute.SPELL_COST_MODIFIER), 100)) / 100f);
		
		// Round float value to nearest 1 decimal place:
		calculatedCost = (Math.round(calculatedCost*10))/10f;
		
		return calculatedCost;
	}
	
	protected void applyStatusEffects(GameCharacter caster, GameCharacter target, boolean isCritical) {
		for (Entry<StatusEffect, Integer> se : getStatusEffects().entrySet()) {
			target.addStatusEffect(se.getKey(), se.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1));
		}
	}

	public abstract String getDescription(GameCharacter caster, int level);

	private StringBuilder damageCostDescriptionSB;

	
	protected String getDamageAndCostDescription(GameCharacter caster, GameCharacter target, float cost, float damage, boolean isHit, boolean isCritical) {
		damageCostDescriptionSB = new StringBuilder();

		boolean selfCast = caster.equals(target);
		
		if (caster == Main.game.getPlayer()) {
			if (isCritical) {
				damageCostDescriptionSB.append(!selfCast
						? "<p>" + (isHit ? "<b>You <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
								+ damageType.getName() + "</b>" + " damage!</b>" : "<b>You missed!</b>") + "</p>"
						: "<p>" + (isHit ? "<b>You <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> cast the spell, doubling its duration!</b>" : "<b>You missed!</b>") + "</p>");
			} else {
				damageCostDescriptionSB.append(!selfCast
						? "<p>" + (isHit ? "<b>You did " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + damageType.getName() + "</b>" + " damage!</b>" : "<b>You missed!</b>") + "</p>"
						: "");
			}
			
			if (statusEffects != null && isHit) {
				damageCostDescriptionSB.append("<p>"
						+UtilText.parse(target,
								(!selfCast
									? "[npc.She] is now suffering "
									: "You are now benefiting from ")));
				
				int i = 0;
				for (Entry<StatusEffect, Integer> seEntry : statusEffects.entrySet()) {
					if (i != 0) {
						if (i == statusEffects.size() - 1) {
							damageCostDescriptionSB.append(" and ");
						} else {
							damageCostDescriptionSB.append(", ");
						}
					}
					damageCostDescriptionSB.append("<b>" + seEntry.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1)
							+ "</b> turns"
							+(caster.hasTrait(Perk.JOB_MUSICIAN, true)?" ([style.boldExcellent(doubled)] from <b style='color:"+Perk.JOB_MUSICIAN.getColour().toWebHexString()+";'>"+Perk.JOB_MUSICIAN.getName(caster)+"</b>)":"")
							+ " of <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
					i++;
				}
				damageCostDescriptionSB.append(".</p>");
			}

			damageCostDescriptionSB.append("<p>" + "Harnessing the arcane to cast spells is incredibly draining, and you lose <b>" + cost + "</b> <b style='color:" + Attribute.MANA_MAXIMUM.getColour().toWebHexString() + ";'>aura</b>!</b>" + "</p>");
		} else {

			if (isCritical) {
				damageCostDescriptionSB.append(!selfCast
						? "<p>" + (isHit ? "<b>You were <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> hit for " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>"
								+ damageType.getName() + "</b>" + " damage!</b>" : "<b>" + caster.getName("The") + " missed!</b>") + "</p>"
						: "<p>" + (isHit ? "<b>" + caster.getName("The") + " <b style='color: " + Colour.CLOTHING_GOLD.toWebHexString() + ";'>critically</b> cast the spell, doubling its duration!</b>"
								: "<b>" + caster.getName("The") + " missed!</b>") + "</p>");
			} else {
				damageCostDescriptionSB
						.append(!selfCast ? "<p>" + (isHit ? "<b>You took " + damage + " <b style='color: " + damageType.getMultiplierAttribute().getColour().toWebHexString() + ";'>" + damageType.getName() + "</b>" + " damage!</b>"
								: "<b>" + caster.getName("The") + " missed!</b>") + "</p>" : "");
			}
			
			if (statusEffects != null && isHit) {
				damageCostDescriptionSB.append(UtilText.parse(caster, (!selfCast ? "<p>You are now suffering " : "<p>[npc.She] is now benefiting from ")));
				int i = 0;
				for (Entry<StatusEffect, Integer> seEntry : statusEffects.entrySet()) {
					if (i != 0) {
						if (i == statusEffects.size() - 1) {
							damageCostDescriptionSB.append(" and ");
						} else {
							damageCostDescriptionSB.append(", ");
						}
					}
					damageCostDescriptionSB.append("<b>" + seEntry.getValue() * (caster.isPlayer() && caster.hasTrait(Perk.JOB_MUSICIAN, true)?2:1) * (isCritical?2:1)
							+ "</b> turns"
							+(caster.hasTrait(Perk.JOB_MUSICIAN, true)?" ([style.boldExcellent(doubled)] from <b style='color:"+Perk.JOB_MUSICIAN.getColour().toWebHexString()+";'>"+Perk.JOB_MUSICIAN.getName(caster)+"</b>)":"")
							+ " of <b style='color:" + seEntry.getKey().getColour().toWebHexString() + ";'>" + seEntry.getKey().getName(target) + "</b>");
					i++;
				}
				damageCostDescriptionSB.append("!</p>");
			}

			damageCostDescriptionSB.append(UtilText.parse(caster,
					"<p>" + "Harnessing the arcane to cast spells is incredibly draining, and [npc.she] loses <b>" + cost + "</b> <b style='color:" + Attribute.MANA_MAXIMUM.getColour().toWebHexString() + ";'>aura</b>!</b>" + "</p>"));
		}

		return damageCostDescriptionSB.toString();
	}

	public SpellSchool getSpellSchool() {
		return spellSchool;
	}

	public SpellType getType() {
		return type;
	}

	public int getSpellLevelRequired() {
		return spellLevelRequired;
	}

	public String getName() {
		return name;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public float getDamage() {
		return damage;
	}

	public DamageVariance getDamageVariance() {
		return damageVariance;
	}

	public Map<StatusEffect, Integer> getStatusEffects() {
		return statusEffects;
	}

	public String getSVGString() {
		return SVGString;
	}

	public static Map<SpellSchool, List<Spell>> getSpellsFromSchoolMap() {
		return spellsFromSchoolMap;
	}
}

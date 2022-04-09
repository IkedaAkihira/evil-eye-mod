package com.ikeharad.mymod;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.init.MobEffects;

public class EntityAIEyeEvilAttackMelee extends EntityAIAttackMelee {
    public EntityAIEyeEvilAttackMelee(EntityCreature creature, double speedIn, boolean useLongMemory) {
        super(creature, speedIn, useLongMemory);
    }

    @Override
    public boolean shouldContinueExecuting() {
        if(this.attacker.getAttackTarget()==null)
            return false;
        if(!this.attacker.getAttackTarget().isPotionActive(MobEffects.GLOWING)) {
            this.attacker.setAttackTarget(null);
            return false;
        }
        return super.shouldContinueExecuting();
    }
}

package com.ikeharad.mymod;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.Sys;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EntityAIEvilEyeSeenTarget extends EntityAITarget {
    private EntityLivingBase targetEntity;
    public EntityAIEvilEyeSeenTarget(EntityCreature creature, boolean checkSight) {
        super(creature, checkSight);
    }
    @Override
    public boolean shouldExecute() {
        //System.out.println("Evi Eye AI");
        List<Entity> list=this.taskOwner.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getTargetableArea(getTargetDistance()), new Predicate<Entity>() {
            @Override
            public boolean apply(@Nullable Entity input) {
                if(input==null)
                    return false;
                if(input==taskOwner)
                    return false;
                if(!(input instanceof EntityLivingBase))
                    return false;
                if(((EntityLivingBase) input).isPotionActive(MobEffects.GLOWING))
                    return true;
                return false;
            }
        });
        if(list.size()==0)
            return false;
        Collections.sort(list,new EntityAINearestAttackableTarget.Sorter(this.taskOwner));
        this.targetEntity=(EntityLivingBase) list.get(0);
        //System.out.println(targetEntity);
        return true;
    }

    @Override
    public void startExecuting() {
        //System.out.println(targetEntity);
        this.taskOwner.setAttackTarget(targetEntity);
        super.startExecuting();
    }

    protected AxisAlignedBB getTargetableArea(double targetDistance){
        return this.taskOwner.getEntityBoundingBox().grow(targetDistance, 10.0D, targetDistance);
    }
}

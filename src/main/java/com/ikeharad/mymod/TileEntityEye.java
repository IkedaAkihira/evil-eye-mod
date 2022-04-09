package com.ikeharad.mymod;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.Sys;

import java.util.List;
import java.util.Random;

public class TileEntityEye extends TileEntity implements ITickable {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        if(world.getWorldTime()%20==0) {
            EnumFacing facing=world.getBlockState(pos).getValue(BlockEye.FACING);
            BlockPos from0,to0;
            Vec3i vec;
            int observeDistance=10;
            switch (facing){
                case SOUTH:
                    from0=pos.add(0,0,1);
                    vec=new Vec3i(0,0,1);
                    break;
                case NORTH:
                    from0=pos.add(0,0,-1);
                    vec=new Vec3i(0,0,-1);
                    break;
                case EAST:
                    from0=pos.add(1,0,0);
                    vec=new Vec3i(1,0,0);
                    break;
                case WEST:
                    from0=pos.add(-1,0,0);
                    vec=new Vec3i(-1,0,0);
                    break;
                default:
                    from0=pos;
                    vec=new Vec3i(0,0,0);
            }
            to0=from0;
            int i;

            for(i=0;i<observeDistance;i++){
                if(world.getBlockState(to0).isOpaqueCube())
                    break;
                to0=to0.add(vec);
            }
            if(i!=0){
                to0=to0.subtract(vec);
            }
            BlockPos from=new BlockPos(
                    Math.min(from0.getX(),to0.getX()),
                    Math.min(from0.getY(),to0.getY()),
                    Math.min(from0.getZ(),to0.getZ())
            );
            BlockPos to=new BlockPos(
                    Math.max(from0.getX(),to0.getX())+1,
                    Math.max(from0.getY(),to0.getY())+1,
                    Math.max(from0.getZ(),to0.getZ())+1
            );

            AxisAlignedBB area = new AxisAlignedBB(
                from,
                to
            );
            List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, area, null);
            for (EntityLivingBase entity : entities) {
                entity.addPotionEffect(
                        new PotionEffect(
                                MobEffects.GLOWING,
                                20 * 1,
                                0,
                                true,
                                true
                        )
                );
            }
            Random rand=new Random();
            for (int j = 0; j < i; j++) {
                world.spawnParticle(
                        EnumParticleTypes.REDSTONE,
                        from.getX()+rand.nextDouble()*(to.getX()-from.getX()),
                        from.getY()+rand.nextDouble()*(to.getY()-from.getY()),
                        from.getZ()+rand.nextDouble()*(to.getZ()-from.getZ()),
                        1.0f,
                        1.0f,
                        1.0f
                );
            }
        }
    }
}

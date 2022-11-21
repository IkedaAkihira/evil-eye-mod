package com.ikeharad.mymod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.Sys;

import java.util.Random;

public class TileEntityBind extends TileEntity{
    String playerName;
    int damages=0;
    boolean isExist=true;

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setString("playerName",playerName);
        compound.setInteger("damages",damages);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        playerName=compound.getString("playerName");
        damages=compound.getInteger("damages");
        super.readFromNBT(compound);
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        //System.out.println("Living Hurt!!");
        //System.out.println(this);
        if(!isExist)
            return;
        if (playerName == null)
            return;
        Entity entity=event.getEntity();
        EntityPlayer player=getWorld().getPlayerEntityByName(playerName);
        if (player == null)
            return;
        if(entity==player) {
            damages+=event.getAmount();
            System.out.println(damages);
            if(damages>=300){
                world.createExplosion(null, pos.getX(), pos.getY(),pos.getZ(),1.0f,true);
            }
            event.setCanceled(true);
        }
    }

    public TileEntityBind(){
        MinecraftForge.EVENT_BUS.register(this);
    }
}
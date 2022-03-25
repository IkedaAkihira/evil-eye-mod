package com.ikeharad.mymod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;

import java.util.Random;

public class TileEntityBind extends TileEntity implements ITickable {
    String playerName;
    int damages=0;

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

    @Override
    public void update() {

        System.out.println(playerName);
        if (playerName == null)
            return;
        EntityPlayer player = getWorld().getPlayerEntityByName(playerName);
        if (player == null)
            return;
        if(player.isDead)
            return;
        damages+=(20.0f-player.getHealth());
        player.setHealth(20.0f);
    }
}

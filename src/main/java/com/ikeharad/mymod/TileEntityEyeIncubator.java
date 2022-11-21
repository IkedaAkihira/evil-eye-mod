package com.ikeharad.mymod;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEyeIncubator extends TileEntity {
    public int eyeCount;
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        eyeCount=compound.getInteger("eye_count");
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("eye_count",eyeCount);
        return super.writeToNBT(compound);
    }
}

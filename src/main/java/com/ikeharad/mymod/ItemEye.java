package com.ikeharad.mymod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import java.util.Iterator;

public class ItemEye extends ItemFood {
    public ItemEye(){
        super(0,5.0f,true);
        this.setRegistryName(MyMod.ID_MY_MOD,MyMod.NAME_EYE);
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setUnlocalizedName(MyMod.NAME_EYE);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        super.onFoodEaten(stack, worldIn, player);
        //player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,10,5));


        worldIn.createExplosion(null, player.posX, player.posY-1, player.posZ, 4.5f, true);
        worldIn.createExplosion(null, player.posX, player.posY-1, player.posZ, 4.5f, true);
        worldIn.createExplosion(null, player.posX, player.posY-1, player.posZ, 4.5f, true);
    }

}
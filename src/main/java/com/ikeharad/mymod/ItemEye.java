package com.ikeharad.mymod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

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
        worldIn.createExplosion(null, player.posX, player.posY, player.posZ, 8.0f, true);
    }

}
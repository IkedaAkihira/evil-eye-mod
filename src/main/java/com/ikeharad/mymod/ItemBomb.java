package com.ikeharad.mymod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Date;
import java.util.Random;

public class ItemBomb extends Item {
    public ItemBomb(){
        super();
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setRegistryName(MyMod.ID_MY_MOD,"bomb");
        this.setUnlocalizedName("bomb");
        this.setMaxStackSize(1);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        World world=player.getEntityWorld();
        world.createExplosion(player,entity.posX,entity.posY,entity.posZ,1.3f,true);
        return super.onLeftClickEntity(stack, player, entity);
    }
}

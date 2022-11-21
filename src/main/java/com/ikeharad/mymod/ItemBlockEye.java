package com.ikeharad.mymod;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBlockEye extends ItemBlock {
    public ItemBlockEye(Block block) {
        super(block);
    }

    @Override
    public boolean isValidArmor(ItemStack stack, EntityEquipmentSlot armorType, Entity entity) {
        return armorType==EntityEquipmentSlot.HEAD;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if(world.getWorldTime()%20==0) {
            player.addPotionEffect(
                    new PotionEffect(
                            MobEffects.BLINDNESS,
                            20 * 2,
                            0,
                            true,
                            true
                    )
            );
            /*player.addPotionEffect(
                    new PotionEffect(
                            MobEffects.WITHER,
                            20 * 2,
                            0,
                            true,
                            true
                    )
            );*/
        }
        super.onArmorTick(world, player, itemStack);
    }
}

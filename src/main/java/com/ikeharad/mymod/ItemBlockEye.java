package com.ikeharad.mymod;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
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
                            20 * 13,
                            0,
                            true,
                            true
                    )
            );

            player.addPotionEffect(
                    new PotionEffect(
                            MobEffects.GLOWING,
                            20 * 13,
                            0,
                            true,
                            true
                    )
            );

            player.addPotionEffect(
                    new PotionEffect(
                            MobEffects.SLOWNESS,
                            20*13,
                            3,
                            true,
                            true
                    )
            );

            player.addPotionEffect(
                    new PotionEffect(
                            MobEffects.HEALTH_BOOST,
                            20 * 13,
                            3,
                            true,
                            true
                    )
            );

            player.addPotionEffect(
                    new PotionEffect(
                            MobEffects.STRENGTH,
                            20 * 13,
                            2,
                            true,
                            true
                    )
            );
        }
        super.onArmorTick(world, player, itemStack);
    }
}

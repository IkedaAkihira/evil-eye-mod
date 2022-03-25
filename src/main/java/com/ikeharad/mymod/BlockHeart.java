package com.ikeharad.mymod;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockHeart extends Block {

    public BlockHeart() {
        super(Material.CAKE, MapColor.PINK);
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setRegistryName(MyMod.ID_MY_MOD,MyMod.NAME_HEART_BLOCK);
        this.setUnlocalizedName(MyMod.NAME_HEART_BLOCK);
        this.setHardness(0.1f);
        this.setResistance(0.1f);
    }
}

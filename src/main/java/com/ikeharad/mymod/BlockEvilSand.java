package com.ikeharad.mymod;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockEvilSand extends Block {

    public BlockEvilSand() {
        super(Material.SAND, MapColor.BLACK);
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setRegistryName(MyMod.ID_MY_MOD,MyMod.NAME_EVIL_SAND_BLOCK);
        this.setUnlocalizedName(MyMod.NAME_EVIL_SAND_BLOCK);
        this.setHardness(0.01f);
        this.setResistance(0.1f);
        this.setHarvestLevel("shovel",1);
    }
}

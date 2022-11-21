package com.ikeharad.mymod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class BlockBlack extends Block {
    public BlockBlack(){
        super(Material.ROCK, MapColor.BLACK);
        this.setRegistryName(MyMod.ID_MY_MOD,"black_block");
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setUnlocalizedName("black_block");
        this.setSoundType(SoundType.SLIME);
        this.setHardness(0.01f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe",2);
    }
}

package com.ikeharad.mymod.worldgen;

import com.ikeharad.mymod.EntityEyeApostle;
import com.ikeharad.mymod.MyMod;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeEndDecorator;
import net.minecraft.world.biome.BiomeHellDecorator;
import net.minecraft.world.biome.BiomeVoidDecorator;

public class BiomeEvilEye extends Biome{

    public BiomeEvilEye() {
        super(new BiomeProperties(MyMod.NAME_EYE_BIOME)
            .setBaseHeight(1.0f)
            .setHeightVariation(0.5f)
            .setTemperature(0.3f)
            );
        setRegistryName(MyMod.ID_MY_MOD,MyMod.NAME_EYE_BIOME);
        topBlock = MyMod.EVIL_SAND_BLOCK.getDefaultState();
        fillerBlock = MyMod.EVIL_SAND_BLOCK.getDefaultState();
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        spawnableMonsterList.add(new SpawnListEntry(
            EntityEyeApostle.class, 
            100, 
            2, 
            5
            )
        );
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new BiomeHellDecorator();
    }

    
}

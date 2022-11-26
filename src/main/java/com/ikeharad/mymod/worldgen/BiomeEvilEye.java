package com.ikeharad.mymod.worldgen;

import com.ikeharad.mymod.EntityEyeApostle;
import com.ikeharad.mymod.MyMod;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeHell;
import net.minecraft.world.biome.BiomeHellDecorator;

public class BiomeEvilEye extends Biome{

    public BiomeEvilEye() {
        super(new BiomeProperties(MyMod.NAME_EYE_BIOME)
            .setBaseHeight(0.0f)
            .setHeightVariation(0.5f)
            .setTemperature(0.3f)
            .setWaterColor(0)
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
            4, 
            5
            )
        );
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new BiomeHellDecorator();
    }

    
}

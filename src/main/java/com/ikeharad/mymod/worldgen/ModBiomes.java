package com.ikeharad.mymod.worldgen;

import com.ikeharad.mymod.*;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;

@ObjectHolder(MyMod.ID_MY_MOD)
public class ModBiomes
{
    // instantiate Biomes
    public final static BiomeEvilEye evil_eye_biome = new BiomeEvilEye();

    
    
    /**
     * This method should be called during the "init" FML lifecycle 
     * because it must happen after object handler injection.
     */
    public static void initBiomeManagerAndDictionary()
    {
        ForgeRegistries.BIOMES.register(evil_eye_biome);
        BiomeDictionary.addTypes(evil_eye_biome, 
                BiomeDictionary.Type.PLAINS
                );
    }
}
package com.ikeharad.mymod.worldgen;

import net.minecraft.block.BlockWeb;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.Chunk;

import com.ikeharad.mymod.*;

public class WorldProviderEvilEye extends WorldProvider{

    @Override
    protected void init() {
        super.init();
        this.doesWaterVaporize = false;
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderSingle(ModBiomes.evil_eye_biome);
    }

    @Override
    public DimensionType getDimensionType() {
        return MyMod.DIM_TYPE_EVIL_EYE;
    }

    @Override
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
        return new Vec3d(0.7,0.7,0.7);
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public boolean canDoLightning(net.minecraft.world.chunk.Chunk chunk)
    {
        return true;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk)
    {
        return false;
    }
    
    @Override
    public boolean canSnowAt(BlockPos pos, boolean checkLight)
    {
        return false; 
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z) {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks)
    {
        return 0.5F;
    }

    ///*
    @Override
    public boolean isDaytime() {
        return false;
    }
    /*
    */
}
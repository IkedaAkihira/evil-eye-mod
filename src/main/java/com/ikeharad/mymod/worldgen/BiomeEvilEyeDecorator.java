package com.ikeharad.mymod.worldgen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

public class BiomeEvilEyeDecorator extends BiomeDecorator{
    @Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos) {
        this.chunkPos = pos;
        if(!decorating){
            genDecorations(biome, worldIn, random);
            decorating = false;
        }
    }

    @Override
    protected void genDecorations(Biome biomeIn, World worldIn, Random random) {
        net.minecraft.util.math.ChunkPos forgeChunkPos = new net.minecraft.util.math.ChunkPos(chunkPos); // actual ChunkPos instead of BlockPos, used for events
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.terraingen.DecorateBiomeEvent.Pre(worldIn, random, forgeChunkPos));

        if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, random, forgeChunkPos, net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CUSTOM)){
            if(random.nextInt(2) == 0){
                (new WorldGenEvilEye()).generate(worldIn, random, chunkPos);
            }
        }
    }
    
}

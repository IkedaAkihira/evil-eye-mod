package com.ikeharad.mymod.worldgen;

import java.util.Random;

import com.ikeharad.mymod.BlockEye;
import com.ikeharad.mymod.MyMod;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEvilEye extends WorldGenerator{

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for(int i=0;i<64;i++){
            BlockPos totemRootPos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if(worldIn.isAirBlock(totemRootPos) && worldIn.getBlockState(totemRootPos.down()).getBlock() == MyMod.EVIL_SAND_BLOCK){
                int totemHeight = 10 + rand.nextInt(2);
                for(int j=0;j<totemHeight;j++){
                    BlockPos pos = totemRootPos.add(0, j, 0);
                    if(MyMod.EVIL_SAND_BLOCK.canPlaceBlockAt(worldIn, pos)){
                        System.out.println("\n\n\nGenerated Eye Totem!!\n\n\n");
                        worldIn.setBlockState(pos, MyMod.EYE_BLOCK.getDefaultState().withProperty(BlockEye.FACING, EnumFacing.Plane.HORIZONTAL.random(rand)), 2);
                    }
                }
            }
        }
        return true;
    }
    
}

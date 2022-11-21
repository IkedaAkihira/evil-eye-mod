package com.ikeharad.mymod;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockEyeIncubator extends Block implements ITileEntityProvider {
    static PropertyEnum<EnumEyeCount> EYE_COUNT_PROPERTY=PropertyEnum.create("eye_count",EnumEyeCount.class, Lists.newArrayList(EnumEyeCount.values()));
    public BlockEyeIncubator(){
        super(Material.ROCK, MapColor.BLACK);
        this.setDefaultState(getBlockState().getBaseState().withProperty(EYE_COUNT_PROPERTY,EnumEyeCount.ZERO));
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setUnlocalizedName(MyMod.NAME_EYE_INCUBATOR);
        this.setRegistryName(MyMod.NAME_EYE_INCUBATOR);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityEyeIncubator te=(TileEntityEyeIncubator) worldIn.getTileEntity(pos);
        if(te.eyeCount>=300){
            te.eyeCount-=300;
            worldIn.spawnEntity(new EntityItem(worldIn,pos.getX(),pos.getY(),pos.getZ(),new ItemStack(MyMod.EYE)));
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        TileEntityEyeIncubator te=(TileEntityEyeIncubator) worldIn.getTileEntity(pos);
        if(worldIn.getTotalWorldTime()%1==0){
            te.eyeCount=Math.min(te.eyeCount+1,2400);
        }
        state.withProperty(EYE_COUNT_PROPERTY,EnumEyeCount.getValueFromInteger(te.eyeCount/300));
        super.updateTick(worldIn, pos, state, rand);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEyeIncubator();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{EYE_COUNT_PROPERTY});
    }

    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(EYE_COUNT_PROPERTY,EnumEyeCount.getValueFromInteger(meta));
    }

    public int getMetaFromState(IBlockState state)
    {
        return ((EnumEyeCount)state.getValue(EYE_COUNT_PROPERTY)).getMeta();
    }
}

package com.ikeharad.mymod;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.Sys;

import javax.annotation.Nullable;

public class BlockBind extends Block implements ITileEntityProvider {

    public BlockBind() {
        super(Material.CLAY,MapColor.MAGENTA);
        this.setRegistryName(MyMod.ID_MY_MOD,MyMod.NAME_BIND_BLOCK);
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setUnlocalizedName(MyMod.NAME_BIND_BLOCK);
        this.setSoundType(SoundType.ANVIL);
        this.setHardness(1.0f);
        this.setHarvestLevel("pickaxe",4);
        this.setLightLevel(10.0f);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBind();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        System.out.println("aaa");
        System.out.println(placer.getClass());
        TileEntityBind te=(TileEntityBind) worldIn.getTileEntity(pos);
        te.playerName=placer.getName();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityBind te=(TileEntityBind) worldIn.getTileEntity(pos);
        System.out.println(te.damages);
        if(te.playerName==null)
            return;
        EntityPlayer player=worldIn.getPlayerEntityByName(te.playerName);
        te.isExist=false;
        if(player==null)
            return;
        player.attackEntityFrom(new DamageSource((te.damages>=300)?"price_over":"price"),te.damages);
        super.breakBlock(worldIn, pos, state);
    }
}

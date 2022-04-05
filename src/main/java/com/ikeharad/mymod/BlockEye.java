package com.ikeharad.mymod;

import com.google.common.base.Optional;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Random;

import static com.ikeharad.mymod.MyMod.EYE;

public class BlockEye extends BlockHorizontal implements ITileEntityProvider{

    public static final IProperty<Boolean> IS_HANDLES= PropertyBool.create("is_handles");

    //public static final PropertyDirection FACING=BlockHorizontal.FACING;

    public BlockEye(){
        super(Material.CLAY, MapColor.BLACK);
        this.setRegistryName(MyMod.ID_MY_MOD,MyMod.NAME_EYE_BLOCK);
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setUnlocalizedName("eye_block");
        this.setSoundType(SoundType.SLIME);
        this.setHardness(5.0f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe",1);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING,EnumFacing.NORTH));

        //this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING,EnumFacing.NORTH));
    }



    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return EYE;
    }

    @Override
    public int quantityDropped(Random random) {
        return random.nextInt(25)/10;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityEye();
    }

    @Override
    public void breakBlock(World world,BlockPos blockPos,IBlockState state){
        super.breakBlock(world,blockPos,state);
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        System.out.println(player.getName());
        player.addPotionEffect(
                new PotionEffect(
                        MobEffects.WITHER,
                        20*13,
                        4,
                        true,
                        true
                )
        );
        player.addPotionEffect(
                new PotionEffect(
                        MobEffects.HUNGER,
                        20*13,
                        169,
                        true,
                        true
                )
        );
        player.addPotionEffect(
                new PotionEffect(
                        MobEffects.BLINDNESS,
                        20*13,
                        169,
                        true,
                        true
                )
        );
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING,rot.rotate((EnumFacing) state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return this.getDefaultState().withProperty(FACING,placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return super.getDefaultState().withProperty(FACING,EnumFacing.getHorizontal(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }
}

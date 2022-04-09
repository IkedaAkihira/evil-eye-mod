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
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Random;

import static com.ikeharad.mymod.MyMod.EYE;

public class BlockEye extends BlockHorizontal implements ITileEntityProvider{

    public static final IProperty<Boolean> IS_HANDLES= PropertyBool.create("is_handles");

    //public static final PropertyDirection FACING=BlockHorizontal.FACING;

    public BlockEye(){
        super(Material.ROCK, MapColor.BLACK);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING,EnumFacing.NORTH));
        this.setRegistryName(MyMod.ID_MY_MOD,MyMod.NAME_EYE_BLOCK);
        this.setCreativeTab(MyMod.Tabs.MY_MOD);
        this.setUnlocalizedName("eye_block");
        this.setSoundType(SoundType.SLIME);
        this.setHardness(0.01f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe",2);

        //this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING,EnumFacing.NORTH));
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return EYE;
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

    /*@Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (!willHarvest) {
            if(world.isRemote) {
                for (int i = 0; i < 130; i++)
                    world.spawnParticle(
                            EnumParticleTypes.ENCHANTMENT_TABLE,
                            pos.getX() + world.rand.nextDouble() * 1.3 - 0.15,
                            pos.getY() + world.rand.nextDouble() * 1.3,
                            pos.getZ() + world.rand.nextDouble() * 1.3 - 0.15,
                            0.0,
                            0.0,
                            0.0
                    );
            }else {
                player.addPotionEffect(
                        new PotionEffect(
                                MobEffects.BLINDNESS,
                                20 * 13,
                                0,
                                true,
                                true
                        )
                );
                player.addPotionEffect(
                        new PotionEffect(
                                MobEffects.GLOWING,
                                20 * 13,
                                0,
                                true,
                                true
                        )
                );
                player.addPotionEffect(
                        new PotionEffect(
                                MobEffects.SLOWNESS,
                                20 * 13,
                                3,
                                true,
                                true
                        )
                );
            }
        }
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }*/

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

package com.ikeharad.mymod;

import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;

import javax.annotation.Nullable;
import java.util.Random;

import static com.ikeharad.mymod.MyMod.EYE;

public class BlockEye extends Block implements ITileEntityProvider {

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
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        System.out.println("Activated!!");
        System.out.println(player.getName());
        return true;
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
}

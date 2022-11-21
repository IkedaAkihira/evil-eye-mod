package com.ikeharad.mymod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.Sys;

import java.util.List;
import java.util.Random;

public class TileEntityEye extends TileEntity implements ITickable {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return super.writeToNBT(compound);
    }

    @Override
    public void update() {
        long time=world.getWorldTime();
        if(time%10==0) {
            EnumFacing facing = world.getBlockState(pos).getValue(BlockEye.FACING);
            BlockPos from0, to0;
            Vec3i vec;
            int observeDistance = 10;
            switch (facing) {
                case SOUTH:
                    from0 = pos.add(0, 0, 1);
                    vec = new Vec3i(0, 0, 1);
                    break;
                case NORTH:
                    from0 = pos.add(0, 0, -1);
                    vec = new Vec3i(0, 0, -1);
                    break;
                case EAST:
                    from0 = pos.add(1, 0, 0);
                    vec = new Vec3i(1, 0, 0);
                    break;
                case WEST:
                    from0 = pos.add(-1, 0, 0);
                    vec = new Vec3i(-1, 0, 0);
                    break;
                default:
                    from0 = pos;
                    vec = new Vec3i(0, 0, 0);
            }
            to0 = from0;
            int i;

            for (i = 0; i < observeDistance; i++) {
                if (world.getBlockState(to0).isOpaqueCube())
                    break;
                to0 = to0.add(vec);
            }
            if (i != 0) {
                to0 = to0.subtract(vec);
            }
            BlockPos from = new BlockPos(
                    Math.min(from0.getX(), to0.getX()),
                    Math.min(from0.getY(), to0.getY()),
                    Math.min(from0.getZ(), to0.getZ())
            );
            BlockPos to = new BlockPos(
                    Math.max(from0.getX(), to0.getX()) + 1,
                    Math.max(from0.getY(), to0.getY()) + 1,
                    Math.max(from0.getZ(), to0.getZ()) + 1
            );

            AxisAlignedBB area = new AxisAlignedBB(
                    from,
                    to
            );
            List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, area, null);
            for (EntityLivingBase entity : entities) {
                entity.addPotionEffect(
                        new PotionEffect(
                                MobEffects.GLOWING,
                                20 * 1,
                                0,
                                true,
                                true
                        )
                );
            }
            //System.out.println(time);
            //if (time % 80 == 0) {
                Random rand = new Random();
                for (int j = 0; j < i; j++) {
                /*world.spawnParticle(
                        EnumParticleTypes.SWEEP_ATTACK,
                        from.getX()+rand.nextDouble()*(to.getX()-from.getX()),
                        from.getY()+rand.nextDouble()*(to.getY()-from.getY()),
                        from.getZ()+rand.nextDouble()*(to.getZ()-from.getZ()),
                        1.0f,
                        1.0f,
                        1.0f
                );*/
                    if(rand.nextInt(5)==0)
                        Minecraft.getMinecraft().effectRenderer.addEffect(
                                new ParticleEye(
                                        world,
                                        from.getX() + rand.nextDouble() * (to.getX() - from.getX()),
                                        from.getY() + rand.nextDouble() * (to.getY() - from.getY()),
                                        from.getZ() + rand.nextDouble() * (to.getZ() - from.getZ())
                                )
                        );
                    else
                        Minecraft.getMinecraft().effectRenderer.addEffect(
                                new ParticleEyeFlame(
                                        world,
                                        from.getX() + rand.nextDouble() * (to.getX() - from.getX()),
                                        from.getY() + rand.nextDouble() * (to.getY() - from.getY()),
                                        from.getZ() + rand.nextDouble() * (to.getZ() - from.getZ())
                                )
                        );
                }
            //}
        }
    }

    /*boolean judge(BlockPos start, BlockPos goal){
        //https://tech.cygames.co.jp/archives/2272/#:~:text=%E5%8A%B9%E6%9E%9C%E7%9A%84%E3%81%A7%E3%81%99%E3%80%82-,2.%20%E8%A6%96%E7%B7%9A%E3%81%AE%E9%81%AE%E8%94%BD%E3%82%92%E8%A9%95%E4%BE%A1,-%E3%81%8A%E5%8C%96%E3%81%91%E3%81%8B%E3%82%89%E8%A6%8B
        int curCell_X = start.getX(); // 現在のセルの水平軸インデックス( 初期セルのインデックスを代入 )
        int curCell_Y = start.getY(); // 現在のセルの垂直軸インデックス( 初期セルのインデックスを代入 )
        int curCell_Z = start.getZ();
        int dir_X = 1; //直線の水平軸方向の進行方向
        int dir_Y = 1; //直線の垂直軸方向の進行方向
        int dir_Z = 1;





        double dist = Math.hypot(start.getX()-goal.getX(),Math.hypot(start.getY()-goal.getY(),start.getX()-goal.getX())); //始点から終点までの距離
        double t = 0; //始点から現在の交点までの距離


        int tx = dist/Math.abs(start.getX()-goal.getX()); //水平軸と直線との交差点間の距離
        int ty = *; //垂直軸と直線との交差点間の距離
        int tz = *;

        double next_X = *: //次の垂直軸との交差点までの、始点からの距離
        double next_Y = *; //次の水平軸との交差点までの、始点からの距離

        while( t < dist ){
            if( next_X <= next_Y ){ //水平軸との交点の方が近い
                curCell_X += dir_X; t = next_X; next_X +=tx; //水平軸方向に交点を辿る
            }else{ //垂直軸との交点の方が近い
                curCell_Y += dir_Y; t = next_Y; next_Y +=ty; //垂直軸方向に交点を辿る
            }

            //curCell_X、curCell_Yが示すセルの状態を見る
        }
        return true;
    }*/
}

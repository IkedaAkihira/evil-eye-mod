package com.ikeharad.mymod;

import ibxm.Player;
import net.minecraft.advancements.Advancement;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.fixes.PotionItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraft.creativetab.CreativeTabs.getNextID;


@Mod(modid="mymod",version="1.0",name="MyMod")
public class MyMod {
    public static final String ID_MY_MOD="mymod",
    NAME_EYE="eye",
    NAME_EYE_BLOCK="eye_block",
    NAME_BIND_BLOCK="bind_block",
    NAME_HEART_BLOCK="heart_block",
    NAME_NOT_HUMAN_ACHIEVEMENT="not_human_achievement",
    LABEL_MY_MOD_TAB="mymod_tab";

    public static final Item EYE= new ItemEye();
    public static final Block EYE_BLOCK=new BlockEye();
    public static final Block BIND_BLOCK=new BlockBind();
    public static final Block HEART_BLOCK=new BlockHeart();

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(EYE);

        event.getRegistry().register(new ItemBlock(EYE_BLOCK).setRegistryName(ID_MY_MOD, NAME_EYE_BLOCK));
        GameRegistry.registerTileEntity(TileEntityEye.class, new ResourceLocation(ID_MY_MOD, NAME_EYE_BLOCK));

        GameRegistry.registerTileEntity(TileEntityBind.class, new ResourceLocation(ID_MY_MOD, NAME_BIND_BLOCK));
        event.getRegistry().register(new ItemBlock(BIND_BLOCK).setRegistryName(ID_MY_MOD, NAME_BIND_BLOCK));

        event.getRegistry().register(new ItemBlock(HEART_BLOCK).setRegistryName(ID_MY_MOD, NAME_HEART_BLOCK));
    }
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event){

        event.getRegistry().register(EYE_BLOCK);
        event.getRegistry().register(BIND_BLOCK);
        event.getRegistry().register(HEART_BLOCK);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){

        ModelLoader.setCustomModelResourceLocation(EYE,0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_EYE),"inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(EYE_BLOCK),0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_EYE_BLOCK),"inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BIND_BLOCK),0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_BIND_BLOCK),"inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(HEART_BLOCK),0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_HEART_BLOCK),"inventory"));
    }

    abstract static class Tabs{
        public static CreativeTabs MY_MOD=new CreativeTabs(getNextID(),LABEL_MY_MOD_TAB) {
            @Override
            @SideOnly(Side.CLIENT)
            public ItemStack getTabIconItem() {
                return new ItemStack(EYE);
            }
        };
    }
}
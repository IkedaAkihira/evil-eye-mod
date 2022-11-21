package com.ikeharad.mymod;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Iterator;

import static net.minecraft.creativetab.CreativeTabs.getNextID;


@Mod(modid="mymod",version="1.0",name="MyMod")
public class MyMod {
    public static final String ID_MY_MOD="mymod",
    NAME_EYE="eye",
    NAME_EYE_BLOCK="eye_block",
    NAME_BIND_BLOCK="bind_block",
    NAME_HEART_BLOCK="heart_block",
    NAME_NOT_HUMAN_ACHIEVEMENT="not_human_achievement",
    NAME_EYE_APOSTLE="eye_apostle",
    LABEL_MY_MOD_TAB="mymod_tab",
    NAME_BOMB="bomb",
    NAME_EYE_INCUBATOR="eye_incubator";

    public static final Item EYE= new ItemEye();
    public static final Block EYE_BLOCK=new BlockEye();
    public static final Block BIND_BLOCK=new BlockBind();
    public static final Block HEART_BLOCK=new BlockHeart();
    public static final Item BOMB=new ItemBomb();
    public static final Block BLACK_BLOCK=new BlockBlack();
    public static final Block EYE_INCUBATOR_BLOCK=new BlockEyeIncubator();

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(EYE);
        event.getRegistry().register(BOMB);

        event.getRegistry().register(new ItemBlockEye(EYE_BLOCK).setRegistryName(ID_MY_MOD, NAME_EYE_BLOCK));
        GameRegistry.registerTileEntity(TileEntityEye.class, new ResourceLocation(ID_MY_MOD, NAME_EYE_BLOCK));

        GameRegistry.registerTileEntity(TileEntityBind.class, new ResourceLocation(ID_MY_MOD, NAME_BIND_BLOCK));
        event.getRegistry().register(new ItemBlock(BIND_BLOCK).setRegistryName(ID_MY_MOD, NAME_BIND_BLOCK));

        event.getRegistry().register(new ItemBlock(HEART_BLOCK).setRegistryName(ID_MY_MOD, NAME_HEART_BLOCK));

        event.getRegistry().register(new ItemBlock(BLACK_BLOCK).setRegistryName(ID_MY_MOD,"black_block"));
        event.getRegistry().register(new ItemBlock(EYE_INCUBATOR_BLOCK).setRegistryName(ID_MY_MOD,NAME_EYE_INCUBATOR));
        GameRegistry.registerTileEntity(TileEntityEyeIncubator.class, new ResourceLocation(ID_MY_MOD, NAME_EYE_INCUBATOR));
    }
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event){

        event.getRegistry().register(EYE_BLOCK);
        event.getRegistry().register(BIND_BLOCK);
        event.getRegistry().register(HEART_BLOCK);
        event.getRegistry().register(BLACK_BLOCK);
        event.getRegistry().register(EYE_INCUBATOR_BLOCK);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(EYE,0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_EYE),"inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(EYE_BLOCK),0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_EYE_BLOCK),"inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BIND_BLOCK),0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_BIND_BLOCK),"inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(HEART_BLOCK),0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_HEART_BLOCK),"inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BLACK_BLOCK),0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,"black_block"),"inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(EYE_INCUBATOR_BLOCK),0,new ModelResourceLocation(new ResourceLocation(ID_MY_MOD,NAME_EYE_INCUBATOR),"inventory"));
    }


    abstract static class Tabs{
        public static CreativeTabs MY_MOD=new CreativeTabs(getNextID(),LABEL_MY_MOD_TAB) {
            @Override
            @SideOnly(Side.CLIENT)
            @MethodsReturnNonnullByDefault
            public ItemStack getTabIconItem() {
                return new ItemStack(EYE);
            }
        };
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityAttacked(LivingAttackEvent event){
        //System.out.println("faze 1");
        if(event.getEntity() instanceof EntityPlayer){
            //System.out.println("faze 2");
            EntityPlayer entityLiving=(EntityPlayer) event.getEntity();
            //System.out.println(entityLiving);
            Iterable<ItemStack> armorList=entityLiving.getArmorInventoryList();
            Iterator<ItemStack> iterator= armorList.iterator();
            while(iterator.hasNext()){
                ItemStack itemStack=iterator.next();
                if(itemStack.getItem()== Items.GOLDEN_CHESTPLATE){
                    //System.out.println("faze 3");
                    Entity entity=event.getSource().getTrueSource();
                    //System.out.println(entity);
                    if(entity!=null){
                        entity.getEntityWorld().createExplosion(entityLiving,entity.posX,entity.posY,entity.posZ,2.5f,true);
                    }

                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void render(){
        RenderingRegistry.registerEntityRenderingHandler(EntityEyeApostle.class, new IRenderFactory<EntityEyeApostle>(){
            @Override
            public Render<? super EntityEyeApostle> createRenderFor(RenderManager manager){
                return new RenderBiped<EntityEyeApostle>(manager,new ModelBiped(),0.5f);
            }
        });
    }

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event){
        EntityRegistry.registerModEntity(new ResourceLocation(ID_MY_MOD,NAME_EYE_APOSTLE),EntityEyeApostle.class,NAME_EYE_APOSTLE,0,this,200,1,true);
    }

}
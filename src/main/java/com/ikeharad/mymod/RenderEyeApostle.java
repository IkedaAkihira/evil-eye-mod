package com.ikeharad.mymod;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;

@SideOnly(Side.CLIENT)
public class RenderEyeApostle extends RenderBiped<EntityEyeApostle>
{
    private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation(MyMod.ID_MY_MOD,"textures/entity/eye.png");

    public RenderEyeApostle(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelEyeApostle(), 0.5F);
        LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this)
        {
            protected void initArmor()
            {
                this.modelLeggings = new ModelZombie(0.5F, true);
                this.modelArmor = new ModelZombie(1.0F, true);
            }
        };
        this.addLayer(layerbipedarmor);
        this.addLayer(new LayerEyeApostle(this));
    }

    protected ResourceLocation getEntityTexture(EntityEyeApostle entity)
    {
        return ZOMBIE_TEXTURES;
    }
}

package ru.zoom4ikdan4ik.minecraftmenu.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.Charsets;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;
import ru.zoom4ikdan4ik.minecraftmenu.interfaces.IMinecraftMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GuiCustomMenu extends GuiMainMenu implements IMinecraftMenu {
    private int panoramaTimer;
    private static final ResourceLocation[] titlePanoramaPaths = new ResourceLocation[]{
            new ResourceLocation("textures/gui/title/background/panorama_0.png"),
            new ResourceLocation("textures/gui/title/background/panorama_1.png"),
            new ResourceLocation("textures/gui/title/background/panorama_2.png"),
            new ResourceLocation("textures/gui/title/background/panorama_3.png"),
            new ResourceLocation("textures/gui/title/background/panorama_4.png"),
            new ResourceLocation("textures/gui/title/background/panorama_5.png")};
    private DynamicTexture field_73977_n = new DynamicTexture(256, 256);
    private ResourceLocation field_110352_y = new ResourceLocation("textures/gui/title/minecraft.png");
    private static final ResourceLocation splashTexts = new ResourceLocation("texts/splashes.txt");
    private String splashText;

    public void initGui() {
        super.initGui();

        List rem = new ArrayList();

        for (Object b : super.buttonList)
            if (b instanceof GuiButton) {
                GuiButton button = (GuiButton) b;

                if (button.id == 14) {
                    button.displayString = "Группа ВК";
                    button.width = 200;
                    button.id = 221;
                }

                if (button.id == 6)
                    rem.add(b);
            }

        for (Object o : rem)
            super.buttonList.remove(o);

        try {
            BufferedReader var1 = new BufferedReader(new InputStreamReader(
                    Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(),
                    Charsets.UTF_8));

            String var3;
            ArrayList var2 = new ArrayList();

            while ((var3 = var1.readLine()) != null) {
                var3 = var3.trim();

                if (!var3.isEmpty()) {
                    var2.add(var3);
                }
            }

            if (!var2.isEmpty()) {
                do {
                    this.splashText = (String) var2.get(new Random().nextInt(var2.size()));
                } while (this.splashText.hashCode() == 125780783);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(GuiButton p_146284_1_) {
        super.actionPerformed(p_146284_1_);

        if (p_146284_1_.id == 221)
            go(configManager.vk);
    }

    public static void go(String url) {
        try {
            Class oclass = Class.forName("java.awt.Desktop");
            Object object = oclass.getMethod("getDesktop", new Class[0]).invoke(null);
            oclass.getMethod("browse", new Class[]{URI.class}).invoke(object, new URI(url));
        } catch (Throwable var3) {
            var3.printStackTrace();
        }
    }

    public void updateScreen() {
        ++this.panoramaTimer;
    }

    private void drawPanorama(int p_73970_1_, int p_73970_2_, float p_73970_3_) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glMatrixMode(5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(5888);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glDisable(2884);
        GL11.glDepthMask(false);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        byte b0 = 8;

        for (int k = 0; k < b0 * b0; ++k) {
            GL11.glPushMatrix();
            float f1 = ((float) (k % b0) / (float) b0 - 0.5F) / 64.0F;
            float f2 = ((float) (k / b0) / (float) b0 - 0.5F) / 64.0F;
            float f3 = 0.0F;
            GL11.glTranslatef(f1, f2, f3);
            GL11.glRotatef(MathHelper.sin(((float) this.panoramaTimer + p_73970_3_) / 400.0F) * 25.0F + 20.0F, 1.0F,
                    0.0F, 0.0F);
            GL11.glRotatef(-((float) this.panoramaTimer + p_73970_3_) * 0.1F, 0.0F, 1.0F, 0.0F);

            for (int l = 0; l < 6; ++l) {
                GL11.glPushMatrix();
                if (l == 1) {
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 2) {
                    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 3) {
                    GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                }

                if (l == 4) {
                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                }

                if (l == 5) {
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                super.mc.getTextureManager().bindTexture(titlePanoramaPaths[l]);
                tessellator.startDrawingQuads();
                tessellator.setColorRGBA_I(16777215, 255 / (k + 1));
                float f4 = 0.0F;
                tessellator.addVertexWithUV(-1.0D, -1.0D, 1.0D, 0.0F + f4, 0.0F + f4);
                tessellator.addVertexWithUV(1.0D, -1.0D, 1.0D, 1.0F - f4, 0.0F + f4);
                tessellator.addVertexWithUV(1.0D, 1.0D, 1.0D, 1.0F - f4, 1.0F - f4);
                tessellator.addVertexWithUV(-1.0D, 1.0D, 1.0D, 0.0F + f4, 1.0F - f4);
                tessellator.draw();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        tessellator.setTranslation(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5888);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(2884);
        GL11.glEnable(2929);
    }

    private void rotateAndBlurSkybox(float p_73968_1_) {
        super.mc.getTextureManager()
                .bindTexture(super.mc.getTextureManager().getDynamicTextureLocation("background", this.field_73977_n));
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
        GL11.glEnable(3042);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColorMask(true, true, true, false);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        GL11.glDisable(3008);
        byte b0 = 3;

        for (int i = 0; i < b0; ++i) {
            tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F / (float) (i + 1));
            int j = super.width;
            int k = super.height;
            float f1 = (float) (i - b0 / 2) / 256.0F;
            tessellator.addVertexWithUV(j, k, super.zLevel, 0.0F + f1, 1.0D);
            tessellator.addVertexWithUV(j, 0.0D, super.zLevel, 1.0F + f1, 1.0D);
            tessellator.addVertexWithUV(0.0D, 0.0D, super.zLevel, 1.0F + f1, 0.0D);
            tessellator.addVertexWithUV(0.0D, k, super.zLevel, 0.0F + f1, 0.0D);
        }

        tessellator.draw();
        GL11.glEnable(3008);
        GL11.glColorMask(true, true, true, true);
    }

    private void renderSkybox(int p_73971_1_, int p_73971_2_, float p_73971_3_) {
        super.mc.getFramebuffer().unbindFramebuffer();
        GL11.glViewport(0, 0, 256, 256);
        this.drawPanorama(p_73971_1_, p_73971_2_, p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        this.rotateAndBlurSkybox(p_73971_3_);
        super.mc.getFramebuffer().bindFramebuffer(true);
        GL11.glViewport(0, 0, super.mc.displayWidth, super.mc.displayHeight);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        float f1 = super.width > super.height ? 120.0F / (float) super.width : 120.0F / (float) super.height;
        float f2 = (float) super.height * f1 / 256.0F;
        float f3 = (float) super.width * f1 / 256.0F;
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        int k = super.width;
        int l = super.height;
        tessellator.addVertexWithUV(0.0D, l, super.zLevel, 0.5F - f2,
                0.5F + f3);
        tessellator.addVertexWithUV(k, l, super.zLevel, 0.5F - f2,
                0.5F - f3);
        tessellator.addVertexWithUV(k, 0.0D, super.zLevel, 0.5F + f2,
                0.5F - f3);
        tessellator.addVertexWithUV(0.0D, 0.0D, super.zLevel, 0.5F + f2, 0.5F + f3);
        tessellator.draw();
    }

    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        GL11.glDisable(3008);
        this.renderSkybox(p_73863_1_, p_73863_2_, p_73863_3_);
        GL11.glEnable(3008);
        Tessellator tessellator = Tessellator.instance;
        short short1 = 274;
        int k = super.width / 2 - short1 / 2;
        byte b0 = 30;
        this.drawGradientRect(0, 0, super.width, super.height, -2130706433, 16777215);
        this.drawGradientRect(0, 0, super.width, super.height, 0, Integer.MIN_VALUE);
        super.mc.getTextureManager().bindTexture(this.field_110352_y);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(k + 0, b0 + 0, 0, 0, 155, 44);
        this.drawTexturedModalRect(k + 155, b0 + 0, 0, 45, 155, 44);
        tessellator.setColorOpaque_I(-1);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) (super.width / 2 + 90), 70.0F, 0.0F);
        GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
        float f1 = 1.8F - MathHelper
                .abs(MathHelper.sin((float) (Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
        f1 = f1 * 100.0F / (float) (super.fontRendererObj.getStringWidth(configManager.project) + 32);
        GL11.glScalef(f1, f1, f1);
        this.drawCenteredString(super.fontRendererObj, this.splashText, 0, -8, -256);
        GL11.glPopMatrix();
        this.drawString(super.fontRendererObj, configManager.client,
                super.width - super.fontRendererObj.getStringWidth(configManager.client) - 2, super.height - 10, -1);
        this.drawString(super.fontRendererObj, configManager.project, 2, super.height - 10, -1);

        for (k = 0; k < super.buttonList.size(); ++k) {
            ((GuiButton) super.buttonList.get(k)).drawButton(super.mc, p_73863_1_, p_73863_2_);
        }

    }
}

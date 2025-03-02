package serilia.world.draw.drawblock;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.DrawBlock;

public class DrawBurstArrows extends DrawBlock {
    public int arrows = 3;
    public float arrowSpacing = 4f, arrowOffset = 0f;
    public Color arrowColor = Color.valueOf("feb380"), baseArrowColor = Color.valueOf("6e7080");
    public Color glowColor = arrowColor.cpy().a(0.6f);
    TextureRegion arrow, arrowBlur, glow;

    public DrawBurstArrows(){}

    public DrawBurstArrows(int arrows, float arrowSpacing, float arrowOffset){
        this.arrows = arrows;
        this.arrowSpacing = arrowSpacing;
        this.arrowOffset = arrowOffset;
    }

    @Override
    public void draw(Building build) {
        if (!(build instanceof BurstDrill.BurstDrillBuild) || ((BurstDrill.BurstDrillBuild)build).dominantItem == null) return;

        float fract = ((BurstDrill.BurstDrillBuild)build).smoothProgress;
        Draw.color(arrowColor);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < arrows; j++) {
                float arrowFract = (arrows - 1 - j);
                float a = Mathf.clamp(fract * arrows - arrowFract);
                Tmp.v1.trns(i * 90 + 45, j * arrowSpacing + arrowOffset);

                Draw.z(Layer.block);
                Draw.color(baseArrowColor, arrowColor, a);
                Draw.rect(arrow, build.x + Tmp.v1.x, build.y + Tmp.v1.y, i * 90);

                Draw.color(arrowColor);

                if (arrowBlur.found()) {
                    Draw.z(Layer.blockAdditive);
                    Draw.blend(Blending.additive);
                    Draw.alpha(Mathf.pow(a, 10f));
                    Draw.rect(arrowBlur, build.x + Tmp.v1.x, build.y + Tmp.v1.y, i * 90);
                    Draw.blend();
                }
            }
        }
        Draw.color();

        if(glow.found()){
            Drawf.additive(glow, Tmp.c2.set(glowColor).a(Mathf.pow(((BurstDrill.BurstDrillBuild)build).smoothProgress, 3f) * glowColor.a), build.x, build.y);
        }
    }

    @Override
    public void load(Block block){

        arrow = Core.atlas.find(block.name + "-arrow");
        arrowBlur = Core.atlas.find(block.name + "-arrow-blur");
        glow = Core.atlas.find(block.name + "-glow");
    }
}

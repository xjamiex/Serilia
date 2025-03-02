package serilia.world.draw.drawblock;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import arc.util.Log;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;

//DrawDirty lives on
public class DrawProgressFrames extends DrawBlock {
    public TextureRegion[] regions;
    public int frames = 3;
    public String suffix = "", region;
    public Color color = Color.white;

    public DrawProgressFrames(int frames){
        this.frames = frames;
    }

    @Override
    public void draw(Building build) {
        Draw.color(color);
        Draw.rect(regions[(int)(frames * build.progress())], build.x, build.y);
        Log.info(build.progress());
        Draw.color();
    }

    @Override
    public void load(Block block){
        Seq<TextureRegion> r = new Seq<>();
        for(int i = 0; i < frames; i++) r.add(Core.atlas.find(region != null ? region + i : block.name + suffix + i));
        regions = r.toArray(TextureRegion.class);
    }
}

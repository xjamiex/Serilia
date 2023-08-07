package serilia.content;

import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.Attributes;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.draw.DrawBlurSpin;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockGroup;
import serilia.world.draw.drawblock.DrawHalfSpinner;

import static mindustry.type.Category.production;
import static mindustry.type.ItemStack.with;

public class AndBlocks {
    public static Block
            //drills
            hydraulicCrusher,

    //env
    thinIce;


    public static void load() {
        //env
        thinIce = new Floor("thin-ice") {{
            dragMultiplier = 0.35f;
            speedMultiplier = 0.9f;
            attributes.set(AndAttributes.thinIce, 1f);
            albedo = 0.65f;
        }};


        //drills
        hydraulicCrusher = new AttributeCrafter("hydraulic-crusher") {{
            requirements(production, with(Items.copper, 60));
            attribute = AndAttributes.thinIce;
            group = BlockGroup.drills;
            displayEfficiency = false;
            minEfficiency = 0.0001f;
            baseEfficiency = 0f;
            boostScale = 1f / 4f;
            size = 2;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            craftTime = 120f;

            drawer = new DrawMulti(new DrawDefault(), new DrawHalfSpinner("-rotator", 0.6f * 9f), new DrawRegion("-top"));
            hasLiquids = true;
            outputLiquid = new LiquidStack(Liquids.water, 5f / 60f / 9f);
            outputItem = new ItemStack(Items.thorium, 4);
            liquidCapacity = 20f;
            researchCost = with(Items.copper, 15);
        }};
    }
}

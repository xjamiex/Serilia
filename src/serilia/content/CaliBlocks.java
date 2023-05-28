package serilia.content;

import mindustry.content.Fx;
import mindustry.type.Category;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.BurstDrill;
import mindustry.world.blocks.storage.CoreBlock;
import serilia.world.blocks.distribution.ShadedDuct;
import serilia.world.blocks.misc.DrawTest;
import serilia.world.blocks.payload.MoreGenericCrafter;

import static mindustry.type.Category.*;
import static mindustry.type.ItemStack.with;
import static mindustry.world.meta.BuildVisibility.sandboxOnly;
import static serilia.content.SeResources.*;

public class CaliBlocks {
    public static Block

        //turret

        //drill
        heatDrill, largeheatdrill, ignitionDrill, radiatorBore, bulkQuarry, bulkDrill,

        //distribution (payload too)
        ducter,

        //liquid

        //power

        //defense

        //crafting
        bulkRefinery,

        //unit

        //effect
        coreSprout, coreBurgeon, coreGreenhouse,

        //payloads
        vanadiniteRock,

        //misc
        drawTest, bitTiler;

    public static void load() {

        //drill

        heatDrill = new BurstDrill("heat-drill"){{
            requirements(Category.production, with(SeResources.iridium, 20));
            scaledHealth = 75;
            drillTime = 60f * 10f;
            size = 2;
            tier = 1;
            drillEffect = Fx.mineBig;
            shake = 0.5f;
            itemCapacity = 16;
            arrowOffset = 0f;
            arrowSpacing = 0f;
            arrows = 1;

            drillMultipliers.put(SeResources.iridium, 1.5f);

            consumeLiquids(LiquidStack.with(SeResources.methane, 5f / 60f));

        }};

        //distribution
        ducter = new ShadedDuct("ducter"){{
            requirements(distribution, with(SeResources.iridium, 2));
        }};

        //defense

        //crafting
        bulkRefinery = new MoreGenericCrafter("bulk-refinery"){{
            requirements(crafting, sandboxOnly, with());
            size = 4;

            /*consumeLiquid(methane, 2);
            recipes.add(
                new CraftRecipe(){{
                    inputPayload = vanadiniteRock;
                    outputItems = with(vanadium, 6, sand, 6, metaglass, 2);
                    outputLiquids = LiquidStack.with(chlorine, 0.3f);

                    craftTime = 120f;
                    craftEffect = Fx.breakProp;
                }}
            );*/
        }};

        //effect
        coreSprout = new CoreBlock("core-sprout"){{
            requirements(effect, with(iridium, 2500, vanadium, 2000, tarnide, 1500));
            alwaysUnlocked = true;

            scaledHealth = 220;
            armor = 1f;
            size = 4;
            itemCapacity = 3000;

            isFirstTier = true;
            unitType = SeUnits.scion;
            thrusterLength = 34/4f;

            unitCapModifier = 5;
        }};
        coreBurgeon = new CoreBlock("core-burgeon"){{
            requirements(effect, with(iridium, 4000, tarnide, 2000, chirokyn, 1500, paragonite, 1500));
            alwaysUnlocked = false;

            scaledHealth = 300;
            armor = 4f;
            size = 5;
            itemCapacity = 6000;

            isFirstTier = false;
            unitType = SeUnits.scion;
            thrusterLength = 34/4f;

            unitCapModifier = 10;
        }};

        //payloads
        vanadiniteRock = new Block("vanadinite-rock"){{
            requirements(logic, sandboxOnly, with());
            update = true;
        }};

        //misc
        drawTest = new DrawTest("draw-test"){{
            requirements(effect, sandboxOnly, with());
        }};
    }
}
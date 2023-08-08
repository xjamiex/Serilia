package serilia.content;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.UnitTypes;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.ExplosionBulletType;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.gen.Sounds;
import mindustry.graphics.Layer;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.type.Weapon;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.Attributes;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.production.AttributeCrafter;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.DrawBlurSpin;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;
import mindustry.world.meta.Attribute;
import mindustry.world.meta.BlockGroup;
import mindustry.world.meta.BuildVisibility;
import serilia.world.draw.drawblock.DrawHalfSpinner;

import static mindustry.content.Items.*;
import static mindustry.type.Category.production;
import static mindustry.type.Category.turret;
import static mindustry.type.ItemStack.with;
import static serilia.content.SeResources.*;

public class AndBlocks {
    public static Block
            //drills
            hydraulicCrusher,

            //turret
            icicle,

            //env
            thinIce, oreMagnesium,

            //effect
            coreSaint
            ;


    public static void load() {
        //env
        thinIce = new Floor("thin-ice") {{
            dragMultiplier = 0.35f;
            speedMultiplier = 0.9f;
            attributes.set(AndAttributes.thinIce, 1f);
            albedo = 0.65f;
            variants = 4;
        }};

        oreMagnesium = new OreBlock("ore-magnesium") {{
            variants = 3;
            itemDrop = magnesium;
        }};


        //drills
        hydraulicCrusher = new AttributeCrafter("hydraulic-crusher") {{
            requirements(production, with(SeResources.magnesium, 30));
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
            ignoreLiquidFullness = true;

            drawer = new DrawMulti(new DrawDefault(), new DrawHalfSpinner("-rotator", 0.6f * 9f), new DrawRegion("-top"));
            hasLiquids = true;
            outputLiquid = new LiquidStack(Liquids.water, 1f / 4f / 2f);
            outputItem = new ItemStack(iceChunk, 2);
            liquidCapacity = 40f;
            researchCost = with(SeResources.magnesium, 50);
        }};

        //turret
        icicle = new ItemTurret("icicle"){{
            health = 980;
            size = 2;
            buildCostMultiplier = 10/3.45f;
            requirements(turret, with(magnesium, 120, titanium, 80));

            liquidCapacity = 10;
            maxAmmo = 15;

            range = 240;
            shootY = 0.7f;
            shootSound = Sounds.shootBig;
            inaccuracy = 2f;
            rotateSpeed = 2f;
            reload = 120;
            minWarmup = 0.90f;
            targetAir = true;
            targetGround = true;
            coolant = consumeCoolant(0.14f);

            ammo(
                    //replace with ice-chunks
                    iceChunk, new BasicBulletType(6f, 42){{
                        shootEffect = new ParticleEffect(){{
                            particles = 6;
                            colorFrom = AndPal.icicleBlueLight;
                            colorTo = AndPal.icicleBlue;
                            sizeFrom = 4;
                            sizeTo = 0;
                            length = 5;
                            offsetX = 10f;
                        }};
                        hitEffect = despawnEffect = new WaveEffect(){{
                            colorFrom = AndPal.icicleBlueLight;
                            colorTo = AndPal.icicleBlue;
                            sizeFrom = 6;
                            sizeTo = 10;
                            strokeFrom = 4;
                            strokeTo = 0;
                        }};

                        width = 12;
                        height = 12;
                        backColor = AndPal.icicleBlue;
                        frontColor = AndPal.icicleBlueLight;
                        trailLength = 5;
                        trailWidth = 2;
                        trailColor = AndPal.icicleBlueLight;

                        hitSound = despawnSound = Sounds.boom;

                        splashDamage = 10f;
                        splashDamageRadius = 10f;
                    }}
            );

            outlineColor = Color.valueOf("313a3b");

        }};

        //effect
        coreSaint = new CoreBlock("core-saint"){{
            requirements(Category.effect, BuildVisibility.shown, with(magnesium, 1100, titanium, 850));
            alwaysUnlocked = true;

            isFirstTier = true;
            unitType = UnitTypes.alpha;
            health = 4650;
            itemCapacity = 4500;
            size = 3;

            unitCapModifier = 8;
        }};
    }
}

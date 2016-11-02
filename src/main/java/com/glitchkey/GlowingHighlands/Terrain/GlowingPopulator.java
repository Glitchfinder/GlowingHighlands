/*
 * Copyright (c) 2012-2016 Sean Porter <glitchkey@gmail.com>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.glitchkey.glowinghighlands.terrain;

//* IMPORTS: JDK/JRE
	import java.util.Random;
//* IMPORTS: BUKKIT
	import org.bukkit.Chunk;
	import org.bukkit.plugin.Plugin;
	import org.bukkit.World;
//* IMPORTS: PANDORA
	import org.pandora.PandoraBiomePopulator;
	import org.pandora.trees.Redwood;
	import org.pandora.trees.TallRedwood;
//* IMPORTS: GLOWING HIGHLANDS
	import com.glitchkey.glowinghighlands.GlowingHighlandsPlugin;
	import com.glitchkey.glowinghighlands.structures.GlowingDungeon;
	import com.glitchkey.glowinghighlands.structures.GlowingOre;
//* IMPORTS: OTHER
	//* NOT NEEDED

public class GlowingPopulator extends PandoraBiomePopulator
{
	Redwood redwood;
	TallRedwood tallRedwood;
	GlowingDungeon dungeon;
	GlowingOre coal, lapis, diamond, redstone, emerald;
	PopulationHelper pop;

	public GlowingPopulator(GlowingHighlandsPlugin plugin)
	{
		Plugin p = (Plugin) plugin;
		minTemperature = -1.0F;
		maxTemperature = 101.0F;
		minHumidity = -1.0F;
		maxHumidity = 101.0F;
		pop = new PopulationHelper();
		redwood     = new Redwood(p, false);
		tallRedwood = new TallRedwood(p, false);
		dungeon  = new GlowingDungeon(p, false);
		coal     = new GlowingOre(p, false, 16, 3D, 5.5D, 1D, 2.3D);
		lapis    = new GlowingOre(p, false, 21, 2D, 3D, 1D, 1.5D);
		diamond  = new GlowingOre(p, false, 56, 1.5D, 3D, 0.5D, 1.2D);
		redstone = new GlowingOre(p, false, 73, 3D, 5D, 1D, 2D);
		emerald  = new GlowingOre(p, false, 129, 1.5D, 3.5D, 1D, 2D);

	}

	public void populate(World w, Random r, Chunk source, int x, int z) {
		generateOre(w, r, x, z);
		int height = w.getMaxHeight();
		int chunkX = source.getX();
		int chunkZ = source.getZ();
		int lastId = w.getBlockTypeIdAt(x, 0, z);

		if ((chunkX << 4) == x && (chunkZ << 4) == z) {
			Random rand = getRandom(w, x, z);

			if(rand.nextInt(100) <= 5 && rand.nextInt(100) <= 20) {
				if (dungeon.place(w, r, x, 0, z))
					return;
			}
		}

		for (int y = 1; y < height; y++) {
			if (lastId == 0) {
				lastId = w.getBlockTypeIdAt(x, y, z);
				continue;
			}

			int currId = w.getBlockTypeIdAt(x, y, z);

			if (currId != 0) {
				lastId = currId;
				continue;
			}

			if (lastId != 2 && lastId != 3) {
				lastId = currId;
				continue;
			}

			boolean tree = false;

			if (r.nextInt(30) == 0) {
				if (r.nextInt(3) == 0)
					tree = tallRedwood.place(w, r, x, y, z);
				else
					tree = redwood.place(w, r, x, y, z);
			}

			if (tree)
				continue;

			place(w, r, x, y, z);
		}
	}

	private void place(World w, Random r, int x, int y, int z) {
		if (r.nextInt(60) == 0) {
			pop.spawnGrass(w, r, 48, 8, x, y, z);
		}

		if (r.nextInt(60) == 0) {
			pop.spawnFern(w, r, 48, 8, x, y, z);
		}

		if (r.nextInt(28672) == 0) {
			pop.spawnPumpkin(w, r, 48, 6, x, y, z);
		}

		if (r.nextInt(32768) == 0) {
			pop.spawnMelon(w, r, 40, 6, x, y, z);
		}

		if (r.nextInt(1536) == 0) {
			if (r.nextInt(4) == 0)
				pop.spawnDandelion(w, r, 24, 8, x, y, z);
			else
				pop.spawnPoppy(w, r, 24, 8, x, y, z);
		}

		if (r.nextInt(1536) == 0) {
			if (r.nextInt(2) == 0)
				pop.spawnBlueOrchid(w, r, 48, 8, x, y, z);
			else
				pop.spawnAllium(w, r, 48, 8, x, y, z);
		}

		if (r.nextInt(1536) == 0) {
			if (r.nextInt(2) == 0)
				pop.spawnAzureBluet(w, r, 48, 8, x, y, z);
			else
				pop.spawnRedTulip(w, r, 48, 8, x, y, z);
		}

		if (r.nextInt(1536) == 0) {
			if (r.nextInt(2) == 0)
				pop.spawnOrangeTulip(w, r, 48, 8, x, y, z);
			else
				pop.spawnWhiteTulip(w, r, 48, 8, x, y, z);
		}

		if (r.nextInt(1536) == 0) {
			if (r.nextInt(2) == 0)
				pop.spawnPinkTulip(w, r, 48, 8, x, y, z);
			else
				pop.spawnOxeyeDaisy(w, r, 48, 8, x, y, z);
		}

		if (r.nextInt(1536) == 0) {
			if (r.nextInt(4) == 0)
				pop.spawnBrownMushroom(w, r, 24, 8, x, y, z);
			else
				pop.spawnRedMushroom(w, r, 24, 8, x, y, z);
		}

		if (r.nextInt(3072) == 0) {
			pop.spawnSunflower(w, r, 24, 8, x, y, z);
		}

		if (r.nextInt(3072) == 0) {
			pop.spawnLilac(w, r, 24, 8, x, y, z);
		}

		if (r.nextInt(3072) == 0) {
			pop.spawnTallGrass(w, r, 24, 8, x, y, z);
		}

		if (r.nextInt(3072) == 0) {
			pop.spawnTallFern(w, r, 24, 8, x, y, z);
		}

		if (r.nextInt(3072) == 0) {
			pop.spawnRoseBush(w, r, 24, 8, x, y, z);
		}

		if (r.nextInt(3072) == 0) {
			pop.spawnPeony(w, r, 24, 8, x, y, z);
		}
	}

	private void generateOre(World w, Random r, int x, int z) {
		if(r.nextInt(100) < 2) {
			coal.place(w, r, x, r.nextInt(55) + 10, z);
		}

		if(r.nextInt(100) < 1) {
			lapis.place(w, r, x, r.nextInt(50) + 10, z);
		}

		if(r.nextInt(10000) < 78) {
			redstone.place(w, r, x, r.nextInt(30) + 10, z);
		}

		if(r.nextInt(10000) < 39) {
			diamond.place(w, r, x, r.nextInt(30) + 10, z);
		}

		if(r.nextInt(100000) < 78) {
			emerald.place(w, r, x, r.nextInt(25) + 10, z);
		}
	}

	private Random getRandom(World w, long x, long z) {
		long seed = (x * 341873128712L + z * 132897987541L);
		seed = seed ^ w.getSeed();
		return new Random(seed);
	}

}
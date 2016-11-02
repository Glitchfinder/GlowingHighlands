/*
 * Copyright (c) 2014-2016 Sean Porter <glitchkey@gmail.com>
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
	import org.bukkit.block.Block;
	import org.bukkit.World;
//* IMPORTS: PANDORA
	//* NOT NEEDED
//* IMPORTS: GLOWING HIGHLANDS
	//* NOT NEEDED
//* IMPORTS: OTHER
	//* NOT NEEDED

public class PopulationHelper
{
	public static void spawnPlants(World world, Random rand, int attempts,
		int range, int x, int y, int z, int id, byte data)
	{
		for (int attempt = 0; attempt < attempts; attempt++) {
			int cx = x + rand.nextInt(range) - rand.nextInt(range);
			int cy = y + rand.nextInt(range) - rand.nextInt(range);
			int cz = z + rand.nextInt(range) - rand.nextInt(range);

			if (cy <= 0 || cy >= world.getMaxHeight())
				continue;

			int chX = cx >> 4;
			int chZ = cz >> 4;

			boolean condition1 = world.isChunkLoaded(chX, chZ);
			boolean condition2 = world.loadChunk(chX, chZ, false);

			if (!condition1 && !condition2)
				continue;

			if (world.getBlockTypeIdAt(cx, cy, cz) != 0)
				continue;

			int cid = world.getBlockTypeIdAt(cx, cy - 1, cz);

			if (cid != 3 && cid != 2)
				continue;

			Block block = world.getBlockAt(cx, cy, cz);

			block.setTypeIdAndData(id, (byte) data, true);
		}
	}

	public static void spawnGrass(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 31, (byte) 1);
	}

	public static void spawnFern(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 31, (byte) 2);
	}

	public static void spawnDandelion(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 37, (byte) 0);
	}

	public static void spawnPoppy(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 0);
	}

	public static void spawnBlueOrchid(World w, Random r, int att,
		int range, int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 1);
	}

	public static void spawnAllium(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 2);
	}

	public static void spawnAzureBluet(World w, Random r, int att,
		int range, int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 3);
	}

	public static void spawnRedTulip(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 4);
	}

	public static void spawnOrangeTulip(World w, Random r, int att,
		int range, int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 5);
	}

	public static void spawnWhiteTulip(World w, Random r, int att,
		int range, int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 6);
	}

	public static void spawnPinkTulip(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 7);
	}

	public static void spawnOxeyeDaisy(World w, Random r, int att,
		int range, int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 38, (byte) 8);
	}

	public static void spawnBrownMushroom(World w, Random r, int att,
		int range, int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 39, (byte) 0);
	}

	public static void spawnRedMushroom(World w, Random r, int att,
		int range, int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 40, (byte) 0);
	}

	public static void spawnPumpkin(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 86, (byte) 4);
	}

	public static void spawnMelon(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnPlants(w, r, att, range, x, y, z, 103, (byte) 0);
	}

	public static void spawnTallPlants(World world, Random rand,
		int attempts, int range, int x, int y, int z, byte data)
	{
		for (int attempt = 0; attempt < attempts; attempt++) {
			int cx = x + rand.nextInt(range) - rand.nextInt(range);
			int cy = y + rand.nextInt(range) - rand.nextInt(range);
			int cz = z + rand.nextInt(range) - rand.nextInt(range);

			if (cy <= 0 || cy >= world.getMaxHeight())
				continue;

			int chX = cx >> 4;
			int chZ = cz >> 4;

			boolean condition1 = world.isChunkLoaded(chX, chZ);
			boolean condition2 = world.loadChunk(chX, chZ, false);

			if (!condition1 && !condition2)
				continue;

			if (world.getBlockTypeIdAt(cx, cy, cz) != 0)
				continue;

			if (world.getBlockTypeIdAt(cx, cy + 1, cz) != 0)
				continue;

			int cid = world.getBlockTypeIdAt(cx, cy - 1, cz);

			if (cid != 3 && cid != 2)
				continue;

			Block block = world.getBlockAt(cx, cy, cz);
			block.setTypeIdAndData(175, (byte) data, true);
			block = world.getBlockAt(cx, cy + 1, cz);
			block.setTypeIdAndData(175, (byte) 8, true);
		}
	}

	public static void spawnSunflower(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnTallPlants(w, r, att, range, x, y, z, (byte) 0);
	}

	public static void spawnLilac(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnTallPlants(w, r, att, range, x, y, z, (byte) 1);
	}

	public static void spawnTallGrass(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnTallPlants(w, r, att, range, x, y, z, (byte) 2);
	}

	public static void spawnTallFern(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnTallPlants(w, r, att, range, x, y, z, (byte) 3);
	}

	public static void spawnRoseBush(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnTallPlants(w, r, att, range, x, y, z, (byte) 4);
	}

	public static void spawnPeony(World w, Random r, int att, int range,
		int x, int y, int z)
	{
		spawnTallPlants(w, r, att, range, x, y, z, (byte) 5);
	}
}
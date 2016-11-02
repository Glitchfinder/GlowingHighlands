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
	import org.bukkit.generator.ChunkGenerator.BiomeGrid;
	import org.bukkit.World;
//* IMPORTS: PANDORA
	import org.pandora.PandoraBiome;
//* IMPORTS: GLOWING HIGHLANDS
	//* NOT NEEDED
//* IMPORTS: OTHER
	//* NOT NEEDED

public class GlowingGenerator extends PandoraBiome
{
	public GlowingGenerator()
	{
		this.minTemperature = -1.0F;
		this.maxTemperature = 101.0F;
		this.minHumidity = -1.0F;
		this.maxHumidity = 101.0F;
	}

	public short[] generateExtSections(World w, Random r, int xPos,
		int zPos, BiomeGrid biomes)
	{
		short[] result = new short[w.getMaxHeight()];

		int x = Math.abs(xPos % 16);
		int z = Math.abs(zPos % 16);

		double mod1 = getNoise(w, xPos, zPos, 1D, 1D, 2, 200D, 0.0075D);
		mod1 = Math.abs(mod1) / 200D;
		double mod2 = getNoise(w, zPos, xPos, 1D, 1D, 2, 100D, 0.0075D);
		mod2 += 120D;
		double temp1 = getTemperature(w, xPos, zPos);
		double temp2 = (temp1 / 200D) + 0.50D;
		temp1 = (temp1 / 400D) + 0.875D;
		double humi1 = getHumidity(w, xPos, zPos);
		double humi2 = (humi1 / 200D) + 0.5D;
		humi1 = (humi1 / 400D) + 0.875D;

		for (int y = 235; y >= 0; y--) {
			short id;

			id = this.getId(w, xPos, y, zPos, mod1, mod2, temp1,
				temp2, humi1, humi2, result);

			if (id != 0 && checkGlowstone(w, xPos, y, zPos))
				id = 89;

			result[y] = id;
		}

		return result;
	}

	public short getId(World w, int x, int y, int z, double mod1,
		double mod2, double temp1, double temp2, double humi1,
		double humi2, short[] result)
	{
		short id = 0;
		double n1 = getNoise(w, x, y, z, 4, 0.15D * temp1, 120D);
		n1 *= humi2;
		double n2 = getNoise(w, z, y, x, 4, 0.15D * humi1, 120D);
		n2 /= temp2;
		double distortion = (getNoise(w, x, y, z, 2, 0.06D, 10D) / 9D);
		distortion += 0.5D;
		n1 = (((n1 * 2D) + (n1 * distortion)) / 3D);
		n2 = (((n2 * 2D) + (n2 * distortion)) / 3D);
		double n1Check = 0.5D;
		double n2Check = Math.abs(0.2D * (2D - distortion));
		double perc1 = Math.abs(((double) y) - 120D);
		perc1 = Math.pow(perc1 / 110D, 4D);
		double perc2 = ((perc1 * 2D) + (perc1 * distortion)) / 3D;

		boolean con1 = (lerp(n1, 0D, perc1) >= n1Check);
		boolean con2;
		con2 = (Math.abs(lerp(n2, n2Check + 0.5D, perc2)) <= n2Check);
		if (con1 || con2) {
			short temp = result[y + 1];

			if(temp == 0)
				id = 2;
			else if(temp == 2 || temp == 9)
				id = 3;
			else
				id = 1;
		}

		mod1 = (mod1 + (mod1 * distortion * 1.5D)) / 2D;
		mod2 = ((mod2 * 2D) + (mod2 * distortion)) / 3D;

		if (mod1 >= 0.05D)
			return id;

		double modPerc = 1D - Math.pow(mod1 / 0.05D, 2D);
		double baseHeight = 15D * modPerc;

		if (Math.abs(y - mod2) >= baseHeight)
			return id;

		short temp = result[y + 1];

		if(temp == 0)
			id = 2;
		else if(temp == 2 || temp == 9)
			id = 3;
		else
			id = 1;

		return id;
	}

	private boolean checkGlowstone(World w, int x, int y, int z) {
		double noise = Math.abs(getNoise(w, x, y, z, 2, 0.06D, 50D));

		if (noise <= 0.2D)
			return true;

		return false;
	}

	double lerp(double start, double end, double percent) {
		return (start + (percent * (end - start)));
	}

	private Random getRandom(long x, long z) {
		long seed = (x * 341873128712L + z * 132897987541L) ^ 1575463L;
		return new Random(seed);
	}

	private double getNoise(World w, double x, double y, double z,
		int octaves, double frequency, double amplitude)
	{
		return getNoiseGenerator(w).noise(x, y, z, octaves, frequency,
			amplitude, true);
	}

}
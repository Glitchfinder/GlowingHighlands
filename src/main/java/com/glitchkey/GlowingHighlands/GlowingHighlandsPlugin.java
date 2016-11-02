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

package com.glitchkey.glowinghighlands;

//* IMPORTS: JDK/JRE
	//* NOT NEEDED
//* IMPORTS: BUKKIT
	import org.bukkit.generator.BlockPopulator;
	import org.bukkit.generator.ChunkGenerator;
	import org.bukkit.plugin.java.JavaPlugin;
	import org.bukkit.World;
	import org.bukkit.World.Environment;
	import org.bukkit.WorldCreator;
	import org.bukkit.WorldType;
//* IMPORTS: PANDORA
	import org.pandora.PandoraBiome;
	import org.pandora.PandoraBiomePopulator;
	import org.pandora.PandoraGenerator;
	import org.pandora.PandoraPopulator;
//* IMPORTS: GLOWING HIGHLANDS
	import com.glitchkey.glowinghighlands.terrain.GlowingGenerator;
	import com.glitchkey.glowinghighlands.terrain.GlowingPopulator;
//* IMPORTS: OTHER
	//* NOT NEEDED

public class GlowingHighlandsPlugin extends JavaPlugin
{
	public static GlowingHighlandsPlugin plugin;
	public World world = null;

	public void onDisable() {}

	public void onEnable()
	{
		plugin = this;
		this.createWorld();
	}

	public void createWorld()
	{

		WorldCreator worldCreator = new WorldCreator("GlowingHighlands");
		worldCreator = worldCreator.environment(Environment.NORMAL);
		worldCreator = worldCreator.type(WorldType.NORMAL);
		PandoraGenerator generator = new PandoraGenerator();
		PandoraBiome highlands = (PandoraBiome) (new GlowingGenerator());
		generator.setDefaultBiome(highlands);
		PandoraPopulator populator = new PandoraPopulator();
		PandoraBiomePopulator highlandPop = (PandoraBiomePopulator) (new GlowingPopulator(this));
		populator.setDefaultBiome(highlandPop);
		generator.addPopulator((BlockPopulator) populator);
		worldCreator = worldCreator.generator((ChunkGenerator) generator);
		this.world = worldCreator.createWorld();
	}
}

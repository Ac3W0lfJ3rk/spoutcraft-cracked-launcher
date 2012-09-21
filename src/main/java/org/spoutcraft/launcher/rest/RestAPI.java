/*
 * This file is part of Spoutcraft Launcher.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * Spoutcraft Launcher is licensed under the SpoutDev License Version 1.
 *
 * Spoutcraft Launcher is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Spoutcraft Launcher is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the SpoutDev License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://www.spout.org/SpoutDevLicenseV1.txt> for the full license,
 * including the MIT license.
 */
package org.spoutcraft.launcher.rest;

import org.spoutcraft.launcher.Channel;

public class RestAPI {
	public static final String REST_URL = "http://get.spout.org/nuget/";
	public static final String PROJECT = "spoutcraft";
	public static final String LIBRARIES = "libraries";
	public static final String VERSIONS_URL = REST_URL + "versions/" + PROJECT;
	public static final String INFO_URL = REST_URL + "info/";
	public static final String LIBRARY_GET_URL = REST_URL + "library/";

	public static String getSpoutcraftURL(Channel channel) {
		return INFO_URL + channel.toString() + "/" + PROJECT;
	}

	public static String getLibraryURL(String build) {
		return REST_URL + LIBRARIES + "/build/" + build;
	}
}

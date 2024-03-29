/*
 * This file is part of Spoutcraft.
 *
 * Copyright (c) 2011-2012, SpoutDev <http://www.spout.org/>
 * Spoutcraft is licensed under the SpoutDev License Version 1.
 *
 * Spoutcraft is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the SpoutDev License Version 1.
 *
 * Spoutcraft is distributed in the hope that it will be useful,
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
package org.spoutcraft.launcher.skin.gui;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

import org.jdesktop.swingworker.SwingWorker;

import org.spoutcraft.launcher.api.Event;
import org.spoutcraft.launcher.api.Launcher;
import org.spoutcraft.launcher.skin.gui.LoginFrame.UserPasswordInformation;
import org.spoutcraft.launcher.util.Utils;

public class LoginWorker extends SwingWorker<Object, Object> {
	private final LoginFrame loginFrame;
	private String user;
	private String pass;
	private String[] values = null;

	public LoginWorker(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	protected Object doInBackground() throws Exception {
		loginFrame.getProgressBar().setVisible(true);
		loginFrame.getProgressBar().setString("Connecting to minecraft.net...");
		try {
			values = Utils.doLogin(user, pass, loginFrame.getProgressBar());
			Launcher.getGameUpdater().setMinecraftUser(values[2].trim());
			Launcher.getGameUpdater().setMinecraftSession(values[3].trim());
			Launcher.getGameUpdater().setDownloadTicket(values[1].trim());
			Launcher.getGameUpdater().setMinecraftPass(pass);

			UserPasswordInformation info = null;

			for (String username : loginFrame.usernames.keySet()) {
				if (username.equalsIgnoreCase(user)) {
					info = loginFrame.usernames.get(username);
					break;
				}
			}

			if (info != null) {
				if (user.contains("@")) {
					info.username = values[2].trim();
				} else {
					info.username = user;
				}
				info.password = pass;
			}

			loginFrame.onRawEvent(Event.SUCESSFUL_LOGIN);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

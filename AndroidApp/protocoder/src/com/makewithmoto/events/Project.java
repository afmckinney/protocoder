/*
 * Protocoder 
 * A prototyping platform for Android devices 
 * 
 * 
 * Copyright (C) 2013 Motorola Mobility LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions: 
 * 
 * The above copyright notice and this permission notice shall be included in all 
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN 
 * THE SOFTWARE.
 * 
 */

package com.makewithmoto.events;

import java.io.File;

import com.makewithmoto.base.BaseMainApp;

public class Project {

	String name;
	String url;
	int type;
	
	
	public Project(String projectName, String projectURL, int type) {
		this.name = projectName;
		this.url = projectURL;
		this.type = type;
	}
	
	public Project(String projectName, int type) {
		this.name = projectName;
		
		if (type == ProjectManager.PROJECT_USER_MADE) { 
			this.url = BaseMainApp.projectsDir + File.separator + projectName;
		} else { 
			this.url = BaseMainApp.examplesDir + File.separator + projectName; 
		}
		this.type = type;
	}
	
	
	public String getName() {
		return this.name;
	}

	public String getUrl() {
		return this.url;
	}

	public int getType() {
		return this.type;
	}
}

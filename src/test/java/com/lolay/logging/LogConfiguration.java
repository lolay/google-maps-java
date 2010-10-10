/*
 * Licensed to Lolay, Inc. under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  Lolay, Inc. licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://github.com/lolay/google-maps-java/raw/master/LICENSE
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package com.lolay.logging;

import java.util.logging.LogManager;

public class LogConfiguration {
	public LogConfiguration() {
		String fileName = System.getProperty("java.util.logging.config.file");
		if (fileName == null) {
			fileName = "logging.properties";
		}

		try {
			LogManager.getLogManager().readConfiguration(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName));
		} catch (Exception e) {
			System.out.println(String.format("Could not initialize java.util.logging from configuration file %s", fileName));
			e.printStackTrace();
		}
		
		System.out.println(String.format("Initialized java.util.logging from configuration file %s", fileName));
	}
}
/*
 * Copyright 2013-2014 Classmethod, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.classmethod.aws.gradle.route53;

import jp.classmethod.aws.gradle.AwsPlugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;


public class AmazonRoute53Plugin implements Plugin<Project> {
	
	public void apply(Project project) {
		project.getPluginManager().apply(AwsPlugin.class);
		project.getExtensions().create(AmazonRoute53PluginExtension.NAME, AmazonRoute53PluginExtension.class, project);
		applyTasks(project);
	}
	
	private void applyTasks(final Project project) {
		AmazonRoute53PluginExtension r53Ext = project.getExtensions().getByType(AmazonRoute53PluginExtension.class);
		
		project.getTasks().create("awsR53CreateHostedZone", CreateHostedZoneTask.class, task -> {
			task.setDescription("Create hostedZone.");
			task.doFirst(t -> {
				task.setHostedZoneName(r53Ext.getHostedZone());
				task.setCallerReference(r53Ext.getCallerReference());
			});
		});
	}
}

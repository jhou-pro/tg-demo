#!/bin/sh
java -server -Xms512m -Xmx2048m -XX:+UseG1GC -XX:+UseCompressedOOps -XX:+UseTLAB -Djava.system.class.loader=ua.com.fielden.platform.classloader.TgSystemClassLoader -cp .:lib/* jhou.webapp.Start application.properties

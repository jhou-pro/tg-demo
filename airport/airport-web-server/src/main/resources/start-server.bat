java -server -Xms512m -Xmx2048m -XX:+UseG1GC -XX:+UseCompressedOOps -XX:+UseTLAB -Djava.system.class.loader=ua.com.fielden.platform.classloader.TgSystemClassLoader -XX:+UseConcMarkSweepGC -Xms1024m -Xmx2096m -cp .;lib/* jhou.webapp.Start application.properties

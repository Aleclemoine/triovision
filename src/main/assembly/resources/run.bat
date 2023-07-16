@echo off

set BATCH_HOME=D:\Alec\runtime\triovision
set JAVA_HOME=D:\Alec\tools\java\jdk-19.0.2
set JAVAFX_HOME=D:\Alec\tools\java\javafx-sdk-20

set APP_CLASSPATH=%BATCH_HOME%/conf;%BATCH_HOME%/lib/*

%JAVA_HOME%/bin/java --module-path %JAVAFX_HOME%/lib --add-modules=javafx.controls -Duser.dir=%BATCH_HOME% -classpath %APP_CLASSPATH% com.isep.hpah.Triovision %*

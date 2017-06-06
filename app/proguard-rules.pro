# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/5dr/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:


#This is probably a bug in ProGuard's optimization of enum types. You may be able to work around it by disabling this optimization:
-optimizations !class/unboxing/enum

#To remove debug logs:
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

#GSON
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
# For using GSON @Expose annotation
#-keepattributes *Annotation*


-dontwarn com.google.**
-dontwarn net.minidev.json.**
-dontwarn org.cheffo.jeplite.*
-dontwarn com.madrobot.beans.*
-dontwarn java.beans.*
-dontwarn sun.misc.BASE64Encoder


-keep public class com.github.mikephil.charting.animation.* {
    public protected *;
}


# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#-keepparameternames
#-renamesourcefileattribute SourceFile
#-keepattributes Exceptions,InnerClasses,Signature,Deprecated, SourceFile,LineNumberTable,EnclosingMethod

# Preserve all annotations.
#-keepattributes *Annotation*

# Preserve all public classes, and their public and protected fields and
# methods.
#-keep public class * {
#    public protected *;
#}

# Preserve all .class method names.
#-keepclassmembernames class * {
#    java.lang.Class class$(java.lang.String);
#    java.lang.Class class$(java.lang.String, boolean);
#}

# Preserve all native method names and the names of their classes.

#-keepclasseswithmembernames class * {
#    native <methods>;
#}

# Preserve the special static methods that are required in all enumeration
# classes.

#-keepclassmembers class * extends java.lang.Enum {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your library doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.

#-keepclassmembers class * implements java.io.Serializable {
#    static final long serialVersionUID;
#    static final java.io.ObjectStreamField[] serialPersistentFields;
#    private void writeObject(java.io.ObjectOutputStream);
#    private void readObject(java.io.ObjectInputStream);
#    java.lang.Object writeReplace();
#    java.lang.Object readResolve();
#}

#-optimizationpasses 5

#When not preverifing in a case-insensitive filing system, such as Windows. Because this tool unpacks your processed jars, you should then use:
#-dontusemixedcaseclassnames

#Specifies not to ignore non-public library classes. As of version 4.5, this is the default setting
#-dontskipnonpubliclibraryclasses

#Preverification is irrelevant for the dex compiler and the Dalvik VM, so we can switch it off with the -dontpreverify option.
#-dontpreverify

#Specifies to write out some more information during processing. If the program terminates with an exception, this option will print out the entire stack trace, instead of just the exception message.
#-verbose

#The -optimizations option disables some arithmetic simplifications that Dalvik 1.0 and 1.5 can't handle. Note that the Dalvik VM also can't handle aggressive overloading (of static fields).
#To understand or change this check http://proguard.sourceforge.net/index.html#/manual/optimizations.html
#-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#To repackage classes on a single package
#-repackageclasses ''

#Uncomment if using annotations to keep them.
#-keepattributes *Annotation*

#Keep classes that are referenced on the AndroidManifest
#-keep public class * extends android.app.Activity
#-keep public class * extends android.app.Application
#-keep public class * extends android.app.Service
#-keep public class * extends android.content.BroadcastReceiver
#-keep public class * extends android.content.ContentProvider
#-keep public class com.android.vending.licensing.ILicensingService


#To avoid changing names of methods invoked on layout's onClick.
# Uncomment and add specific method names if using onClick on layouts
#-keepclassmembers class * {
# public void onClickButton(android.view.View);
#}

#Maintain java native methods
#-keepclasseswithmembernames class * {
#    native <methods>;
#}

#To maintain custom components names that are used on layouts XML.
#Uncomment if having any problem with the approach below
#-keep public class custom.components.package.and.name.**

#To maintain custom components names that are used on layouts XML:
#-keep public class * extends android.view.View {
#    public <init>(android.content.Context);
#    public <init>(android.content.Context, android.util.AttributeSet);
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#    public void set*(...);
#}

#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet);
#}

#-keepclasseswithmembers class * {
#    public <init>(android.content.Context, android.util.AttributeSet, int);
#}

#Maintain enums
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}

#To keep parcelable classes (to serialize - deserialize objects to sent through Intents)
#-keep class * implements android.os.Parcelable {
#  public static final android.os.Parcelable$Creator *;
#}

#Keep the R
#-keepclassmembers class **.R$* {
#    public static <fields>;
#}



#-keepattributes Signature

# The official support library.
#-keep class android.support.v4.app.** { *; }
#-keep interface android.support.v4.app.** { *; }

#  Library JARs.
#-keep class de.greenrobot.dao.** { *; }
#-keep interface de.greenrobot.dao.** { *; }

# Library projects.
#-keep class com.actionbarsherlock.** { *; }
#-keep interface com.actionbarsherlock.** { *; }


###### ADDITIONAL OPTIONS NOT USED NORMALLY

#To keep callback calls. Uncomment if using any
#http://proguard.sourceforge.net/index.html#/manual/examples.html#callback
#-keep class mypackage.MyCallbackClass {
#   void myCallbackMethod(java.lang.String);
#}


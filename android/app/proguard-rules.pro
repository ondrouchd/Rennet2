# ProGuard rules for Rennet2
# Flutter-specific keep rules
-keep class io.flutter.** { *; }
-keep class io.flutter.embedding.** { *; }

# Keep the application class
-keep class com.example.rennet2.** { *; }

# Kotlin
-keep class kotlin.** { *; }
-dontwarn kotlin.**

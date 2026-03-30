# Rennet2 – Sýrařův rádce

A Flutter-based cheese-making assistant app for Android.

## Requirements

- [Flutter SDK](https://docs.flutter.dev/get-started/install) ≥ 3.0.0
- Android SDK (API 34)
- Java 17

## Getting started

```bash
# Install dependencies
flutter pub get

# Run in debug mode on a connected device / emulator
flutter run

# Run unit & widget tests
flutter test
```

## Building a release APK for Google Play

### 1. Generate a signing keystore

```bash
keytool -genkey -v \
  -keystore android/upload-keystore.jks \
  -keyalg RSA -keysize 2048 -validity 10000 \
  -alias upload
```

> Keep `android/upload-keystore.jks` **secret** and **outside** of version control.

### 2. Create `android/key.properties`

Copy the template and fill in your values:

```bash
cp android/key.properties.example android/key.properties
```

Edit `android/key.properties`:

```properties
storePassword=<your-keystore-password>
keyPassword=<your-key-password>
keyAlias=upload
storeFile=../android/upload-keystore.jks
```

> `android/key.properties` is listed in `.gitignore` – it will **never** be committed.

### 3. Build the signed release APK

```bash
flutter build apk --release
```

The signed APK will be at: `build/app/outputs/flutter-apk/app-release.apk`

### 4. Build an App Bundle (recommended for Google Play)

```bash
flutter build appbundle --release
```

The bundle will be at: `build/app/outputs/bundle/release/app-release.aab`

Upload the `.aab` file to [Google Play Console](https://play.google.com/console).

## Project structure

```
lib/
  main.dart          # App entry point
android/
  app/
    build.gradle     # Android build configuration with signing
    src/main/
      AndroidManifest.xml
      kotlin/com/example/rennet2/MainActivity.kt
  key.properties.example   # Template – copy to key.properties and fill in
  key.properties           # Your signing config (gitignored – never commit!)
test/
  widget_test.dart   # Widget tests
```

## Changing the app ID

To publish under your own domain, change `applicationId` in
`android/app/build.gradle` from `com.example.rennet2` to your own reverse-domain
package name (e.g. `cz.yourdomain.rennet2`).


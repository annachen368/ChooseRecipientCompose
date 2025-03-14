# Android Project Template

## Overview
Base Android template with:
- Retrofit
- Hilt (DI)
- Room (Database)
- Coroutines
- Clean Architecture

## How to Use
After cloning this template or copying the base project, follow this checklist to properly set up your new Android project:

| Item                                             | Where to Update                                                                          |
|--------------------------------------------------|------------------------------------------------------------------------------------------|
| **Project Name (Window title, Recent Projects)** | Rename the root folder & update `settings.gradle` `rootProject.name`                     |
| **Package Name**                                 | Use **Refactor > Rename Package** in Right-click on the package (e.g., com.example.base). |
| **Application ID (for Play Store)**              | Update `applicationId` in `app/build.gradle`                                             |
| **App Display Name**                             | Update `app_name` in `res/values/strings.xml`                                            |
| **Theme**                                        | Update `android:theme="@style/Theme.Base"` in `app/build.gradle`                         |
| **Activity**                                     | Update `android:name="..."` in `app/src/main/AndroidManifest.xml`                        |
| **Git**                                          | Git remote remove origin in Git > Manage Remotes                                         |

## Dependencies
- Hilt
- Retrofit
- Room
- Coroutines

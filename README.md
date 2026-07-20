====================================================
DATA-DRIVEN DECISION SUPPORT SYSTEM FOR
MARKETING CAMPAIGN OPTIMIZATION
====================================================

Author: Low Ren Xing
Student ID: 243UT246WL
Multimedia University (MMU)

====================================================
1. Software Requirements
====================================================

Operating System
- Windows 10/11 (64-bit)

Development Environment
- Android Studio Koala Feature Drop 2024.1.2 or newer

Java Development Kit
- JDK 17

Android SDK
- Android SDK Platform 35
- Minimum SDK: API Level 26 (Android 8.0)
- Target SDK: API Level 35

Database
- SQLite (Built into Android)
- Firebase Authentication

====================================================
2. Required Libraries
====================================================

The project uses the following libraries:

- Firebase Authentication
- MPAndroidChart
- AndroidX Libraries
- Material Design Components

All required dependencies are managed automatically through Gradle.

====================================================
3. Download Required Software
====================================================

Android Studio
https://developer.android.com/studio

Java JDK 17
https://adoptium.net/

Firebase Console
https://console.firebase.google.com/

====================================================
4. Project Setup
====================================================

Step 1
Download the project source code.

Step 2
Open Android Studio.

Step 3
Select
File → Open

Choose the project folder.

Step 4
Wait for Gradle Sync to complete.

Step 5
Connect an Android device or create an Android Emulator.

Step 6
Click Run to build and launch the application.

====================================================
5. Firebase Configuration
====================================================

The project uses Firebase Authentication.

If the google-services.json file is not included,
create a Firebase project and:

1. Register the Android application.
2. Download google-services.json.
3. Place the file inside:

app/

4. Rebuild the project.

====================================================
6. Dataset
====================================================

This project uses exported advertising reports from:

- Shopee
- Lazada
- TikTok Shop

Sample CSV files are included in:

Shopee - TC01 Valid Shopee CSV (PASS).CSV
Lazada.CSV
Tiktok.CSV

If additional datasets are required, download them from:

Kaggle:
https://www.kaggle.com/datasets/renxinglow/data-driven-decision-support-system-sample-dataset

====================================================
7. How to Use
====================================================

1. Register or Login.
2. Upload a CSV report.
3. The system validates the dataset.
4. KPIs are calculated automatically.
5. SAW ranking is generated.
6. Dashboard charts and ranking results are displayed.
7. Analysis history is saved automatically.

====================================================
8. Notes
====================================================

- Internet connection is required only for Firebase Authentication.
- SQLite stores all local analysis history.
- CSV files must follow the supported Shopee, Lazada, or TikTok template.

====================================================
End of Readme
====================================================

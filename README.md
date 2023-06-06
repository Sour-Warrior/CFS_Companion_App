# CFS_Companion_App
This is the User Manual for the Mobile Companion app for the Figherfighter Simulation Application, developed by Group 2022-SP2-12 for the CFS.

It was written in Java using the Android Studio IDE

This app has been tested on the Android Studio virtual device (Google Pixel 2) and on a Samsung Galaxy S21+ device, but should work on any Android Device
running Android version 8.0 or above
## Connecting to a headset
Before beginning, ensure that the Android device is connected to the same WiFi network as the Oculus Quest 2 headset.

To start, launch the Android application. This will begin a UDP broadcast for IP address discovery and also begin a TCP server. 
After the app has been launched, launch the firefighting vr app on the Oculus Quest 2 headset. The VR app will pick up the UDP broadcast and obtain the 
IP address of the Android device. The Quest 2 will then use this IP address and connect to the TCP server.

Make sure that the VR app is still on the first menu screen otherwise any further communication will not work.

## Changing the difficulty settings

On the main menu, select the "Configure Difficulty" button. This will take you to the ConfigureSettings screen
There are three preset difficulty settings, "HARD", "MEDIUM" and "EASY". Selecting one will change the difficulty settings of the VR app
Press the Back button on the top left corner of the screen to navigate back to the main menu

## Beginning the main scenario on VR

On the main menu, press the "START TRAINING" button. If the connection has not been interrupted, the current scene in the VR app will change to the Main Scene
(the game itself).





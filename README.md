## Android - Integrate Alexa Voice Service into your Android App

This Project shows you how to integrate the Alexa Voice Service which you can find [here](https://developer.amazon.com).  
The aim of the project is that you can talk to your personalized Alexa in an Android App instead of using a physical device from Amazon like the Echo or the Echo Dot.
So let's get started.

## Prerequsites
1. [Amazon Developer Account](https://developer.amazon.com)
2. [Android Studio](https://developer.android.com/studio/index.html)

## Getting Started
The sample Application includes a login screen where you have to login into your Amazon Account. After that you can already use your personalized Alexa.  
If you want to Test this Application there are some steps that you need to do before you can use this project.  

**Step 1:**
### _Create a security profile_

1. Log in to the [developer console](https://developer.amazon.com) and move to the tab ***Apps & Services***
2. Now move to the Security Profiles Tag
![MacDown ScreenShot](/PATH/SCREENSHOT/SecurityProfile.png)
3. Give your Security Profile a Name (e.g. *"Android AVS Sample App"*) and a short description
4. You must enter a Privacy Notice URL. For this example we just use *"http://www.example.com/privacy.html"*
5. In addition you can upload an image but that is not really necessary for this example

**Step 2:**  
### _Create an AVS product_

1. Log in to the [developer console](https://developer.amazon.com) and move to the tab ***"Alexa"***. ![MacDown ScreenShot](/PATH/SCREENSHOT/DeveloperConsole.png)
2. Press on GetStarted and click on the ["Create Product"](https://developer.amazon.com/avs/home.html#/avs/products/new) Button in the top right corner.  
3. Now you have to fill in the fields
  - As a productname you can take e.g. *"AlexaVoiceServiceAndroid"*
  - As a product id it is useful to use the productname with underscores like *"`alexa_voice_service_android`"*
  - Choose *"Application"* and as Product Category you chosse *"Mobile Apps"*
  - Now give a short description about what your app is doing
  - Next choose *"Touch-initiated"* and select No for the two questions
  - Press on Next

**Step 3:**
### _Connect your Security Profile with your AVS Product_
1. Choose your created Security Profile



https://developer.amazon.com/docs/login-with-amazon/register-android.html

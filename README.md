## Android - Integrate Alexa Voice Service into your Android App

This Project shows you how to integrate the Alexa Voice Service which you can find [here](https://developer.amazon.com).  
The aim of the project is that you can talk to your personalized Alexa in an Android App instead of using a physical device from Amazon like the Echo or the Echo Dot.
So let's get started.

## Prerequisites
1. [Amazon Developer Account](https://developer.amazon.com)
2. [Android Studio](https://developer.android.com/studio/index.html)

## Getting Started
The sample Application includes a login screen where you have to log in into your Amazon Account. After that you can almost use your personalized Alexa.  
If you want to Test this Application there are some steps that you need to do before you can use this project. 

### **_Step 1: Download the project_** 
1. Download this project as a zip and 

### **_Step 2: Create an AVS product_**
1. Go to [developer console](https://developer.amazon.com) and press on ***"Developer Console"*** on the top right corner. Afterwards log in with your Amazon Developer Account.
2. Move to the tab ***"Alexa"*** and press on ***"Get Started"*** under ***"Alexa Voice Service"***.
3. On the top right corner press on the blue button **"CREATE PRODUCT"**.
4. Now you have to fill in the fields
  - As a productname you can take e.g. *"`AlexaVoiceServiceAndroid`"*
  - As a product id it is useful to use the productname with underscores like *"`alexa_voice_service_android`"*.
  - Choose **"Alexa-Enabled Application"** and as Product Category you chosse **"Mobile Apps"**.
  - Now give a short description about what your app is doing.
  - Next choose **"Touch-initiated"** and select **"No"** for the question if you intend to distribute you product commercially and also **"No"** for the question if your product is a children's product.
  - Press on Next.
5. Now you have to create a Security Profile which is connected to your product. 
 - Choose **"CREATE A NEW PROFILE"** 
 - As a security profile name you can choose e.g. *"`Alexa Voice Service Android Security Profile`"*
 - Give a short description about what your security profile is doing.
 - Press on Next.
6. Another tab will open where you have to modify your security profile.
 - Press on the Android / Kindle tab.
 - As api key name you can choose e.g. *"`api_key`"*.
 - As package name choose *"`com.jamitlabs.bestpracticeavs`"*.
 - For the MD5 and SHA256 signature take the values that you copied out in Step 1 and fill them into the fields.
 - Finally press on **"GENERATE KEY"** and save the value of the api key.
 - Now agree to the AVS agreement and to the AVS Program Requirements and press on **"FINISH"**.
7. Your product should have been created and you should now see your product in the overview. Now press on the **"APPS & SERVICES"** tab on the top and move to **"Login with Amazon"** ![MacDown ScreenShot](MacDownPictures/LoginWithAmazon.png) 
 - Now you should see the following screen. ![MacDown ScreenShot](MacDownPictures/SelectASecurityProfile.png)
 - Press on **"Select a Security Profile"** and choose your Security Profile.
 - Press on Confirm. Now another window should open.
 - You must enter a Privacy Notice URL. For this example we just use *"`http://www.example.com/privacy.html`"*.
 - In addition you can upload an image but that is not really necessary for this example. 
 - Press on Save.


//OLD

### **Step 4:**  
### _Download the Project_
1. First of all we need to download the [login-with-amazon-sdk.jar](https://amazonadsi-a.akamaihd.net/public/Amazon-Mobile-App-SDK-by-Platform/Amazon-Android-SDKs.zip)
2. When the download is finish unzip the file and open the folder. In it you should see a subfolder called *"LoginWithAmazon"*. Go into the folder and there you should see a file called *"login-with-amazon-sdk.jar"*. 
3. Now download the project.zip and extract it. Copy the login-with-amazon-sdk.jar into the downloaded and extracted zip. 
4. Open Android Studio and choose "*Open an existing Android Studio Project"*. Choose the downloaded project and open it. 
5. On the left side choose Project View, then make a right-click on the "*login-with-amazon-sdk.jar"*  and press on "*Add as Library"*.
6. Now open your build.gradle and there you should fill in your android keystore details. 
7. Before we can build the project we still need to add an api_key. Therefore let your project open and move back to the [developer console](https://developer.amazon.com). Go to the developer Console and choose "Alexa" and then go on Get Started and then press behind your product "manage". 

Api key einfügen und keystore einfügen



https://developer.amazon.com/docs/login-with-amazon/register-android.html

## Android - Integrate Alexa Voice Service into your Android App

This Project shows you how to integrate the Alexa Voice Service which you can find [here](https://developer.amazon.com).  
The aim of the project is that you can talk to your personalized Alexa in an Android App instead of using a physical device from Amazon like the Echo or the Echo Dot.
So let's get started.

## Important!
The functional library was written by Will Blaschko at the following repository. Thanks for that! This is really awesome, because Amazon didn't provide this functionallity so far. 

<https://github.com/willblaschko/AlexaAndroid>

In this project you can find 3 single libraries. All I did was combining them and updating the outdated dependencies. In Addition I was only implementing the Push-To-Talk functionallity, because I do not think that the other two are that useful. Also I implemented the new WorkflowActivity, that Amazon provides to build your own Login-Screen.

## Prerequisites
1. [Amazon Developer Account](https://developer.amazon.com)
2. [Android Studio](https://developer.android.com/studio/index.html)
3. Physical Device with a minimum version of Lollipop

## Getting Started
The sample Application includes a login screen where you have to log in into your Amazon Account. After that you can almost use your personalized Alexa.  
If you want to Test this Application there are some steps that you need to do before you can use this project. If you just want to use the library and make it your own project go to the ***"Use Library"*** part of this Readme.

### **_Step 1: Create an Android Keystore_**
1. If you already have an Android Keystore you can skip this step and move to Step 2.
2. Download this project and unzip it.
3. Open Android Studio and choose **"Open an existing Android Studio Project"**. Move to the downloaded project and select it.
4. In the menu press on **"Build"** -> **"Generate Signed APK..."**
5. Android Studio will open a small window, where you have to choose a module. Choose the app-module and press on **"Next"**. 
6. Press on **"Create New"**. Now you have to choose a path. Personally I created a folder named "keystores", where I save my keystores. So navigate to your folder and then give your keystore a name like **"best-practice.jks"**. Give your keystore a password and confirm it. For this example it is enough if you give a password like **"alexaTest"**.
7. Now you have to type in the data for the keystore. As an alias I would take e.g. **"BestPracticeTestKey"**. As a password I take the same password I used for the Keystore. But that is something you can choose yourself. **Just be sure to remember those data for later!!.**
8. As last step fill in the Data for the certificate. Press on **"Okay"**. Then press on **"Next"** and for the Signature versions choose **"V1"** and **"V2"**.
9. If the build went through without mistakes, then open a terminal and navigate to the folder where you saved your keystore. Now we need to get out the MD5 and SHA256 values that we will need later. Now you have to type in the following command. *"`keytool -list -v -keystore best-practice.jks`"*. If you named you keystore differently than I did, replace **"best-practices.jks"** with your keystore.
10. You have to type in the password you gave to the keystore. If you did and succeeded then you will see smt like this. 
![MacDown ScreenShot](MacDownPictures/MD5AndSHA256Values.png)
11. **Save the MD5 and SHA256 values. You will need them later!** 
12. Close the terminal and go on with Step 2.


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

### **_Step 3: Run the project_** 
3. Now you have to do three things
 - First of all you need to go into the ***"assets"*** folder and open the ***"api_key.txt"*** file. Here you replace the text with your api key.
 - Next open the ***"Constants"*** class that you can find under global. Here type in your *"`product_id`"* that you can find in your developer console when you open your product. ![MacDown ScreenShot](MacDownPictures/ProductId.png)
 - Last you have to open the build.gradle and fill in the values for your keystore.
4. Execute the project, sign in and talk to Alexa.

## Using the Library
1. If you want to design your own project there are a couple of things that you should not forget to implement.  
 - First of all you need to implement a login Screen, because you need to have a registered Alexa. To implement this, follow these following instructions [Login Screen](https://developer.amazon.com/docs/login-with-amazon/install-sdk-android.html). 
 - You need to create the assets folder in the main folder with a file called "*`api_key.txt`"*. There you have to fill in your api key that you get while creating the security profile.
 - You also need a global folder with a Constants class, where you need to add this following line. "*`public static final String product_id="your_product_id";`"*.
 - In addition you need a release keystore that you need to add in the **"build.gradle"** to work with the libraries.
2. Add the library to your imports. "*`implementation 'com.github.DariuschDideban::BestPracticeForAVS:v1.0'`*"





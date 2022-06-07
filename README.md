# Find It
<img src="https://user-images.githubusercontent.com/78266241/161099748-0d735436-7d77-41cf-8f84-0da98e9b613c.png" width=50>

> An app leveraging AR Core technology to help navigating and providing knowledge of places. <br>
> Live demo [Find It video](https://www.youtube.com/watch?v=5fJSGYPfJM8). <!-- If you have the project hosted somewhere, include the link here. -->

## Table of Contents
* [Overview](#overview)
* [How to run code?](#how-to-run-code)
* [Technologies Used](#technologies-used)
* [Screenshots](#screenshots)
* [Contact](#contact)
<!-- * [Room for Improvement](#room-for-improvement)
* [Acknowledgements](#acknowledgements) -->
<!-- * [License](#license) -->


## Overview
If you are from an area far away, going to a completely new place to attend an event, visiting campus for the first time as a new student or wanting to see what life out there, wandering throw places to raise knowledge about how big our world is. Maps will make you confuse a lot but not anymore because Find It is here to help.

Find It, what? Here our app allows users to review all detailed information about the destinations, giving practical suggestions so that users can have an overview of places. Applying the most advanced AR technology from Google, we pride ourselves in providing users with immersive experiences that blend reality and virtual worlds to guide their destination. Bring services and events held at the venue with dedicated instructions, step-by-step processes with an intuitive and beautiful user interface. Find It is an application designed for all users, especially travelers from far away. They no longer have to worry about finding a new place without a map or guide, and even save a lot of time because everything to do is just a fingertip away.

Download Find It: https://bit.ly/FindItApp

## How to run code
1. Install [Android Studio](https://developer.android.com/studio?gclid=CjwKCAjwopWSBhB6EiwAjxmqDTWtCY4Ih65UNlYy5IjL_RpfmRggMSPU3mYgsPmexEnSMjJ1BWxiEhoCUn4QAvD_BwE&gclsrc=aw.ds).
2. Install [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).
3. Clone this repository (the main branch) on your computer by this commmand.
```
git clone https://github.com/SANGNGUYEN24/find-it.git
```
In my case, I used command prompt on my Window computer to clone this repo.

![image](https://user-images.githubusercontent.com/78266241/161089521-f345f07c-5b5f-40eb-98c8-8dd54f2b1dd1.png)

4. In your Android Studio, go to `File` -> `Open` -> navigate to the repo you have cloned, it named `find-it`
![image](https://user-images.githubusercontent.com/78266241/161091102-61f734b8-c16c-4540-bd82-6923383f21d4.png)

   and wait until Android Studio finishes indexing process

5. Connect Android Studio to a [physical smartphone](https://developer.android.com/studio/run/device) or an [emulator](https://developer.android.com/studio/run/emulator)

   For best experience, please run the app on a physical Android smartphone with Android 8 or higher.  
   
6. After connecting to the phone successfully, click run button ![image](https://user-images.githubusercontent.com/78266241/161093587-f35d2949-4932-473d-b377-d4bb99e7da54.png) to build the app.

7. To test the app, you can scan one of following QR codes:

    _Increase screen brightness to make QR scanning smoother_.
    
    <img src="https://user-images.githubusercontent.com/78266241/161094621-fedd4455-0f4b-4bf4-b599-da987272cdfa.png" width=200> <img src="https://user-images.githubusercontent.com/78266241/161094628-c8ea3d3e-7314-4f8b-954d-77433abdec31.png" width=200> <img src="https://user-images.githubusercontent.com/78266241/161094632-a388b792-a383-425a-9c94-ba24ffb8b75e.png" width=200> <img src="https://user-images.githubusercontent.com/78266241/161094635-56621d1d-2bd0-419e-b88d-13abf3d504e2.png" width=200> <img src="https://user-images.githubusercontent.com/78266241/161094640-39ce4a96-c56c-498b-84be-33ed0de243f9.png" width=200>
    
    These QR codes are attached to Ho Chi Minh City University of Technology, Vietnam.
    
    To test the direction feature with AR map:
    
    - Firstly, LOGIN an account to the app (our Cloud Firestore security rules require you login to write data to database) 
    - Secondly, scan a QR code, the app will show you are standing somewhere. 
    - Thirdly, select any destination then scan the surrounding environment until a virtual mesh appears, 
    then you can act as admin to place some map pointers (anchor) , wait until the app notices that the anchors have been hosted.
    
        ![image](https://user-images.githubusercontent.com/78266241/161474577-50d02a31-855e-4600-a24f-a72533248f6d.png)

    
    - Press the clear button, now you are acting as a visitor, press the resolve button to make the anchors you set earlier appear again.
    - At that time, the remaining distance bar will show you how far you have to go to your destination.

        <img src="https://user-images.githubusercontent.com/57563272/172281666-910f4f0a-8ea0-4b01-ba71-43e4b6c6d826.jpg" width=100>

    That's exactly how the AR navigation feature in Find It works.
    
    <img src="https://user-images.githubusercontent.com/57563272/172281319-84d851ef-60f8-4959-9e89-72a9709d1260.jpg" width=200>
    


## Technologies Used
- Android with Kotlin, Java
- Firebase (Authentication, Cloud Firestore, Storage, ML Kit, App Distribution, Crashlytics)
- ARCore

## Screenshots
![Home page](_readme/home.jpg)
![Desination page](_readme/destination.jpg)
![Service page](_readme/service.jpg)
![Review page](_readme/review.jpg)
![User page](_readme/user.jpg)
<!-- ![Find It airport](_readme/airport.jpg) -->
![Campus](_readme/campus3.jpg)
<!-- <img src="./_readme/home.jpg" alt="home" width="200"/>
<img src="./_readme/destination.jpg" alt="destination" width="200"/>
<img src="./_readme/service.jpg" alt="service" width="200"/>
<img src="./_readme/review.jpg" alt="review" width="200"/>
<img src="./_readme/user.jpg" alt="user" width="200"/> -->
<!-- If you have screenshots you'd like to share, include them here.


<!-- ## Setup
What are the project requirements/dependencies? Where are they listed? A requirements.txt or a Pipfile.lock file perhaps? Where is it located?

Proceed to describe how to install / setup one's local environment / get started with the project.
 -->

<!-- ## Usage
How does one go about using it?
Provide various use cases and code examples here.

`write-your-code-here`
 -->



<!-- ## Room for Improvement
Include areas you believe need improvement / could be improved. Also add TODOs for future development.

Room for improvement:
- Improvement to be done 1
- Improvement to be done 2

To do:
- Feature to be added 1
- Feature to be added 2
 -->

<!-- ## Acknowledgements
Give credit here.
- This project was inspired by...
- This project was based on [this tutorial](https://www.example.com).
- Many thanks to... -->


## Contact
Created by [@SANGNGUYEN24](https://github.com/SANGNGUYEN24), [@Proton2001](https://github.com/Pronton2001) and [@electrodrago](https://github.com/electrodrago) - feel free to contact us!


<!-- Optional -->
<!-- ## License -->
<!-- This project is open source and available under the [... License](). -->

<!-- You don't have to include all sections - just the one's relevant to your project -->

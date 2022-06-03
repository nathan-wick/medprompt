# *Medprompt*
![App Logo](https://github.com/nathan-wick/IT3048C-FinalProject/blob/main/Prototype/Images/App%20Logo.png?raw=true) <br />

# Introduction
Keeping track of medications and doctors appointments can be overwhelming, especially when you are recovering from surgery or learning to manage a chronic health condition. It is important to keep track of medication schedules and appointments when missing just one dose or one appointment can derail your treatment. Medprompt is there to help you: <br />
* Keep track of medications:
  * Record dosage amount and time of day to take medication
  * Record stock of medication and date to refill prescription
* Keep track of upcoming appointments and recurring appointments
* Keep track of any forms you need to fill out for medical or insurance puprposes
* Provide timely remiders to your android cellphone via push notifications <br />


Use Medprompt to remind you of your important medical dates and times so you can put your best effort towards healing.

# Storyboards
![Authentication View](https://github.com/nathan-wick/IT3048C-FinalProject/blob/8bcf4212df14883937bfe9e9d68ab2acae68426e/Prototype/Images/AuthView.png?raw=true)
![Main View](https://github.com/nathan-wick/IT3048C-FinalProject/blob/8bcf4212df14883937bfe9e9d68ab2acae68426e/Prototype/Images/MainView.png?raw=true)
![Main Plus View](https://github.com/nathan-wick/IT3048C-FinalProject/blob/8bcf4212df14883937bfe9e9d68ab2acae68426e/Prototype/Images/MainPlusView.png?raw=true)
![Medication View](https://github.com/nathan-wick/IT3048C-FinalProject/blob/8bcf4212df14883937bfe9e9d68ab2acae68426e/Prototype/Images/MedicationView.png?raw=true)
![Appointment View](https://github.com/nathan-wick/IT3048C-FinalProject/blob/8bcf4212df14883937bfe9e9d68ab2acae68426e/Prototype/Images/AppointmentView.png?raw=true)
![Form View](https://github.com/nathan-wick/IT3048C-FinalProject/blob/8bcf4212df14883937bfe9e9d68ab2acae68426e/Prototype/Images/FormView.png?raw=true) <br />
<br />
Interactive Prototype: https://www.fluidui.com/editor/live/preview/cF9JcVVRSFV4U1VUZE5zdFB3Z0pMclpnYWU2dXJnT2xldA== <br />
Color Palette: https://coolors.co/010d28-1768ac-f5f5f5-ffffff
# Functional Requirements
**Requirement 100: Add Medication**<br />
**Scenario**: As a user who regularly takes medications, I would like to be notified when I need to take them and when they need refilled.<br />
**Dependencies**: Automatic phone notifications are easy to setup.<br />
**Assumptions**: User knows what medications they use, the date, time, and frequency that they use the medications, and how much medication they have in stock.<br />
**Example**:<br />
**Given** I entered my medication information<br />
**When** I need to take or refill my medication<br />
**Then** I’ll get a phone notification to remind me<br />

**Requirement 101: Add Appointment**
**Scenario**: As a user who regularly attends doctor appointments, I would like to be notified when I have an upcoming appointment.<br />
**Dependencies**: Automatic phone notifications are easy to setup.<br />
**Assumptions**: User knows what appointments they have, the date, time, and frequency that they attend the appointments, and the location of the appointment.<br />
**Example**:<br />
**Given** I entered my appointment information<br />
**When** I need to attend an appointment<br />
**Then** I’ll get a phone notification to remind me<br />

**Requirement 102: Add Form**
**Scenario**: As a user who regularly completes medical forms, I would like to be notified when I need to complete a form.<br />
**Dependencies**: Automatic phone notifications are easy to setup.<br />
**Assumptions**: User knows what forms they complete, the date, time, and frequency that they complete the forms, and what information the form collects.<br />
**Example**:<br />
**Given** I entered my form information<br />
**When** I need to complete a form<br />
**Then** I’ll get a phone notification to remind me<br />

# Class Diagram
![UML Diagram](https://github.com/nathan-wick/IT3048C-FinalProject/blob/f6bd3c874fb91edc2055ce7c100431128c15eecf/Prototype/Images/MedPrompt.drawio.png?raw=true)
## Class Diagram Description
### AppModule
The main class – entry module to the app. Most likely will have config set ups, etc

### FirebaseInstance
This class will have setup and configuration actions for our Firebase instance. Firestore, Auth, etc

### MainActivity
This class will be responsible for the Android Composer. It will be the main handler for the UI components, and the data that changes between each Views

### ViewModel, ApplicationViewModel
These classes will probably not be used (that much) given that we have the Composer that will handle most of our dynamic data handling between components

### INotificationService
Interface will directly handle the “talking” between the ViewModel and NotificationService which will contain the getting of the data

### INotificationDAO
Will handle the “talking” between the database data and the NotificationService

### NotificationLocalDatabase
This class will contain methods that are responsible for syncing with cloud data. We will probably use this for auto complete features for medicine searching. It also extends the RoomDatabase.

### RoomDatabase
This class contains methods and attributes for our implementation of the RoomDatabase for Android.

### INotification
Is an interface that will be the rough implementation of the parent Notification Class, and any sub-classes. That way, front end guys can start implementing features while back end guys work on the backend implementations

### Notification
This class is the parent class for Medication, Form, and Appointment class

### Medication
This class implements the parent class Notification, but has its own methods and attributes

### Form
This class implements the parent class Notification, but has its own methods and attributes

### Appointment
This class implements the parent class Notification, but has its own methods and attributes

### MeasurementMetric
This class will handle (conversions, maybe) metric types. It will give in string format a metric of some sort. Ex: ml, mg, etc.

# Product Backlog and Kanban Board
Under GitHub Projects with Release 1.0 Sprint 1.0 Milestones for Tasks in Each Project.


# Roles
Scrum Master: Nathan <br />
Product Owner: Saumick <br />
Development Team <br />
 UI Specialists: Alex, Molly <br />
 Integration Specialists: Jack, Juan <br />

# Meetings
Meetings are every Thursday at 6:00 PM. <br /> Meeting link: https://teams.microsoft.com/l/meetup-join/19%3ameeting_YmYxOTVkNWEtYWVjNC00ZGEwLTlmNWEtYjJhYWM0NGY0MDg3%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%22cef6eb41-aa90-4f23-99d4-83ac4141e2be%22%7d

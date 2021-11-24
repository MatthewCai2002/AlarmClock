# My Persistent Alarm Clock Project

## What it does

The app is designed to wake up users by forcing the user to use their brain in an engaging way,
helping them overcome that morning tiredness and desire to stay in bed; making them more active
and productive. The app acts as a standard alarm clock with the ability to set an alarm at 
any time on a 24 hour clock that will ring at that set time. This alarm will exist 
indefinitely until removed by the user. Furthermore, the alarm can be toggled active or inactive
to prevent the alarm from going off indefinitely without deleting it. 

Turning off the alarm will require a specific action to be done. 
In the first iteration this action would be a simple problem that would have to be 
correctly answered to turn off the alarm and incorrect attempts will not turn off the alarm. 
In later iterations a timer feature could also be added, as well as an override feature
so that if a user is unable to correctly answer a problem they can still shut off their alarm after
a too many attempts. In addition, a feature that locks your computer when the alarm goes off
could be considered to enforce the alarm. Other features include volume setters, 
more ringtones, and an alarm toggle will be added as well. 

## Who will use it

The intended user base are people who have trouble getting out of bed in the morning and feel
consistently tired after they wake up. By stimulating higher brain function when they wake up it 
forces them to wake up faster to shut off their alarm thereby, helping them wake up faster.

## My interest in creating this app

Previously I used to wake up and get out of bed in the morning at 6:00am consistently every day. However,
over the fall term I have noticed that I have been sleeping later as well as waking up later, and I have
been on my phone for longer periods of time in the morning. So to counteract my habits faltering I
want to create an app that forces me to wake up and get out of bed in the morning to help maintain 
my morning habit.

## User Stories

- As a user, I would like to be able to add multiple alarms
- As a user, I would like to be able to add an alarm
- As a user, I want to be able to see the  number of alarms I have
- as a user, I want to be able to solve a problem to turn off my alarm
- As a user, I would like to be able to delete alarms
- As a user, I would like to be able to set an alarm at any time of day
- As a user, I would like to be able to save the alarms I add
- As a user, I would like to be able to load my alarms from a file and start where I left off
- As a user, I would like to be able to select the difficulty of the problems I need to solve

## Phase 4: Task 3

I was actually really surprised by how little coupling I had between classes that 
weren't UI and how simple the overall design of my app was. In particular, I was
surprised that there were very little associations between classes in the model
and puzzle packages as while I was writing them I knew that alarms had to do 
something with all the puzzles so I imagined that there would be more associations
between them. Originally this was the case however, I refactored several times
throughout phase 3 to mainly reduce coupling and increase cohesion. This then
resulted in the current app design where the alarms are basically completely 
separate from the puzzles, only being related through AlarmAppUI.

If I had more time to work on the project:

- I would have liked to implement the observer pattern to AlarmAppUI and Siren
    - Siren needed to know if the clock in AlarmAppUI was at an alarm time to know to when to play the alarm sound. 
    - This could have been done through applying the observer pattern with AlarmAppUI as the subject and Siren as the observer
- I would have liked to improve cohesion in AlarmAppUI
  - AlarmAppUI does way too much such as:
    - checking when to ring the alarm
    - ringing the alarm
    - displaying everything
    - managing all the clocks
    - managing all actionEvents
    - managing all popups
  - so I would have liked to separate some of these responsibilities into separate classes to improve cohesion
  


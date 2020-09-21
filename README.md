# Wild-Ranger-Tracker

## Author
Michael Mbaya Gikunda.

### Description


## Prerequisites & setup
### Prerequisites
* 1.Git.
* 2.Java SDK and Environment
* 3.Java IDE preferably IntelliJIDEA Community or Ultimate Edition.
* 4.Gradle.
* 5.PostgreSQL
* 6.Ubuntu OS

### Setup

* Install PostgreSQL. for help installing ==> https://www.youtube.com/watch?v=-LwI4HMR_Eg
* Open up your terminal and type the following commands(only works if postgreSQL is installed)
* 
        psql
        
        CREATE DATABASE wildlife_tracker;
        
        \c wildlife_tracker;
        
        CREATE TABLE animals 
        (id serial PRIMARY KEY, name varchar, age varchar,health varchar, type varchar);
        
        CREATE TABLE sightings
        (id serial PRIMARY KEY, animalId int, location varchar, rangerName varchar, sightingDate timestamp);
        
        CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
        
* Clone this repository unto your local machine in a directory of choice.
* Open the cloned directory using Intellij IDEA
* In the src/main/java/ folder, go to the file DB.java and replace user and password in line 4 with your postgreSQL user and password respectively
* In the src/test/java/ folder, go to the file DatabaseRule.java and replace user and password in line 10 with your postgreSQL user and password respectively
* Open in the IDE and run the command "gradle build" and "gradle run"

##### Or
* Click on the link provided in the description: https://rangertrack.herokuapp.com/ 

## How to use (Behavior)

* Add an Endangered Animal
* Add other Animals (Non-Endangered)
* Add a Sighting of an Animal that has been Added before, otherwise repeat the previous steps
* See added Animals; endangered, non-endangered and all as well
* See Sightings Recorded and time recorded

## TECHNNOLOGIES USED

* Java
* Java Spark
* postgreSQL

## BUGS

* Remote Database Connectivity for live site.
* If you come across any bugs, please reach out to the contact below

## CONTACTS

* For any enquiries and comments, reach out at: 
* gikundamike@gmail.com

## LICENSE

* MIT License

Copyright (c) 2020 Michael-Mbaya

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
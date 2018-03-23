### A travles management system application for Abhi Tours and Travels 

#### Prerequesite

    * Java
    * Postgresql

#### Import schema to postgresql database server
   
   `createdb travels`

   `psql -f sql/travels.sql -d travels`

#### Configure hibernate parameters

   `Modify the database connection settings in src/hibernate.cfg.xml file`

#### Run the project

   * To run the application

   `./gradlew run`

   * To generate a executable jar file

   `./gradlew buildJar` 

   * For list of other operations

   `./gradlew tasks`


**Note :** For windows OS use gradlew.bat script for any operations

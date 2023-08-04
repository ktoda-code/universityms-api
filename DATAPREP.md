# Data Preparation

To properly use the application, follow these steps:

1. Create a MySQL connection using the username "unimsapi-admin" and any password you prefer.
2. Download the "database-scheme.sql" script file from the "[database-scheme](database-scheme)" folder.
3. Execute the "database-scheme.sql" script in your MySQL database to create the necessary tables and 
schema for the application.
4. If any errors occur during the execution of the script, carefully review the "database-scheme.sql" file to 
identify and correct any potential problems.
5. Ensure that the data for the users follows the specified format:
   * Users with administrative privileges should have email addresses with the suffix "@admin.edu."
   * Students should have email addresses with the suffix "@student.edu."
   * Teachers should have email addresses with the suffix "@teacher.edu."
6. The passwords for all users should be stored as bcrypt hashes to enhance security.

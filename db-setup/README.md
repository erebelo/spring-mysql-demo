# MySQL Server Setup

1. Download and install [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) for Windows by choosing the **Windows (x86, 64-bit), ZIP Archive** option

2. Extract the ZIP archive

3. Navigate to _C:\<MY_SQL_PATH>\bin_ directory using Git Bash terminal

   **NOTE:** use this path while running the commands in the next steps.

4. Create a MySQL Configuration File in _/bin_ with the content below:

   **NOTE:** change the _<MY_SQL_PATH>_ references.

   **my.ini**

   ```
   # Path to MySQL installation directory
   basedir=C:\<MY_SQL_PATH>
   # Path to MySQL data directory
   datadir=C:\<MY_SQL_PATH>\data
   # Port number to listen on
   port=3306

   # Other custom settings can go here
   # Default storage engine
   default-storage-engine=INNODB
   # Maximum allowed packet size
   max_allowed_packet=16M
   # Error log file
   log-error=C:\<MY_SQL_PATH>\data\mysql_error.log
   ```

5. Initialize the Data Directory by running the command:

   `$ ./mysqld --initialize --console`

   **NOTE:** save the temporary password generated for root@localhost.

6. Start MySQL Server (**KEEP THIS TERMINAL OPPENED**):

   `$ ./mysqld --console`

7. Run the **C:\<MY_SQL_PATH>\bin\mysql_secure_installation.exe** by double clicking it:

   - Enter the previously temporary password generated
   - Enter a new password
   - Follow the remaining steps

8. (optional, requires admin rights) Add MySQL bin directory to the system environment variables path: _C:\<MY_SQL_PATH>\bin_

9. (optional, requires admin rights) Download and install [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)

## Batch Files

**NOTE:** change the _<MY_SQL_PATH>_ references.

1. MySQL connection:

   **mysql_conn.bat**

   ```
   @echo off
   cd C:\<MY_SQL_PATH>\bin
   mysql -u root -p
   ```

2. Start MySQL:

   **start_mysql.bat**

   ```
   @echo off
   cd C:\<MY_SQL_PATH>\bin
   start mysqld --console
   ```

3. Stop MySQL:

   **stop_mysql.bat**

   ```
   @echo off
   cd C:\<MY_SQL_PATH>\bin
   mysqladmin -u root -p shutdown
   ```

## Create Database & User

**NOTE:** change the _<DB_NAME>_ and _<DB_USERNAME>_ references and use the **mysql_conn.bat** to run the commands below.

1. `$ CREATE DATABASE <DB_NAME>;`

2. `$ CREATE USER '<DB_USERNAME>'@'%' IDENTIFIED BY '<DB_PASSWORD>';`

3. `$ GRANT ALL PRIVILEGES ON <DB_NAME>.* TO '<DB_USERNAME>'@'%';`

4. `$ FLUSH PRIVILEGES;`

5. `$ SHOW DATABASES;`

6. `$ EXIT;`

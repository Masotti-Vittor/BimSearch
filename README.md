BIM SEARCH

🏁
          Hello! I’m excited to share my first program, developed in Java and MySQL.This project's objective is to optimize the modeling of elements in large-scale projects, such as industrial plants or electrical stations.

💥
The problem:
          In big projects, it’s common for team members to accidentally model the same element more than once. Because the project area is huge, different members work on different sections. Thus, the same element may appear in multiple places. If this duplication goes unnoticed, someone might spend anywhere from 15 minutes to several hours redoing something that already exists. On the other hand, searching for an element can also waste time due to the amount of components.

⁉️
The cause:
          Large projects can contain thousands of different elements. As the number grows, it becomes harder to track what’s already been created. Some elements can even get “lost” among the completed projects.

✅️
The solution:
          I built a program that allows users to store images of elements along with metadata describing their characteristics. This data is stored in a database, and the images are saved to a designated folder on the company’s server. After modeling an element, a user can add it to the program. Other team members can then search for it using filters based on the metadata. Instead of searching through 400 components, a user might look within 15; or even fewer as more filters are applied. This saves significant time and avoids unnecessary duplication.

---

<<<<<<< HEAD
### REQUIREMENTS
         
	* You must have Java 25 installed on your machine. 
	
	- After intalling, you may compile the files, and run the program. 

	- To run the program, you MUST run it with the h2 file, which is the Data Base. 
		- I usually run it with the terminal, thus, I just run "java -cp .:h2.jar Main". This way, we are running the Main with the h2 file. 
	
	
	— A data base will be created when the program runs for the first time. This data base contains two tables, one which contains the users, and another that has the images information. 

	— An initial user is also added to the users table. To log in the program, you must use the following credentials
	***Login: admin***

	***Password: admin1***  


	This is the required to use the program. The following message is about future updates considering confidentialy. 

# **For reasons of confidentiality and exclusivity from the company, the last updates won't be pushed here, though I may still push some updates**

# **But I hope this project may show my interest in learning and my desire to do more, since I work at a company where computer science isn’t the main field.**

# **I also hope this project can bring you some value to you. Thank you! I’ll be working on more projects and posting them here!**
=======
```
• How to clone and use this project

```

### REQUIREMENTS
          You must have MySQL on your machine. I installed MariaDB.
          I still do not have the knowledge to work and fix absolute paths. Thus, you may change the paths to yours, in the following files:

- BimSearch.desktop, install.sh, launch_bimsearch.sh.

Easier way:

- If you do not want to change the paths in the files (to use the desktop executable), you may just run the .jar folder, which has all the compiled classes.
Command to run the program: **jar cfm app-fat.jar manifest.txt -C tmp .**
// You must be inside the folder/directory “dataBaseInternship”, and then run the command.

After you clone the project to your machine, and initialize your MySQL, you may start the program. 

— A data base will be created when the program runs for the first time. This data base contains two tables, one which contains the users, and another that has the images information. 

— *An initial user is also added to the users table. To log in the program, you must use the following credentials
**Login: admin***

***Password: admin1***  

# **For reasons of confidentiality and exclusivity at the company, future updates won’t be pushed here.**

# **But I hope this project — even unfinished — can show my interest in learning and my desire to do more, since I work at a company where computer science isn’t the main field.**

# **I also hope this project can bring you some value. Thank you! I’ll be working on more projects and posting them here!**
>>>>>>> b3f1c6e7125fac1b6d7b1cd4892413e571eb1491

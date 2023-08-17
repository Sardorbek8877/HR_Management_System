#HR Management System with Spring Boot and Spring Security

Welcome to the **HR Management System** repository, showcasing a comprehensive solution for efficient HR management built using Spring Boot, PostgreSQL, and Spring Security.

##About the System
The HR Management System is a sophisticated service platform tailored for HR management tasks. It offers a secure and accessible environment through username and password authentication.

##Features
###Company Hierarchy
**Director:** The company is led by a single director responsible for overseeing all operations.

**Managers:** Directors have the authority to add managers to the system. When adding managers, a unique link is sent to their email addresses for account activation.

**HR Manager:** Within the department hierarchy, the HR Manager position holds a special significance.

##Employee Management
**Employees:** Managers, especially HR Managers, can add employees to the system. Similar to managers, employees receive an email link for account activation upon registration.

**Authentication:** Employees are assigned unique passwords through email links for secure access.

**Unique Email:** Employee email addresses serve as non-repeatable usernames.

**Tourniquet:** Employees are provided with a tourniquet to track their entrances and exits.

##Task Management
**Task States:** Tasks are categorized into three states: new, in progress, and completed.

**Assignment:** The director assigns tasks to managers and employees. Managers are responsible for employees, while directors oversee the entire process.

**Details:** Each task includes a name, description, estimated completion time, and assigned employee.

**Notifications:** Employees receive email notifications upon task assignment and completion.

**Task Completion:** After completing a task, employees mark it as completed, triggering an email notification to the assigning manager or director.

##Attendance Tracking
**Attendance:** A attendance is integrated to monitor employee work hours, recording entry and exit times.
Leadership Insights
**Visibility:** Managers in the positions of director and HR Manager can access a list of employees.

**Employee Information:** Managers can view comprehensive information about employees, including attendance within specified intervals and task performance.

**Salary Management:** Monthly salaries are determined for each employee, and salary issuance is recorded in the system. Managers can review issued salaries by employee or specific month.

**Task Performance:** Managers can assess the timely completion of tasks assigned to employees and identify any potential delays.

##Installation and Usage
**Prerequisites:** Ensure you have Java, Maven, PostgreSQL, and Spring Boot set up on your system.

**Database Configuration:** Configure your PostgreSQL database connection details in the application.properties file.

**Build and Run:** Execute mvn spring-boot:run in the project's root directory to compile and run the application.

**Access:** Open your web browser and navigate to http://localhost:8080 to access the HR Management System.

# AccountingCapstone
 CMPSC Capstone Project -- Accounting Program

Problem Statement:
It’s known how important documentation and economics are to companies in the
real-world. How this connects to computer science is through software for internal
financing. Software tends to be more secure, fast, and available than paper.
Our aim for this app will allow a company to efficiently manage budget, expenses,
income, payables/receivables, cash flow, taxes, audits and other document handling
matters such as Profit/Loss statements. We will be working with an external database
to store files (PDF statements) and other confidential information like login information
and user information.

We will use JavaFX as our base application integrated with Azure SQL Database as the database
platform that can not only store key-value information, but in addition to media, which
pertains to the application’s use of economic documents. We will also potentially use
Python in some of the backend modules.

Advisor: Kabir

In order to generate the graphs, make sure to install the latest version of Python first.
<br><b>1.</b> Install the required dependencies. To install the Python packages run the command in you command prompt window: <br> 
<code>pip install seaborn matplotlib pandas pypyodbc</code>
<br><b>2.</b> Install the <a href='https://learn.microsoft.com/en-us/sql/connect/odbc/download-odbc-driver-for-sql-server?view=sql-server-ver16'>ODBC Driver Version 18 for Windows</a>. Install the driver using the deazfult options.
After installing, go to your Windows search bar and type <code>ODBC Data Srources</code>.
<br><b>3.</b> After opening 'ODBC Data Sources', navigate to the <code>Drivers</code> tab. Look for the name <code>ODBC Driver 18 for SQL Server </code>. This verifies you installed the correct version.
<br><b>4.</b> Everything should work now.


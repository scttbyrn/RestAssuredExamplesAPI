This is Setup for Email Reciept after run on CICD pipeline.

******Pre-requisite:******
Install Extended E-mail Notification
Install E-mail Notification
The user should be also on Manage System > System

******Step 1:******
On Extended E-mail Notification section
fill up the ff for:

SMTP Server - smtp.gmail.com

SMTP Port 465

Click Advance
Add credential
If google email{
username - scttsmrfng2@gmail.com
password using secret password - qwertyuiopasdfgh

}

Default Content Type - Plain Text (text/plain)

Tick Use Use SSL

Default Recipients - scttsmrfng2@gmail.com

click Default Triggers
you may choose what event you want e.g. Always, Failure, SUCCESS


******Step 2:******
On E-mail Notification section

SMTP server - smtp.gmail.com

click Advanced
Tick Use SMTP Authentication
User Name - scttsmrfng2@gmail.com
Password - qwertyuiopasdfgh, secret password
Tick Use Use SSL
SMTP Port 465

Step 3:
check if the email is successful ping

tick Test configuration by sending test e-mail
Test e-mail recipient - scttsmrfng2@gmail.com
click Test Configuration
validate: Email was successfully sent



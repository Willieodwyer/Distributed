# Distributed
reiner plz

### Configuration
A message queue must be created within the Glassfish server for the project to function correctly.
 - Navigate to default Glassfish installation directory, on Windows this is "C:\Program Files\glassfish-4.1.1\bin".
 - Launch the Glassfish admin terminal, asadmin, and enter the following command "create-jms-resource --restype javax.jms.Queue --property Name=PhysicalQueue jms/MsgQueue".
 - Restart Netbeans in privileged mode.

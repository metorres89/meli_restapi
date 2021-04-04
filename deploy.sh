#vars
ec2dns=ec2-18-188-38-174.us-east-2.compute.amazonaws.com
ec2id=ec2-user@$ec2dns #ec2 id restarts every time you restart the ec2 instance. Check before running this script!
echo "using ec2id:{$ec2id}"

#!/usr/bin/env bash

#rm -rf build/
#echo "Deleted build/ folder"

#maven build
mvn clean package
echo "Generating jar file (MVN)"

#Copy execute_commands_on_ec2.sh file which has commands to be executed on server... Here we are copying this file
# every time to automate this process through 'deploy.sh' so that whenever that file changes, it's taken care of
scp -i "~/Documents/aws/aws-ec2-demo-1.pem" execute_commands_on_ec2.sh  $ec2id:/home/ec2-user
echo "Copied latest 'execute_commands_on_ec2.sh' file from local machine to ec2 instance"

scp -i "~/Documents/aws/aws-ec2-demo-1.pem" target/restapi-0.0.1-SNAPSHOT.jar  $ec2id:/home/ec2-user
echo "Copied jar file from local machine to ec2 instance"

echo "Connecting to ec2 instance and starting server using java -jar command"
ssh -i "~/Documents/aws/aws-ec2-demo-1.pem" $ec2id ./execute_commands_on_ec2.sh
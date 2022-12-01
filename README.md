# NewsLetter-Serverless-App
NewsLetter App using spring cloud functions deployable to AWS Lambda

Requirements - Java8, Maven, MongoDB

mvn clean package </br>
inside target package</br>
serverless-0.0.1-SNAPSHOT.jar standalone spring boot App</br>
serverless-0.0.1-SNAPSHOT-aws.jar this can be uploaded to AWS lambda.</br>

We need to add 2 env variables MAIL_USERNAME, MAIL_PASSWORD to send mail while running the App.

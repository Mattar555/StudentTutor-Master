# Context

I volunteer in a non for profit tutoring service over the weekends targeting disadvantaged children, specifically as a campus coordinator aided by other coordinators regionally. As this is a volunatary initiative, it is not a requirmement (nor is it expected) for volunteers (tutors) to attend every single weekend. Coordinators are thus responsible for keeping tabs on attendance on a weekly basis, ensuring each student has a volunteer aiding him/her for the duration of the session. 

Normally, corodinators would send notifications to tutors via email or message (following up again for non responders) and recording changes in an excel sheet. This process is somewhat tedious, time consuming, and prone to errors. Not to mention, it does not scale well.

This app (for lack of better word) aims to automate the above. An email notification (as executed by the "writer service") would be sent on a daily basis to tutors (who have not yet responded) confirming whether or not they are attending the coming weekend. Tutor simply need to reply with a "YES" or a "NO" to the prompt. An email monitoring service (the "reader service) polls, filters and parses the replies on a daily basis. This parsed information is then relayed back to the master service updating a remote SQL database. Updates performed on the DB are reflected via the web-frontend, where a query is performed in order to retrieve the real time student tutor allocation for the coming weekend displayed in a tabular format. Adminstators are authorized to peform CRUD like operations on the table if need be (For example, adding a student tutor pair).

The (official) web portal is outdated and is subject to undergo a major infrastructure change over the new few months, with the main aim of relying on off the shelf automated solutions offered by various service providers. This is a (fairly rudimentary) example of the nature of work proposed in the pipeline.

This is where I drew inspiration for this application. 


# Additonal Information

Please note this application is a proof of concept. It was completed in a matter of days and is mainly aimed at showcasing my capbilities as a developer. One can, with relative ease, leverage cloud based SAAS providers, to replicate, extend and maintain an application such as the one described above. Taking AWS as an example, the use of S3, Lambda, EventBridge, Macie (for added security) along with SNS/SES offers a flexible, serverless, easy to maintain and low cost alternative to the above.

Please refer to the folder named "doc" for a deeper technical overview to the inner workings of this application.

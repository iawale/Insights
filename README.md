# Insights
Instagram follower insight app. (Client id and redirect URI not present in the code). A slightly different version of Folinsight.

Before you build and run the application, please goto com.chiragawale.insights.keys.keys_access.java file and insert your Redirect Uri, and Client Id's. For use without any code changes please put redirect uri as "http://localhost/".

This application uses Instagram Api to get data on likes/comments of followers, and users that the owner of the access token follows, and stored in Temporary Global Variables which are accessed through interfaces, and data required to plot graphs is also stored in a SQLite database. The application uses Recycler View to display data to the user. It shows number of likes, comments from the follower and follows, and provides graphs by day, and other such details in the form of lists/points.

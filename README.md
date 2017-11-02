# Tweeter Manager

Tweeter manger is a RESTful web service helps to fetch a PDF report of your tweets. It follows MVC architectural pattern with a front controller.


### Features
- Tweets (view your recent tweets including date, content and date of posting)
- Index your own tweets and find a particular tweet by unique id
- PDF report of your tweets
- Authentication

### Requirements
- Java 1.8
- Maven 3.0+

### Installation Instructions
```
git clone https://github.com/BoZhaoUTSC/spring-mvc.git
cd spring-mvc
mvn package
mvn tomcat7:run
```

#### As a User
-   install this project following installation instructions above
-   go to localhost:8080
-   Enter "Kenny" as username and "123" as password
-   Then click "Login"


### API documentation

#### Get all tweets
-   url: /tweets
-   method: GET
-   description: Retrieves all tweets stored in this Tweeter Manager.
-   response sample:
[
    {
        "id": 1,
        "user": "Kenny,
        "content":"Happy Halloween",
        "targetDate":1509599291724
    }, {
        "id": 2,
        "user": "Kenny",
        "content":"Merry Chritmas",
        "targetDate":1509599291724
    }
]

#### Get a particular tweet by id
-   url: /tweet/{id}
-   method: GET
-   description: Retrieve a particular tweets with custom id setted by Tweeter Manager
-   response sample:
{
    "id": 1,
    "user": "Kenny,
    "content": "Happy Halloween",
    "targetDate": 1509599291724
}

#### Get tweets with multiple filters(To Be Worked On)
-   url: /tweets/
-   method: GET
-   description: Retrieve tweets based on a time interval, tags and number of retweets
-   this endpoint allows following parameters:
-   start: the start date and time of a time interval
-   end: the ending date and time of a time interval
-   num_retweet_min: the minimum number of retweets
-   num_retweet_max: the maximum number of retweets
-   tags: tags associates to tweets
-   response sample:
[
    {
        "id": 1,
        "user": "Kenny,
        "content": "Happy Halloween",
        "tags": ["holiday", "party", "custom"]
    }, {
        "id": 2,
        "user": "Kenny",
        "content": "Merry Chritmas",
        "tags": ["holiday", "party", "chritmas-gift"]
    }
]

#### Post a new tweet(To Be Worked On)
-   url: /tweet
-   method: POST
-   description: post a new tweet through Tweet Manager API
-   body schema:
{
    "content": (required) content of this tweet,
    "user": (required) original author of this tweet
    "tags": (optional) tags associate to this tweet
}


### About
Tweeter Manager is developed in the [Java Spring MVC Framework](https://projects.spring.io/spring-framework/).

It has a RESTful API and takes GET and POST requests at different endpoints. The RESTful API allows us to post and retrieve tweets in a flexible way by accepting multiple filters and request body.

Tweeter Manger uses iText to generate, and send PDF reports about recent tweets, statistical of tweets and tweets with custom id and tags to ensure proper management of all tweets.

It uses Maven to help managing dependencies such as Bootstrap, Tomcat7 and MongoDB.

Tweeter Manager is deployed and tested in WebSphere Liberty Profile(To Be Worked On).

It uses MongoDB to store data and the instance of MongoDB is hosted on [cloud](https://mlab.com/home).
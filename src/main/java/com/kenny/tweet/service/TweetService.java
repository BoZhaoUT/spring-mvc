package com.kenny.tweet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Service;
import org.bson.Document;

import com.kenny.model.Tweet;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;


@Service
public class TweetService {
	private static List<Tweet> tweets = new ArrayList<Tweet>();
	private static int tweetCount = 3;
	// create a connection to mongoDB
	MongoClient mongoClient = new MongoClient();
	MongoDatabase database = mongoClient.getDatabase("spring");
	
	
	public String getUserByName(String name) {
		MongoCollection<Document> collection = database.getCollection("users");
		FindIterable<Document> cursor = collection.find(eq("name", name));
		// TODO: change return type to user
		return (String) cursor.first().get("name");
	}

	// for development only
	static {
		tweets.add(new Tweet(1, "Kenny", "Happy Halloween", new Date()));
		tweets.add(new Tweet(2, "Kenny", "Merry Christmas", new Date()));
		tweets.add(new Tweet(3, "Kenny", "Happy New Year", new Date()));
	}

	public List<Tweet> retrieveTweets(String user) {
		List<Tweet> filteredTweets = new ArrayList<Tweet>();
		for (Tweet tweet : tweets) {
			if (tweet.getUser().equals(user))
				filteredTweets.add(tweet);
		}
		return filteredTweets;
	}

	public Tweet retrieveTweet(int id) {
		for (Tweet tweet : tweets) {
			if (tweet.getId() == id)
				return tweet;
		}
		return null;
	}

	public void updateTweet(Tweet tweet) {
		tweets.remove(tweet);
		tweets.add(tweet);
	}

	public void addTweet(String name, String desc, Date targetDate) {
		tweets.add(new Tweet(++tweetCount, name, desc, targetDate));
	}

	public void deleteTweet(int id) {
		Iterator<Tweet> iterator = tweets.iterator();
		while (iterator.hasNext()) {
			Tweet tweet = iterator.next();
			if (tweet.getId() == id) {
				iterator.remove();
			}
		}
	}
}
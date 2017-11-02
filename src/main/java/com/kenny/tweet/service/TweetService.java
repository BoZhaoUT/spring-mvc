package com.kenny.tweet.service;

import java.util.ArrayList;
import java.util.Date;
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

	// a list of current tweets
	public List<Tweet> tweets = new ArrayList<Tweet>();
	// create a connection to mongoDB
	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://db_admin:db_admin@ds243295.mlab.com:43295/spring-kenny"));
	MongoDatabase database = mongoClient.getDatabase("spring-kenny");


	/**
	 * Reeturn all tweets posted by a specific user.
	 * @param name of this user
	 * @return all tweets posted by a specific user
	 */
	public List<Tweet> retrieveTweetsByName(String name) {
		MongoCollection<Document> collection = database.getCollection("tweets");
		collection.find(eq("name", name)).forEach(printBlock);
		return this.tweets;
	}
	
	/**
	 * Return all tweets.
	 * @return all tweets
	 */
	public List<Tweet> retrieveAllTweets() {
		MongoCollection<Document> collection = database.getCollection("tweets");
		collection.find().forEach(printBlock);
		return this.tweets;
	}

	/**
	 * Retrieve a tweet by its id.
	 * @param id of the tweet
	 * @return a tweet matching id
	 */
	public Tweet retrieveTweet(int id) {
		MongoCollection<Document> collection = database.getCollection("tweets");
		collection.find(eq("id", Integer.toString(id))).forEach(printBlock);
		return this.tweets.get(0);
	}

	/**
	 * Add a new tweet.
	 * @param name of the user
	 * @param content of this tweet
	 * @param targetDate posting date
	 */
	public void addTweet(String name, String content, Date targetDate) {
		MongoCollection<Document> collection = database.getCollection("tweets");
		long count = collection.count(new Document()) + 1;
		Document document = new Document("name", name)
               .append("content", content)
               .append("id", Long.toString(count));
		collection.insertOne(document);
	}

	/**
	 * Delete a tweet based on its id
	 * @param id of this tweet
	 * @param name of original poster
	 */
	public void deleteTweet(int id, String name) {
		MongoCollection<Document> collection = database.getCollection("tweets");
		collection.deleteOne(eq("id", Integer.toString(id)));
		this.updateTweets(name);
	}
	
	/**
	 * Update current tweets after any changes.
	 * @param name of the user
	 */
	private void updateTweets(String name) {
		this.tweets.clear();
		this.tweets = this.retrieveTweetsByName(name);
	}
	
	/**
	 * Return a user object.
	 * @param name of the user
	 * @return user object
	 */
	public String getUserByName(String name) {
		MongoCollection<Document> collection = database.getCollection("users");
		// TODO: return null if user is not found
		FindIterable<Document> cursor = collection.find(eq("name", name));
		// TODO: return a user object
		return (String) cursor.first().get("name");
	}
	
	// used as a callback function between API and mongoDB
	Block<Document> printBlock = new Block<Document>() {
		@Override
		public void apply(final Document document) {
			// parse result obtained from collection
			String user = (String) document.get("user");
			int id   = Integer.parseInt((String) document.get("id"));
			String content = (String) document.get("content");
			Date date = new Date();
			// create a new tweet object and store in result array
			Tweet tweet = new Tweet(id, user, content, date);
			tweets.add(tweet);
		}
	};
}

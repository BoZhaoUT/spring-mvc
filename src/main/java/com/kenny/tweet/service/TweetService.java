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
	Block<Document> printBlock = new Block<Document>() {
		@Override
		public void apply(final Document document) {
			System.out.println(document);
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

	private static List<Tweet> tweets = new ArrayList<Tweet>();
	// create a connection to mongoDB
	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://db_admin:db_admin@ds243295.mlab.com:43295/spring-kenny"));
	MongoDatabase database = mongoClient.getDatabase("spring-kenny");


	public String getUserByName(String name) {
		MongoCollection<Document> collection = database.getCollection("users");
		FindIterable<Document> cursor = collection.find(eq("name", name));
		return (String) cursor.first().get("name");
	}

	public List<Tweet> retrieveTweets(String user) {
		MongoCollection<Document> collection = database.getCollection("tweets");
		tweets.clear();
		collection.find(eq("user", user)).forEach(printBlock);
		return this.tweets;
	}

	public Tweet retrieveTweet(int id) {
		MongoCollection<Document> collection = database.getCollection("tweets");
		collection.find(eq("id", id)).forEach(printBlock);
		return this.tweets.get(0);
	}

	public void updateTweet(Tweet tweet) {
		tweets.remove(tweet);
		tweets.add(tweet);
	}

	public void addTweet(String name, String content, Date targetDate) {
		MongoCollection<Document> collection = database.getCollection("tweets");
		long count = collection.count(new Document()) + 1;
		Document document = new Document("name", name)
               .append("content", content)
               .append("stars", 3)
               .append("id", Long.toString(count));
		collection.insertOne(document);
	}

	public void deleteTweet(int id) {
		MongoCollection<Document> collection = database.getCollection("tweets");
		collection.deleteOne(eq("id", Integer.toString(id)));
	}
}

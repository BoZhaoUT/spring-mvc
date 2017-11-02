package com.kenny.tweet.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kenny.model.Tweet;
import com.kenny.tweet.service.TweetService;

@RestController
public class TweetRestController {
	@Autowired
	private TweetService service;

	/**
	 * Return all tweets in JSON.
	 * @return all tweets in JSON
	 */
	@RequestMapping(value = "/tweets", method = RequestMethod.GET)
	public List<Tweet> listAllTweets() {
		List<Tweet> tweets = (List<Tweet>) service.retrieveAllTweets();
		return tweets;
	}

	/**
	 * Return a specific tweet by id in JSON.
	 * @param id of a tweet
	 * @return a specific tweet by id
	 */
	@RequestMapping(value = "/tweet/{id}", method = RequestMethod.GET)
	public Tweet retrieveTweetById(@PathVariable("id") int id) {
		return service.retrieveTweet(id);
	}
}

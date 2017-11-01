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
public class TodoRestController {
	@Autowired
	private TweetService service;

	@RequestMapping(value = "/tweet/", method = RequestMethod.GET)
	public List<Tweet> listAllTodos() {
		List<Tweet> users = (List<Tweet>) service.retrieveTweet(1);
		return users;
	}

	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public Tweet retrieveTodo(@PathVariable("id") int id) {
		return service.retrieveTweet(id);
	}
}

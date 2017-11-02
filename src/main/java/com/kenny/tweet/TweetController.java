package com.kenny.tweet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kenny.model.Tweet;
import com.kenny.tweet.service.TweetService;

@Controller
public class TweetController {

	@Autowired
	private TweetService service;

	/**
	 * Display all tweets posted by a particular user.
	 */
	@RequestMapping(value = "/list-tweets", method = RequestMethod.GET)
	public String showTweetsList(ModelMap model) {
		String user = getLoggedInUserName();
		model.addAttribute("tweets", service.retrieveTweetsByName(user));
		return "list-tweets";
	}

	/**
	 * Show a page to allow users adding a new tweet.
	 */
	@RequestMapping(value = "/add-tweet", method = RequestMethod.GET)
	public String showAddTweetPage(ModelMap model) {
		model.addAttribute("tweet", new Tweet());
		return "tweet";
	}

	/**
	 * Add a new tweet.
	 */
	@RequestMapping(value = "/add-tweet", method = RequestMethod.POST)
	public String addTweet(ModelMap model, @Valid Tweet tweet, BindingResult result) {

		if (result.hasErrors())
			return "tweet";

		service.addTweet(getLoggedInUserName(), tweet.getContent(),
				tweet.getTargetDate());
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-tweets";
	}

	/**
	 * Get current logged in user's name.
	 * @return user's name from his credentials
	 */
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	/**
	 * Modify a tweet's content.
	 */
	@RequestMapping(value = "/update-tweet", method = RequestMethod.GET)
	public String showUpdateTweetPage(ModelMap model, @RequestParam int id) {
		model.addAttribute("tweet", service.retrieveTweet(id));
		return "tweet";
	}

	/**
	 * Delete a tweet based on its id.
	 * @param id of this tweet
	 */
	@RequestMapping(value = "/delete-tweet", method = RequestMethod.GET)
	public String deleteTweet(@RequestParam int id) {
		String user = getLoggedInUserName();
		service.deleteTweet(id, user);
		return "redirect:/list-tweets";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}


}
/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:25:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T11:45:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T11:47:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T10:47:00Z");

    private static final Tweet tweet1 = new Tweet(1, "jkl", "is it reasonable to talk about religion so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "lmn", "Welcome to lab 5 SC", d2);
    private static final Tweet tweet3 = new Tweet(3, "abc", "can we please pass this test??", d3);
    private static final Tweet tweet4 = new Tweet(4, "abc2", "@qaiser is this another one of these amazing tests?",
            d4);
    private static final Tweet tweet5 = new Tweet(5, "xyz", "@qaiser @alishba welcome back",
            d5);
    private static final Tweet tweet6 = new Tweet(6, "xyz2", "@qaiser @muneeb welcome back have a great time",
            d6);


    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     */
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
       
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<Tweet>());
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    @Test
    public void testInfluencersSixTweets(){
        Map<String,Set<String>> followsGraph= SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1,tweet2,tweet3,tweet4,tweet5,tweet6));
        List<String> influencers= SocialNetwork.influencers(followsGraph);
        assertEquals(influencers.get(0),"qaiser");
                  
        }

    @Test
    public void testInfluencersSingleTweetnoInfluence(){
	        Map<String,Set<String>> followsGraph= SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));
	        List<String> influencers= SocialNetwork.influencers(followsGraph);
	        assertTrue(influencers.isEmpty());    
        }

    @Test
    public void testInfluencersSingleTweetOneInfluence(){
        Map<String,Set<String>> followsGraph= SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4));
        List<String> influencers= SocialNetwork.influencers(followsGraph);
        assertEquals(influencers.get(0),"qaiser");
                  
        }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}

package com.demo.comments.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.comments.models.Comments;
import com.mongodb.MongoClient;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
public class CommentsRepositoryImplTest {
	@Autowired
	private MongoTemplate mongoTemplate;
	Comments commentsToSave = new Comments("", "comment comment", "Anupama", System.currentTimeMillis(), true, false,
			"anupama@gmail.com");

	@Before
	public void setup() throws Exception {
		String ip = "localhost";
		int port = 27017;

		mongoTemplate = new MongoTemplate(new MongoClient(ip, port), "test");
	}

	/* Method to test the DB calls in repository layer */
	@Test
	public void commentsRepositoryLayer_Test() throws Exception {
		// When
		mongoTemplate.save(commentsToSave, "collection");

		// then
		assertThat(mongoTemplate.findAll(Comments.class, "collection"))
		.extracting("commentBy").containsOnly("Anupama");

	}

}

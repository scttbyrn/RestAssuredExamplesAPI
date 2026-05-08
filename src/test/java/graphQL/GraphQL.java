package graphQL;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GraphQL {
	
	@Test
	public void graphQLsample() {
		
		//Mutation:
		Response mutation = given().log().all()
		.header("Content-Type", "application/json")
		.body("{\"query\":\"mutation {\\n  createLocation(location: {name: \\\"scott\\\", type: \\\"PH\\\", dimension: \\\"234\\\"}) {\\n    id\\n  }\\n  \\n  createCharacter(character: {name: \\\"byron\\\", type: \\\"Manila\\\", status: \\\"single\\\", species: \\\"Human\\\", gender: \\\"Male\\\", image: \\\".png\\\", originId: 30820, locationId: 30820} ){\\n    \\n    id\\n  }\\n  \\n  createEpisode(episode: {name: \\\"One Piece\\\", air_date: \\\"01/01/1990\\\", episode: \\\"Egghead Arc\\\"}){\\n    \\n    id\\n  }\\n}\\n\",\"variables\":null}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().prettyPeek();
		
		String createLocation = mutation.jsonPath().getString("data.createLocation.id");
		String createCharacter = mutation.jsonPath().getString("data.createCharacter.id");
		String createEpisode = mutation.jsonPath().getString("data.createEpisode.id");
		
		System.out.println("Created Location ID: " +createLocation);
		System.out.println("Created Character ID: " +createCharacter);
		System.out.println("Created Episode ID: " +createEpisode);
		
		//Query
		Response query = given().log().all()
		.header("Content-Type", "application/json")
		.body("{\"query\":\"query ($locationId: Int!, $characterId: Int!, $episodeId: Int!) {\\n  location(locationId: $locationId) {\\n    name\\n    type\\n  }\\n  \\n  character(characterId: $characterId){\\n    \\n    id\\n    name\\n    species\\n    gender\\n  }\\n  \\n  episode(episodeId: $episodeId){\\n    id\\n    name\\n    episode\\n    \\n  }\\n}\\n\",\"variables\":{\"locationId\":"+createLocation+",\"characterId\":"+createCharacter+",\"episodeId\":"+createEpisode+"}}")
		.when().post("https://rahulshettyacademy.com/gq/graphql")
		.then().extract().response().prettyPeek();
		
		String extractLocation = query.jsonPath().getString("data.location.type");
		String extractCharacter = query.jsonPath().getString("data.character.name");
		String extractEpisode = query.jsonPath().getString("data.episode.name");
		
		System.out.println("Extract Location: " +extractLocation);
		System.out.println("Extract Character: " +extractCharacter);
		System.out.println("Extract Episode: " +extractEpisode);
		
		Assert.assertEquals("PH", extractLocation);
		Assert.assertEquals("byron", extractCharacter);
		Assert.assertEquals("One Piece", extractEpisode);
	}

}

package org.kompiro;

import java.util.Scanner;

import org.junit.Test;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.GithubApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class ScribeGithubApiLerning {
	private static final String NETWORK_NAME = "Github";
	private static final String PROTECTED_RESOURCE_URL = "https://api.github.com/user/watches";
	private static final Token EMPTY_TOKEN = null;

	@Test
	public void learning() throws Exception {
		String apiKey = "c40bf841fb6b3358c444";
		String apiSecret = "89edf5cc64f65ae7dc062d9a036c5b69f5762216";
		OAuthService service = new ServiceBuilder().provider(GithubApi.class)
				.apiKey(apiKey).apiSecret(apiSecret)
				.callback("http://kompiro.org/hoge").debug().build();
		Scanner in = new Scanner(System.in);

		System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
		System.out.println();

		// Obtain the Authorization URL
		System.out.println("Fetching the Authorization URL...");
		String authorizationUrl = service.getAuthorizationUrl(EMPTY_TOKEN);
		System.out.println("Got the Authorization URL!");
		System.out.println("Now go and authorize Scribe here:");
		System.out.println(authorizationUrl);
		System.out.println("And paste the authorization code here");
		System.out.print(">>");
		Verifier verifier = new Verifier(in.nextLine());
		System.out.println();

		// Trade the Request Token and Verfier for the Access Token
		System.out.println("Trading the Request Token for an Access Token...");
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);
		System.out.println("Got the Access Token!");
		System.out.println("(if your curious it looks like this: "
				+ accessToken + " )");
		System.out.println();

		// Now let's go and ask for a protected resource!
		System.out.println("Now we're going to access a protected resource...");
		OAuthRequest request = new OAuthRequest(Verb.GET,
				PROTECTED_RESOURCE_URL);
		service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println("Got it! Lets see what we found...");
		System.out.println();
		System.out.println(response.getCode());
		System.out.println(response.getBody());

		System.out.println();
		System.out
				.println("Thats it man! Go and build something awesome with Scribe! :)");
	}
}

package ohm.softa.a06;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ohm.softa.a06.ICNDBApi;
import ohm.softa.a06.adapters.JokeAdapter;
import ohm.softa.a06.model.Joke;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author Peter Kurfer
 * Created on 11/10/17.
 */
public class App {

	public static void main(String[] args) throws IOException{
		// TODO fetch a random joke and print it to STDOUT
		ICNDBApi icndbApi;
		Joke joke = new Joke();

		final Gson responseGson = new GsonBuilder()
			.registerTypeAdapter(Joke.class, new JokeAdapter())
			.create();

		Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://api.icndb.com/")
			.addConverterFactory(GsonConverterFactory.create(responseGson))
			.build();

		icndbApi = retrofit.create(ICNDBApi.class);

		Call<Joke> call = icndbApi.getRandomJoke();
		Response<Joke> jokeResponse = call.execute();

		joke = jokeResponse.body();
		System.out.println(joke);

	}

}

package ohm.softa.a06.adapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ohm.softa.a06.model.Joke;

import java.io.IOException;

public class JokeAdapter extends TypeAdapter <Joke> {

	//getter und setter für gson

	private final Gson gson;

	public JokeAdapter(){
		gson = new Gson();
	}

	@Override
	public void write(JsonWriter out, Joke value) throws IOException {
	}

	@Override
	public Joke read(JsonReader in) throws IOException {

		Joke result = null;

		/* start to read from JsonReader */
		in.beginObject();

		/* iterate the reader (iterator!) */
		while (in.hasNext()) {

			/* switch-case on String (supported since Java 8) */
			switch (in.nextName()) {
				/* check if request was successfull */
				case "type":
					if (!in.nextString().equals("success")) {
						throw new IOException();
					}
					break;
				/* serialize the inner value simply by calling Gson because we mapped the fields to JSON keys */
				case "value":
					result = gson.fromJson(in, Joke.class);
					break;
			}
		}

		/* required to fix JSON document not fully consumed exception */
		in.endObject();

		return result;
	}

}

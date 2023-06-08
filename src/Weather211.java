import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Weather211 {
	private static HashMap<String, String> cityWeather = new HashMap<>();
	static Date date;
	static SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
	public HashMap<String, String> getCityWeather(){
		return cityWeather;
	}
	public static boolean CityWeather(String cityName) throws Exception{
		boolean validCityName = false;
		try {
			String firstURL = "https://api.openweathermap.org/data/2.5/weather?q=";
			String apiCode = "&appid=fafb0839e16241e0e6e11960bc0ac43a";
			URL url = new URL(firstURL+cityName+apiCode);//creates the url link by using specific city and personal api code.
			
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));//creates buffer reader to read the url
			JSONParser jsonParser = new JSONParser();//initialized jsonparser to parse the buffered reader.
			JSONObject jsonObj = (JSONObject)jsonParser.parse(br);//jsonobj created to read the json file. All data is stored in this object.
			
			JSONArray weatherArray = (JSONArray)jsonObj.get("weather");//creates a json array to read the first index of the given array
			JSONObject w = (JSONObject)weatherArray.get(0);
			
			String weatherNow = (String)w.get("description");//stores the description of the city and adds to hashmap
			cityWeather.put("1. Current Weather", weatherNow);
			
			double cityTemp = (double)((JSONObject)jsonObj.get("main")).get("temp");//stores the general city temperature and adds to hashmap
			cityTemp = ((cityTemp -273.15)*9)/5+32;
			cityWeather.put("2. Current Temperature",(String.format("%.1f", cityTemp)+"\u00B0"+"F"));
			
			double cityTempMin = (double)((JSONObject)jsonObj.get("main")).get("temp_min");//stores the min city temperature and adds to hashmap
			cityTempMin = ((cityTempMin-273.15)*9)/5+32;
			cityWeather.put("3. Low Temperature", (String.format("%.1f",cityTempMin)+"\u00B0"+"F"));
			
			double cityTempMax = (double)((JSONObject)jsonObj.get("main")).get("temp_max");//stores the max city temp and adds to hashmap
			cityTempMax = ((cityTempMax-273.15)*9)/5+32;
			cityWeather.put("4. Max Temperature", (String.format("%.1f", cityTempMax)+"\u00B0"+"F"));
			
			long cityHumidity = (long)((JSONObject)jsonObj.get("main")).get("humidity");//stores the humidity of the city and adds to hashmap
			cityWeather.put("5. Humidity", Long.toString(cityHumidity)+"%");
			
			double cityWindSpeed = (double)((JSONObject)jsonObj.get("wind")).get("speed");//stores the city wind speed and adds to hashmap
			cityWeather.put("6. Wind", Double.toString(cityWindSpeed)+" meter/sec");
			
			long citySunrise = (long)((JSONObject)jsonObj.get("sys")).get("sunrise");//stores when the city sunrise and adds to hashmap
			date = new Date(citySunrise*1000L);
			cityWeather.put("7. Sunrise", dateFormater.format(date));
			
			long citySunset = (long)((JSONObject)jsonObj.get("sys")).get("sunset");//stores when the city setset and adds to hashmap
			date = new Date(citySunset*1000L);
			cityWeather.put("8. Sunset", dateFormater.format(date));
			
			validCityName = true;//if all code above ran, it will set the validCityname to true. 
		}catch(Exception e) {
			
		}
		return validCityName;
	}
}

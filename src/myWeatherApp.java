import java.util.*;
public class myWeatherApp {
	static Scanner console = new Scanner(System.in);
	static HashMap<String, String> weatherNow = new HashMap<>();
	
	static HashMap<String, String> weather1;
	static HashMap<String, String> weather2;
	
	static Weather211 temp;
	
	public static void main(String[] args) throws Exception{
		System.out.println("Welcome to Weather 211");
		while(true) {
			System.out.print("Press 1 check a single city weather, Press 2 compare two cities weather: ");
			int input = console.nextInt();
			if(input == 1) {
				inputCityName();
				printWeatherData();
			}else if(input ==2) {
				input2CityName();
				compareTwoCities();
			}else {
				System.out.println("please enter a number.");
			}
		}
	}
	public static void inputCityName() throws Exception{
		boolean validCityName = false;
		while(!validCityName) {
			System.out.print("Enter a city name: ");
			boolean valid = Weather211.CityWeather(console.next());
			if(valid) {
				break;
			}else {
				System.out.println("Invalid city name. Type again.\n");
			}
		}
	}
	public static void input2CityName() throws Exception{
		boolean validCityName = false;
		while(!validCityName) {
			System.out.print("Enter first city name: ");
			boolean valid = Weather211.CityWeather(console.next());
			if(valid) {
				weather1 = getData();
//				printWeatherData();
				while(!validCityName) {
					System.out.print("Enter second city name: ");
					boolean valid2 = Weather211.CityWeather(console.next());
					if(valid2) {
						weather2 = getData();
						//printWeatherData();
						break;
					}else {
						System.out.println("Invalid city2, please try again.");
					}
				}
				break;
			}else {
				System.out.println("Invalid city1, please try again.");
			}
		}
	}
	public static void compareTwoCities() {
		double temp = Math.max(Double.parseDouble(weather1.get("2. Current Temperature")), Double.parseDouble(weather2.get("2. Current Temperature")));
		if(temp == Double.parseDouble(weather1.get("2. Current Temperature"))){
			System.out.println(weather1.get("9. Name") + " has a greater temperature of " + (Double.parseDouble(weather1.get("2. Current Temperature"))-Double.parseDouble(weather2.get("2. Current Temperature"))));
		}
	}
	public static HashMap<String, String> getData() {
		Weather211 tempWeath = new Weather211();
		return tempWeath.getCityWeather();
	}
	public static void printWeatherData() {
		Weather211 cityWeath = new Weather211();
		weatherNow.putAll(cityWeath.getCityWeather());
		ArrayList<String> keys = new ArrayList<String>(weatherNow.keySet());
		Collections.sort(keys);
		 for (String key : keys) { 
		    System.out.println(key + ": "+weatherNow.get(key));
		 } 
	}
}


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class AlperenKoker_AhmetTurgut {

	static int length1 = 0;

	static class City {

		double x, y;
		int id;
		boolean visited;

		public City(int id, double x, double y) {
			visited = false;
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);
		System.out.println("Please enter file name [with extension] : ");

		String fileName = input.nextLine();

		input.close();

		File file = new File(fileName);

		BufferedReader buff = new BufferedReader(new FileReader(file)); // Read file

		String control;

		ArrayList<City> cities = new ArrayList<City>();

		while ((control = buff.readLine()) != null) {

			String[] splitStr = control.split("\\s+");

			City newCity = new City(Integer.parseInt(splitStr[0]), Double.parseDouble(splitStr[1]),
					Double.parseDouble(splitStr[2]));

			cities.add(newCity);
		}

		buff.close();

		double xy[] = findOrigin(cities);

		for (City c : cities) { // orjine göre koordinatlar düzenlendi!!!
			c.x = c.x - xy[0];
			c.y = c.y - xy[1];
		}

		ArrayList<City> area1 = new ArrayList<City>();
		ArrayList<City> area2 = new ArrayList<City>();
		ArrayList<City> area3 = new ArrayList<City>();
		ArrayList<City> area4 = new ArrayList<City>();
		ArrayList<City> area5 = new ArrayList<City>();
		ArrayList<City> area6 = new ArrayList<City>();
		ArrayList<City> area7 = new ArrayList<City>();
		ArrayList<City> area8 = new ArrayList<City>();

		int starterId[] = { -1, -1, -1, -1, -1, -1, -1, -1 };
		int finishId[] = { -1, -1, -1, -1, -1, -1, -1, -1 };
		double sTan[] = { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
				Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE };
		double fTan[] = { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
				Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };

		for (City c : cities) {

			double tan = c.y / c.x;

			if (c.x >= 0 && tan > 0 && tan < 1) {
				addCityANDFindSF(c, area1, tan, 1, sTan, fTan, starterId, finishId);
			} else if (c.x > 0 && tan >= 1) {
				addCityANDFindSF(c, area2, tan, 2, sTan, fTan, starterId, finishId);
			} else if (c.x <= 0 && tan < -1) {
				addCityANDFindSF(c, area3, tan, 3, sTan, fTan, starterId, finishId);
			} else if (c.x < 0 && tan >= -1 && tan < 0) {
				addCityANDFindSF(c, area4, tan, 4, sTan, fTan, starterId, finishId);
			} else if (c.x < 0 && tan >= 0 && tan < 1) {
				addCityANDFindSF(c, area5, tan, 5, sTan, fTan, starterId, finishId);
			} else if (c.x < 0 && tan >= 1) {
				addCityANDFindSF(c, area6, tan, 6, sTan, fTan, starterId, finishId);
			} else if (c.x >= 0 && tan < -1) {
				addCityANDFindSF(c, area7, tan, 7, sTan, fTan, starterId, finishId);
			} else if (c.x > 0 && tan >= -1 && tan < 0) {
				addCityANDFindSF(c, area8, tan, 8, sTan, fTan, starterId, finishId);
			}
		}

		if (starterId[0] != -1) {
			cities.get(starterId[0]).visited = true;
		}
		if (finishId[0] != -1) {
			cities.get(finishId[0]).visited = true;
		}
		if (starterId[1] != -1) {
			cities.get(starterId[1]).visited = true;
		}
		if (finishId[1] != -1) {
			cities.get(finishId[1]).visited = true;
		}
		if (starterId[2] != -1) {
			cities.get(starterId[2]).visited = true;
		}
		if (finishId[2] != -1) {
			cities.get(finishId[2]).visited = true;
		}
		if (starterId[3] != -1) {
			cities.get(starterId[3]).visited = true;
		}
		if (finishId[3] != -1) {
			cities.get(finishId[3]).visited = true;
		}
		if (starterId[4] != -1) {
			cities.get(starterId[4]).visited = true;
		}
		if (finishId[4] != -1) {
			cities.get(finishId[4]).visited = true;
		}
		if (starterId[5] != -1) {
			cities.get(starterId[5]).visited = true;
		}
		if (finishId[5] != -1) {
			cities.get(finishId[5]).visited = true;
		}
		if (starterId[6] != -1) {
			cities.get(starterId[6]).visited = true;
		}
		if (finishId[6] != -1) {
			cities.get(finishId[6]).visited = true;
		}
		if (starterId[7] != -1) {
			cities.get(starterId[7]).visited = true;
		}
		if (finishId[7] != -1) {
			cities.get(finishId[7]).visited = true;
		}

		ArrayList<City> path1 = new ArrayList<City>();
		ArrayList<City> path2 = new ArrayList<City>();
		ArrayList<City> path3 = new ArrayList<City>();
		ArrayList<City> path4 = new ArrayList<City>();
		ArrayList<City> path5 = new ArrayList<City>();
		ArrayList<City> path6 = new ArrayList<City>();
		ArrayList<City> path7 = new ArrayList<City>();
		ArrayList<City> path8 = new ArrayList<City>();

		if (area1.size() > 0) {
			path1.add(cities.get(starterId[0]));
			path1 = findPathArea(area1, cities.get(starterId[0]), cities.get(finishId[0]), path1);
			length1 += findLength(cities.get(finishId[0]), path1.get(path1.size() - 1));
			path1.add(cities.get(finishId[0]));
		}
		if (area2.size() > 0) {
			path2.add(cities.get(starterId[1]));
			path2 = findPathArea(area2, cities.get(starterId[1]), cities.get(finishId[1]), path2);
			length1 += findLength(cities.get(finishId[1]), path2.get(path2.size() - 1));
			path2.add(cities.get(finishId[1]));
		}
		if (area3.size() > 0) {
			path3.add(cities.get(starterId[2]));
			path3 = findPathArea(area3, cities.get(starterId[2]), cities.get(finishId[2]), path3);
			length1 += findLength(cities.get(finishId[2]), path3.get(path3.size() - 1));
			path3.add(cities.get(finishId[2]));
		}
		if (area4.size() > 0) {
			path4.add(cities.get(starterId[3]));
			path4 = findPathArea(area4, cities.get(starterId[3]), cities.get(finishId[3]), path4);
			length1 += findLength(cities.get(finishId[3]), path4.get(path4.size() - 1));
			path4.add(cities.get(finishId[3]));

		}
		if (area5.size() > 0) {
			path5.add(cities.get(starterId[4]));
			path5 = findPathArea(area5, cities.get(starterId[4]), cities.get(finishId[4]), path5);
			length1 += findLength(cities.get(finishId[4]), path5.get(path5.size() - 1));
			path5.add(cities.get(finishId[4]));
		}
		if (area6.size() > 0) {
			path6.add(cities.get(starterId[5]));
			path6 = findPathArea(area6, cities.get(starterId[5]), cities.get(finishId[5]), path6);
			length1 += findLength(cities.get(finishId[5]), path6.get(path6.size() - 1));
			path6.add(cities.get(finishId[5]));
		}
		if (area7.size() > 0) {
			path7.add(cities.get(starterId[6]));
			path7 = findPathArea(area7, cities.get(starterId[6]), cities.get(finishId[6]), path7);
			length1 += findLength(cities.get(finishId[6]), path7.get(path7.size() - 1));
			path7.add(cities.get(finishId[6]));
		}
		if (area8.size() > 0) {
			path8.add(cities.get(starterId[7]));
			path8 = findPathArea(area8, cities.get(starterId[7]), cities.get(finishId[7]), path8);
			length1 += findLength(cities.get(finishId[7]), path8.get(path8.size() - 1));
			path8.add(cities.get(finishId[7]));
		}

		City lastCity = null;
		City starter = null;
		if (path1.size() > 0) {
			starter = path1.get(0);
			lastCity = path1.get(path1.size() - 1);
		}
		if (path2.size() > 0) {
			if (starter == null) {
				starter = path2.get(0);
			}
			length1 += findLength(path2.get(0), lastCity);
			lastCity = path2.get(path2.size() - 1);
		}
		if (path3.size() > 0) {
			if (starter == null) {
				starter = path3.get(0);
			}
			length1 += findLength(path3.get(0), lastCity);
			lastCity = path3.get(path3.size() - 1);
		}
		if (path4.size() > 0) {
			if (starter == null) {
				starter = path4.get(0);
			}
			length1 += findLength(path4.get(0), lastCity);
			lastCity = path4.get(path4.size() - 1);
		}
		if (path5.size() > 0) {
			if (starter == null) {
				starter = path5.get(0);
			}
			length1 += findLength(path5.get(0), lastCity);
			lastCity = path5.get(path5.size() - 1);
		}
		if (path6.size() > 0) {
			if (starter == null) {
				starter = path6.get(0);
			}
			length1 += findLength(path6.get(0), lastCity);
			lastCity = path6.get(path6.size() - 1);
		}
		if (path7.size() > 0) {
			if (starter == null) {
				starter = path7.get(0);
			}
			length1 += findLength(path7.get(0), lastCity);
			lastCity = path7.get(path7.size() - 1);
		}
		if (path8.size() > 0) {
			if (starter == null) {
				starter = path8.get(0);
			}
			length1 += findLength(path8.get(0), lastCity);
			lastCity = path8.get(path8.size() - 1);
		}

		length1 += findLength(starter, lastCity);
		System.out.println(length1);
		PrintWriter pr = new PrintWriter("output." + fileName);
		pr.write(String.valueOf(length1) + "\n");

		if (path1.size() > 0) {
			for (City c : path1) {
				pr.write(String.valueOf(c.id) + "\n");
				if (area1.size() == 1) {
					break;
				}
			}
		}
		if (path2.size() > 0) {
			for (City c : path2) {
				pr.write(String.valueOf(c.id + "\n"));
				if (area2.size() == 1) {
					break;
				}
			}
		}
		if (path3.size() > 0) {
			for (City c : path3) {
				pr.write(String.valueOf(c.id + "\n"));
				if (area3.size() == 1) {
					break;
				}
			}
		}
		if (path4.size() > 0) {
			for (City c : path4) {
				pr.write(String.valueOf(c.id + "\n"));
				if (area4.size() == 1) {
					break;
				}
			}
		}
		if (path5.size() > 0) {
			for (City c : path5) {
				pr.write(String.valueOf(c.id + "\n"));
				if (area5.size() == 1) {
					break;
				}
			}
		}
		if (path6.size() > 0) {
			for (City c : path6) {
				pr.write(String.valueOf(c.id + "\n"));
				if (area6.size() == 1) {
					break;
				}
			}
		}
		if (path7.size() > 0) {
			for (City c : path7) {
				pr.write(String.valueOf(c.id + "\n"));
				if (area7.size() == 1) {
					break;
				}
			}
		}
		if (path8.size() > 0) {
			for (City c : path8) {
				pr.write(String.valueOf(c.id + "\n"));
				if (area8.size() == 1) {
					break;
				}
			}
		}

		pr.close();

	}

	public static ArrayList<City> findPathArea(ArrayList<City> areaCities, City starter, City finish,
			ArrayList<City> path) {

		int length = -1;
		City addCity = null;
		City c1 = path.get(path.size() - 1);

		for (City c2 : areaCities) {
			if (c2.visited == false) {
				if (length == -1) {
					length = findLength(c1, c2);
					addCity = c2;
				} else if (length > findLength(c1, c2)) {
					length = findLength(c1, c2);
					addCity = c2;
				}
			}
		}

		if (addCity != null) {
			addCity.visited = true;
			path.add(addCity);
			length1 = length1 + length; // burayý kontrol et
			path = findPathArea(areaCities, starter, finish, path);

		}

		return path;
	}

	public static void addCityANDFindSF(City c, ArrayList<City> area, double tan, int areaId, double[] sTan,
			double[] fTan, int[] starterId, int[] finishId) {
		if (tan < sTan[areaId - 1]) {
			sTan[areaId - 1] = tan;
			starterId[areaId - 1] = c.id;
		}
		if (tan > fTan[areaId - 1]) {
			fTan[areaId - 1] = tan;
			finishId[areaId - 1] = c.id;
		}

		area.add(c);
		if (area.size() == 1) {
			sTan[areaId - 1] = tan;
			fTan[areaId - 1] = tan;
		}
	}

	public static double[] findOrigin(ArrayList<City> cities) {

		double x = 0, y = 0;
		for (City c : cities) {
			x = x + c.x;
			y = y + c.y;
		}

		x = x / cities.size();
		y = y / cities.size();

		double[] xy = new double[2];

		xy[0] = x;
		xy[1] = y;

		return xy;
	}

	public static int findLength(City c1, City c2) {

		int distance = (int) Math.round(Math.sqrt((Math.pow(c1.x - c2.x, 2)) + (Math.pow(c1.y - c2.y, 2))));
		return distance;
	}

}

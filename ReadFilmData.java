package CA;
import java.io.File;
import java.util.*;

public class ReadFilmData {
	
	//Made it global variable to have access in the addnewFilm method
	static Film[] films = new Film[10000];

	public static void main(String[] args) throws Exception{
		//parsing and reading the CSV file data into the film (object) array
		// provide the path here...
        File directory = new File("C:\\Code\\Algorithms\\src\\CA");
  		String name = directory.getAbsolutePath() + "//Film.csv";
		Scanner sc = new Scanner(new File(name));
		
		
		// this will just print the header in CSV file
		sc.nextLine();

		int i = 0; String st = "";
		while (sc.hasNextLine())  //returns a boolean value
		{
			st = sc.nextLine();
			String[] data = st.split(",");
			films[i++] = new Film(Integer.parseInt(data[0]), data[1], data[2], data[3], Float.parseFloat(data[4]), Float.parseFloat(data[5]));
		}
		sc.close();  //closes the scanner
		
		//Testing HighRatedInLimit(float limit)
		//Film[] see = HighRatedInLimit((float)5);
		//System.out.println("The movies in the limit we asked: 5");
		//print(see);
		
		//Movie adding test
		//addnewFilm("Spiderman", "Fiction", "Quantin Tarantino", (float)3.19, (float)3.19);
		//addnewFilm("Batman", "Fiction", "Quantin Tarantino", (float)-5, (float)55);
		//System.out.println(films[10001]);
		
		/*Film[] films10 = createNew(films, 10);
		Film[] films100 = createNew(films, 100);
		Film[] films1000 = createNew(films, 1000);
		Film[] films5000 = createNew(films, 5000);
		*/
		
		//Passing the array and copy arrays to mergeSort to see the time complexity
		//double current = System.currentTimeMillis();/*
		/*mergeSort(films10);
		System.out.println("\n"+"MergeSort 10 data: " + (System.currentTimeMillis()-current));
		
		current = System.currentTimeMillis();
		mergeSort(films100);
		System.out.println("MergeSort 100 data: " + (System.currentTimeMillis()-current));
		
		current = System.currentTimeMillis();
		mergeSort(films1000);
		System.out.println("MergeSort 1000 data: " + (System.currentTimeMillis()-current));
		
		current = System.currentTimeMillis();
		mergeSort(films5000);
		System.out.println("MergeSort 5000 data: " + (System.currentTimeMillis()-current));
		*/
		//current = System.currentTimeMillis();
		mergeSort(films);
		//System.out.println("MergeSort 10000 data: " + (System.currentTimeMillis()-current));
		//System.out.println(films[10001]);
		
		
		print(films);
		/*System.out.println("\nfilms10");
		print(films10);
		System.out.println("\nfilms100");
		print(films100, 10);
		System.out.println("\nfilms1000");
		print(films1000, 10);
		System.out.println("\nfilms5000");
		print(films5000, 10);
		System.out.println("\nfilms10000");
		print(films, 10);
		System.out.println("\n"+"should be true " + (films[0]==films10[0])); //check this out*/
		
		//System.out.println("\n"+binarySearch(films, (float) 1.5));
		
		//System.out.println("\n" + binarySearch(films, (float)1.5));
		/*current = System.currentTimeMillis();
		String test10000 = binarySearch(films, (float)3.19);
		System.out.println("BinarySearch with 10000 data: " +(System.currentTimeMillis()-current));
		
		current = System.currentTimeMillis();
		String test5000 = binarySearch(films5000, (float)3.19);
		System.out.println("BinarySearch with 5000 data: " +(System.currentTimeMillis()-current));
		
		current = System.currentTimeMillis();
		String test1000 = binarySearch(films1000, (float)3.19);
		System.out.println(test1000);
		System.out.println("BinarySearch with 1000 data: " +(System.currentTimeMillis()-current));
		
		current = System.currentTimeMillis();
		String test100 = binarySearch(films100, (float)3.19);
		System.out.println("BinarySearch with 100 data: " +(System.currentTimeMillis()-current));
		
		current = System.currentTimeMillis();
		String test10 = binarySearch(films10, (float)3.19);
		System.out.println("BinarySearch with 10 data: " +(System.currentTimeMillis()-current));*/
	}
	
	//MergeSort Algorithm
	//I used the bold youtuber guy to get inspiration
	public static void mergeSort(Film[] arr) {
		int length = arr.length;
		if(length<=1) return;
		int mid = length / 2;
		
		Film [] leftArr = new Film[mid];
		Film [] rightArr = new Film[length-mid];
		
		int i =0;
		int j=0;
		for(; i<length; i++) {
			if(i<mid) {
				leftArr[i]=arr[i];
			}else {
				rightArr[j] = arr[i];
				j++;
			}
		}
		mergeSort(leftArr);
		mergeSort(rightArr);
		merge(leftArr, rightArr, arr);
	}

	private static void merge(Film[] left, Film[] right, Film[] actual) {
		int leftSize = actual.length/2;
		int rightSize = actual.length-leftSize;
		int i =0, l=0, r=0;
		while(l<leftSize && r<rightSize) {
			if(left[l].compareTo(right[r])<0) {
				actual[i]=left[l];
				i++; l++;
			}else if(left[l].compareTo(right[r])==0) {
				if(left[l].c1compareTo(right[r])<0) { //if right ID is bigger than left
					actual[i] = left[l];
					i++; l++;
				}
				else {
					actual[i] = right[r];
					i++; r++;
				}
			}
			else {
				actual[i]=right[r];
				i++; r++;
			}
		}
		while(l<leftSize) {
			actual[i]=left[l];
			i++; l++;
		}
		while(r<rightSize) {
			actual[i]=right[r];
			i++; r++;
		}
	}

	//In order to create new arrays of the exist array for seeing the time complexity
	public static Film[] createNew(Film[] arr, int n) {
		Film[] result = new Film[n];
		for(int i=0; i<n && i<arr.length; i++) {
			result[i] = arr[i];
		}
		if(n>arr.length) { //if the new array length bigger than existing array 
			for(int i=arr.length; i<n; i++) {
				result[i]=null; //Set the uninitialized elements to null
			}
		}
		return result;
	}
	
	//Print method only for testing
	public static void print(Film[] arr) {
		for(Film aa: arr) {
			System.out.println(aa);
		}
	}
	
	public static void print(Film[] arr, int quantity) {
		for(int i=0; i<quantity; i++) {
			System.out.println(arr[i]);
		}
	}
	
	/*Searches the target length in the film array and prints outs all the matching results*/
	public static String binarySearch(Film[] arr, float targetLength) {
		int upperB = arr.length-1;
		int lowerB = 0;
		int[] indexes = new int[40];
		int count = 0;
		while(lowerB <= upperB) {
			int mid = (upperB + lowerB) / 2;
			if((arr[mid].compareTo(targetLength))==0) {
				//System.out.println("test");
				indexes[count] = mid;
				count++;
				break;
			}else if(arr[mid].compareTo(targetLength)>1) {
				upperB = mid - 1;
			}else {
				lowerB = mid + 1;
			}
		}
		if(count>0) {
			String out="\n Indexes that have the same " + targetLength + ": \n";
			int leftIndex = indexes[0] - 1;
			while(leftIndex >= 0 && (arr[leftIndex].compareTo(targetLength))==0) {
				indexes[count] = leftIndex;
				count++;
				leftIndex--;
			}
			int rightIndex = indexes[0] + 1;
			while(rightIndex < arr.length && (arr[rightIndex].compareTo(targetLength))==0) {
				indexes[count] = rightIndex;
				count++;
				rightIndex++;
			}
			/*for(int a : indexes) {
				out += arr[a].toString()+"\n";
			}*/
			//Iterating only how many times we found the matching length
			for(int j = 0; j < count; j++) {
				out += arr[indexes[j]].toString()+"\n";
			}
			return out;
		}else {
			return "Not an existing " + targetLength + "!";
		}
	}

	/*Adding new movie to array, incrementing size +1 to have space for new movie*/
	public static void addnewFilm(String title, String genre, String directorName, float length, float rating) {

		try {
			if(length<0) {
				throw new IncorrectLengthException(title + " length can't be less than zero");
			}
			if(length==0) {
				throw new IncorrectLengthException(title + " length can't be 0");
			}
			films = createNew(films, films.length+1);
			films[films.length-1] = new Film(films.length, genre, directorName, title, (float)length, (float)rating);	
			System.out.println("The movie: " + title + " succesfully has been saved.");
		}catch(IncorrectLengthException e) {
			System.out.println(e);
		}
	}
	
	/*Finds the movies with the highest rate within the sum of the lengths in limit
	 * Calls the quick sort the array with high rate based*/
	public static Film[] HighRatedInLimit(float limit) {
		//Film [] ranked = new Film[15];
		int n = films.length;
		quickSort(films, 0, n-1); //Calling the quick sort with starting and end index
		int count = discountLength(limit);
		Film[] ranked = new Film[count]; //Declaring new Film array with the number indicates how many high rated movie length sum could be in the limit
		//print(films);
		ranked = copy(films, count);
		return ranked;
	}
	
	//to copy the sorted array to new array
	private static Film[] copy(Film[] flms, int count) {
		int lastIndex = flms.length-1;
		//System.out.println(lastIndex);
		Film[] high = new Film[count];
		int j = 0; //index number for high
		//Going from last to last-count
		for(int i=lastIndex; i>(lastIndex-count); i--) {
			high[j] = flms[i];
			j++;
		}
		//print(high);
		return high;
		
	}

	public static void quickSort(Film[] arr, int low, int high) {
		if(low<high) {
			//pi is the pivot to divide the array
			int pi = partition(arr, low, high);
			
			//Sort the elements before and after partition separately
			quickSort(arr, low, pi-1);
			quickSort(arr, pi+1, high);
		}
	}
	public static int partition(Film[] arr, int low, int high) {
		//pivot will be based on the the last element
		float pivot = (float)arr[high].ratio();
		//Smaller element and also indicates the right position of pivot after swaps
		int i = (low-1);
		
		//Iterating from left to right until pivot
		for(int j=low; j<=high-1; j++) {
			//if current ratio smaller than the pivot ratio
			if((float)arr[j].ratio()<pivot) {
				//increment index of smaller element
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i+1, high); //last thing update pivot location to have
		//elements greater than pivot on the right hand and smaller to it's left hand
		return (i+1);
	}

	//To swap given indexes two array elements
	private static void swap(Film[] arr, int i, int j) {
		Film temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/*Helper method to see how many movies in sorted movies could be discounted from limit*/
	public static int discountLength(float lim) {
		int lastIndex = films.length-1;
		int count=0;
		while(lim>0) {
			lim = lim-(float)(films[lastIndex].getLength());
			lastIndex--;
			count++;
		}
		if(lim<0) {
			count--;
		}
		return count;
	}

	
}

class Film implements Comparable<Object>{

	private int filmID;
	private String title;
	private String genre;
	private String directorName;
	private float length;
	private float rating;


	// constructor
	public Film(int filmID, String genre, String directorName, String title, float length, float rating) {
		super();
		this.filmID = filmID;
		this.title = title;
		this.genre = genre;
		this.directorName = directorName;
		this.length = length;
		this.rating = rating;
	}

	// setters and getters
	public int getFilmID() {
		return filmID;
	}

	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}


	// so the film objects can be compared when sorting/searching
	// NOTE: this will only allow comparisons based on the title of the film
	@Override
	public int compareTo(Object obj) {

		/*
		Edit this section so it compares the appropriate
		column you wish to sort by
		*/
		Film flm = (Film) obj;
		float left = this.length;
		float right = flm.getLength();
		if(left-right<0) {
			return -1;
		}
		else if(left-right==0) {
			return 0;
		}
		else {
			return 1;
		}
		
	}
	/*Overloading the method in order to use in binarySearch*/
	@Overload
	public int compareTo(Float lngth) {
		
		float left = this.length;
		if(Float.compare(left, lngth)==0) {
			return 0;
		}
		else if(Float.compare(left, lngth)<0) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	//If the two film has the same length compare according to ID
	public int c1compareTo(Object obj) {
		
		Film flm = (Film) obj; //Casting to film object
		int left = this.filmID;
		int right = flm.getFilmID();
		if(left-right<0) {
			return -1;
		}else {
			return 1;
		}
	}
	
	/*Part 3*/
	/*To find the ratio of the movie*/
	public double ratio() {
		return (float)rating/length;
	}

	@Override
	public String toString() {
		return "Film [filmID=" + filmID + ", title=" + title + ", genre=" + genre+ ", director name="
				+ directorName +  ", length=" + length + ", rating="
				+ rating  + "]";
	}


}

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Program randomly selects courses and places them in an ArrayList, then uses
 * recursion to make sure courses are taken at the right time and meeting
 * requirements.
 * 
 * @author Paty Vang
 *
 */
public class CSDegree {
	Course course120 = new Course(120);
	Course course210 = new Course(210);
	Course course215 = new Course(215);
	Course course240 = new Course(240);
	Course course311 = new Course(311);
	Course course340 = new Course(340);
	Course course365 = new Course(365);
	Course course372 = new Course(372);
	Course course440 = new Course(440);
	Course course499 = new Course(499);
	Course course492 = new Course(492);
	Course course490 = new Course(490);
	Course course460 = new Course(460);
	Course course462 = new Course(462);
	Course course998 = new Course(998);
	Course course999 = new Course(999);
	Course course0 = new Course(" ");
	Course course00 = new Course(" ");
	Course course140 = new Course(140);
	Course course232 = new Course(232);
	Course course141 = new Course(141);
	static int[] domain1 = { 0, 1, 2 };
	static int[] domain2 = { 3, 4, 5 };
	static int[] domain3 = { 3, 4, 5, 6, 7, 8 };
	static int[] domain4 = { 0, 1, 2, 3, 4, 5 };
	static int[] domain5 = { 6, 7, 8 };
	static int[] domain6 = { 9, 10, 11 };
	static int[] domain7 = { 12, 13, 14, 15, 16, 17 };
	static int[] domain8 = { 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	static int[] domain9 = { 15, 16, 17, 18, 19, 20 };
	static int[] domain10 = { 20 };
	static int[] domain11 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
	static int[] domain12 = { 0, 1, 2, 3, 4, 5 };
	ArrayList<Course> courses = new ArrayList<>();
	static ArrayList<Course> finalSchedule = new ArrayList<>();;
	static PrintWriter writer;

	public CSDegree() {
		course120.setDomain(domain1);
		course210.setDomain(domain3);
		course00.setDomain(domain11);
		course215.setDomain(domain2);
		course140.setDomain(domain1);
		course141.setDomain(domain2);
		course232.setDomain(domain5);
		course240.setDomain(domain6);
		course311.setDomain(domain8);
		course340.setDomain(domain7);
		course365.setDomain(domain8);
		course372.setDomain(domain7);
		course440.setDomain(domain9);
		course499.setDomain(domain10);
		course492.setDomain(domain10);
		course490.setDomain(domain9);
		course460.setDomain(domain8);
		course462.setDomain(domain8);
		course998.setDomain(domain11);
		course999.setDomain(domain11);
		course0.setDomain(domain11);
		courses.addAll(Arrays.asList(course120, course210, course215, course140, course141, course232, course240,
				course311, course340, course365, course372, course440, course499, course492, course490, course460,
				course462, course998, course999, course0, course00));
	}

	/**
	 * Main method to start program. Gives user option to choose what kind of
	 * printout they'd like to see. Also creates text file called, "Verbose.txt"
	 * to store data table.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		CSDegree cs = new CSDegree();
		writer = new PrintWriter("Verbose.txt");
		cs.startFile();
		cs.localSearch();

		System.out.println("Enter 1 to print out by Summary");
		System.out.println("Enter 2 to print out Verbose");
		Scanner in = new Scanner(System.in);
		int printStyle = in.nextInt();

		if (printStyle == 1) {
			System.out.println("For verbose table view, check Verbose.txt file.");
			System.out.println("Summer: " + finalSchedule.get(0).getCourseNum() + " "
					+ finalSchedule.get(1).getCourseNum() + " " + finalSchedule.get(2).getCourseNum());
			System.out.println("Autumn: " + finalSchedule.get(3).getCourseNum() + " "
					+ finalSchedule.get(4).getCourseNum() + " " + finalSchedule.get(5).getCourseNum());
			System.out.println("Spring: " + finalSchedule.get(6).getCourseNum() + " "
					+ finalSchedule.get(7).getCourseNum() + " " + finalSchedule.get(8).getCourseNum());
			System.out.println("Summer: " + finalSchedule.get(9).getCourseNum() + " "
					+ finalSchedule.get(10).getCourseNum() + " " + finalSchedule.get(11).getCourseNum());
			System.out.println("Autumn: " + finalSchedule.get(12).getCourseNum() + " "
					+ finalSchedule.get(13).getCourseNum() + " " + finalSchedule.get(14).getCourseNum());
			System.out.println("Spring: " + finalSchedule.get(15).getCourseNum() + " "
					+ finalSchedule.get(16).getCourseNum() + " " + finalSchedule.get(17).getCourseNum());
			System.out.println("Summer: " + finalSchedule.get(18).getCourseNum() + " "
					+ finalSchedule.get(19).getCourseNum() + " " + finalSchedule.get(20).getCourseNum());
		}
		if (printStyle == 2) {
			Collections.sort(finalSchedule);
			System.out.println("This is the final summary of Verbose table row. For complete iterations, check Verbose.txt file.");
			System.out.println("120  " + "140   " + "141   " + "210  " + "215  " + "232  " + "240  " + "311  " + "340  " + "365  "
					+ "372  " + "440  " + "460  " + "462  " + "490  " + "492  " + "499  " + "998  " + "999  ");
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println(" " + finalSchedule.get(2).getViolation() + "    " + finalSchedule.get(3).getViolation()
					+ "    " + finalSchedule.get(4).getViolation() + "    " + finalSchedule.get(5).getViolation() + "    "
					+ finalSchedule.get(6).getViolation() + "    " + finalSchedule.get(7).getViolation() + "    "
					+ finalSchedule.get(8).getViolation() + "    " + finalSchedule.get(9).getViolation() + "    "
					+ finalSchedule.get(10).getViolation() + "    " + finalSchedule.get(11).getViolation() + "    "
					+ finalSchedule.get(12).getViolation() + "    " + finalSchedule.get(13).getViolation() + "    "
					+ finalSchedule.get(14).getViolation() + "    " + finalSchedule.get(15).getViolation() + "    "
					+ finalSchedule.get(16).getViolation() + "    " + finalSchedule.get(17).getViolation() + "    "
					+ finalSchedule.get(18).getViolation() + "    " + finalSchedule.get(19).getViolation() + "    "
					+ finalSchedule.get(20).getViolation());
		}
		in.close();
		writer.close();
	}

	/**
	 * Checks to see if the list meets the constraint requirements of the course
	 * orders.
	 * 
	 * @param ArrayList<Course>
	 * @return True if meets prerequisite requirements, else false.
	 */
	public boolean meetsConstraints(ArrayList<Course> c) {
		if (c.indexOf(course120) + 2 < c.indexOf(course210) && c.indexOf(course120) + 2 < c.indexOf(course215)
				&& c.indexOf(course120) < c.indexOf(course140) && c.indexOf(course215) < c.indexOf(course141)
				&& c.indexOf(course140) + 2 < c.indexOf(course141) && c.indexOf(course141) + 2 < c.indexOf(course232)
				&& c.indexOf(course141) + 2 < c.indexOf(course240) && c.indexOf(course141) + 2 < c.indexOf(course311)
				&& c.indexOf(course240) + 2 < c.indexOf(course311) && c.indexOf(course240) + 2 < c.indexOf(course340)
				&& c.indexOf(course240) + 2 < c.indexOf(course365) && c.indexOf(course240) + 2 < c.indexOf(course372)
				&& c.indexOf(course340) + 2 < c.indexOf(course440) && c.indexOf(course372) + 2 < c.indexOf(course499)) {
			return true;
		} else
			return false;
	}

	/**
	 * Checks to see if the courses are taken in the right order, and if not,
	 * then adds penalty to the latter course.
	 * 
	 * @param ArrayList
	 *            of course.
	 */
	public void setViolations(ArrayList<Course> c) {
		c.get(c.indexOf(course210)).addViolation();
		if (c.indexOf(course120) > c.indexOf(course210)) {
			c.get(c.indexOf(course210)).addViolation();
		}
		if (c.indexOf(course120) > c.indexOf(course215)) {
			c.get(c.indexOf(course215)).addViolation();
		}
		if (c.indexOf(course120) > c.indexOf(course140)) {
			c.get(c.indexOf(course140)).addViolation();
		}
		if (c.indexOf(course215) > c.indexOf(course141)) {
			c.get(c.indexOf(course141)).addViolation();
		}
		if (c.indexOf(course140) > c.indexOf(course141)) {
			c.get(c.indexOf(course141)).addViolation();
		}
		if (c.indexOf(course141) > c.indexOf(course232)) {
			c.get(c.indexOf(course232)).addViolation();
		}
		if (c.indexOf(course141) > c.indexOf(course240)) {
			c.get(c.indexOf(course240)).addViolation();
		}
		if (c.indexOf(course141) > c.indexOf(course311)) {
			c.get(c.indexOf(course311)).addViolation();
		}
		if (c.indexOf(course240) > c.indexOf(course311)) {
			c.get(c.indexOf(course311)).addViolation();
		}
		if (c.indexOf(course240) > c.indexOf(course340)) {
			c.get(c.indexOf(course340)).addViolation();
		}
		if (c.indexOf(course240) > c.indexOf(course365)) {
			c.get(c.indexOf(course365)).addViolation();
		}
		if (c.indexOf(course240) > c.indexOf(course372)) {
			c.get(c.indexOf(course372)).addViolation();
		}
		if (c.indexOf(course340) > c.indexOf(course440)) {
			c.get(c.indexOf(course440)).addViolation();
		}
		if (c.indexOf(course372) > c.indexOf(course499)) {
			c.get(c.indexOf(course499)).addViolation();
		}
	}

	/**
	 * Randomly selects course and fills them into an ArrayList of final
	 * schedule.
	 * 
	 * @param ArrayList
	 *            of courses available.
	 * @return final schedule
	 */
	public ArrayList<Course> randomFill(ArrayList<Course> c) {
		int randomCourse = new Random().nextInt(c.size());
		Course course;
		course = c.get(randomCourse);
		int num = 0;
		int domainIndex = course.getDomainValue(num);
		if (!finalSchedule.contains(course) && finalSchedule.get(domainIndex) == null) {
			finalSchedule.add(domainIndex, course);
			num++;
		} else if (!finalSchedule.contains(course) && finalSchedule.get(domainIndex) != null) {
			finalSchedule.add(domainIndex + num, course);
			num++;
		}

		return finalSchedule;
	}

	/**
	 * Fills the final schedule with null elements to start with.
	 * 
	 * @return final schedule
	 */
	public ArrayList<Course> fillWithNull() {
		finalSchedule.clear();
		for (int i = 0; i < courses.size(); i++) {
			finalSchedule.add(i, null);
		}
		return finalSchedule;
	}

	public ArrayList<Course> localSearch() throws IOException {
		fillWithNull();
		while (!finalSchedule.containsAll(courses)) {
			randomFill(courses);
		}
		if (meetsConstraints(finalSchedule)) {
			finalSchedule.removeAll(Collections.singleton(null));
			return finalSchedule;
		} else
			setViolations(courses);
		writeFile(courses);
		localSearch();

		return finalSchedule;
	}

	/**
	 * Creates text file called "Verbose" and prints out course numbers along
	 * with violation numbers as program iterates through to find final schedule
	 * that meets requirements.
	 * 
	 * @param ArrayList
	 *            of courses
	 * @throws IOException
	 */
	public void writeFile(ArrayList<Course> c) throws IOException {
		Collections.sort(c);
		writer.println(" " + c.get(2).getViolation() + "   " + c.get(3).getViolation() + "   " + c.get(4).getViolation()
				+ "   " + c.get(5).getViolation() + "   " + c.get(6).getViolation() + "   " + c.get(7).getViolation()
				+ "   " + c.get(8).getViolation() + "   " + c.get(9).getViolation() + "   " + c.get(10).getViolation()
				+ "   " + c.get(11).getViolation() + "   " + c.get(12).getViolation() + "   " + c.get(13).getViolation()
				+ "   " + c.get(14).getViolation() + "   " + c.get(15).getViolation() + "   " + c.get(16).getViolation()
				+ "   " + c.get(17).getViolation() + "   " + c.get(18).getViolation() + "   " + c.get(19).getViolation()
				+ "   " + c.get(20).getViolation());

	}

	public void startFile() throws IOException {
		writer.println("120 " + "140 " + "141 " + "210 " + "215 " + "232 " + "240 " + "311 " + "340 " + "365 " + "372 "
				+ "440 " + "460 " + "462 " + "490 " + "492 " + "499 " + "998 " + "999 ");
		writer.println("---------------------------------------------------------------------------");
	}

	/**
	 * Creates courses objects with course numbers, domains, and violation
	 * numbers.
	 */
	public class Course implements Comparable<Object> {
		int courseNum;
		int[] domain;
		String course = "";
		int violation;

		public Course(int courseNum) {
			this.courseNum = courseNum;
			violation = 0;
		}

		public Course(String course) {
			this.course = course;
			violation = 0;
		}

		public void setDomain(int[] semester) {
			this.domain = semester;
		}

		@Override
		public String toString() {
			return "Course [course=" + course + courseNum + ",domain=" + Arrays.toString(domain) + ",  violation="
					+ violation + "]";
		}

		public int getCourseNum() {
			return courseNum;
		}

		public int[] getDomain() {
			return domain;
		}

		public int getDomainValue(int index) {
			return domain[index];
		}

		public int addViolation() {

			return violation++;
		}

		public int getViolation() {
			return violation;
		}

		@Override
		public int compareTo(Object comparesTo) {
			int compareNum = ((Course) comparesTo).getCourseNum();
			return this.courseNum - compareNum;
		}

	}
}

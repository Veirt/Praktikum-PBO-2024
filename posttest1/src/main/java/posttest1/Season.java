package posttest1;

public class Season {
	SeasonEnum name;
	int year;

	Season(SeasonEnum name, int year) {
		this.name = name;
		this.year = year;
	}

	public String toString() {
		return String.format("%s %d", this.name.toString(), year);
	}
}

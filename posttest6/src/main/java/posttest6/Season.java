package posttest6;

public class Season {
	private SeasonEnum name;
	private int year;

	Season() {
		this.name = null;
		this.year = 0;
	}

	// overloading
	Season(SeasonEnum name, int year) {
		this.name = name;
		this.year = year;
	}

	// overloading
	Season(String name, int year) {
		this.setSeason(name);
		this.year = year;
	}

	public String toString() {
		return String.format("%s %d", this.name.toString(), year);
	}

	public String getSeason() {
		return this.name.toString();
	}

	public void setSeason(SeasonEnum season) {
		this.name = season;
	}

	public void setSeason(String season) throws IllegalArgumentException {
		// by names
		for (SeasonEnum se : SeasonEnum.values()) {
			if (se.toString().toLowerCase().equals(season.toLowerCase())) {
				this.name = se;
			}
		}

		// by choices
		// 1: winter
		// 2: spring
		// 3: summer
		// 4: fall
		switch (season) {
			case "1":
				this.name = SeasonEnum.WINTER;
				break;
			case "2":
				this.name = SeasonEnum.SPRING;
				break;
			case "3":
				this.name = SeasonEnum.SUMMER;
				break;
			case "4":
				this.name = SeasonEnum.FALL;
				break;
		}

		if (this.name == null) {
			throw new IllegalArgumentException("Season tidak valid.");
		}
	}

	public void setYear(int year) throws IllegalArgumentException {
		// first anime was in 1917
		if (year < 1917) {
			throw new IllegalArgumentException("Tahun tidak valid. Tahun anime pertama kali adalah 1917.");
		}

		this.year = year;
	}
}

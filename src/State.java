import java.util.*;

public class State {
	boolean farmer;
	boolean wolf;
	boolean goat;
	boolean cabbage;
	boolean position;

	State(boolean farmer, boolean wolf, boolean goat, boolean cabbage, boolean position) {
		this.farmer = farmer;
		this.wolf = wolf;
		this.goat = goat;
		this.cabbage = cabbage;
		this.position = position;
	}

	int heuristic(State goal) {
		int distance = 0;
		if (farmer != goal.farmer) {
			distance++;
		}
		if (wolf != goal.wolf) {
			distance++;
		}
		if (goat != goal.goat) {
			distance++;
		}
		if (cabbage != goal.cabbage) {
			distance++;
		}
		return distance;
	}

	boolean isSafe() {
		if (wolf == goat && farmer != wolf) {
			return false;
		}
		if (goat == cabbage && farmer != goat) {
			return false;
		}
		return true;
	}

	boolean isGoal(State goal) {
		return farmer == goal.farmer && wolf == goal.wolf && goat == goal.goat && cabbage == goal.cabbage
				&& position == goal.position;
	}

	List<State> getNeighbors() {
		List<State> neighbors = new ArrayList<>();
		if (farmer == position) {
			// Farmer crosses alone.
			neighbors.add(new State(!farmer, wolf, goat, cabbage, !position));
			// Farmer crosses with wolf.
			if (wolf == position) {
				neighbors.add(new State(!farmer, !wolf, goat, cabbage, !position));
			}
			// Farmer crosses with goat.
			if (goat == position) {
				neighbors.add(new State(!farmer, wolf, !goat, cabbage, !position));
			}
			// Farmer crosses with cabbage.
			if (cabbage == position) {
				neighbors.add(new State(!farmer, wolf, goat, !cabbage, !position));
			}
		}
		return neighbors;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof State)) {
			return false;
		}
		State s = (State) o;
		return farmer == s.farmer && wolf == s.wolf && goat == s.goat && cabbage == s.cabbage && position == s.position;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + (farmer ? 1 : 0);
		result = 31 * result + (wolf ? 1 : 0);
		result = 31 * result + (goat ? 1 : 0);
		result = 31 * result + (cabbage ? 1 : 0);
		result = 31 * result + (position ? 1 : 0);
		return result;
	}
}


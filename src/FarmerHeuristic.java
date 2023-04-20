import java.util.*;

public class FarmerHeuristic {

	public static void main(String[] args) {
		System.out.println("Framer heuristic algorithm!!!");

		State initialState = new State(true, true, true, true, true);
		State goalState = new State(false, false, false, false, false);
		List<State> path = aStar(initialState, goalState);
		if (path != null) {
			System.out.println("Path: ");
			int index = 1;
			for (State state : path) {
				System.out.print("Step " + index++ + ": ");
				System.out.println("[" + convertValue(state.farmer) + ", " 
						+ convertValue(state.wolf) + ", " 
						+ convertValue(state.goat)+ ", " 
						+ convertValue(state.cabbage) + ", " 
						+ convertValue(state.position)  + "]");
			}
		} else {
			System.out.println("No path found");
		}
	}

	static List<State> aStar(State initialState, State goalState) {
		PriorityQueue<State> openSet = new PriorityQueue<>(
				Comparator.comparingInt(state -> state.heuristic(goalState)));
		Map<State, State> cameFrom = new HashMap<>();
		Map<State, Integer> gScore = new HashMap<>();

		gScore.put(initialState, 0);
		openSet.add(initialState);

		while (!openSet.isEmpty()) {
			State current = openSet.poll();
			if (current.isGoal(goalState)) {
				List<State> path = new ArrayList<>();
				while (current != null) {
					path.add(current);
					current = cameFrom.get(current);
				}
				Collections.reverse(path);
				return path;
			}

			for (State neighbor : current.getNeighbors()) {
				if (!neighbor.isSafe()) {
					continue;
				}
				int tentativeGScore = gScore.get(current) + 1;
				if (!gScore.containsKey(neighbor) || tentativeGScore < gScore.get(neighbor)) {
					gScore.put(neighbor, tentativeGScore);
					cameFrom.put(neighbor, current);
					int fScore = tentativeGScore + neighbor.heuristic(goalState);
					openSet.remove(neighbor);
					openSet.add(neighbor);
				}
			}
		}

		return null;
	}
	
	static String convertValue(boolean value) {
		return value? "1" : "0";
	}
}

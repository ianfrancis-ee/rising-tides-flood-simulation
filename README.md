# Rising Tides Flood Simulation

Java flood simulation that models how rising water levels impact terrain using **Breadth First Search (BFS)** and **Weighted Quick Union (Union Find)**. This project analyzes flooding, land loss, and island formation across a given 2D terrain.

---

# Project Overview
This project was completed as part of the Rutgers University Data Structures course (CS112).

The classes, terrains, and overall framework were provided as part of the assignment. My goal was designing and implementing all of the methods within the starter project using my knowledge of data structures and algorithms learned throughout the course.

The program analyzes a terrain represented by a two-dimensional array and determines:

- Flooded regions at different water heights
- Remaining visible land
- Land lost as water levels rise
- Number of remaining islands
- Minimum and maximum terrain elevations

---

# Algorithms & Data Structures

My implementation applies several data structures and algorithms to solve each problem.

### Breadth First Search (BFS)

The `floodedRegionsIn()` method uses Breadth First Search to simulate water spreading from predefined water sources. Only connected terrain below the selected water height becomes flooded.

### ArrayList Traversal

An `ArrayList<GridLocation>` is used to keep track of terrain locations that still need to be explored during the flood fill process. As neighboring cells become flooded, they are added to the list until all of the terrain cells has been processed.

### Weighted Quick Union (Union-Find)

The `numOfIslands()` method uses Union Find to efficiently group connected land cells and determine how many separate islands remain after flooding.

---

# Visualization

## Initial Terrain

Initial visualization before flooding is input.

<img width="450" height="510" alt="terrain_initial" src="https://github.com/user-attachments/assets/7a1252c6-5b3e-4492-9b78-52fc298c46a8" />



---

## Terrain Representation

Each terrain is stored as a two dimensional elevation array that are used in the union find to determine which regions are flooded or not at different water levels.

<img width="610" height="464" alt="terrain_array" src="https://github.com/user-attachments/assets/095357db-4aae-4708-bd6b-8830b1b9a5b1" />



---
# Visualisations
  The terrain floods based on connected regions that are either the same or lower elevation. At different input water levels the region will flood and calculate the number of islands and total visible land.

## Low Water Level

<img width="450" height="510" alt="flood_low" src="https://github.com/user-attachments/assets/f71fa0d0-9cd2-472f-9fcd-f2205a16ce7f" />


---

## Medium Water Level

<img width="450" height="510" alt="flood_medium" src="https://github.com/user-attachments/assets/b1ba5722-9b61-424e-889d-051c86f3f035" />

---

## High Water Level

<img width="450" height="510" alt="flood_high" src="https://github.com/user-attachments/assets/cb0b6259-f9c5-4452-a392-ebb071e27cc5" />

---

## Land Loss Analysis

The program compares visible land at the current and future predicted level to determine how much land will be lost. Sho

<img width="450" height="510" alt="land_lost" src="https://github.com/user-attachments/assets/396ad84b-8b23-49c3-88fc-c19427a4ec2e" />


---

# Implementations

Implemented methods:

- `elevationExtrema()` – Finds the minimum and maximum terrain elevations.
- `floodedRegionsIn()` – Performs a BFS flood fill from water sources.
- `isFlooded()` – Determines whether a specific location is underwater.
- `heightAboveWater()` – Calculates elevation relative to the current water level.
- `totalVisibleLand()` – Counts terrain remaining above water.
- `landLost()` – Computes land lost between two water levels.
- `numOfIslands()` – Uses Union Find to count disconnected land masses.

---

# What I Learned

- Applied data structures to solve a realistic environmental simulation problem.
- improved my understanding of Breadth First Search on a two dimensional grid.
- Improved my ability to choose appropriate data structures for different algorithmic tasks.


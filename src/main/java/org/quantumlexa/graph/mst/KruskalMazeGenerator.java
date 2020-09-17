package org.quantumlexa.graph.mst;

import java.util.*;

public class KruskalMazeGenerator {

    public static void main(String[] args) {
        Map<Cell, List<Cell>> maze = new KruskalMazeGenerator().generateMaze(10, 10);
        System.out.println(maze);
    }

    static class Cell {
        int i, j;

        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return i == cell.i &&
                    j == cell.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    static class Wall {
        Cell a, b;

        public Wall(Cell a, Cell b) {
            this.a = a;
            this.b = b;
        }
    }


    Map<Cell, List<Cell>> generateMaze(int n, int m) {
        Cell[][] cells = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        List<Wall> walls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j < m - 1) walls.add(new Wall(cells[i][j], cells[i][j + 1]));
                if (i < n - 1) walls.add(new Wall(cells[i][j], cells[i + 1][j]));
            }
        }

        UnionFind unionFind = new UnionFind(n * m);

        Map<Cell, List<Cell>> maze = new HashMap<>();
        while (!walls.isEmpty()) {
            Wall wall = getAndRemoveRandomWall(walls);
            int idx1 = idx(wall.a.i, wall.a.j, m);
            int idx2 = idx(wall.b.i, wall.b.j, m);
            if (unionFind.connected(idx1, idx2)) {
                continue;
            }
            unionFind.connect(idx1, idx2);
            if (!maze.containsKey(wall.a)) {
                maze.put(wall.a, new LinkedList<>());
            }
            if (!maze.containsKey(wall.b)) {
                maze.put(wall.b, new LinkedList<>());
            }
            maze.get(wall.a).add(wall.b);
            maze.get(wall.b).add(wall.a);
        }
        return maze;


    }

    static class UnionFind {
        private final int root[];

        public UnionFind(int size) {
            this.root = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
            }
        }

        public int root(int id) {
            while (root[id] != id) {
                root[id] = root[root[id]];
                id = root[id];
            }
            return id;
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        public void connect(int p, int q) {
            int rootQ = root(q);
            int rootP = root(p);
            if (rootP == rootQ) return;
            root[rootP] = rootQ;
        }
    }


    private int idx(int i, int j, int m) {
        return i * m + j;
    }

    private Wall getAndRemoveRandomWall(List<Wall> list) {
        Random random = new Random();
        int idx = random.nextInt(list.size());
        if (idx != list.size() - 1) {
            Wall wall = list.get(idx);
            list.set(idx, list.get(list.size() - 1));
            list.set(list.size() - 1, wall);
        }
        return list.remove(list.size() - 1);
    }
}

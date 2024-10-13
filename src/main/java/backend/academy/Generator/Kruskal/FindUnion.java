package backend.academy.Generator.Kruskal;

public class FindUnion {
    private final int[] hues;

    public FindUnion(int size) {
        hues = new int[size];
        for (int i = 0; i < size; ++i) {
            hues[i] = i;
        }
    }

    public int find(int hue) {
        if (hue != hues[hue]) {
            hues[hue] = find(hues[hue]);
        }
        return hues[hue];
    }

    public void union(int hue1, int hue2) {
        int root1 = find(hue1);
        int root2 = find(hue2);
        if (root1 != root2) {
            hues[root2] = root1;
        }
    }
}

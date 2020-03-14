/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class MyWeightedQuickUnionUF {

    private int[] parents;
    private int[] treeSizes;

    public MyWeightedQuickUnionUF(int n) {
        parents = new int[n];
        treeSizes = new int[n];
        for (int index = 0; index < n; ++index) {
            parents[index] = index;
            treeSizes[index] = 1;
        }
    }

    public int find(int p) {
        if (parents[p] == p) return p;
        return find(parents[p]);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (treeSizes[rootP] > treeSizes[rootQ]) {
            int temp = rootP;
            rootP = rootQ;
            rootQ = temp;
        }
        parents[rootQ] = rootP;
        treeSizes[rootP] = treeSizes[rootP] + treeSizes[rootQ];
    }
}

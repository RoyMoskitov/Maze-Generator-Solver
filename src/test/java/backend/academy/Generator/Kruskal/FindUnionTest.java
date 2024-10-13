package backend.academy.Generator.Kruskal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindUnionTest {

    FindUnion findUnion = new FindUnion(5);

    @BeforeEach
    void Init() {
        findUnion.union(1, 3);
        findUnion.union(3, 2);
    }

    @Test
    void TestParentChainCreation() {
        assertEquals(findUnion.find(2), 1);
    }

    @Test
    void TestDefaultParent() {
        assertEquals(findUnion.find(4), 4);
    }
}

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class VisualPythonTest01.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VisualPythonTest01
{
    /**
     * Default constructor for test class VisualPythonTest01
     */
    public VisualPythonTest01() {
    }

    
    @Test
    public void cornerShouldBeAdded() {
        VisualPython vp = new VisualPython(200, 200);
        int[] cnr = new int[]{9, 8}; // {r, c}
        vp.putCornerBottomRight(cnr);
        assertTrue(vp.ok());
    }
    
    @Test
    public void cornerShouldBeRemovedAfterAdded() {
        VisualPython vp = new VisualPython(200, 200);
        int[] cnr = new int[]{9, 8};
        vp.putCornerBottomRight(cnr);
        assertTrue(vp.ok());
        vp.removeCorner(cnr);
        assertTrue(vp.ok());
    }
    
    @Test
    public void removeCornerFromEmptyEditorShouldFail() {
        VisualPython vp = new VisualPython(200, 200);
        int[] cnr = new int[]{9, 8};
        vp.putCornerBottomRight(cnr);
        assertTrue(vp.ok());
        vp.removeCorner(cnr);
        assertTrue(vp.ok());
        vp.removeCorner(cnr);
        assertFalse(vp.ok());
    }
        
    @Test
    public void addTwoTopLeftCornersWithSameCoordsShouldFail() {
        VisualPython vp = new VisualPython(200, 200);
        int[] cnr = new int[]{9, 8};
        vp.putCornerTopLeft(cnr);
        assertTrue(vp.ok());
        vp.putCornerTopLeft(cnr);
        assertFalse(vp.ok());
    }
    
    @Test
    public void addTwoBottomRightCornersWithSameCoordsShouldFail() {
        VisualPython vp = new VisualPython(200, 200);
        int[] cnr = new int[]{21, 4};
        vp.putCornerBottomRight(cnr);
        assertTrue(vp.ok());
        vp.putCornerBottomRight(cnr);
        assertFalse(vp.ok());
    }
    
    @Test
    public void addTopLeftAndBottomRightCornersWithSameCoordsShouldFail() {
        VisualPython vp = new VisualPython(200, 200);
        int[] cnr = new int[]{21, 4};
        vp.putCornerTopLeft(cnr);
        assertTrue(vp.ok());
        vp.putCornerBottomRight(cnr);
        assertFalse(vp.ok());
    }
    
    @Test
    public void simulatorWithNoBlocksShouldCompile() {
        VisualPython vp = new VisualPython(200, 200);
        assertTrue(vp.compile());
    }
    
    @Test
    public void compileABlockWhichNotExistsShouldFail() {
        VisualPython vp = new VisualPython(200, 200);
        assertTrue(vp.compile());
        assertFalse(vp.compile("blue"));
        assertFalse(vp.ok());
    }
    
    @Test
    public void simulatorWithASingleBlockShouldCompile() {
        VisualPython vp = new VisualPython(200, 200);
        int[] topLeft = new int[]{21, 4}; // {r, c}
        int[] bottomRight = new int[]{22, 5};
        vp.putBlock("blue", topLeft, bottomRight);
        assertTrue(vp.compile());
    }
    
    @Test
    public void singleBlockShouldCompile() {
        VisualPython vp = new VisualPython(200, 200);
        int[] topLeft = new int[]{6, 7}; // {r, c}
        int[] bottomRight = new int[]{8, 9};
        vp.putBlock("blue", topLeft, bottomRight);
        assertTrue(vp.compile("blue"));
        assertTrue(vp.ok());
    }
    
    @Test
    public void aBlockContainingAnotherShouldCompile() {
        VisualPython vp = new VisualPython(200, 200);
        int[] topLeft1 = new int[]{4, 7}; // {r, c}
        int[] topLeft2 = new int[]{9, 8}; // {r, c}
        int[] bottomRight1 = new int[]{14, 17};
        int[] bottomRight2 = new int[]{19, 18};

        vp.putBlock("blue", topLeft1, bottomRight2);
        vp.putBlock("red", topLeft2, bottomRight1);
        assertTrue(vp.compile("blue"));
    }

    @Test
    public void twoDisjointBlocksShouldCompile() {
        VisualPython vp = new VisualPython(200, 200);
        int[] topLeft1 = new int[]{4, 7}; // {r, c}
        int[] topLeft2 = new int[]{14, 17}; // {r, c}
        int[] bottomRight1 = new int[]{9, 8};
        int[] bottomRight2 = new int[]{19, 18};

        vp.putBlock("blue", topLeft1, bottomRight1);
        vp.putBlock("red", topLeft2, bottomRight2);
        assertTrue(vp.compile("blue"));
    }

}
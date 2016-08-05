package com.xavier.googlemaps.test;
import com.xavier.googlemaps.main.*;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by Xavier on 8/5/16.
 */

public class GoogleMapTest extends TestCase {
    @Test
    public void testNeedsFormatting() throws Exception {
        String noFormatting = "Worcester";
        String needsFormatting = "New York";
        GoogleMap mapNF = new GoogleMap(noFormatting);

        assertFalse(mapNF.needsFormatting(noFormatting));
        assertTrue(mapNF.needsFormatting(needsFormatting));

    }

}
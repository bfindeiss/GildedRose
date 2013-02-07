
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void testTheTruth() {
        assertTrue(true);
    }

    @Test
    public void updateQuality() {

        GildedRose.addItemToShop(new Item("+5 Dexterity Vest", 10, 20));
        GildedRose.addItemToShop(new Item("Aged Brie", 2, 0));
        GildedRose.addItemToShop(new Item("Elixir of the Mongoose", 5, 7));
        GildedRose.addItemToShop(new Item("Elixir of the Mongoose", -1, 7));
        GildedRose.addItemToShop(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        GildedRose.addItemToShop(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        GildedRose.addItemToShop(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
        GildedRose.addItemToShop(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
        GildedRose.addItemToShop(new Item("Conjured Mana Cake", 3, 6));

        GildedRose.updateQuality();
    }

    @Test
    public void updateDexterityVest() {
        final Item item = new Item("+5 Dexterity Vest", 10, 20);
        GildedRose.addItemToShop(item);
        
        GildedRose.updateQuality();
        assertThat(item.getQuality(), is(19));
        assertEquals(9, item.getSellIn());          
    }
    
    @Test
    public void updateNormalItemQulityNeverUnderZeroTest() {
        int days = 10;
        final Item item = new Item("+5 Dexterity Vest", days, 1);
        
        GildedRose.addItemToShop(item);
        for(int i = 0; i<days; i++) {
                GildedRose.updateQuality();
        }

        assertThat(item.getQuality(), is(0));       
    }
    
    @Test
    public void updateNormalItemQulityUpdatedCorrectyTest() {
        int days = 1;
        final Item item = new Item("+5 Dexterity Vest", days, 20);
        GildedRose.addItemToShop(item);

        GildedRose.updateQuality();
        assertThat(item.getQuality(), is(19));  
        GildedRose.updateQuality();
        assertThat(item.getQuality(), is(17));       
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void normalItemQualityNoMoreThan50Test() {
        final Item item = new Item("+5 Dexterity Vest", 1, 67);
        GildedRose.addItemToShop(item);

        GildedRose.updateQuality();    
    }
}

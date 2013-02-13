
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

        GildedRose.addItemToShop(new Item(GildedRose.PLUS_5_DEXTERITY_VEST, 10, 20));
        GildedRose.addItemToShop(new Item(GildedRose.AGED_BRIE, 2, 0));
        GildedRose.addItemToShop(new Item(GildedRose.ELIXIR_OF_THE_MONGOOSE, 5, 7));
        GildedRose.addItemToShop(new Item(GildedRose.ELIXIR_OF_THE_MONGOOSE, -1, 7));
        GildedRose.addItemToShop(new Item(GildedRose.SULFURAS_HAND_OF_RAGNAROS, 0, 80));
        GildedRose.addItemToShop(new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
        GildedRose.addItemToShop(new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 10, 20));
        GildedRose.addItemToShop(new Item(GildedRose.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 5, 20));
        GildedRose.addItemToShop(new Item(GildedRose.CONJURED_MANA_CAKE, 3, 6));

        GildedRose.updateQuality();
    }

    @Test
    public void updateDexterityVest() {
        final Item item = new Item(GildedRose.PLUS_5_DEXTERITY_VEST, 10, 20);
        GildedRose.addItemToShop(item);

        GildedRose.updateQuality();
        assertThat(item.getQuality(), is(19));
        assertEquals(9, item.getSellIn());
    }

    @Test
    public void updateNormalItemQulityNeverUnderZeroTest() {
        int days = 10;
        final Item item = new Item(GildedRose.PLUS_5_DEXTERITY_VEST, days, 1);

        GildedRose.addItemToShop(item);
        for (int i = 0; i < days; i++) {
            GildedRose.updateQuality();
        }

        assertThat(item.getQuality(), is(0));
    }

    @Test
    public void updateNormalItemQulityUpdatedCorrectyTest() {
        int days = 1;
        final Item item = new Item(GildedRose.PLUS_5_DEXTERITY_VEST, days, 20);
        GildedRose.addItemToShop(item);

        GildedRose.updateQuality();
        assertThat(item.getQuality(), is(19));
        GildedRose.updateQuality();
        assertThat(item.getQuality(), is(17));
    }

    @Test(expected = IllegalArgumentException.class)
    public void normalItemQualityNoMoreThan50Test() {
        final Item item = new Item(GildedRose.PLUS_5_DEXTERITY_VEST, 1, 67);
        GildedRose.addItemToShop(item);

        GildedRose.updateQuality();
    }
}

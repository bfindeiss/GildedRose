
import java.util.ArrayList;
import java.util.List;

public class GildedRose {

    private static List<Item> items = new ArrayList<Item>();
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    public static final String PLUS_5_DEXTERITY_VEST = "+5 Dexterity Vest";
    public static final String ELIXIR_OF_THE_MONGOOSE = "Elixir of the Mongoose";
    public static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    /**
     * @param args
     */
    public static void main(String[] args) {


        GildedRose.addItemToShop(new Item(PLUS_5_DEXTERITY_VEST, 10, 20));
        GildedRose.addItemToShop(new Item(AGED_BRIE, 2, 0));
        GildedRose.addItemToShop(new Item(ELIXIR_OF_THE_MONGOOSE, 5, 7));
        GildedRose.addItemToShop(new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80));
        GildedRose.addItemToShop(new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 20));
        GildedRose.addItemToShop(new Item(CONJURED_MANA_CAKE, 3, 6));

        updateQuality();
    }

    public static void updateQuality() {
        for (Item item : items) {
            final String name = item.getName();
            final int quality = item.getQuality();

            if ((!AGED_BRIE.equals(name)) && !BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.equals(name)) {
                if (quality > 0 && !SULFURAS_HAND_OF_RAGNAROS.equals(name)) {
                    item.setQuality(quality - 1);
                }
            } else {
                if (quality < 50) {
                    item.setQuality(quality + 1);

                    if (BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.equals(name)) {
                        if (item.getSellIn() < 11) {
                            if (quality < 50) {
                                item.setQuality(quality + 1);
                            }
                        }

                        if (item.getSellIn() < 6) {
                            if (quality < 50) {
                                item.setQuality(quality + 1);
                            }
                        }
                    }
                }
            }

            if (!SULFURAS_HAND_OF_RAGNAROS.equals(name)) {
                item.setSellIn(item.getSellIn() - 1);
            }

            if (item.getSellIn() < 0) {
                if (!AGED_BRIE.equals(name)) {
                    if (!BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT.equals(name)) {
                        if (quality > 0) {
                            if (!SULFURAS_HAND_OF_RAGNAROS.equals(name)) {
                                item.setQuality(quality - 1);
                            }
                        }
                    } else {
                        item.setQuality(quality - quality);
                    }
                } else {
                    if (quality < 50) {
                        item.setQuality(quality + 1);
                    }
                }
            }
        }
    }

    public static List<Item> getItems() {
        return items;
    }

    public static void addItemToShop(Item item) {
        if (checkItem(item)) {
            GildedRose.items.add(item);
        }
    }

    private static boolean checkItem(Item item) {
        if (item.quality < 0) {
            throw new IllegalArgumentException("Quality of the item must be at least 0!");
        }

        if (item.quality > 50 && !item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            throw new IllegalArgumentException("Quality of the item must be at most 50!");
        }

        if (item.quality != 80 && item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            throw new IllegalArgumentException("Quality of Sulfuras item must equal 80!");
        }

        return true;
    }
}
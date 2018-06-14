package pl.refactoring.interpreter;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductFinderTest {
    private ProductFinder finder;

    private Product fireTruck = new Product("Fire Truck", ProductSize.MEDIUM, 8.95f, 1234, ProductColor.RED);
    private Product barbieClassic = new Product("Barbie Classic", ProductSize.SMALL, 15.95f, 7654, ProductColor.YELLOW);
    private Product frisbee = new Product("Frisbee", ProductSize.LARGE, 9.99f, 4321, ProductColor.PINK);
    private Product baseball = new Product("Baseball", ProductSize.NOT_APPLICABLE, 8.95f, 2343, ProductColor.WHITE);
    private Product toyConvertible = new Product("Toy Prosche Convertible", ProductSize.NOT_APPLICABLE, 230f,1112, ProductColor.RED);

    @Before
    public void setUp() throws Exception {
        finder = new ProductFinder(createProductRepository());
    }

    private List<Product> createProductRepository() {
        return Arrays.asList(fireTruck, barbieClassic, frisbee, baseball, toyConvertible);
    }

    @Test
    public void shouldFindByColor() throws Exception {
        //when
        List<Product> actual = finder.byColor(ProductColor.PINK.RED);

        //then
        assertEquals("found 2 products",2, actual.size());
        assertTrue(actual.contains(fireTruck));
        assertTrue(actual.contains(toyConvertible));
    }

    @Test
    public void shouldFindByPrice() throws Exception {
        //given
        float price = 8.95f;

        //when
        List<Product> actual = finder.byPrice(price);

        //then
        assertEquals(2, actual.size());
        Iterator<Product> it = actual.iterator();
        while(it.hasNext()){
            Product product = it.next();
            assertEquals(price, product.getPrice());
        }
        
    }


    @Test
    public void shouldFindByColorAndBelowPrice() {
        List foundProducts =
                finder.byColorAndBelowPrice(ProductColor.RED, 10.00f);
        assertEquals(
                "found 1 small RED products below $10.00",
                1,
                foundProducts.size());

        foundProducts =
                finder.byColorAndBelowPrice(ProductColor.RED, 10.00f);
        assertEquals(
                "found firetruck when looking for cheap medium RED toys",
                fireTruck,
                foundProducts.get(0));
    }

    @Test
    public void shouldFindBelowPriceAvoidingAColor() {
        List foundProducts =
                finder.belowPriceAvoidingAColor(9.00f, ProductColor.WHITE);
        assertEquals(
                "found 1 non-WHITE product < $9.00",
                1,
                foundProducts.size());
        assertTrue("found fireTruck", foundProducts.contains(fireTruck));

        foundProducts = finder.belowPriceAvoidingAColor(9.00f, ProductColor.RED);
        assertEquals(
                "found 1 non-RED product < $9.00",
                1,
                foundProducts.size());
        assertTrue("found baseball", foundProducts.contains(baseball));
    }

   @Test
   public void shouldFindByColorAndAbovePrice() {
      List foundProducts = finder.byColorAndAbovePrice(ProductColor.RED, 9.00f);
      assertEquals("found 1 RED product > $9.00", 1, foundProducts.size());
      assertTrue("found toyConvertible",
         foundProducts.contains(toyConvertible));

      foundProducts = finder.byColorAndAbovePrice(ProductColor.RED, 1.00f);
      assertEquals("found 2 RED products > $1.00", 2, foundProducts.size());
      assertTrue("found toyConvertible",
         foundProducts.contains(toyConvertible));
      assertTrue("found fireTruck", foundProducts.contains(fireTruck));

      foundProducts = finder.byColorAndAbovePrice(ProductColor.RED, 230.00f);
      assertEquals("found 0 RED products > $230.00", 0, foundProducts.size());
   }

   @Test
   public void shouldFindByColorSizeAndBelowPrice() {
      List foundProducts = finder.byColorSizeAndBelowPrice(ProductColor.PINK,
         ProductSize.LARGE, 10f);
      assertEquals("found 1 large PINK product < $10", 1,
         foundProducts.size());
      assertTrue("found frisbee", foundProducts.contains(frisbee));

      foundProducts = finder.byColorSizeAndBelowPrice(ProductColor.PINK,
         ProductSize.LARGE, 9.99f);
      assertEquals("found 0 large PINK product < $9.99", 0,
         foundProducts.size());

      foundProducts = finder.byColorSizeAndBelowPrice(ProductColor.RED,
         ProductSize.LARGE, 10f);
      assertEquals("found 0 large RED product < $10", 0, foundProducts.size());

      foundProducts = finder.byColorSizeAndBelowPrice(ProductColor.PINK,
         ProductSize.MEDIUM, 10f);
      assertEquals("found 0 medium PINK product < $10", 0,
         foundProducts.size());
   }

}

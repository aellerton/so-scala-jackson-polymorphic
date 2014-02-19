package so.poly

import org.scalatest._
import so.{InventoryTestData, InventoryHelper}

class PolymorphicInventorySpec extends FlatSpec with MustMatchers with InventoryHelper with InventoryTestData {
  "Plain Inventory" should "serialize and load again to JSON as you'd expect" in new Case {
    val inv = PolymorphicInventory(
      List(
        ProductDetailsSimple(productId="Some_id", description="some description"),
        ProductDetailsComplex(productId="Some_id", description=Map("someKey" -> "somevalue"))
      )
    )

    val s = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(inv)
    println("Polymorphic Inventory as JSON: "+s)

    val reloaded = jsonMapper.readValue[PolymorphicInventory](s, classOf[PolymorphicInventory])
    reloaded must equal (inv)
  }

  it should "load from JSON (modified from question" in new Case {
    val reloaded = jsonMapper.readValue[PolymorphicInventory](jsonWithPolymorphicData, classOf[PolymorphicInventory])
    reloaded.products.size must equal (2)
  }

}

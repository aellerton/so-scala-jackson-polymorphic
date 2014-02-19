package so.plain

import org.scalatest._
import so.{InventoryTestData, InventoryHelper}

class PlainInventorySpec extends FlatSpec with MustMatchers with InventoryHelper with InventoryTestData {
  "Plain Inventory" should "serialize and load again to JSON as you'd expect" in new Case {
    val inv = PlainInventory(
      List(
        Product(productId="Some_id", description=Some("some description")),
        Product(productId="Some_id", attributes=Some(Map("someKey" -> "somevalue")))
      )
    )

    val s = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(inv)
    println("Plain Inventory as JSON: "+s)

    val reloaded = jsonMapper.readValue[PlainInventory](s, classOf[PlainInventory])
    reloaded must equal (inv)
  }

  it should "load from JSON (modified from question" in new Case {
    val reloaded = jsonMapper.readValue[PlainInventory](modifiedJsonAvoidingTheProblem, classOf[PlainInventory])
    reloaded.products.size must equal (2)
  }
}

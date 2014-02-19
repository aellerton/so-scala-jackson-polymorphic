package so

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

trait InventoryTestData {
  val originalJsonFromQuestion =
    """
      |{ inventory:
      |   [ { productType: 'someProduct1',
      |       details:
      |        { productId: 'Some_id',
      |          description: 'some description' }
      |        },
      |     { productType: 'someProduct2',
      |       details:
      |        { productId: 'Some_id',
      |          description: { someKey: 'somevalue' }
      |        }
      |     }
      |   ]
      |}
    """.stripMargin

  /**
   * Test data for the so.poly._ classes. Note that "type" field provided
   * inside each product.
   */
  val jsonWithPolymorphicData =
    """
      |{
      |  "products" : [
      |    {
      |      "type" : "simple",
      |      "productId" : "Some_id",
      |      "description" : "some description"
      |    }, {
      |      "type" : "complex",
      |      "productId" : "Some_id",
      |      "description" : {
      |        "someKey" : "somevalue"
      |      }
      |    }
      |  ]
      |}
    """.stripMargin

  /**
   * Test data for the so.plain._ classes. No type field required, and is
   * simpler and cleaner.
   */
  val modifiedJsonAvoidingTheProblem =
    """
      |{
      |  "products" : [
      |    {
      |      "productId" : "Some_id",
      |      "description" : "some description"
      |    },
      |    {
      |      "productId" : "Some_id",
      |      "attributes" : {
      |        "someKey" : "somevalue"
      |      }
      |    }
      |  ]
      |}
    """.stripMargin
}

trait InventoryHelper {
  trait Case {
    val jsonMapper = {
      val m = new ObjectMapper()
      m.registerModule(DefaultScalaModule)
      m
    }
  }
}
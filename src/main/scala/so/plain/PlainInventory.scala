package so.plain

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * My view is that the original SO question makes a mistake by wanting to treat
 * description as either a string or a key/value map.
 *
 * By making those fields either description or, say, "attributes", the class
 * design becomes simpler and Jackson polymorphism is avoided altogether.
 *
 * Note that the JsonInclude is included only because it makes the test JSON
 * look cleaner, but isn't really necessary.
 */

@JsonInclude(Include.NON_EMPTY)
case class Product(productId: String,
                   description: Option[String]=None,
                   attributes: Option[Map[String, String]]=None)

case class PlainInventory(products: List[Product])

package so.poly

/**
 * The types here are close to the original question types but use
 * Jackson annotations to mark the polymorphic JSON treatment, and
 * remove the ProductDetails intermediary class.
 */

import scala.Array
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME,
  include = JsonTypeInfo.As.PROPERTY,
  property = "type")
@JsonSubTypes(Array(
  new Type(value = classOf[ProductDetailsSimple], name = "simple"),
  new Type(value = classOf[ProductDetailsComplex], name = "complex")
))
abstract class Product

case class ProductDetailsSimple(productId: String, description: String) extends Product

case class ProductDetailsComplex(productId: String, description: Map[String, String]) extends Product

case class PolymorphicInventory(products: List[Product])

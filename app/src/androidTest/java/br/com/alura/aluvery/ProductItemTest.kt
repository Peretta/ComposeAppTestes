package br.com.alura.aluvery

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertWidthIsEqualTo
import androidx.compose.ui.test.assertHeightIsAtLeast
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.components.ProductItem
import br.com.alura.aluvery.ui.theme.AluveryTheme
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import androidx.compose.material.Surface
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.extensions.toBrazilianCurrency
import org.junit.Before

class ProductItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var product: Product
    private lateinit var formattedPrice: String

    @Before
    fun criandoProduto() {
        product = Product(
            name = "Produto de Teste",
            price = BigDecimal("14.99"),
            image = "https://example.com/image.jpg"
        )
        formattedPrice = product.price.toBrazilianCurrency()
    }

    @Test
    fun TextoapareceCorretamente() {
        composeTestRule.setContent {
            AluveryTheme {
                Surface {
                    ProductItem(product = product)
                }
            }
        }
        composeTestRule.onNodeWithText("Produto de Teste").assertExists()
        composeTestRule.onNodeWithText(formattedPrice).assertExists()
    }

    @Test
    fun TemImagemEComponentesDeTexto() {
        composeTestRule.setContent {
            AluveryTheme {
                Surface {
                    ProductItem(product = product)
                }
            }
        }
        composeTestRule.onNode(hasContentDescription("Imagem do Produto: " + product.name)).assertExists()
        composeTestRule.onNodeWithText("Produto de Teste").assertExists()
        composeTestRule.onNodeWithText(formattedPrice).assertExists()
    }

    @Test
    fun TemOLayoutPadrao() {
        composeTestRule.setContent {
            AluveryTheme {
                Surface {
                    ProductItem(product = product)
                }
            }
        }
        composeTestRule.onNodeWithText("Produto de Teste").assertHeightIsAtLeast(24.dp)
        composeTestRule.onNodeWithText("Produto de Teste").assertWidthIsEqualTo(137.dp)
    }

    @Test
    fun MostraPlaceholderQuandoImagemEhNulll() {
        product = Product(
            name = "Produto de Teste",
            price = BigDecimal("14.99"),
            image = null
        )
        composeTestRule.setContent {
            AluveryTheme {
                Surface {
                    ProductItem(product = product)
                }
            }
        }
        composeTestRule.onNode(hasContentDescription("Imagem do Produto: " + product.name)).assertExists()
    }
}

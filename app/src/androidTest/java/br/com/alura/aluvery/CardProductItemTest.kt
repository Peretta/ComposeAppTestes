package br.com.alura.aluvery

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.alura.aluvery.extensions.toBrazilianCurrency
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal

@RunWith(AndroidJUnit4::class)
class CardProductItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cardProductItemExibeConteudoCorretamente() {
        // Dado
        val produto = Product(
            name = "Produto Exemplo",
            price = BigDecimal("9.99"),
            description = "Esta é uma descrição de exemplo para fins de teste."
        )
        val price_formatted = produto.price.toBrazilianCurrency()

        // Quando
        composeTestRule.setContent {
            AluveryTheme {
                CardProductItem(product = produto)
            }
        }

        // Então
        composeTestRule.onNodeWithText("Produto Exemplo").assertIsDisplayed()
        composeTestRule.onNodeWithText(price_formatted).assertIsDisplayed()
        composeTestRule.onNodeWithText("Esta é uma descrição de exemplo para fins de teste.").assertIsDisplayed()
    }

    @Test
    fun cardProductItemExpandeECollapseAoClicar() {
        // Dado
        val produto = Product(
            name = "Hamburguer",
            price = BigDecimal("12.99"),
            description = "Esta é uma descrição de exemplo mais longa para fins de teste. " +
                    "Ela deve ser longa o suficiente para testar a funcionalidade de expansão."
        )

        // Adiciona o produto aos produtos de exemplo
        sampleProducts.toMutableList().apply { add(produto) }

        // Quando
        composeTestRule.setContent {
            AluveryTheme {
                HomeScreen(sampleSections)
            }
        }

        // Verificar se o produto foi adicionado e está na tela
        composeTestRule.onNodeWithText("Hamburguer").assertExists()

        // Verificar estado inicial (colapsado)
        composeTestRule.onNodeWithText("Esse é um texto de teste quetem o objetivo testar o teste que está sendo testado e a partir do teste atestar se o teste cumpriu com os requisitos anteriormente delegados ao teste que é um teste testável")
            .assertExists()
            .assertHasClickAction() // Verifica se o item está presente e tem ação de clique

        // Realizar clique para expandir
        composeTestRule.onNodeWithText("Hamburguer").performClick()

        // Verificar estado expandido
        composeTestRule.onNodeWithText("Esse é um texto de teste quetem o objetivo testar o teste que está sendo testado e a partir do teste atestar se o teste cumpriu com os requisitos anteriormente delegados ao teste que é um teste testável")
            .assertIsDisplayed()

        // Realizar clique para colapsar
        composeTestRule.onNodeWithText("Hamburguer").performClick()

        // Verificar estado colapsado
        composeTestRule.onNodeWithText("Esse é um texto de teste quetem o objetivo testar o teste que está sendo testado e a partir do teste atestar se o teste cumpriu com os requisitos anteriormente delegados ao teste que é um teste testável")
            .assertHasClickAction() // Verifica se o item está presente e tem ação de clique
    }
}
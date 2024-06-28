package br.com.alura.aluvery

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.screens.HomeScreen
import br.com.alura.aluvery.ui.theme.AluveryTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_exibeTodasAsSecoes_quandoNaoHaTextoDeBusca() {
        // Configurar o composable
        composeTestRule.setContent {
            AluveryTheme {
                HomeScreen(sampleSections)
            }
        }

        // Verificar se todas as seções são exibidas
        sampleSections.keys.forEach { tituloSecao ->
            composeTestRule.onNodeWithText(tituloSecao).assertIsDisplayed()
        }
    }

    @Test
    fun homeScreen_exibeResultadosFiltrados_quandoTextoDeBuscaEFornecido() {
        // Configurar o composable com um texto de busca
        val textoDeBusca = "Pizza"
        composeTestRule.setContent {
            AluveryTheme {
                HomeScreen(sampleSections, textoDeBusca)
            }
        }

        // Verificar se o campo de texto de busca contém o texto correto
        composeTestRule.onNodeWithTag("SearchTextField").assertTextContains(textoDeBusca)

        // Verificar se apenas os produtos que contêm o texto de busca são exibidos
        sampleProducts.filter {
            it.name.contains(textoDeBusca, true) ||
                    it.description?.contains(textoDeBusca, true) ?: false
        }.forEach { produto ->
            composeTestRule.onNodeWithTag("ProductItem_${produto.name}").assertIsDisplayed()
        }
    }


    @Test
    fun homeScreen_atualizaResultados_quandoTextoDeBuscaMuda() {
        // Configurar o composable
        composeTestRule.setContent {
            AluveryTheme {
                HomeScreen(sampleSections)
            }
        }

        // Inserir texto de busca
        val textoDeBusca = "Pizza"
        composeTestRule.onNodeWithTag("SearchTextField")
            .performTextInput(textoDeBusca)

        // Verificar se apenas os produtos que contêm o texto de busca são exibidos
        sampleProducts.filter {
            it.name.contains(textoDeBusca, true) ||
                    it.description?.contains(textoDeBusca, true) ?: false
        }.forEach { produto ->
            composeTestRule.onNodeWithText(produto.name).assertIsDisplayed()
        }
    }
}

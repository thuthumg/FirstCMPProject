package org.example.firstcmpproject

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.runComposeUiTest
import org.example.firstcmpproject.core.utils.CATEGORY_SECTION
import org.example.firstcmpproject.core.utils.EMAIL_OR_PHONE_TEXT_FIELD
import org.example.firstcmpproject.core.utils.FEATURE_MOVIE_IMAGE
import org.example.firstcmpproject.core.utils.GENRE_NAME
import org.example.firstcmpproject.core.utils.MOVIE_DETAIL_IMAGE
import org.example.firstcmpproject.core.utils.MOVIE_DETAIL_LAZY_COLUMN
import org.example.firstcmpproject.core.utils.MOVIE_NAME
import org.example.firstcmpproject.core.utils.SIGN_IN_BUTTON
import org.example.firstcmpproject.core.utils.SIMILAR_MOVIE_GRID
import kotlin.test.Test

class UITest {

//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun loginScreen_emailTextFieldExists() = runComposeUiTest {
//        setContent {
//            App()
//        }
//
//        onNodeWithTag(EMAIL_OR_PHONE_TEXT_FIELD).assertExists()
//    }
//
//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun fromLoginScreen_navigateToHomeScreen_categorySectionExists() = runComposeUiTest {
//        setContent {
//            App()
//        }
//
//        onNodeWithTag(SIGN_IN_BUTTON).performClick()
//        waitUntil (timeoutMillis = 10000){
//            onNodeWithTag(CATEGORY_SECTION).isDisplayed()
//        }
//
//        onNodeWithTag(CATEGORY_SECTION).assertExists()
//    }
//
//    @OptIn(ExperimentalTestApi::class)
//    @Test
//    fun fromLoginScreen_navigateToHomeScreen_genreNameExists() = runComposeUiTest {
//        setContent {
//            App()
//        }
//
//        onNodeWithTag(SIGN_IN_BUTTON).performClick()
//        waitUntil (timeoutMillis = 10000){
//            onNodeWithTag("${GENRE_NAME}-Action").isDisplayed()
//        }
//
//        onNodeWithTag("${GENRE_NAME}-Action").assertExists()
//    }


    @OptIn(ExperimentalTestApi::class)
    @Test
    fun fromLoginScreen_navigateToMovieDetailScreen() = runComposeUiTest {
        setContent {
            App()
        }

        onNodeWithTag(SIGN_IN_BUTTON).performClick()
        waitUntil (timeoutMillis = 50000){
            onNodeWithTag(FEATURE_MOVIE_IMAGE).isDisplayed()
        }
        onNodeWithTag(FEATURE_MOVIE_IMAGE).assertExists()


        onNodeWithTag(FEATURE_MOVIE_IMAGE).performClick()
        waitUntil (timeoutMillis = 50000){
            onNodeWithTag(MOVIE_DETAIL_IMAGE).isDisplayed()
            onNodeWithTag(MOVIE_NAME).isDisplayed()


        }
        onNodeWithTag(MOVIE_DETAIL_IMAGE).assertExists()
        onNodeWithTag(MOVIE_NAME).assertExists()


        // 1) Scroll to similar movie grid
        onNodeWithTag(MOVIE_DETAIL_LAZY_COLUMN)
            .performScrollToNode(hasTestTag(SIMILAR_MOVIE_GRID))

        onNodeWithTag(SIMILAR_MOVIE_GRID)
            .assertExists()
            .assertIsDisplayed()


        // 2) Ensure detail movie not inside similar list
        val detailId = 83533 // known in fake data
        onNodeWithTag("SIMILAR_MOVIE_ITEM_$detailId")
            .assertDoesNotExist()


    }
}
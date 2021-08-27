package rs.sloman.albums

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import rs.sloman.albums.ui.activities.MainActivity

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ComposeTesting {

    @get:Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkToolbarForAppNameExists() {
        composeAndroidTestRule
            .onNodeWithText(composeAndroidTestRule.activity.getString(R.string.app_name))
            .assertExists()
    }

    @Test
    fun checkToolbarForAppNameDisplayed() {
        composeAndroidTestRule
            .onNodeWithText(composeAndroidTestRule.activity.getString(R.string.app_name))
            .assertIsDisplayed()
    }

    @Test
    fun checkInitialAlbumsListDoesNotExist() {
        composeAndroidTestRule
            .onNodeWithContentDescription("List with Albums")
            .assertDoesNotExist()
    }

    @Test
    fun checkSnackBarSuccessfullyDisplayed() {

        val text = "quidem molestiae enim"

        composeAndroidTestRule
            .onNodeWithText(text)
            .performClick()

        composeAndroidTestRule.onRoot(useUnmergedTree = true).printToLog("ComposeTesting")
        composeAndroidTestRule
            .onNodeWithText("Name $text")
            .assertIsDisplayed()
    }

    @Test
    fun checkLoaderIsInitiallyDisplayed() {
        composeAndroidTestRule
            .onNodeWithContentDescription("CircularProgressIndicator")
            .assertIsDisplayed()
    }

}
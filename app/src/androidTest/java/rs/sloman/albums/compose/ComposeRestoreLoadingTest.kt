package rs.sloman.albums.compose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rs.sloman.albums.Constants
import rs.sloman.albums.R
import rs.sloman.albums.ui.activities.MainActivity

class ComposeRestoreLoadingTest {

    @get:Rule
    val composeAndroidTestRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun turnOffDataAndClearDatabase(){
        InstrumentationRegistry.getInstrumentation().uiAutomation.apply {
            executeShellCommand("svc wifi disable")
            executeShellCommand("svc data disable")
        }
        composeAndroidTestRule.activity.applicationContext.deleteDatabase(Constants.ALBUM_DB)

    }

    @Test
    fun checkRestoringInternetLoadingDisplayed(){

        composeAndroidTestRule
            .onNodeWithText(composeAndroidTestRule.activity.getString(R.string.retry))
            .assertIsDisplayed()

        InstrumentationRegistry.getInstrumentation().uiAutomation.apply {
            executeShellCommand("svc wifi enable")
            executeShellCommand("svc data enable")

            Thread.sleep(1000)

        composeAndroidTestRule
            .onNodeWithText(composeAndroidTestRule.activity.getString(R.string.retry))
            .performClick()
        }

        composeAndroidTestRule.onRoot(useUnmergedTree = true).printToLog("ComposeTesting")
        composeAndroidTestRule
            .onNodeWithContentDescription("CircularProgressIndicator")
            .assertExists()
    }
}
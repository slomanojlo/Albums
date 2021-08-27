package rs.sloman.albums

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import rs.sloman.albums.ui.activities.MainActivity

class ComposeErrorTest {

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
    fun checkErrorDisplayed() {
        composeAndroidTestRule
            .onNodeWithText(composeAndroidTestRule.activity.getString(R.string.retry))
            .assertIsDisplayed()

    }

}
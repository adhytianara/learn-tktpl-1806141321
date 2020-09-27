package id.ac.ui.cs.mobileprogramming.adhytia1806141321.helloworld;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void viewIsDisplayed() {
        onView(withId(R.id.tv_helloworld)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_submit)).check(matches(isDisplayed()));
        onView(withId(R.id.et_aboutme)).check(matches(isDisplayed()));

        onView(withId(R.id.et_aboutme)).perform(typeText("aboutme"), closeSoftKeyboard());
        onView(withId(R.id.btn_submit)).perform(click());

        onView(withId(R.id.tv_nama)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_npm)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_github)).check(matches(isDisplayed()));
        onView(withId(R.id.img_myFoto)).check(matches(isDisplayed()));
    }
}
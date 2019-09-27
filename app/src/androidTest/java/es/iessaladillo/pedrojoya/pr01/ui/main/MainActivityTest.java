package es.iessaladillo.pedrojoya.pr01.ui.main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import es.iessaladillo.pedrojoya.pr01.R;
import es.iessaladillo.pedrojoya.pr01.base.DrawableMatcher;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule
        = new ActivityTestRule<>(MainActivity.class);

    // EditTexts show errors when invalid

    @Test
    public void should_txtWeight_show_error_when_not_float() {
        onView(withId(R.id.txtWeight))
            .perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnCalculate)).perform(click());

        onView(withId(R.id.txtWeight))
            .check(matches(hasErrorText(activityRule.getActivity().getString(R.string.main_invalid_weight))));
    }

    @Test
    public void should_txtHeight_show_error_when_not_float() {
        onView(withId(R.id.txtWeight))
            .perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.txtHeight))
            .perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnCalculate)).perform(click());

        onView(withId(R.id.txtHeight))
            .check(matches(hasErrorText(activityRule.getActivity().getString(R.string.main_invalid_height))));
    }

    @Test
    public void should_txtWeight_show_error_when_0() {
        onView(withId(R.id.txtWeight))
            .perform(typeText("0"), closeSoftKeyboard());
        onView(withId(R.id.txtHeight))
            .perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.btnCalculate)).perform(click());

        onView(withId(R.id.txtWeight))
            .check(matches(hasErrorText(activityRule.getActivity().getString(R.string.main_invalid_weight))));
    }

    @Test
    public void should_txtHeight_show_error_when_0() {
        onView(withId(R.id.txtWeight))
            .perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.txtHeight))
            .perform(typeText("0"), closeSoftKeyboard());
        onView(withId(R.id.btnCalculate)).perform(click());

        onView(withId(R.id.txtHeight))
            .check(matches(hasErrorText(activityRule.getActivity().getString(R.string.main_invalid_height))));
    }

    // Reset button works.

    @Test
    public void should_btnReset_reset() {
        onView(withId(R.id.txtWeight))
            .perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.txtHeight))
            .perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.btnCalculate)).perform(click());
        onView(withId(R.id.btnReset)).perform(click());

        onView(withId(R.id.txtHeight))
            .check(matches(withText("")));
        onView(withId(R.id.txtWeight))
            .check(matches(withText("")));
        onView(withId(R.id.lblResult))
            .check(matches(withText("")));
        onView(withId(R.id.imgBmi))
            .check(matches(new DrawableMatcher(R.drawable.bmi)));
    }

    // Calculate button works

    @Test
    public void should_btnCalculate_show_bmi() {
        onView(withId(R.id.txtWeight))
            .perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.txtHeight))
            .perform(typeText("2"), closeSoftKeyboard());
        onView(withId(R.id.btnCalculate)).perform(click());

        onView(withId(R.id.lblResult))
            .check(matches(withText(activityRule.getActivity().getString(R.string.main_bmi, 25f,
                activityRule.getActivity().getString(R.string.main_overweight)))));
        onView(withId(R.id.imgBmi))
            .check(matches(new DrawableMatcher(R.drawable.overweight)));
    }

    // Focus flow

    @Test
    public void should_focus_flow_work() {

        // Initial focus.
        onView(withId(R.id.txtWeight))
            .check(matches(hasFocus()));

        onView(withId(R.id.txtWeight)).perform(click(), pressImeActionButton());
        onView(withId(R.id.txtHeight))
            .check(matches(hasFocus()));

    }

    // Correct initial state.

    @Test
    public void should_have_correct_initial_state() {
        onView(withId(R.id.txtWeight))
            .check(matches(withText("")));
        onView(withId(R.id.txtHeight))
            .check(matches(withText("")));
        onView(withId(R.id.imgBmi))
            .check(matches(new DrawableMatcher(R.drawable.bmi)));
    }

}
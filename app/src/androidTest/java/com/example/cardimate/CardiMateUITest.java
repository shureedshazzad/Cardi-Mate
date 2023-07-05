package com.example.cardimate;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CardiMateUITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void MainActivityTest() {
        //check imageview
        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
        //check textview
        onView(ViewMatchers.withId(R.id.textView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Check if the TextView has the correct text
        onView(ViewMatchers.withId(R.id.textView))
                .check(ViewAssertions.matches(withText("Cardi Mate")));

        //transition from activity to activity2
        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, MainActivity2.class);
            activity.startActivity(intent);
        });


        onView(ViewMatchers.withId(R.id.activity2Layout))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    @Test
    public void CreateRecordTest() {
        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, Interface.class);
            activity.startActivity(intent);
        });

        onView(withId(R.id.interfacelayout))
                .check(matches(isDisplayed()));



        onView(withId(R.id.record_create)).perform(click());

        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, MainActivity4.class);
            activity.startActivity(intent);
        });



        onView(withId(R.id.activity4Layout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.create_record))
                .check(matches(withText("Create Record")));


        onView(withId(R.id.sp)).perform(ViewActions.typeText("120"));
        Espresso.pressBack();
        onView(withId(R.id.dp)).perform(ViewActions.typeText("80"));
        Espresso.pressBack();
        onView(withId(R.id.hr)).perform(ViewActions.typeText("70"));
        Espresso.pressBack();
        onView(withId(R.id.t)).perform(ViewActions.typeText("09:35"));
        Espresso.pressBack();
        onView(withId(R.id.d)).perform(ViewActions.typeText("26/07/2023"));
        Espresso.pressBack();
        onView(withId(R.id.c)).perform(ViewActions.typeText("This is a comment."));
        Espresso.pressBack();
        onView(withId(R.id.btn_6)).perform(click());



    }



    @Test
    public void DeleteRecordTest()
    {

        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, Interface.class);
            activity.startActivity(intent);
        });

        onView(withId(R.id.interfacelayout))
                .check(matches(isDisplayed()));



        onView(withId(R.id.record_show)).perform(click());

        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, MainActivity5.class);
            activity.startActivity(intent);
        });



        onView(withId(R.id.activity5Layout))
                .check(matches(isDisplayed()));





        onView(withId(R.id.main_recycler_view)).perform(click());

        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, MainActivity6.class);
            activity.startActivity(intent);
        });

        onView(withId(R.id.activity6Layout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.delete)).perform(click());

    }


    @Test
    public void UpdateRecordTest()
    {

        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, Interface.class);
            activity.startActivity(intent);
        });
        onView(withId(R.id.interfacelayout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.record_show)).perform(click());

        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, MainActivity5.class);
            activity.startActivity(intent);
        });

        onView(withId(R.id.activity5Layout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.main_recycler_view)).perform(click());

        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, MainActivity6.class);
            activity.startActivity(intent);
        });

        onView(withId(R.id.activity6Layout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.edit)).perform(click());


        activityRule.getScenario().onActivity(activity -> {
            Intent intent = new Intent(activity, MainActivity4.class);
            activity.startActivity(intent);
        });



        onView(withId(R.id.activity4Layout))
                .check(matches(isDisplayed()));

        onView(withId(R.id.sp)).perform(ViewActions.typeText("120"));

        onView(withId(R.id.btn_6)).perform(click());




    }









}

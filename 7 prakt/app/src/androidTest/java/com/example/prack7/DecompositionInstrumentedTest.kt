package com.example.prack7

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click

import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId

import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class DecompositionInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.prack7", appContext.packageName)
    }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun decomposition12() {

        onView(withId(R.id.editTextNumber)).perform(typeText("12"))
        onView(withId(R.id.buttonAdd)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("2 * 2 * 3")))
    }
    @Test
    fun decomposition15(){
        onView(withId(R.id.editTextNumber)).perform(typeText("15"))
        onView(withId(R.id.buttonAdd)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("3 * 5")))
    }
    @Test
    fun decomposition2(){
        onView(withId(R.id.editTextNumber)).perform(typeText("2"))
        onView(withId(R.id.buttonAdd)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("2")))
    }
    @Test
    fun decomposition11(){
        onView(withId(R.id.editTextNumber)).perform(typeText("11"))
        onView(withId(R.id.buttonAdd)).perform(click())
        onView(withId(R.id.textViewResult)).check(matches(withText("11")))
    }
}
package com.example.amazingbirthdayidentifierreloaded

import com.example.amazingbirthdayidentifierreloaded.model.Type
import com.example.amazingbirthdayidentifierreloaded.viewmodel.BirthdayViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId

@OptIn(ExperimentalCoroutinesApi::class)
class BirthdayViewModelTest {

    // A dispatcher specifically created for testing coroutines.
    // It replaces Dispatchers.Main during tests.
    private val dispatcher = UnconfinedTestDispatcher()

    // The ViewModel we want to test.
    private lateinit var viewModel: BirthdayViewModel

    /**
     * Runs before each test.
     * We set Dispatchers.Main to our test dispatcher so coroutines
     * launched in the ViewModel behave predictably.
     */
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = BirthdayViewModel()
    }

    /**
     * Runs after each test.
     * Restores the real Main dispatcher and cleans up coroutine resources.
     */
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Verifies that when the user selects today's date,
     * the ViewModel updates its state and sets `isBirthDay = Type.TRUE`.
     */
    @Test
    fun `checkIsBirthday returns TRUE when the selected date matches today`() = runTest {
        // Arrange
        val today = System.currentTimeMillis()
//        val todayMillis = today
//            .atStartOfDay(ZoneId.systemDefault())
//            .toInstant()
//            .toEpochMilli()

        // Act
        viewModel.checkIsBirthday(today)

        // Assert
        assertEquals(Type.TRUE, viewModel.state.value.isBirthDay)
    }

    /**
     * Verifies that when the user selects a date that is NOT today,
     * the ViewModel sets `isBirthDay = Type.FALSE`.
     */
    @Test
    fun `checkIsBirthday returns FALSE when the selected date does NOT match today`() = runTest {
        // Arrange
        val differentDay = LocalDate.now().minusDays(5)
        val wrongDateMillis = differentDay
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        // Act
        viewModel.checkIsBirthday(wrongDateMillis)

        // Assert
        assertEquals(Type.FALSE, viewModel.state.value.isBirthDay)
    }
}

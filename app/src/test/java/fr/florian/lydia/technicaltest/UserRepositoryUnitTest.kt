package fr.florian.lydia.technicaltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.florian.lydia.technicaltest.data.models.Result
import fr.florian.lydia.technicaltest.data.models.User
import fr.florian.lydia.technicaltest.data.remote.routes.UserApi
import fr.florian.lydia.technicaltest.data.repositories.UserRepository
import io.mockk.coEvery
import io.mockk.mockkClass
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class UserRepositoryUnitTest {

    private val userApi = mockkClass(UserApi::class)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        // Sets the given [dispatcher] as an underlying dispatcher of [Dispatchers.Main].
        // All consecutive usages of [Dispatchers.Main] will use given [dispatcher] under the hood.
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // Resets state of the [Dispatchers.Main] to the original main dispatcher.
        // For example, in Android Main thread dispatcher will be set as [Dispatchers.Main].
        Dispatchers.resetMain()

        // Clean up the TestCoroutineDispatcher to make sure no other work is running.
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should return remote data`() = runBlocking {
        //Given
        coEvery {
            userApi.getUsersBatch(1, 10)
        } returns createExpectedRemoteData()

        // When
        val result = UserRepository().loadUsersBatch().results.take(2).toList()

        // Then
        assertEquals(createExpectedResultFirst().results[0], result[0])
        assertEquals(createExpectedResultSecond().results[0], result[1])
    }

    private fun createExpectedResultFirst() =
        Result<User>(
            listOf(
                User(
                    "female",
                    User.Name("mrs", "frances", "herrera"),
                    User.Location("2876 oak lawn ave", "tweed", "western australia", 1748),
                    "frances.herrera@example.com",
                    User.Login(
                        "bigwolf188",
                        "beefcake",
                        "63zM1zTz",
                        "e5e6c5606704f4655076c7a71ac77be7",
                        "701d3375874edd63fbd5e7b1446be7c2ba8dc6ee",
                        "c17a7f6874370cecebecff65aed8613fd78bbbe0f4a439e32cc1ecf389bb02ab"
                    ),
                    1192836162,
                    1030053735,
                    "02-2112-2147",
                    "0404-433-992",
                    User.Id("TFN", "417419587"),
                    User.Picture(
                        "https://randomuser.me/api/portraits/women/21.jpg",
                        "https://randomuser.me/api/portraits/med/women/21.jpg",
                        "https://randomuser.me/api/portraits/thumb/women/21.jpg"
                    ),
                    "AU"
                )
            )
        )

    private fun createExpectedResultSecond() =
        Result<User>(
            listOf(
                User(
                    "male",
                    User.Name("mr", "juliano", "da mota"),
                    User.Location("1502 avenida brasil ", "são josé dos campos", "piauí", 10016),
                    "juliano.damota@example.com",
                    User.Login(
                        "smallladybug751",
                        "ernesto",
                        "ApTWQ3mz",
                        "0d9587d22326e347d1d5ac7aa1554711",
                        "1e654e9605c2ef5a1dbea42c8491c6c7c779494a",
                        "b7d71d6b3d17da532bf41b9e6116b3a2d016976d225536aaa5b4b4a8add9d921"
                    ),
                    942181386,
                    1264975981,
                    "(86) 3557-7585",
                    "(17) 1143-4874",
                    User.Id("", null),
                    User.Picture(
                        "https://randomuser.me/api/portraits/men/96.jpg",
                        "https://randomuser.me/api/portraits/med/men/96.jpg",
                        "https://randomuser.me/api/portraits/thumb/men/96.jpg"
                    ),
                    "BR"
                )
            )
        )

    private fun createExpectedRemoteData() =
        Result<User>(
            listOf(
                User(
                    "m",
                    User.Name("title", "toto", "dupont"),
                    User.Location("123 Sesame Street", "San Francisco", "Benin", 93100),
                    "test@test.com",
                    User.Login(
                        "login",
                        "password",
                        "63zM1zTz",
                        "e5e6c5606704f4655076c7a71ac77be7",
                        "701d3375874edd63fbd5e7b1446be7c2ba8dc6ee",
                        "c17a7f6874370cecebecff65aed8613fd78bbbe0f4a439e32cc1ecf389bb02ab"
                    ),
                    83,
                    96,
                    "cell-phone",
                    "cell",
                    User.Id("name", "007"),
                    User.Picture(
                        "large",
                        "medium",
                        "thumbnail"
                    ),
                    "FR"
                )
            )
        )
}
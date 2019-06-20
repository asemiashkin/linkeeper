package services

import com.asemiashkin.Role
import com.asemiashkin.User
import com.asemiashkin.persistence.UsersRepository
import com.asemiashkin.services.UserService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import java.util.*
import kotlin.random.Random

class UserServiceTest {

    private val user = User(0, "John Doe", "email@email.com", Role.USER)

    @Test
    @Ignore("smth strange with NPE")
    fun testCreate() {
        val usersRepository = mock<UsersRepository> {
            on { create(any()) } doReturn Random.nextLong()
        }

        val userService = UserService(usersRepository)

        Assert.assertNotNull(userService.create(user))
    }

    @Test
    fun testUpdate() {
        val userRepository = mock<UsersRepository> {
            on { update(any()) } doReturn true
        }

        val userService = UserService(userRepository)

        Assert.assertTrue(userService.update(user))
    }

    @Test
    fun testRemove() {
        val userRepository = mock<UsersRepository> {
            on { remove(any()) } doReturn true
        }

        val userService = UserService(userRepository)

        Assert.assertTrue(userService.remove(0))
    }

    @Test
    fun testFetch() {
        var user1 = user
        val userRepository = mock<UsersRepository> {
            on { fetch(any()) } doReturn user1
        }

        val userService = UserService(userRepository)

        Assert.assertEquals(userService.fetch(0), user1)
    }

    @Test
    fun testFetchAll() {
        var user1 = user
        val singletonList = Collections.singletonList(user1)

        val userRepository = mock<UsersRepository> {
            on { fetchAll() } doReturn singletonList
        }

        val userService = UserService(userRepository)

        Assert.assertEquals(userService.fetchAll(), singletonList)
    }
}